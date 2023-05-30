package com.example.consulta.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Historial {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@ManyToOne
    @JoinColumn(name = "cliente_id")
	private Cliente cliente;
//	
	@OneToMany(cascade= CascadeType.ALL, mappedBy="historial")
	private List<Citas> Citas;


	public Historial(long id, Cliente cliente, List<com.example.consulta.entity.Citas> citas) {
		super();
		this.id = id;
		this.cliente = cliente;
		Citas = citas;
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

	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<Citas> getCitas() {
		return Citas;
	}
	public void setCitas(List<Citas> citas) {
		Citas = citas;
	}
	@Override
	public String toString() {
		return "Historial [id=" + id + ", cliente=" + cliente + ", Citas=" + Citas + "]";
	}

	
	
}
