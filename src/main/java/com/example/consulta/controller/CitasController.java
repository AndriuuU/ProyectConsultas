package com.example.consulta.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.consulta.constantes.Constantes;
import com.example.consulta.entity.Citas;
import com.example.consulta.entity.Servicio;
import com.example.consulta.model.CitasModel;
import com.example.consulta.model.ServicioModel;
import com.example.consulta.repository.CitasRepository;
import com.example.consulta.repository.ClienteRepository;
import com.example.consulta.service.CitasService;
import com.example.consulta.service.ClienteService;
import com.example.consulta.service.ServicioService;

@Controller
@RequestMapping("/cita")
public class CitasController {

	@Autowired
	@Qualifier("clienteRepository")
	private ClienteRepository clienteRepository;

	@Autowired
	@Qualifier("citasRepository")
	private CitasRepository citasRepository;

	@Autowired
	@Qualifier("clienteService")
	private ClienteService clienteService;

	@Autowired
	@Qualifier("servicioService")
	private ServicioService servicioService;

	@Autowired
	@Qualifier("citasService")
	private CitasService citasService;

	@GetMapping("/usuario/miscitas")
	public ModelAndView registerForm(Model model) {
		ModelAndView mav = new ModelAndView(Constantes.OBTENER_MI_CITA);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = authentication.getName();
		if (!userEmail.equalsIgnoreCase("anonymousUser")) {
			List<Citas> listci = clienteService.findByEmail(userEmail).getCitas();
			List<Citas> listaOrdenada = listci.stream().sorted(Comparator.comparing(Citas::getFechaCompleta))
					.collect(Collectors.toList());

			mav.addObject("citas", listaOrdenada);
		}

		return mav;
	}

	@GetMapping("/usuario/get/cita/{date}/{fecha}")
	public String registrarCita(@PathVariable("date") String fecha, @PathVariable("fecha") String fecha2,
			RedirectAttributes flash) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = authentication.getName();
		long idCliente = clienteService.findByEmail(userEmail).getId();
		boolean userLoing = !userEmail.equalsIgnoreCase("anonymousUser");
		System.out.println(fecha);
		String[] elementos = fecha.split("&");
		String hora = elementos[0].replace("am", "");
		hora = hora.replace("pm", "");
//		String dia=elementos[2];
		//2023-11-10T23:00:00.000Z
		System.out.println(fecha2);
		String[] fechacomple=fecha2.split("T");
		
		System.out.println("fechacomple:  "+fechacomple[1]);
		String fechaEntera=fechacomple[0]+"T"+hora+":00.000Z";
				//fecha2.replace("T23:00", "T" + hora);
//		fechacomple = fecha2.replace("T22:00", "T" + hora);
		String sumado = citasService.sumarDia(fechaEntera);
		System.out.println(hora);
		System.out.println("Esta es la fecha buena: " + sumado);

		if (userLoing && citasService.findFechaCompleta(sumado) == null
				&& citasService.findByFechaCitas(fecha) == null) {
			CitasModel cita = new CitasModel();
			cita.setFechaCita(fecha);
			cita.setCliente(clienteService.findCliente(idCliente));
			Servicio servi = servicioService.findServicioByNombre(elementos[5]);
			cita.setServicio(servi);
			cita.setFechaCompleta(sumado);
			citasService.addCitas(cita);
			flash.addFlashAttribute("success", "Cita obtenida");
			return "redirect:/cita/usuario/miscitas";
		} else {
			flash.addFlashAttribute("error", "No se pudo coger la cita");
			return "redirect:/cita/usuario/miscitas?error";
		}
	}

	@GetMapping("/usuario/obtener/cita")
	public ModelAndView registerCita(Model model) {
		ModelAndView mav = new ModelAndView(Constantes.OBTENER_CITA);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = authentication.getName();
		boolean userLoing = !userEmail.equalsIgnoreCase("anonymousUser");
		List<CitasModel> listci = citasService.listAllCitass();
		List<ServicioModel> listServi = servicioService.listAllServicios();

		if (userLoing && listci != null) {
			mav.addObject("citas", listci);
			mav.addObject("servicios", listServi);
		}
		return mav;
	}

	// Metodo de borrar
	@GetMapping("/usuario/delete/{id}")
	public String deleteUser(@PathVariable("id") int id, RedirectAttributes flash) throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = authentication.getName();

//		if (!userEmail.equalsIgnoreCase("anonymousUser")) {
//			List<Citas> listci = clienteService.findByEmail(userEmail).getCitas();
//			for (Citas cita : listci) {
//				if (cita.getId() == id) {
//					
		if (citasService.removeCitas(id)) {
			citasService.removeCitas(id);
			flash.addFlashAttribute("success", "Cita eliminada con éxito");

			if (userEmail.equalsIgnoreCase("ADMIN")) {
				return "redirect:/cita/admin/listCitas";
			} else
				return "redirect:/home";
		}
//				}
//			}
//			System.out.println(id);
//			
//			
//		} else
		flash.addFlashAttribute("error", "No se pudo eliminar la cita");
		if (userEmail.equalsIgnoreCase("ADMIN")) {
			return "redirect:/cita/admin/listCitas";
		} else
			return "redirect:/home?error";
		
	}

	@GetMapping("/admin/listCitas")
	public ModelAndView listClientes(@RequestParam(name = "error", required = false) String error) {
		ModelAndView mav = new ModelAndView(Constantes.CRUD_CITAS_VIEW);
		List<CitasModel> listCitas = citasService.listAllCitass();
		List<CitasModel> listaOrdenada = listCitas.stream().sorted(Comparator.comparing(CitasModel::getFechaCompleta))
				.collect(Collectors.toList());

		mav.addObject("citas", listaOrdenada);
		mav.addObject("error", error);
		return mav;
	}

}
