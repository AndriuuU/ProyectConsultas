package com.example.consulta.entity;

import java.util.Date;
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
	
	@Column(name = "fecha", unique = true, nullable = false)
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name="ClienteId")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="historialCitas")
	private Historial historial;
	
	
	@ManyToMany(cascade= CascadeType.ALL, mappedBy="citas")
	private List<Servicio> servicio;
	
	
	private boolean activa;


	public Citas() {
		super();
	}


	public Citas(long id, Date fecha, Cliente cliente, List<Servicio> servicio, boolean activa) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.cliente = cliente;
		this.servicio = servicio;
		this.activa = activa;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public List<Servicio> getServicio() {
		return servicio;
	}


	public void setServicio(List<Servicio> servicio) {
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
		return "Citas [id=" + id + ", fecha=" + fecha + ", cliente=" + cliente + ", servicio=" + servicio + ", activa="
				+ activa + "]";
	}
	
	
	
}
