package com.gapsi.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gapsi.entity.Proveedor;
import com.gapsi.service.IProveedorService;
import com.gapsi.util.PageRender;

@Controller
@SessionAttributes("proveerdor")
public class ProveedorController {

	@Autowired
	private IProveedorService proveedorService;

	@RequestMapping(value = "/consulta-lista", method = RequestMethod.GET)
	public String listar(@RequestParam(name="page", defaultValue="0") int page, Model model) {
		
		Pageable pageRequest = PageRequest.of(page, 4);
		
		Page<Proveedor> proveedores = proveedorService.findAll(pageRequest);
		
		PageRender<Proveedor> pageRender = new PageRender<Proveedor>("/consulta-lista", proveedores);
		model.addAttribute("titulo", "Administración de Proveedores");
		model.addAttribute("proveedores", proveedores);
		model.addAttribute("page", pageRender);
		return "consulta-lista";
	}

	@RequestMapping(value = "/formulario-proveedor" , method = RequestMethod.GET)
	public String crear(Map<String, Object> model) {
		
		Proveedor proveedor = new Proveedor();
		model.put("proveedor", proveedor);
		model.put("titulo", "Formulario de Proveedor");
		return "formulario-proveedor";
	}
	
	
	@RequestMapping(value = "/consulta-proveedor/{id}")
	public String consultar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		
		Proveedor proveedor = null;
		
		if (id > 0) {
			proveedor = proveedorService.findOne(id);
			if (proveedor == null) {
				flash.addFlashAttribute("error", "El ID del proveedor no existe en la BBDD!");
				return "redirect:/consulta-lista";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del proveedor no puede ser cero!");
			return "redirect:/consulta-lista";
		}
		model.put("proveedor", proveedor);
		model.put("titulo", "Consulta Proveedor");

		return "consulta-proveedor";
	}

	@RequestMapping(value = "/formulario-proveedor", method = RequestMethod.POST)
	public String guardar(@Valid Proveedor proveedor, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {

		String mensajeFlash = null;
		if (result.hasErrors()) {
			
			model.addAttribute("titulo", "Formulario del Proveedor");
			return "formulario-proveedor";
		}
		
		
		boolean existeProveedor = buscaProveedorExistente(proveedor.getNombre());
		
		if (existeProveedor) {
			mensajeFlash = "Proveedor ya existe con ese nombre: " + proveedor.getNombre();
			flash.addFlashAttribute("success", mensajeFlash);
		}else{
			
			mensajeFlash = validaDatosProveedor(proveedor);
			
			if(mensajeFlash != null) {
				
				model.addAttribute("titulo", "Formulario del Proveedor - " + mensajeFlash );
				model.addAttribute("proveedor", proveedor);
				model.addAttribute("error", mensajeFlash );
				return "formulario-proveedor";
				
			}else {
				mensajeFlash = "Proveedor creado con éxito!";	
				proveedorService.save(proveedor);
				status.setComplete();
				flash.addFlashAttribute("success", mensajeFlash);
				
			}
		}

		return "redirect:consulta-lista";
	}

	@RequestMapping(value = "/eliminar-proveedor/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if (id > 0) {
			proveedorService.delete(id);
			flash.addFlashAttribute("success", "Proveedor eliminado con éxito!");
		}
		return "redirect:/consulta-lista";
	}
	
	
	private boolean buscaProveedorExistente(String nombre) {
		List<Proveedor> listProveedores = proveedorService.findAll();
		
		for(Proveedor proveedor: listProveedores) {
		
			if(proveedor.getNombre().equals(nombre)) {
				return true;
			}
			
		}
		return false;
	}
	
	
	private String validaDatosProveedor(Proveedor proveedor) {
		String mensage = null;
		
		System.out.println(proveedor.toString());
		
		if(proveedor.getNombre() == null || proveedor.getNombre().trim().length() == 0) { 
			mensage = "Falta agregar la Nombre!";
		}else {
			if(proveedor.getDireccion() == null || proveedor.getDireccion().trim().length() == 0) {
				mensage = "Falta agregar el Direccion!!!";
			}else {
				if(proveedor.getRazonsocial() == null || proveedor.getRazonsocial().trim().length() == 0) {
					mensage = "Falta agregar el Razon Social!!";
				}
			}
		}
		
		return mensage;
	}

	
}