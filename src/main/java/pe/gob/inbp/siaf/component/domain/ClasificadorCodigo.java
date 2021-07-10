package pe.gob.inbp.siaf.component.domain;

import java.io.Serializable;

public class ClasificadorCodigo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigo_clasificador;
	private String codigo_nombre;

	public ClasificadorCodigo() {

	}

	public ClasificadorCodigo(String codigo_clasificador, String codigo_nombre) {
		super();
		this.codigo_clasificador = codigo_clasificador;
		this.codigo_nombre = codigo_nombre;
	}

	public String getCodigo_clasificador() {
		return codigo_clasificador;
	}

	public void setCodigo_clasificador(String codigo_clasificador) {
		this.codigo_clasificador = codigo_clasificador;
	}

	public String getCodigo_nombre() {
		return codigo_nombre;
	}

	public void setCodigo_nombre(String codigo_nombre) {
		this.codigo_nombre = codigo_nombre;
	}

	@Override
	public String toString() {
		return "ClasificadorCodigo [codigo_clasificador=" + codigo_clasificador + ", codigo_nombre=" + codigo_nombre
				+ "]";
	}

}
