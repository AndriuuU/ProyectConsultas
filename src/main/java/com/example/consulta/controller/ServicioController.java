package com.example.consulta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.consulta.constantes.Constantes;
import com.example.consulta.entity.Cliente;
import com.example.consulta.entity.Servicio;
import com.example.consulta.repository.CitasRepository;
import com.example.consulta.repository.ClienteRepository;
import com.example.consulta.repository.ServicioRepository;
import com.example.consulta.service.CitasService;
import com.example.consulta.service.ClienteService;
import com.example.consulta.service.ServicioService;

@Controller
@RequestMapping("/servicio")
public class ServicioController {

	@Autowired
	@Qualifier("servicioRepository")
	private ServicioRepository servicioRepository;

	@Autowired
	@Qualifier("servicioService")
	private ServicioService servicioService;

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
	@Qualifier("citasService")
	private CitasService citasService;

	@GetMapping("/admin/listServicios")
	public ModelAndView listServicios(@RequestParam(name = "error", required = false) String error) {
		ModelAndView mav = new ModelAndView(Constantes.CRUD_SERVICIO_VIEW);
		mav.addObject("servicios", servicioService.listAllServicios());
		mav.addObject("error", error);
		return mav;
	}

	@GetMapping("/admin/delete/{id}")
	public String deleteServicio(@PathVariable("id") int id, RedirectAttributes flash) throws Exception {

		if (servicioService.removeServicio(id)) {
			flash.addFlashAttribute("success", "Servicio eliminado con éxito");
			return "redirect:/servicio/admin/listServicios?success";
		} else {
			flash.addFlashAttribute("error", "No se pudo eliminar el servicio");
			return "redirect:/servicio/admin/listServicios?error";
		}
	}

	@GetMapping(value = { "/admin/insertService", "/admin/insertService/{id}" })
	public String registerForm(Model model, @RequestParam(name = "error", required = false) String error,
			@PathVariable(name = "id", required = false) Integer id) {
		if (id == null) {
			model.addAttribute("servicio", new Servicio());
	
		} else {
			model.addAttribute("servicio", servicioService.findServicioById(id));
		}
		return Constantes.INSERT_SERVICIO;
	}

	@PostMapping("/admin/insert/servicio")
	public String register(@ModelAttribute Servicio servicio, RedirectAttributes flash) {
		if (servicio.getId() != 0) {
			servicioService.updateServicio(servicioService.transform(servicio));
			flash.addFlashAttribute("success", "Servicio actualizado correctamente");
			return "redirect:/servicio/admin/listServicios";
		} else {
			if (servicioRepository.findByNombre(servicio.getNombre()) == null) {
				servicioService.addServicio(servicioService.transform(servicio));
				flash.addFlashAttribute("success", "Servicio registrado correctamente");
				return "redirect:/servicio/admin/listServicios";
			} else {
				flash.addFlashAttribute("error", "No se pudo registrar/actualizar el servicio");
				return "redirect:/servicio/admin/listServicios?error";
			}
		}

	}
}
