package pe.gob.inbp.siaf.component.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class MigracionPresupuesto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id_presupuesto;
	private Long id_meta;
	private Long id_clasificador_gasto;
	private String ano_eje;
	private String fuente_financ;
	private BigDecimal monto_inicial;

	public MigracionPresupuesto() {

	}

	public MigracionPresupuesto(Long id_presupuesto, Long id_meta, Long id_clasificador_gasto, String ano_eje,
			String fuente_financ, BigDecimal monto_inicial) {

		this.id_presupuesto = id_presupuesto;
		this.id_meta = id_meta;
		this.id_clasificador_gasto = id_clasificador_gasto;
		this.ano_eje = ano_eje;
		this.fuente_financ = fuente_financ;
		this.monto_inicial = monto_inicial;
	}

	public Long getId_presupuesto() {
		return id_presupuesto;
	}

	public void setId_presupuesto(Long id_presupuesto) {
		this.id_presupuesto = id_presupuesto;
	}

	public Long getId_meta() {
		return id_meta;
	}

	public void setId_meta(Long id_meta) {
		this.id_meta = id_meta;
	}

	public Long getId_clasificador_gasto() {
		return id_clasificador_gasto;
	}

	public void setId_clasificador_gasto(Long id_clasificador_gasto) {
		this.id_clasificador_gasto = id_clasificador_gasto;
	}

	public String getAno_eje() {
		return ano_eje;
	}

	public void setAno_eje(String ano_eje) {
		this.ano_eje = ano_eje;
	}

	public String getFuente_financ() {
		return fuente_financ;
	}

	public void setFuente_financ(String fuente_financ) {
		this.fuente_financ = fuente_financ;
	}

	public BigDecimal getMonto_inicial() {
		return monto_inicial;
	}

	public void setMonto_inicial(BigDecimal monto_inicial) {
		this.monto_inicial = monto_inicial;
	}

	@Override
	public String toString() {
		return "MigracionPresupuesto [id_presupuesto=" + id_presupuesto + ", id_meta=" + id_meta
				+ ", id_clasificador_gasto=" + id_clasificador_gasto + ", ano_eje=" + ano_eje + ", fuente_financ="
				+ fuente_financ + ", monto_inicial=" + monto_inicial + "]";
	}

}
