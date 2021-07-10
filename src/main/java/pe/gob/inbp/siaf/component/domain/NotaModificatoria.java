package pe.gob.inbp.siaf.component.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class NotaModificatoria implements Serializable {

	private static final long serialVersionUID = 1L;

	private String sec_nota;
	private BigDecimal credito;
	private BigDecimal anulacion;
	private String notas;
	private String fecha;

	public NotaModificatoria() {

	}

	public NotaModificatoria(String sec_nota, BigDecimal credito, BigDecimal anulacion, String notas, String fecha) {
		super();
		this.sec_nota = sec_nota;
		this.credito = credito;
		this.anulacion = anulacion;
		this.notas = notas;
		this.fecha = fecha;
	}

	public String getSec_nota() {
		return sec_nota;
	}

	public void setSec_nota(String sec_nota) {
		this.sec_nota = sec_nota;
	}

	public BigDecimal getCredito() {
		return credito;
	}

	public void setCredito(BigDecimal credito) {
		this.credito = credito;
	}

	public BigDecimal getAnulacion() {
		return anulacion;
	}

	public void setAnulacion(BigDecimal anulacion) {
		this.anulacion = anulacion;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "NotaModificatoria [sec_nota=" + sec_nota + ", credito=" + credito + ", anulacion=" + anulacion
				+ ", notas=" + notas + ", fecha=" + fecha + "]";
	}

}
