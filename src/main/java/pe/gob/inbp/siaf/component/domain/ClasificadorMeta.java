package pe.gob.inbp.siaf.component.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ClasificadorMeta implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	private String ano_eje;
	@JsonIgnore
	private String sec_ejec;
	@JsonIgnore
	private String sec_func;
	private String fuente_financ;
	private String fuente_financ_nombre;
	private String tipo_transaccion;
	private String tipo_transaccion_nombre;
	private String generica;
	private String generica_nombre;
	private String subgenerica;
	private String subgenerica_nombre;
	private String subgenerica_det;
	private String subgenerica_det_nombre;
	private String especifica;
	private String especifica_nombre;
	private String especifica_det;
	private String cod_clasificador;
	private String id_clasificador;
	private String nombre_clasificador;
	private BigDecimal pia;
	private BigDecimal modificacion;
	private BigDecimal pim;
	private BigDecimal compromiso;
	private BigDecimal devengado;
	private BigDecimal girado;
	private BigDecimal pagado;
	private BigDecimal monto_certificado;
	private BigDecimal monto_comprometido_anual;
	private BigDecimal saldo_pca;

	public ClasificadorMeta() {

	}

	public ClasificadorMeta(String ano_eje, String sec_ejec, String sec_func, String fuente_financ,
			String fuente_financ_nombre, String tipo_transaccion, String tipo_transaccion_nombre, String generica,
			String generica_nombre, String subgenerica, String subgenerica_nombre, String subgenerica_det,
			String subgenerica_det_nombre, String especifica, String especifica_nombre, String especifica_det,
			String cod_clasificador, String id_clasificador, String nombre_clasificador, BigDecimal pia,
			BigDecimal modificacion, BigDecimal pim, BigDecimal compromiso, BigDecimal devengado, BigDecimal girado,
			BigDecimal pagado, BigDecimal monto_certificado, BigDecimal monto_comprometido_anual,
			BigDecimal saldo_pca) {

		this.ano_eje = ano_eje;
		this.sec_ejec = sec_ejec;
		this.sec_func = sec_func;
		this.fuente_financ = fuente_financ;
		this.fuente_financ_nombre = fuente_financ_nombre;
		this.tipo_transaccion = tipo_transaccion;
		this.tipo_transaccion_nombre = tipo_transaccion_nombre;
		this.generica = generica;
		this.generica_nombre = generica_nombre;
		this.subgenerica = subgenerica;
		this.subgenerica_nombre = subgenerica_nombre;
		this.subgenerica_det = subgenerica_det;
		this.subgenerica_det_nombre = subgenerica_det_nombre;
		this.especifica = especifica;
		this.especifica_nombre = especifica_nombre;
		this.especifica_det = especifica_det;
		this.cod_clasificador = cod_clasificador;
		this.id_clasificador = id_clasificador;
		this.nombre_clasificador = nombre_clasificador;
		this.pia = pia;
		this.modificacion = modificacion;
		this.pim = pim;
		this.compromiso = compromiso;
		this.devengado = devengado;
		this.girado = girado;
		this.pagado = pagado;
		this.monto_certificado = monto_certificado;
		this.monto_comprometido_anual = monto_comprometido_anual;
		this.saldo_pca = saldo_pca;
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

	public String getFuente_financ_nombre() {
		return fuente_financ_nombre;
	}

	public void setFuente_financ_nombre(String fuente_financ_nombre) {
		this.fuente_financ_nombre = fuente_financ_nombre;
	}

	public String getTipo_transaccion() {
		return tipo_transaccion;
	}

	public void setTipo_transaccion(String tipo_transaccion) {
		this.tipo_transaccion = tipo_transaccion;
	}

	public String getTipo_transaccion_nombre() {
		return tipo_transaccion_nombre;
	}

	public void setTipo_transaccion_nombre(String tipo_transaccion_nombre) {
		this.tipo_transaccion_nombre = tipo_transaccion_nombre;
	}

	public String getGenerica() {
		return generica;
	}

	public void setGenerica(String generica) {
		this.generica = generica;
	}

	public String getGenerica_nombre() {
		return generica_nombre;
	}

	public void setGenerica_nombre(String generica_nombre) {
		this.generica_nombre = generica_nombre;
	}

	public String getSubgenerica() {
		return subgenerica;
	}

	public void setSubgenerica(String subgenerica) {
		this.subgenerica = subgenerica;
	}

	public String getSubgenerica_nombre() {
		return subgenerica_nombre;
	}

	public void setSubgenerica_nombre(String subgenerica_nombre) {
		this.subgenerica_nombre = subgenerica_nombre;
	}

	public String getSubgenerica_det() {
		return subgenerica_det;
	}

	public void setSubgenerica_det(String subgenerica_det) {
		this.subgenerica_det = subgenerica_det;
	}

	public String getSubgenerica_det_nombre() {
		return subgenerica_det_nombre;
	}

	public void setSubgenerica_det_nombre(String subgenerica_det_nombre) {
		this.subgenerica_det_nombre = subgenerica_det_nombre;
	}

	public String getEspecifica() {
		return especifica;
	}

	public void setEspecifica(String especifica) {
		this.especifica = especifica;
	}

	public String getEspecifica_nombre() {
		return especifica_nombre;
	}

	public void setEspecifica_nombre(String especifica_nombre) {
		this.especifica_nombre = especifica_nombre;
	}

	public String getEspecifica_det() {
		return especifica_det;
	}

	public void setEspecifica_det(String especifica_det) {
		this.especifica_det = especifica_det;
	}

	public String getCod_clasificador() {
		return cod_clasificador;
	}

	public void setCod_clasificador(String cod_clasificador) {
		this.cod_clasificador = cod_clasificador;
	}

	public String getId_clasificador() {
		return id_clasificador;
	}

	public void setId_clasificador(String id_clasificador) {
		this.id_clasificador = id_clasificador;
	}

	public String getNombre_clasificador() {
		return nombre_clasificador;
	}

	public void setNombre_clasificador(String nombre_clasificador) {
		this.nombre_clasificador = nombre_clasificador;
	}

	public BigDecimal getPia() {
		return pia;
	}

	public void setPia(BigDecimal pia) {
		this.pia = pia;
	}

	public BigDecimal getModificacion() {
		return modificacion;
	}

	public void setModificacion(BigDecimal modificacion) {
		this.modificacion = modificacion;
	}

	public BigDecimal getPim() {
		return pim;
	}

	public void setPim(BigDecimal pim) {
		this.pim = pim;
	}

	public BigDecimal getCompromiso() {
		return compromiso;
	}

	public void setCompromiso(BigDecimal compromiso) {
		this.compromiso = compromiso;
	}

	public BigDecimal getDevengado() {
		return devengado;
	}

	public void setDevengado(BigDecimal devengado) {
		this.devengado = devengado;
	}

	public BigDecimal getGirado() {
		return girado;
	}

	public void setGirado(BigDecimal girado) {
		this.girado = girado;
	}

	public BigDecimal getPagado() {
		return pagado;
	}

	public void setPagado(BigDecimal pagado) {
		this.pagado = pagado;
	}

	public BigDecimal getMonto_certificado() {
		return monto_certificado;
	}

	public void setMonto_certificado(BigDecimal monto_certificado) {
		this.monto_certificado = monto_certificado;
	}

	public BigDecimal getMonto_comprometido_anual() {
		return monto_comprometido_anual;
	}

	public void setMonto_comprometido_anual(BigDecimal monto_comprometido_anual) {
		this.monto_comprometido_anual = monto_comprometido_anual;
	}

	public BigDecimal getSaldo_pca() {
		return saldo_pca;
	}

	public void setSaldo_pca(BigDecimal saldo_pca) {
		this.saldo_pca = saldo_pca;
	}

	@Override
	public String toString() {
		return "ClasificadorMeta [ano_eje=" + ano_eje + ", sec_ejec=" + sec_ejec + ", sec_func=" + sec_func
				+ ", fuente_financ=" + fuente_financ + ", fuente_financ_nombre=" + fuente_financ_nombre
				+ ", tipo_transaccion=" + tipo_transaccion + ", tipo_transaccion_nombre=" + tipo_transaccion_nombre
				+ ", generica=" + generica + ", generica_nombre=" + generica_nombre + ", subgenerica=" + subgenerica
				+ ", subgenerica_nombre=" + subgenerica_nombre + ", subgenerica_det=" + subgenerica_det
				+ ", subgenerica_det_nombre=" + subgenerica_det_nombre + ", especifica=" + especifica
				+ ", especifica_nombre=" + especifica_nombre + ", especifica_det=" + especifica_det
				+ ", cod_clasificador=" + cod_clasificador + ", id_clasificador=" + id_clasificador
				+ ", nombre_clasificador=" + nombre_clasificador + ", pia=" + pia + ", modificacion=" + modificacion
				+ ", pim=" + pim + ", compromiso=" + compromiso + ", devengado=" + devengado + ", girado=" + girado
				+ ", pagado=" + pagado + ", monto_certificado=" + monto_certificado + ", monto_comprometido_anual="
				+ monto_comprometido_anual + ", saldo_pca=" + saldo_pca + "]";
	}

}
