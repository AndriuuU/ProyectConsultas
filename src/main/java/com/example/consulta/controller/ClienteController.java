package com.example.consulta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.consulta.constantes.Constantes;
import com.example.consulta.repository.UserRepository;
import com.example.consulta.service.ClienteService;
import com.example.consulta.serviceImpl.UserService;

@Controller
@RequestMapping("/clientes")
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
	
	@GetMapping("/listCLiente")
	public ModelAndView listClientes() {
		ModelAndView mav =new ModelAndView(Constantes.CLIENTE_VIEW);
		mav.addObject("clientes", clienteService.listAllAlumnos());
		return mav;
	}
	
	
}
