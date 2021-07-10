package pe.gob.inbp.siaf.component.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class MigracionRegistroSiaf implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id_registro_siaf;
	private String ano_eje;
	private String sec_ejec;
	private String expediente;
	private String secuencia;
	private String correlativo;
	private String cod_doc;
	private String fecha_doc;
	private String num_doc;
	private Integer mes;
	private Integer dia;
	private String fuente_financ;
	private String ruc;
	private String id_clasificador;
	private String sec_func;
	private BigDecimal monto_nacional;
	private String fase;
	private String ciclo;

	public MigracionRegistroSiaf() {

	}

	public MigracionRegistroSiaf(Long id_registro_siaf, String ano_eje, String sec_ejec, String expediente,
			String secuencia, String correlativo, String cod_doc, String fecha_doc, String num_doc, Integer mes,
			Integer dia, String fuente_financ, String ruc, String id_clasificador, String sec_func,
			BigDecimal monto_nacional, String fase, String ciclo) {

		this.id_registro_siaf = id_registro_siaf;
		this.ano_eje = ano_eje;
		this.sec_ejec = sec_ejec;
		this.expediente = expediente;
		this.secuencia = secuencia;
		this.correlativo = correlativo;
		this.cod_doc = cod_doc;
		this.fecha_doc = fecha_doc;
		this.num_doc = num_doc;
		this.mes = mes;
		this.dia = dia;
		this.fuente_financ = fuente_financ;
		this.ruc = ruc;
		this.id_clasificador = id_clasificador;
		this.sec_func = sec_func;
		this.monto_nacional = monto_nacional;
		this.fase = fase;
		this.ciclo = ciclo;
	}

	public Long getId_registro_siaf() {
		return id_registro_siaf;
	}

	public void setId_registro_siaf(Long id_registro_siaf) {
		this.id_registro_siaf = id_registro_siaf;
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

	public String getFecha_doc() {
		return fecha_doc;
	}

	public void setFecha_doc(String fecha_doc) {
		this.fecha_doc = fecha_doc;
	}

	public String getNum_doc() {
		return num_doc;
	}

	public void setNum_doc(String num_doc) {
		this.num_doc = num_doc;
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

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
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

	public BigDecimal getMonto_nacional() {
		return monto_nacional;
	}

	public void setMonto_nacional(BigDecimal monto_nacional) {
		this.monto_nacional = monto_nacional;
	}

	public String getFase() {
		return fase;
	}

	public void setFase(String fase) {
		this.fase = fase;
	}

	public String getCiclo() {
		return ciclo;
	}

	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}

	@Override
	public String toString() {
		return "MigracionRegistroSiaf [id_registro_siaf=" + id_registro_siaf + ", ano_eje=" + ano_eje + ", sec_ejec="
				+ sec_ejec + ", expediente=" + expediente + ", secuencia=" + secuencia + ", correlativo=" + correlativo
				+ ", cod_doc=" + cod_doc + ", fecha_doc=" + fecha_doc + ", num_doc=" + num_doc + ", mes=" + mes
				+ ", dia=" + dia + ", fuente_financ=" + fuente_financ + ", ruc=" + ruc + ", id_clasificador="
				+ id_clasificador + ", sec_func=" + sec_func + ", monto_nacional=" + monto_nacional + ", fase=" + fase
				+ ", ciclo=" + ciclo + "]";
	}

}
