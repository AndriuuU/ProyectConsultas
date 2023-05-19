package com.example.consulta.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.consulta.entity.Cliente;
import com.example.consulta.model.ClienteModel;
import com.example.consulta.repository.ClienteRepository;
import com.example.consulta.service.ClienteService;

@Service("clienteService")
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	@Qualifier("clienteRepository")
	private ClienteRepository clienteRepository;
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Override
	public List<ClienteModel> listAllAlumnos() {
		return clienteRepository.findAll().stream()
				.map(c->transform(c)).collect(Collectors.toList());
	}

	@Override
	public Cliente findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente addAlumno(ClienteModel clienteModel) {
		clienteModel.setPassword(userService.passwordEncoder().encode(clienteModel.getPassword()));
		return clienteRepository.save(transform(clienteModel));
	}

	@Override
	public int removeAlumno(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Cliente updateAlumno(ClienteModel alumnosModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente transform(ClienteModel alumnosModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClienteModel transform(Cliente alumnos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClienteModel findAlumno(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClienteModel findAlumno(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
