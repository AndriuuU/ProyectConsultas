package com.example.consulta.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
public class Cliente {

	@Id	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",unique=true,nullable=false)
	private User usuario;
	@Column(name = "nombre", nullable = false)
	private String nombre;
	@Column(name = "email", unique= true, nullable = false)
	private String email;
	private boolean seguro;
	private String direccion;
	private String telefono;

	@OneToMany(cascade= CascadeType.ALL, mappedBy="cliente")
	private List<Citas> citas;


	@ManyToOne
	@JoinColumn(name="clienteId")
	private Historial historial;

	public Cliente() {
		super();
	}

	public Cliente(User usuario, String nombre, String email, boolean seguro, String direccion, String telefono,
			List<Citas> citas, Historial historial) {
		super();
		this.usuario = usuario;
		this.nombre = nombre;
		this.email = email;
		this.seguro = seguro;
		this.direccion = direccion;
		this.telefono = telefono;
		this.citas = citas;
		this.historial = historial;
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


	@Override
	public String toString() {
		return "Cliente nombre=" + nombre + ", email=" + email + ", seguro=" + seguro
				+ ", direccion=" + direccion + ", telefono=" + telefono + ", citas=" + citas + ", usuario=" + usuario
				+ ", historial=" + historial + "]";
	}


	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}



}