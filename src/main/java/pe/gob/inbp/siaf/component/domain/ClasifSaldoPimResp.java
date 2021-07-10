package pe.gob.inbp.siaf.component.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class ClasifSaldoPimResp implements Serializable {

	private static final long serialVersionUID = 1L;

	private BigDecimal saldo_pim;
	private BigDecimal saldo_pca;

	public ClasifSaldoPimResp() {

	}

	public ClasifSaldoPimResp(BigDecimal saldo_pim, BigDecimal saldo_pca) {
		this.saldo_pim = saldo_pim;
		this.saldo_pca = saldo_pca;
	}

	public BigDecimal getSaldo_pim() {
		return saldo_pim;
	}

	public void setSaldo_pim(BigDecimal saldo_pim) {
		this.saldo_pim = saldo_pim;
	}

	public BigDecimal getSaldo_pca() {
		return saldo_pca;
	}

	public void setSaldo_pca(BigDecimal saldo_pca) {
		this.saldo_pca = saldo_pca;
	}

	@Override
	public String toString() {
		return "ClasifSaldoPimResp [saldo_pim=" + saldo_pim + ", saldo_pca=" + saldo_pca + "]";
	}

}
