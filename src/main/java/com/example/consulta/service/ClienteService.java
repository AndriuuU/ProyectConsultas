package com.example.consulta.service;

import java.util.List;

import com.example.consulta.entity.Cliente;
import com.example.consulta.model.ClienteModel;

public interface ClienteService {

	public abstract List<ClienteModel> listAllAlumnos();
	
	public abstract Cliente findByEmail(String email);
	
	public abstract Cliente addAlumno(ClienteModel alumnosModel);

	public abstract int removeAlumno(int id);
	
	public abstract Cliente updateAlumno(ClienteModel alumnosModel);
	
	public abstract Cliente transform(ClienteModel alumnosModel);
	
	public abstract ClienteModel transform(Cliente alumnos);
	
	public abstract ClienteModel findAlumno(int id);
	
	public abstract ClienteModel findAlumno(String email);
}
