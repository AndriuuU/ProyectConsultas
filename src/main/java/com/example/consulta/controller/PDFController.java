package com.example.consulta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.consulta.entity.Citas;
import com.example.consulta.model.CitasModel;
import com.example.consulta.repository.CitasRepository;
import com.example.consulta.service.CitasService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
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
	public void generarPdf(HttpServletResponse response,@RequestParam("fecha") String fechaweb) {
	    try {
	    	System.out.println("AAAAAAAAAAAAAAAAAAAAAAAA");
	    	System.out.println(fechaweb);
	        // Configurar el título y otras información de la clínica
	        String fecha = fechaweb;
	        String titulo = "Clínica dermatológica".toUpperCase();
	        List<CitasModel> citas = citasService.obtenerCitasDelDia(fechaweb);
System.out.println(citas);
	        // Configurar la respuesta HTTP para un archivo PDF
	        response.setContentType("application/pdf");
	        response.setHeader("Content-Disposition", "attachment; filename=ClinicaDermatologia.pdf");

            // Crear un documento PDF
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            // Agregar contenido al PDF
            document.addTitle(titulo + " " + fecha);

            // Configurar fuente y color para el título
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.BLUE);
            Paragraph title = new Paragraph(titulo, titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Configurar fuente y color para la fecha
            Font dateFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.DARK_GRAY);
            Paragraph date = new Paragraph("Fecha: " + fecha, dateFont);
            date.setAlignment(Element.ALIGN_CENTER);
            document.add(date);

            // Agregar espacio entre el título y la imagen
            document.add(new Paragraph(" "));

            // Obtener la ruta completa de la imagen
            String staticDir = ClassLoader.getSystemResource("static").getPath();
            String imagePath = staticDir + "/images/logoapp3.png";

            // Crear la imagen
            Image image = Image.getInstance(imagePath);
            image.scaleToFit(30, 30);
            image.setAlignment(Element.ALIGN_RIGHT);
            document.add(image);

            // Agregar espacio entre la imagen y la tabla
            document.add(new Paragraph(" "));

            // Crear una tabla para mostrar las citas
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);

            // Configurar fuente y color para las celdas de la tabla
            Font cellFont = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);

            table.addCell(new PdfPCell(new Phrase("HORA")));
            table.addCell(new PdfPCell(new Phrase("SERVICIO")));
            table.addCell(new PdfPCell(new Phrase("NOMBRE CLIENTE")));
            table.addCell(new PdfPCell(new Phrase("TELEFONO")));
	        for (CitasModel cita : citas) {
	            String[] partes = cita.getFechaCita().split("&");
	            String hora = partes[0];
	            String clienteNombre = cita.getCliente().getNombre();
	            String servicio=cita.getServicio().getNombre();
	            String telefono=cita.getCliente().getTelefono();
//	            System.out.println(cita.getFechaCita());
	            // Agregar fila a la tabla
	            table.addCell(new PdfPCell(new Phrase(hora)));
	            table.addCell(new PdfPCell(new Phrase(servicio)));
	            table.addCell(new PdfPCell(new Phrase(clienteNombre)));
	            table.addCell(new PdfPCell(new Phrase(telefono)));
	            
	        }

	        // Agregar la tabla al documento
	        document.add(table);

	        document.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	}
	
	@GetMapping("/admin/generar-pdfhoy")
	public void generarPdf(HttpServletResponse response) {
	    try {

	        // Configurar el título y otras información de la clínica
	    	 String fecha = java.time.LocalDate.now().toString();
	        String titulo = "Clínica dermatológica".toUpperCase();
	        List<CitasModel> citas = citasService.listaCitaHoy();
System.out.println(citas);
	        // Configurar la respuesta HTTP para un archivo PDF
	        response.setContentType("application/pdf");
	        response.setHeader("Content-Disposition", "attachment; filename=ClinicaDermatologia.pdf");

            // Crear un documento PDF
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            // Agregar contenido al PDF
            document.addTitle(titulo + " " + fecha);

            // Configurar fuente y color para el título
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.BLUE);
            Paragraph title = new Paragraph(titulo, titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Configurar fuente y color para la fecha
            Font dateFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.DARK_GRAY);
            Paragraph date = new Paragraph("Fecha: " + fecha, dateFont);
            date.setAlignment(Element.ALIGN_CENTER);
            document.add(date);

            // Agregar espacio entre el título y la imagen
            document.add(new Paragraph(" "));

            // Obtener la ruta completa de la imagen
            String staticDir = ClassLoader.getSystemResource("static").getPath();
            String imagePath = staticDir + "/images/logoapp3.png";

            // Crear la imagen
            Image image = Image.getInstance(imagePath);
            image.scaleToFit(30, 30);
            image.setAlignment(Element.ALIGN_RIGHT);
            document.add(image);

            // Agregar espacio entre la imagen y la tabla
            document.add(new Paragraph(" "));

            // Crear una tabla para mostrar las citas
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);

            // Configurar fuente y color para las celdas de la tabla
            Font cellFont = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);

            table.addCell(new PdfPCell(new Phrase("HORA")));
            table.addCell(new PdfPCell(new Phrase("SERVICIO")));
            table.addCell(new PdfPCell(new Phrase("NOMBRE CLIENTE")));
            table.addCell(new PdfPCell(new Phrase("TELEFONO")));
	        for (CitasModel cita : citas) {
	            String[] partes = cita.getFechaCita().split("&");
	            String hora = partes[0];
	            String clienteNombre = cita.getCliente().getNombre();
	            String servicio=cita.getServicio().getNombre();
	            String telefono=cita.getCliente().getTelefono();
//	            System.out.println(cita.getFechaCita());
	            // Agregar fila a la tabla
	            table.addCell(new PdfPCell(new Phrase(hora)));
	            table.addCell(new PdfPCell(new Phrase(servicio)));
	            table.addCell(new PdfPCell(new Phrase(clienteNombre)));
	            table.addCell(new PdfPCell(new Phrase(telefono)));
	            
	        }

	        // Agregar la tabla al documento
	        document.add(table);

	        document.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	}

}
