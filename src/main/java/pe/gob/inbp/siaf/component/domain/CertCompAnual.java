package pe.gob.inbp.siaf.component.domain;

import java.io.Serializable;
import java.util.List;

public class CertCompAnual implements Serializable {

	private static final long serialVersionUID = 1L;

	private String certificado;
	private String secuencia;
	private String correlativo;
	private String cod_doc;
	private String num_doc;
	private String fecha_doc;
	private String estado_registro;
	private String estado_envio;
	private String ind_certificacion;
	private List<CertCompAnualDetalle> detalle;

	public CertCompAnual() {

	}

	public CertCompAnual(String certificado, String secuencia, String correlativo, String cod_doc, String num_doc,
			String fecha_doc, String estado_registro, String estado_envio, String ind_certificacion,
			List<CertCompAnualDetalle> detalle) {

		this.certificado = certificado;
		this.secuencia = secuencia;
		this.correlativo = correlativo;
		this.cod_doc = cod_doc;
		this.num_doc = num_doc;
		this.fecha_doc = fecha_doc;
		this.estado_registro = estado_registro;
		this.estado_envio = estado_envio;
		this.ind_certificacion = ind_certificacion;
		this.detalle = detalle;
	}

	public String getCertificado() {
		return certificado;
	}

	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}

	public String getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}

	public String getCorrelativo() {
		return correlativo;
	}

	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
	}

	public String getCod_doc() {
		return cod_doc;
	}

	public void setCod_doc(String cod_doc) {
		this.cod_doc = cod_doc;
	}

	public String getNum_doc() {
		return num_doc;
	}

	public void setNum_doc(String num_doc) {
		this.num_doc = num_doc;
	}

	public String getFecha_doc() {
		return fecha_doc;
	}

	public void setFecha_doc(String fecha_doc) {
		this.fecha_doc = fecha_doc;
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

	public String getInd_certificacion() {
		return ind_certificacion;
	}

	public void setInd_certificacion(String ind_certificacion) {
		this.ind_certificacion = ind_certificacion;
	}

	public List<CertCompAnualDetalle> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<CertCompAnualDetalle> detalle) {
		this.detalle = detalle;
	}

	@Override
	public String toString() {
		return "CertCompAnual [certificado=" + certificado + ", secuencia=" + secuencia + ", correlativo=" + correlativo
				+ ", cod_doc=" + cod_doc + ", num_doc=" + num_doc + ", fecha_doc=" + fecha_doc + ", estado_registro="
				+ estado_registro + ", estado_envio=" + estado_envio + ", ind_certificacion=" + ind_certificacion
				+ ", detalle=" + detalle + "]";
	}

}
