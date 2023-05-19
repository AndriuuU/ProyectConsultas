package com.example.consulta.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Historial {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@OneToMany(cascade= CascadeType.ALL, mappedBy="historial")
	private List<Cliente> cliente;
//	
	@OneToMany(cascade= CascadeType.ALL, mappedBy="historial")
	private List<Citas> Citas;

	public Historial(long id, List<Cliente> cliente) {
		super();
		this.id = id;
		this.cliente = cliente;
	}
	public Historial() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<Cliente> getCliente() {
		return cliente;
	}
	public void setCliente(List<Cliente> cliente) {
		this.cliente = cliente;
	}
	@Override
	public String toString() {
		return "Historial [id=" + id + ", cliente=" + cliente + "]";
	}
	
	
	
}
