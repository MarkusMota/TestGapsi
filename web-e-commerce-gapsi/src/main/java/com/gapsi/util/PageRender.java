package com.gapsi.util;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;


/*
 * 
 * Clase para el procesamiento de paginas
 * 
 */
public class PageRender<T> {

	/*
	 * Variable que contiene la URL
	 */
	private String url;
	
	/*
	 * Variable que contiene el objeto para la paginacion
	 */
	private Page<T> pagina;

	/*
	 * Variable que contiene el total de paginas que se deben de desplegar
	 */
	private int totalPaginas;

	/*
	 * Variable que contiene el numero de elementos por pagina
	 */
	private int numElementosPorPagina;

	/*
	 * Variable que contiene el numero actual de pagina desplegada
	 */
	private int paginaActual;

	/*
	 * Variable que contiene la lista de paginas que se pueden desplegar
	 */
	private List<PageItem> paginas;
	
	public String getUrl() {
		return url;
	}

	public int getTotalPaginas() {
		return totalPaginas;
	}

	public int getPaginaActual() {
		return paginaActual;
	}

	public List<PageItem> getPaginas() {
		return paginas;
	}

	public boolean isFirst() {
		return pagina.isFirst();
	}

	public boolean isLast() {
		return pagina.isLast();
	}

	public boolean isHasNext() {
		return pagina.hasNext();
	}

	public boolean isHasPrevious() {
		return pagina.hasPrevious();
	}
	

	/*
	 * Metodo para rederizar la paginación con la pagina que se desea visualizar
	 */
	public PageRender(String url, Page<T> pagina) {
		this.url = url;
		this.pagina = pagina;
		this.paginas = new ArrayList<PageItem>();

		numElementosPorPagina = 6;
		totalPaginas = pagina.getTotalPages();
		paginaActual = pagina.getNumber() + 1;

		int desde, hasta;
		if (totalPaginas <= numElementosPorPagina) {
			desde = 1;
			hasta = totalPaginas;
		} else {
			if (paginaActual <= numElementosPorPagina / 2) {
				desde = 1;
				hasta = numElementosPorPagina;
			} else if (paginaActual >= totalPaginas - numElementosPorPagina / 2) {
				desde = totalPaginas - numElementosPorPagina + 1;
				hasta = numElementosPorPagina;
			} else {
				desde = paginaActual - numElementosPorPagina / 2;
				hasta = numElementosPorPagina;
			}
		}

		for (int i = 0; i < hasta; i++) {
			paginas.add(new PageItem(desde + i, paginaActual == desde + i));
		}

	}



}
