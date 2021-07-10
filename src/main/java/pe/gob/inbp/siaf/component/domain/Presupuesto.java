package pe.gob.inbp.siaf.component.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class Presupuesto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id_presupuesto;
	private String id_meta_presupuestal;
	private String id_clasificador_gasto;
	private String fuente_financ;
	private String fuente_financ_nombre;
	private BigDecimal monto;
	private Integer es_pia;

	public Presupuesto() {

	}

	public Presupuesto(String id_presupuesto, String id_meta_presupuestal, String id_clasificador_gasto,
			String fuente_financ, String fuente_financ_nombre, BigDecimal monto, Integer es_pia) {

		this.id_presupuesto = id_presupuesto;
		this.id_meta_presupuestal = id_meta_presupuestal;
		this.id_clasificador_gasto = id_clasificador_gasto;
		this.fuente_financ = fuente_financ;
		this.fuente_financ_nombre = fuente_financ_nombre;
		this.monto = monto;
		this.es_pia = es_pia;
	}

	public String getId_presupuesto() {
		return id_presupuesto;
	}

	public void setId_presupuesto(String id_presupuesto) {
		this.id_presupuesto = id_presupuesto;
	}

	public String getId_meta_presupuestal() {
		return id_meta_presupuestal;
	}

	public void setId_meta_presupuestal(String id_meta_presupuestal) {
		this.id_meta_presupuestal = id_meta_presupuestal;
	}

	public String getId_clasificador_gasto() {
		return id_clasificador_gasto;
	}

	public void setId_clasificador_gasto(String id_clasificador_gasto) {
		this.id_clasificador_gasto = id_clasificador_gasto;
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

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public Integer getEs_pia() {
		return es_pia;
	}

	public void setEs_pia(Integer es_pia) {
		this.es_pia = es_pia;
	}

	@Override
	public String toString() {
		return "Presupuesto [id_presupuesto=" + id_presupuesto + ", id_meta_presupuestal=" + id_meta_presupuestal
				+ ", id_clasificador_gasto=" + id_clasificador_gasto + ", fuente_financ=" + fuente_financ
				+ ", fuente_financ_nombre=" + fuente_financ_nombre + ", monto=" + monto + ", es_pia=" + es_pia + "]";
	}

}
