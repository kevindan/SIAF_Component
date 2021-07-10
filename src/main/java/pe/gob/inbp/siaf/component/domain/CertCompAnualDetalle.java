package pe.gob.inbp.siaf.component.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class CertCompAnualDetalle implements Serializable {

	private static final long serialVersionUID = 1L;

	private String certificado;
	private String secuencia;
	private String correlativo;
	private String id_clasificador;
	private String sec_func;
	private BigDecimal monto;
	private BigDecimal monto_nacional;
	private String estado_registro;
	private String estado_envio;

	public CertCompAnualDetalle() {

	}

	public CertCompAnualDetalle(String certificado, String secuencia, String correlativo, String id_clasificador,
			String sec_func, BigDecimal monto, BigDecimal monto_nacional, String estado_registro, String estado_envio) {

		this.certificado = certificado;
		this.secuencia = secuencia;
		this.correlativo = correlativo;
		this.id_clasificador = id_clasificador;
		this.sec_func = sec_func;
		this.monto = monto;
		this.monto_nacional = monto_nacional;
		this.estado_registro = estado_registro;
		this.estado_envio = estado_envio;
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

	public String getId_clasificador() {
		return id_clasificador;
	}

	public void setId_clasificador(String id_clasificador) {
		this.id_clasificador = id_clasificador;
	}

	public String getSec_func() {
		return sec_func;
	}

	public void setSec_func(String sec_func) {
		this.sec_func = sec_func;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public BigDecimal getMonto_nacional() {
		return monto_nacional;
	}

	public void setMonto_nacional(BigDecimal monto_nacional) {
		this.monto_nacional = monto_nacional;
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
		return "CertCompAnualDetalle [certificado=" + certificado + ", secuencia=" + secuencia + ", correlativo="
				+ correlativo + ", id_clasificador=" + id_clasificador + ", sec_func=" + sec_func + ", monto=" + monto
				+ ", monto_nacional=" + monto_nacional + ", estado_registro=" + estado_registro + ", estado_envio="
				+ estado_envio + "]";
	}

}
