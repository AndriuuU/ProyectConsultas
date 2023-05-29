package com.example.consulta.model;

import java.util.List;

import com.example.consulta.entity.Citas;
import com.example.consulta.entity.Historial;
import com.example.consulta.entity.User;

public class ClienteModel {

	private long idCliente;
	private String nombre;
	private String email;
	private boolean seguro;
	private String direccion;
	private String telefono;
	private String password;
	private List<Citas> citas;
	private User usuario;
	private Historial historial;

	
	public ClienteModel() {
		super();
	}
	

	public ClienteModel(long idCliente, String nombre, String email, boolean seguro, String direccion, String telefono,
			String password, List<Citas> citas, User usuario, Historial historial) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.email = email;
		this.seguro = seguro;
		this.direccion = direccion;
		this.telefono = telefono;
		this.password = password;
		this.citas = citas;
		this.usuario = usuario;
		this.historial = historial;
	}


	public long getIdCliente() {
		return idCliente;
	}


	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public boolean isSeguro() {
		return seguro;
	}


	public void setSeguro(boolean seguro) {
		this.seguro = seguro;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public List<Citas> getCitas() {
		return citas;
	}


	public void setCitas(List<Citas> citas) {
		this.citas = citas;
	}


	public User getUsuario() {
		return usuario;
	}


	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}


	public Historial getHistorial() {
		return historial;
	}


	public void setHistorial(Historial historial) {
		this.historial = historial;
	}
	

	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nombre=" + nombre + ", email=" + email + ", seguro=" + seguro
				+ ", direccion=" + direccion + ", telefono=" + telefono + ", citas=" + citas + ", usuario=" + usuario
				+ ", historial=" + historial + "]";
	}




}
