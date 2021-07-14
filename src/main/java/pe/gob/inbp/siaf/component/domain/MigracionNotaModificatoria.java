package pe.gob.inbp.siaf.component.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class MigracionNotaModificatoria implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id_nota_modificatoria;
	private String ano_eje;
	private String sec_ejec;
	private String sec_nota;
	private String fecha;
	private String notas;
	private Integer mes;
	private Integer dia;
	private String fuente_financ;
	private String id_clasificador;
	private String sec_func;
	private BigDecimal anulacion;
	private BigDecimal credito;

	public MigracionNotaModificatoria() {

	}

	public MigracionNotaModificatoria(Long id_nota_modificatoria, String ano_eje, String sec_ejec, String sec_nota,
			String fecha, String notas, Integer mes, Integer dia, String fuente_financ, String id_clasificador,
			String sec_func, BigDecimal anulacion, BigDecimal credito) {

		this.id_nota_modificatoria = id_nota_modificatoria;
		this.ano_eje = ano_eje;
		this.sec_ejec = sec_ejec;
		this.sec_nota = sec_nota;
		this.fecha = fecha;
		this.notas = notas;
		this.mes = mes;
		this.dia = dia;
		this.fuente_financ = fuente_financ;
		this.id_clasificador = id_clasificador;
		this.sec_func = sec_func;
		this.anulacion = anulacion;
		this.credito = credito;
	}

	public Long getId_nota_modificatoria() {
		return id_nota_modificatoria;
	}

	public void setId_nota_modificatoria(Long id_nota_modificatoria) {
		this.id_nota_modificatoria = id_nota_modificatoria;
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

	public String getSec_nota() {
		return sec_nota;
	}

	public void setSec_nota(String sec_nota) {
		this.sec_nota = sec_nota;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getDia() {
		return dia;
	}

	public void setDia(Integer dia) {
		this.dia = dia;
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

	public String getSec_func() {
		return sec_func;
	}

	public void setSec_func(String sec_func) {
		this.sec_func = sec_func;
	}

	public BigDecimal getAnulacion() {
		return anulacion;
	}

	public void setAnulacion(BigDecimal anulacion) {
		this.anulacion = anulacion;
	}

	public BigDecimal getCredito() {
		return credito;
	}

	public void setCredito(BigDecimal credito) {
		this.credito = credito;
	}

	@Override
	public String toString() {
		return "MigracionNotaModificatoria [id_nota_modificatoria=" + id_nota_modificatoria + ", ano_eje=" + ano_eje
				+ ", sec_ejec=" + sec_ejec + ", sec_nota=" + sec_nota + ", fecha=" + fecha + ", notas=" + notas
				+ ", mes=" + mes + ", dia=" + dia + ", fuente_financ=" + fuente_financ + ", id_clasificador="
				+ id_clasificador + ", sec_func=" + sec_func + ", anulacion=" + anulacion + ", credito=" + credito
				+ "]";
	}

}
