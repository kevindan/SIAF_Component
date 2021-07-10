package pe.gob.inbp.siaf.component.domain;

import java.io.Serializable;

public class ProgramaPpto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String programa_ppto;
	private String programa_ppto_nombre;

	public ProgramaPpto() {

	}

	public ProgramaPpto(String programa_ppto, String programa_ppto_nombre) {

		this.programa_ppto = programa_ppto;
		this.programa_ppto_nombre = programa_ppto_nombre;
	}

	public String getPrograma_ppto() {
		return programa_ppto;
	}

	public void setPrograma_ppto(String programa_ppto) {
		this.programa_ppto = programa_ppto;
	}

	public String getPrograma_ppto_nombre() {
		return programa_ppto_nombre;
	}

	public void setPrograma_ppto_nombre(String programa_ppto_nombre) {
		this.programa_ppto_nombre = programa_ppto_nombre;
	}

	@Override
	public String toString() {
		return "ProgramaPpto [programa_ppto=" + programa_ppto + ", programa_ppto_nombre=" + programa_ppto_nombre + "]";
	}

}
