package pe.gob.inbp.siaf.component.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class ClasifPcaResp implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id_clasificador;
	private BigDecimal monto_asignado;
	private BigDecimal monto_comprometido;
	private BigDecimal saldo_pca;

	public ClasifPcaResp() {

	}

	public ClasifPcaResp(String id_clasificador, BigDecimal monto_asignado, BigDecimal monto_comprometido,
			BigDecimal saldo_pca) {
	
		this.id_clasificador = id_clasificador;
		this.monto_asignado = monto_asignado;
		this.monto_comprometido = monto_comprometido;
		this.saldo_pca = saldo_pca;
	}

	public String getId_clasificador() {
		return id_clasificador;
	}

	public void setId_clasificador(String id_clasificador) {
		this.id_clasificador = id_clasificador;
	}

	public BigDecimal getMonto_asignado() {
		return monto_asignado;
	}

	public void setMonto_asignado(BigDecimal monto_asignado) {
		this.monto_asignado = monto_asignado;
	}

	public BigDecimal getMonto_comprometido() {
		return monto_comprometido;
	}

	public void setMonto_comprometido(BigDecimal monto_comprometido) {
		this.monto_comprometido = monto_comprometido;
	}

	public BigDecimal getSaldo_pca() {
		return saldo_pca;
	}

	public void setSaldo_pca(BigDecimal saldo_pca) {
		this.saldo_pca = saldo_pca;
	}

	@Override
	public String toString() {
		return "ClasifPcaResp [id_clasificador=" + id_clasificador + ", monto_asignado=" + monto_asignado
				+ ", monto_comprometido=" + monto_comprometido + ", saldo_pca=" + saldo_pca + "]";
	}

}
