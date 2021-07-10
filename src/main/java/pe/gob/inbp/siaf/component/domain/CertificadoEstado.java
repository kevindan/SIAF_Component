package pe.gob.inbp.siaf.component.domain;

import java.io.Serializable;

public class CertificadoEstado implements Serializable {

	private static final long serialVersionUID = 1L;

	private String estado_registro;
	private String estado_envio;

	public CertificadoEstado() {

	}

	public CertificadoEstado(String estado_registro, String estado_envio) {

		this.estado_registro = estado_registro;
		this.estado_envio = estado_envio;
	}

	public String getEstado_registro() {
		return estado_registro;
	}

	public void setEstado_registro(String estado_registro) {
		this.estado_registro = estado_registro;
	}

	public String getEstado_envio() {
		return estado_envio;
	}

	public void setEstado_envio(String estado_envio) {
		this.estado_envio = estado_envio;
	}

	@Override
	public String toString() {
		return "CertificadoEstado [estado_registro=" + estado_registro + ", estado_envio=" + estado_envio + "]";
	}

}
