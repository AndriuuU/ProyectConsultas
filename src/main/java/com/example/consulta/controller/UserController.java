package com.example.consulta.controller;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.consulta.entity.Cliente;
import com.example.consulta.entity.User;
import com.example.consulta.model.ClienteModel;
import com.example.consulta.service.ClienteService;
import com.example.consulta.serviceImpl.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@Autowired
	@Qualifier("clienteService")
	private ClienteService clienteService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public com.example.consulta.entity.User login(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		com.example.consulta.entity.User usuario = userService.findUsuario(username);
		String token = getJWTToken(username);
		usuario.setUsername(username);
		usuario.setPassword(password);
		usuario.setToken(token);
		return usuario;
	}

	@PostMapping("/register")
	public ResponseEntity<?> saveUser(@RequestBody com.example.consulta.entity.User user) {
		boolean exist = userService.findUsuario(user.getUsername()) != null;
		if (exist) {
			return ResponseEntity.internalServerError().body(null);
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(userService.registrar(user));
		}
	}

	private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList(userService.findUsuario(username).getRole());
		System.out.println(userService.findUsuario(username).getRole());
		String token = Jwts.builder().setId("softtekJWT").setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();
		return "Bearer " + token;
	}

	// Usuario
	
	//Mostrar usuario
	@GetMapping("/view/user/{id}")
	public ResponseEntity<?> viewUser(@PathVariable String id) {

			return ResponseEntity.ok(userService.loadUserByUsername(id));
		
	}
	
	// Activar usuario
	@PostMapping("/activar/user/{id}")
	public ResponseEntity<?> enabelUser(@PathVariable long id) {
		int enable = userService.activar(userService.findUsuario(id).getUsername());
		com.example.consulta.entity.User user=userService.findUsuario(id);
		if (user!=null)
			return ResponseEntity.ok(user);
		else
			return ResponseEntity.noContent().build();

	}
	
	// Modificar Usuario
	//Actualiza una categoría si existe
	//Buscar Otra manera
	@PostMapping("/update/user/")
	public ResponseEntity<?> updateUser(@RequestBody com.example.consulta.entity.User user) {
		boolean exist = userService.findUsuario(user.getId())!=null;
		if (!exist) {
			return ResponseEntity.internalServerError().body(null);
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(userService.updateUser(user));
		}
	}

	// Cientes

	// Crea un nuevo cliente
	@PostMapping("/register/cliente")
	public ResponseEntity<?> createCliente(@RequestBody ClienteModel cliente) {
		boolean exist = clienteService.findByEmail(cliente.getEmail()) != null;
		if (exist) {
			return ResponseEntity.internalServerError().body("El usuario ya exliste");
		} else
			return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.addCliente(cliente));
	}

	// Obtener todos los clientes
	@GetMapping("/all/cliente")
	public ResponseEntity<?> getAllClientes() {
		boolean exist = clienteService.listAllClientes() != null;
		if (exist) {
			List<ClienteModel> clientes = clienteService.listAllClientes();
			return ResponseEntity.ok(clientes);
		} else
			return ResponseEntity.noContent().build();
	}

	// Eliminar cliente
	@DeleteMapping("/delete/cliente/{id}")
	public ResponseEntity<?> deleteUserCliente(@PathVariable long id) throws Exception {
		Cliente c=clienteService.findCliente(id);
		boolean deleted = clienteService.removeCliente(id);
		if (deleted)
			return ResponseEntity.ok(c);
		else
			return ResponseEntity.noContent().build();

	}
}
