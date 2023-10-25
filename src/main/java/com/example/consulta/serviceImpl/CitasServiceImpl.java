package com.example.consulta.serviceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
public class CitasServiceImpl implements CitasService {

	@Autowired
	@Qualifier("citasRepository")
	private CitasRepository citasRepository;
//	
//	@Autowired
//	@Qualifier("citasServicioImpl")
//	private CitasService citasService;

	public String sumarDia(String fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        LocalDateTime localDateTime = LocalDateTime.parse(fecha, formatter);
        
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneOffset.UTC);
        ZonedDateTime fechaSumada = zonedDateTime.plusDays(1);
        
        return fechaSumada.format(formatter);
    }
	
	@Override
	public Citas addCitas(CitasModel citas) {
		citas.setFechaCita(citas.getFechaCita());
		citas.setActiva(true);
		return citasRepository.save(transform(citas));
	}


	@Override
	public Citas findCitasById(long id) {
		return citasRepository.findById(id);
	}

	@Override
	public Citas findByFechaCitas(String fecha) {
		return citasRepository.findByFechaCita(fecha);

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
	public Citas transform(CitasModel citasModel) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(citasModel, Citas.class);
	}

	@Override
	public CitasModel transform(Citas citas) {
		ModelMapper modelMapper = new ModelMapper();
		CitasModel citalModel=modelMapper.map(citas, CitasModel.class);
		citalModel.setFechaCita(citas.getFechaCita());
		return citalModel;
	}
	

	@Override
	public List<CitasModel> listAllCitass() {
		return citasRepository.findAll().stream().map(c -> transform(c)).collect(Collectors.toList());

	}

	

	@Override
	public List<CitasModel> listCitasCliente(long idCliente) {
		List<CitasModel> listaClitassCliente=listAllCitass();
		List<CitasModel> clienteCita = new ArrayList<>();
		for(CitasModel cita: listaClitassCliente) {
			if(cita.getCliente().getId()==idCliente) {
				clienteCita.add(cita);
			}
		}
		
		return clienteCita;
	}

	@Override
	public List<CitasModel> listaCitaHoy() {
		
		List<CitasModel> todasCitas=listAllCitass();
		int diaActual = LocalDate.now().getDayOfMonth();
		List<CitasModel> citasDelDia = todasCitas.stream()
	               .filter(cita -> {
	                   String fechaCita = cita.getFechaCita();
	                   int diaCita = Integer.parseInt(fechaCita.split("&")[2]); // Extraer el día de la cadena de fecha
	                   return diaCita == diaActual;
	               })
	               .collect(Collectors.toList());
	
		return citasDelDia;
        
		
	}

	@Override
	public CitasModel findFechaCompleta(String fechaCompleta) {
		List<CitasModel> allCitas=listAllCitass();
		for(CitasModel a: allCitas) {
			if(a.getFechaCompleta().equalsIgnoreCase(fechaCompleta)) {
				return a;
			}
		}
		return null;
	}

}
