package com.example.consulta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.consulta.model.CitasModel;
import com.example.consulta.repository.CitasRepository;
import com.example.consulta.service.CitasService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class PDFController {
	@Autowired
	@Qualifier("citasRepository")
	private CitasRepository citasRepository;
	
	@Autowired
	@Qualifier("citasService")
	private CitasService citasService;
	
	@GetMapping("/admin/generar-pdf")
	public void generarPdf(HttpServletResponse response) {
	    try {
	        // Configurar el título y otras información de la clínica
	        String fecha = java.time.LocalDate.now().toString();
	        String titulo = "Clínica dermatológica".toUpperCase();
	        List<CitasModel> citas = citasService.listaCitaHoy();

	        // Configurar la respuesta HTTP para un archivo PDF
	        response.setContentType("application/pdf");
	        response.setHeader("Content-Disposition", "attachment; filename=ClinicaDermatologia.pdf");

	        // Crear un documento PDF
	        Document document = new Document();
	        PdfWriter.getInstance(document, response.getOutputStream());
	        document.open();

	        // Agregar contenido al PDF
	        document.addTitle(titulo + " " + fecha);
	        document.add(new Paragraph(titulo));
	        document.add(new Paragraph("Fecha: " + fecha));
	        document.add(new Paragraph(" "));

	        // Crear una tabla para mostrar las citas
	        PdfPTable table = new PdfPTable(3); // 2 columnas: Hora y Cliente
	        table.setWidthPercentage(100);

	        for (CitasModel cita : citas) {
	            String[] partes = cita.getFechaCita().split("&");
	            String hora = partes[0];
	            String clienteNombre = cita.getCliente().getNombre();
	            String servicio=cita.getServicio().getNombre();
//	            System.out.println(cita.getFechaCita());
	            // Agregar fila a la tabla
	            table.addCell(new PdfPCell(new Phrase(hora)));
	            table.addCell(new PdfPCell(new Phrase(clienteNombre)));
	            table.addCell(new PdfPCell(new Phrase(servicio)));
	        }

	        // Agregar la tabla al documento
	        document.add(table);

	        document.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
