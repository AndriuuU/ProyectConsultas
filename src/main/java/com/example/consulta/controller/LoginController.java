package com.example.consulta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.consulta.constantes.Constantes;
import com.example.consulta.entity.Cliente;
import com.example.consulta.entity.User;
import com.example.consulta.repository.UserRepository;
import com.example.consulta.service.ClienteService;
import com.example.consulta.serviceImpl.UserService;


@Controller
@RequestMapping("/")
public class LoginController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;
	
	@Autowired
	@Qualifier("clienteService")
	private ClienteService clienteService;
	
	@GetMapping("/")
	public String home(Model model) {
		return Constantes.HOME_VIEW;
	}
	
	@GetMapping("/home")
	public String inicio(Model model) {
		return Constantes.HOME_VIEW;
	}
	
	@GetMapping("/nosotros")
	public String nosotros(Model model) {
		return Constantes.NOSOTROS_VIEW;
	}
	
	@GetMapping("/auth/login")
	public String login (Model model,@RequestParam(name="error", required=false) String error,
			@RequestParam(name="logout", required=false)String logout) {
		
		model.addAttribute("user",new User());
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		return Constantes.LOGIN_VIEW;
	}
	
	@GetMapping("/auth/registerForm")
	public String registerForm(Model model,@RequestParam(name="error",required=false)String error) {
		model.addAttribute("cliente", new Cliente());
		model.addAttribute("error",error);
		return Constantes.REGISTER_VIEW;
	}
	
	@PostMapping("/auth/register")
	public String register(@ModelAttribute Cliente cliente,RedirectAttributes flash) {
		  if (!isValidEmail(cliente.getEmail())) {
	            flash.addAttribute("error","Correo electrónico no válido");
	            return "redirect:/auth/registerForm?error";
	        }else
		  
		if(userRepository.findByUsername(cliente.getEmail())==null) {
			clienteService.addCliente(clienteService.transform(cliente));
			
//			User user = new User();
//			user.setUsername(cliente.getEmail());
//			user.setPassword(cliente.getPassword());
//			user.setRole("ROLE_USER");
//			userService.registrar(user);
			
			flash.addAttribute("success","Registrado correctamente");
			return Constantes.LOGIN_VIEW;
		}else {
			flash.addAttribute("error","Correo ya existe");
			return "redirect:/auth/registerForm?error";
		}
		
	}
	private boolean isValidEmail(String email) {

        return email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");
    }
	@PostMapping("/tryloginn")
	public String login(@RequestParam("username") String username,
	        @RequestParam("password") String password) {
	    System.out.println("/login");
	    try {
	        Authentication authentication = authenticationManager
	                .authenticate(new UsernamePasswordAuthenticationToken(username, password));
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        System.out.println("usuario" + authentication);
	        return Constantes.LOGIN_VIEW;
	    } catch (Exception e) {
	        e.printStackTrace();
	        
	        return "error"; 
	    }
	}

}
