package com.example.consulta.service;

import java.util.List;

import com.example.consulta.entity.Citas;
import com.example.consulta.model.CitasModel;

public interface CitasService {

	public abstract CitasModel addCitas(CitasModel CitasModel);
	public abstract Citas findCitasById(long id);
	public abstract CitasModel findCitasByIdModel(long id);
	public abstract CitasModel updateCitas(CitasModel CitasModel);
	public abstract boolean removeCitas(long id);
	public abstract Citas transform(CitasModel CitasModel);
	public abstract CitasModel transform(Citas Citas);
	public abstract List<CitasModel> listAllCitass();
}