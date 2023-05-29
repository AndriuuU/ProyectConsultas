package com.example.consulta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.consulta.constantes.Constantes;
import com.example.consulta.model.ClienteModel;
import com.example.consulta.repository.UserRepository;
import com.example.consulta.service.ClienteService;
import com.example.consulta.serviceImpl.UserService;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;
	
	@Autowired
	@Qualifier("clienteService")
	private ClienteService clienteService;
	
	@GetMapping("/home")
	public RedirectView redirect() {
		return new RedirectView("/cliente/listclientes");
	}
	
	@GetMapping("/listClientes")
	public ModelAndView listClientes() {
		ModelAndView mav =new ModelAndView(Constantes.CRUD_CLIENTE_VIEW);
		mav.addObject("clientes", clienteService.listAllClientes());
		return mav;
	}
	@GetMapping("/listUsers")
	public ModelAndView listUsers() {
		ModelAndView mav =new ModelAndView(Constantes.CRUD_USER_VIEW);
		mav.addObject("users", userService.listAllUsuarios());
		return mav;
	}
	
//	@PostMapping("/addCliente")
//	public String addCliente(@Valid @ModelAttribute("Cliente") ClienteModel clienteModel, BindingResult bindingResult,
//			RedirectAttributes flash, Model model) {
//		if (bindingResult.hasErrors()) {
////			model.addAttribute("curso", cursosService.listAllCursos());
//			return ClienteS_VIEW;
//		} else {
//			ClienteService.updateCliente(clienteModel);
//			flash.addFlashAttribute("success", "Cliente actualizado satisfactoriamente");
//			return ClienteS_VIEW;
//		}
//	}
	
	
}
