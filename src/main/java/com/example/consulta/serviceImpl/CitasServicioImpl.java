package com.example.consulta.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.consulta.entity.Citas;
import com.example.consulta.model.CitasModel;
import com.example.consulta.repository.CitasRepository;
import com.example.consulta.service.CitasService;

@Service("citasService")
public class CitasServicioImpl implements CitasService {

	@Autowired
	@Qualifier("citasRepository")
	private CitasRepository citasRepository;
//	
//	@Autowired
//	@Qualifier("citasServicioImpl")
//	private CitasService citasService;

	
	@Override
	public Citas addCitas(CitasModel citas) {
		citas.setFecha(citas.getFecha());
		citas.setActiva(true);
		return citasRepository.save(transform(citas));
	}


	@Override
	public Citas findCitasById(long id) {
		return citasRepository.findById(id);
	}

	@Override
	public CitasModel findCitasByDate(String fecha) {
		return transform(citasRepository.findByfecha(fecha));

	}

	@Override
	public CitasModel updateCitas(CitasModel citasModel) {
		citasRepository.save(transform(citasModel));
		return citasModel;	}

	@Override
	public boolean removeCitas(long id) {
		if(citasRepository.findById(id)!=null) {
			citasRepository.deleteById(id);
			return true;
		}
			return false;
	}

	@Override
	public Citas transform(CitasModel CitasModel) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(CitasModel, Citas.class);
	}

	@Override
	public CitasModel transform(Citas Citas) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(Citas, CitasModel.class);
	}

	@Override
	public List<CitasModel> listAllCitass() {
		return citasRepository.findAll().stream().map(c -> transform(c)).collect(Collectors.toList());

	}

}
