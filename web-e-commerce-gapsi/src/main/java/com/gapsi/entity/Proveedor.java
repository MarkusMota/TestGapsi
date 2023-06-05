package com.gapsi.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "proveedor")
public class Proveedor implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	private Integer estatus;
	
	@NotEmpty
	private String nombre;
	
	@NotEmpty
	private String razonsocial;
	
	@NotEmpty
	private String direccion;
	
	@NotNull
	private BigDecimal importe;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRazonsocial() {
		return razonsocial;
	}

	public void setRazonsocial(String razonsocial) {
		this.razonsocial = razonsocial;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}



	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Proveedor [id=" + id + ", estatus=" + estatus + ", nombre=" + nombre + ", razonsocial=" + razonsocial
				+ ", direccion=" + direccion + ", importe=" + importe + "]";
	}

	
	
	
	

}
