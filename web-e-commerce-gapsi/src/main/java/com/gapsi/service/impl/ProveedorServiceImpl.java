package com.gapsi.service.impl;

import java.util.List;
import com.gapsi.dao.IProveedorDao;
import com.gapsi.entity.Proveedor;
import com.gapsi.service.IProveedorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class ProveedorServiceImpl implements IProveedorService {

	@Autowired
	private IProveedorDao clienteDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Proveedor> findAll() {
		return (List<Proveedor>) clienteDao.findAll();
	}
	
	@Override
	@Transactional
	public void save(Proveedor cliente) {
		clienteDao.save(cliente);
	}

	@Override
	@Transactional(readOnly = true)
	public Proveedor findOne(Long id) {
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Proveedor> findAll(Pageable pageable) {
		return clienteDao.findAll(pageable);
	}
}
