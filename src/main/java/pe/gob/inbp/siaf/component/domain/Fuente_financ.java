package pe.gob.inbp.siaf.component.domain;

import java.io.Serializable;

public class Fuente_financ implements Serializable {

	private static final long serialVersionUID = 1L;

	private String fuente_financ;
	private String fuente_financ_nombre;

	public Fuente_financ() {

	}

	public Fuente_financ(String fuente_financ, String fuente_financ_nombre) {

		this.fuente_financ = fuente_financ;
		this.fuente_financ_nombre = fuente_financ_nombre;
	}

	public String getFuente_financ() {
		return fuente_financ;
	}

	public void setFuente_financ(String fuente_financ) {
		this.fuente_financ = fuente_financ;
	}

	public String getFuente_financ_nombre() {
		return fuente_financ_nombre;
	}

	public void setFuente_financ_nombre(String fuente_financ_nombre) {
		this.fuente_financ_nombre = fuente_financ_nombre;
	}

	@Override
	public String toString() {
		return "Fuente_financ [fuente_financ=" + fuente_financ + ", fuente_financ_nombre=" + fuente_financ_nombre + "]";
	}

}
