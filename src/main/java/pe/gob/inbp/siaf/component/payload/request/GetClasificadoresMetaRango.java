package pe.gob.inbp.siaf.component.payload.request;

import java.io.Serializable;

public class GetClasificadoresMetaRango implements Serializable {

	private static final long serialVersionUID = 1L;

	private String fecha_inicio;
	private String fecha_fin;

	public GetClasificadoresMetaRango() {

	}

	public GetClasificadoresMetaRango(String fecha_inicio, String fecha_fin) {

		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
	}

	public String getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public String getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	@Override
	public String toString() {
		return "GetClasificadoresMetaRango [fecha_inicio=" + fecha_inicio + ", fecha_fin=" + fecha_fin + "]";
	}

}
