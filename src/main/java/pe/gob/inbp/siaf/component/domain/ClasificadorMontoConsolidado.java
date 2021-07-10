package pe.gob.inbp.siaf.component.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class ClasificadorMontoConsolidado implements Serializable {

	private static final long serialVersionUID = 1L;

	private String ano_eje;
	private Integer periodo_mensual;
	private String sec_func;
	private String fuente_financ;
	private String id_clasificador;
	private BigDecimal monto;

	public ClasificadorMontoConsolidado() {

	}

	public ClasificadorMontoConsolidado(String ano_eje, Integer periodo_mensual, String sec_func, String fuente_financ,
			String id_clasificador, BigDecimal monto) {

		this.ano_eje = ano_eje;
		this.periodo_mensual = periodo_mensual;
		this.sec_func = sec_func;
		this.fuente_financ = fuente_financ;
		this.id_clasificador = id_clasificador;
		this.monto = monto;
	}

	public String getAno_eje() {
		return ano_eje;
	}

	public void setAno_eje(String ano_eje) {
		this.ano_eje = ano_eje;
	}

	public Integer getPeriodo_mensual() {
		return periodo_mensual;
	}

	public void setPeriodo_mensual(Integer periodo_mensual) {
		this.periodo_mensual = periodo_mensual;
	}

	public String getSec_func() {
		return sec_func;
	}

	public void setSec_func(String sec_func) {
		this.sec_func = sec_func;
	}

	public String getFuente_financ() {
		return fuente_financ;
	}

	public void setFuente_financ(String fuente_financ) {
		this.fuente_financ = fuente_financ;
	}

	public String getId_clasificador() {
		return id_clasificador;
	}

	public void setId_clasificador(String id_clasificador) {
		this.id_clasificador = id_clasificador;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	@Override
	public String toString() {
		return "ClasificadorMontoConsolidado [ano_eje=" + ano_eje + ", periodo_mensual=" + periodo_mensual
				+ ", sec_func=" + sec_func + ", fuente_financ=" + fuente_financ + ", id_clasificador=" + id_clasificador
				+ ", monto=" + monto + "]";
	}

	

}
