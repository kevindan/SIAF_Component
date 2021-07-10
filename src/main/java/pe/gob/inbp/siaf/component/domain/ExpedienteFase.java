package pe.gob.inbp.siaf.component.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class ExpedienteFase implements Serializable {

	private static final long serialVersionUID = 1L;

	private String ano_eje;
	private String sec_ejec;
	private String expediente;
	private String secuencia;
	private String correlativo;
	private String ciclo;
	private String fase;
	private BigDecimal monto_nacional;
	private String cod_doc;
	private String documento_nombre;
	private String num_doc;
	private String fecha_doc;
	private String estado;
	private String estado_envio;

	public ExpedienteFase() {

	}

	public ExpedienteFase(String ano_eje, String sec_ejec, String expediente, String secuencia, String correlativo,
			String ciclo, String fase, BigDecimal monto_nacional, String cod_doc, String documento_nombre,
			String num_doc, String fecha_doc, String estado, String estado_envio) {

		this.ano_eje = ano_eje;
		this.sec_ejec = sec_ejec;
		this.expediente = expediente;
		this.secuencia = secuencia;
		this.correlativo = correlativo;
		this.ciclo = ciclo;
		this.fase = fase;
		this.monto_nacional = monto_nacional;
		this.cod_doc = cod_doc;
		this.documento_nombre = documento_nombre;
		this.num_doc = num_doc;
		this.fecha_doc = fecha_doc;
		this.estado = estado;
		this.estado_envio = estado_envio;
	}

	public String getAno_eje() {
		return ano_eje;
	}

	public void setAno_eje(String ano_eje) {
		this.ano_eje = ano_eje;
	}

	public String getSec_ejec() {
		return sec_ejec;
	}

	public void setSec_ejec(String sec_ejec) {
		this.sec_ejec = sec_ejec;
	}

	public String getExpediente() {
		return expediente;
	}

	public void setExpediente(String expediente) {
		this.expediente = expediente;
	}

	public String getCiclo() {
		return ciclo;
	}

	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}

	public String getFase() {
		return fase;
	}

	public void setFase(String fase) {
		this.fase = fase;
	}

	public BigDecimal getMonto_nacional() {
		return monto_nacional;
	}

	public void setMonto_nacional(BigDecimal monto_nacional) {
		this.monto_nacional = monto_nacional;
	}

	public String getFecha_doc() {
		return fecha_doc;
	}

	public void setFecha_doc(String fecha_doc) {
		this.fecha_doc = fecha_doc;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEstado_envio() {
		return estado_envio;
	}

	public void setEstado_envio(String estado_envio) {
		this.estado_envio = estado_envio;
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

	public String getDocumento_nombre() {
		return documento_nombre;
	}

	public void setDocumento_nombre(String documento_nombre) {
		this.documento_nombre = documento_nombre;
	}

	@Override
	public String toString() {
		return "ExpedienteFase [ano_eje=" + ano_eje + ", sec_ejec=" + sec_ejec + ", expediente=" + expediente
				+ ", secuencia=" + secuencia + ", correlativo=" + correlativo + ", ciclo=" + ciclo + ", fase=" + fase
				+ ", monto_nacional=" + monto_nacional + ", cod_doc=" + cod_doc + ", documento_nombre="
				+ documento_nombre + ", num_doc=" + num_doc + ", fecha_doc=" + fecha_doc + ", estado=" + estado
				+ ", estado_envio=" + estado_envio + "]";
	}

}
