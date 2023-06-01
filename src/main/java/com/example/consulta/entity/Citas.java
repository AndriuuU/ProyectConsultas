package com.example.consulta.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Citas {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String fecha;
	
	@ManyToOne
	@JoinColumn(name="ClienteId")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="citas")
	private Historial historial;
	
	
	@ManyToOne
	@JoinColumn(name="servicio")
	private Servicio servicio;
	
	
	private boolean activa;


	public Citas() {
		super();
	}

	public Citas(long id, String fecha, Cliente cliente, Historial historial, Servicio servicio, boolean activa) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.cliente = cliente;
		this.historial = historial;
		this.servicio = servicio;
		this.activa = activa;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Historial getHistorial() {
		return historial;
	}

	public void setHistorial(Historial historial) {
		this.historial = historial;
	}

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Servicio getServicio() {
		return servicio;
	}


	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}


	public boolean isActiva() {
		return activa;
	}


	public void setActiva(boolean activa) {
		this.activa = activa;
	}


	@Override
	public String toString() {
		return "Citas [id=" + id + ", fecha=" + fecha + ", servicio=" + servicio + ", activa="
				+ activa + "]";
	}
	
	
	
}
