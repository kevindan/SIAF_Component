package pe.gob.inbp.siaf.component.domain;

import java.io.Serializable;

public class MigracionClasificador implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id_clasificador_gasto;
	private String ano_eje;
	private String id_clasificador;
	private String cod_clasificador;
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
	private String nombre_clasificador;

	public MigracionClasificador() {

	}

	public MigracionClasificador(Long id_clasificador_gasto, String ano_eje, String id_clasificador,
			String cod_clasificador, String tipo_transaccion, String tipo_transaccion_nombre, String generica,
			String generica_nombre, String subgenerica, String subgenerica_nombre, String subgenerica_det,
			String subgenerica_det_nombre, String especifica, String especifica_nombre, String especifica_det,
			String nombre_clasificador) {

		this.id_clasificador_gasto = id_clasificador_gasto;
		this.ano_eje = ano_eje;
		this.id_clasificador = id_clasificador;
		this.cod_clasificador = cod_clasificador;
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
		this.nombre_clasificador = nombre_clasificador;
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

	public String getId_clasificador() {
		return id_clasificador;
	}

	public void setId_clasificador(String id_clasificador) {
		this.id_clasificador = id_clasificador;
	}

	public String getCod_clasificador() {
		return cod_clasificador;
	}

	public void setCod_clasificador(String cod_clasificador) {
		this.cod_clasificador = cod_clasificador;
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

	public String getNombre_clasificador() {
		return nombre_clasificador;
	}

	public void setNombre_clasificador(String nombre_clasificador) {
		this.nombre_clasificador = nombre_clasificador;
	}

	@Override
	public String toString() {
		return "MigracionClasificador [id_clasificador_gasto=" + id_clasificador_gasto + ", ano_eje=" + ano_eje
				+ ", id_clasificador=" + id_clasificador + ", cod_clasificador=" + cod_clasificador
				+ ", tipo_transaccion=" + tipo_transaccion + ", tipo_transaccion_nombre=" + tipo_transaccion_nombre
				+ ", generica=" + generica + ", generica_nombre=" + generica_nombre + ", subgenerica=" + subgenerica
				+ ", subgenerica_nombre=" + subgenerica_nombre + ", subgenerica_det=" + subgenerica_det
				+ ", subgenerica_det_nombre=" + subgenerica_det_nombre + ", especifica=" + especifica
				+ ", especifica_nombre=" + especifica_nombre + ", especifica_det=" + especifica_det
				+ ", nombre_clasificador=" + nombre_clasificador + "]";
	}

}
