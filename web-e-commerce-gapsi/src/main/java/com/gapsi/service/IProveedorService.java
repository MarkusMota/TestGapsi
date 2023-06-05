package com.gapsi.service;

import java.util.List;
import com.gapsi.entity.Proveedor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface IProveedorService {
	
	public List<Proveedor> findAll();
	
	public Page<Proveedor> findAll(Pageable pageable);

	public void save(Proveedor cliente);
	
	public Proveedor findOne(Long id);
	
	public void delete(Long id);
	
}
