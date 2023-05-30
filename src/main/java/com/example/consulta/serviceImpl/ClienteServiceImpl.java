package com.example.consulta.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.consulta.entity.Cliente;
import com.example.consulta.entity.User;
import com.example.consulta.model.ClienteModel;
import com.example.consulta.model.UserModel;
import com.example.consulta.repository.ClienteRepository;
import com.example.consulta.repository.UserRepository;
import com.example.consulta.service.ClienteService;

@Service("clienteService")
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	@Qualifier("clienteRepository")
	private ClienteRepository clienteRepository;
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;
	
	@Override
	public List<ClienteModel> listAllClientes() {
		return clienteRepository.findAll().stream()
				.map(c->transform(c)).collect(Collectors.toList());
	}

	@Override
	public Cliente findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente addCliente(ClienteModel clienteModel) {
		clienteModel.setPassword(userService.passwordEncoder().encode(clienteModel.getPassword()));
		if(userService.registrar(new User(clienteModel.getIdCliente(),clienteModel.getEmail(), clienteModel.getPassword(),true, "ROLE_USER", null))!=null) {
			userService.registrar(new User(clienteModel.getIdCliente(),clienteModel.getEmail(), clienteModel.getPassword(),true, "ROLE_USER", null));
			return clienteRepository.save(transform(clienteModel));
		}
		return null;
	}

	@Override
	public int removeCliente(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Cliente updateCliente(ClienteModel ClientesModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente transform(ClienteModel clientesModel) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(clientesModel, Cliente.class);
	}

	@Override
	public ClienteModel transform(Cliente clientes) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(clientes, ClienteModel.class);
	}

	@Override
	public ClienteModel findCliente(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClienteModel findCliente(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
