package pe.gob.inbp.siaf.component.domain;

import java.io.Serializable;

public class TipoOperacion implements Serializable {

	private static final long serialVersionUID = 1L;

	private String tipo_operacion;
	private String tipo_operacion_nombre;

	public TipoOperacion() {

	}

	public TipoOperacion(String tipo_operacion, String tipo_operacion_nombre) {
		this.tipo_operacion = tipo_operacion;
		this.tipo_operacion_nombre = tipo_operacion_nombre;
	}

	public String getTipo_operacion() {
		return tipo_operacion;
	}

	public void setTipo_operacion(String tipo_operacion) {
		this.tipo_operacion = tipo_operacion;
	}

	public String getTipo_operacion_nombre() {
		return tipo_operacion_nombre;
	}

	public void setTipo_operacion_nombre(String tipo_operacion_nombre) {
		this.tipo_operacion_nombre = tipo_operacion_nombre;
	}

	@Override
	public String toString() {
		return "TipoOperacion [tipo_operacion=" + tipo_operacion + ", tipo_operacion_nombre=" + tipo_operacion_nombre
				+ "]";
	}

}
