package pe.gob.inbp.siaf.component.domain;

import java.io.Serializable;

public class CertCabConsulta implements Serializable {

	private static final long serialVersionUID = 1L;

	private String secuencial;
	private String estado;
	private String ue_envio;
	private String ue_estado;
	private String siaf_cer;
	private String siaf_sec;
	private String siaf_cor;

	public CertCabConsulta() {

	}

	public CertCabConsulta(String secuencial, String estado, String ue_envio, String ue_estado, String siaf_cer,
			String siaf_sec, String siaf_cor) {

		this.secuencial = secuencial;
		this.estado = estado;
		this.ue_envio = ue_envio;
		this.ue_estado = ue_estado;
		this.siaf_cer = siaf_cer;
		this.siaf_sec = siaf_sec;
		this.siaf_cor = siaf_cor;
	}

	public String getSecuencial() {
		return secuencial;
	}

	public void setSecuencial(String secuencial) {
		this.secuencial = secuencial;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getUe_envio() {
		return ue_envio;
	}

	public void setUe_envio(String ue_envio) {
		this.ue_envio = ue_envio;
	}

	public String getUe_estado() {
		return ue_estado;
	}

	public void setUe_estado(String ue_estado) {
		this.ue_estado = ue_estado;
	}

	public String getSiaf_cer() {
		return siaf_cer;
	}

	public void setSiaf_cer(String siaf_cer) {
		this.siaf_cer = siaf_cer;
	}

	public String getSiaf_sec() {
		return siaf_sec;
	}

	public void setSiaf_sec(String siaf_sec) {
		this.siaf_sec = siaf_sec;
	}

	public String getSiaf_cor() {
		return siaf_cor;
	}

	public void setSiaf_cor(String siaf_cor) {
		this.siaf_cor = siaf_cor;
	}

	@Override
	public String toString() {
		return "CertCabConsulta [secuencial=" + secuencial + ", ue_envio=" + ue_envio + ", ue_estado=" + ue_estado
				+ ", siaf_cer=" + siaf_cer + ", siaf_sec=" + siaf_sec + ", siaf_cor=" + siaf_cor + "]";
	}

}
