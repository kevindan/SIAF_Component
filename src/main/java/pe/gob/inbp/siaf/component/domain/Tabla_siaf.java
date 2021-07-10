package pe.gob.inbp.siaf.component.domain;

import java.io.Serializable;

public class Tabla_siaf implements Serializable {

	private static final long serialVersionUID = 1L;
	private String status;
	private String nombre_tabla;

	public Tabla_siaf() {

	}

	public Tabla_siaf(String status, String nombre_tabla) {

		this.status = status;
		this.nombre_tabla = nombre_tabla;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNombre_tabla() {
		return nombre_tabla;
	}

	public void setNombre_tabla(String nombre_tabla) {
		this.nombre_tabla = nombre_tabla;
	}

	@Override
	public String toString() {
		return "Tabla_siaf [status=" + status + ", nombre_tabla=" + nombre_tabla + "]";
	}

}
