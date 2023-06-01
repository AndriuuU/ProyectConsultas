package com.example.consulta.model;

import java.util.Date;
import java.util.List;

import com.example.consulta.entity.Servicio;

public class TratamientoModel {

	private long id;
	private String nombre;
	private float precio;
	private String cadaCuanto;
	private Date hastaCuando;
	private Servicio servicio;
	
	public TratamientoModel() {
		super();
	}

	
	public TratamientoModel(long id, String nombre, float precio, String cadaCuanto, Date hastaCuando,
			Servicio servicio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.cadaCuanto = cadaCuanto;
		this.hastaCuando = hastaCuando;
		this.servicio = servicio;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getCadaCuanto() {
		return cadaCuanto;
	}

	public void setCadaCuanto(String cadaCuanto) {
		this.cadaCuanto = cadaCuanto;
	}

	public Date getHastaCuando() {
		return hastaCuando;
	}

	public void setHastaCuando(Date hastaCuando) {
		this.hastaCuando = hastaCuando;
	}

	public Servicio getServicio() {
		return servicio;
	}


	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}


	@Override
	public String toString() {
		return "Tratamiento [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", cadaCuanto=" + cadaCuanto
				+ ", hastaCuando=" + hastaCuando + "]";
	}
	
	
}
