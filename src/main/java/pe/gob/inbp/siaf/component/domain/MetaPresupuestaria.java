package pe.gob.inbp.siaf.component.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MetaPresupuestaria implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	private String id_meta_presupuestal;
	@JsonIgnore
	private String id_centro_costo;
	@JsonIgnore
	private String centro_costo_nombre;
	private String ano_eje;
	private String sec_ejec;
	private String sec_func;
	private String programa_ppto;
	private String programa_ppto_nombre;
	private String funcion;
	private String funcion_nombre;
	private String division_func;
	private String division_func_nombre;
	private String grupo_func;
	private String grupo_func_nombre;
	private String act_ai_obr;
	private String act_ai_obr_nombre;
	private String prod_proy;
	private String prod_proy_nombre;
	private String meta;
	private String nombre_meta;
	private String finalidad;
	private String unidad_medida;
	private String unidad_medida_nombre;

	public MetaPresupuestaria() {

	}

	public MetaPresupuestaria(String id_meta_presupuestal, String id_centro_costo, String centro_costo_nombre,
			String ano_eje, String sec_ejec, String sec_func, String programa_ppto, String programa_ppto_nombre,
			String funcion, String funcion_nombre, String division_func, String division_func_nombre, String grupo_func,
			String grupo_func_nombre, String act_ai_obr, String act_ai_obr_nombre, String prod_proy,
			String prod_proy_nombre, String meta, String nombre_meta, String finalidad, String unidad_medida,
			String unidad_medida_nombre) {

		this.id_meta_presupuestal = id_meta_presupuestal;
		this.id_centro_costo = id_centro_costo;
		this.centro_costo_nombre = centro_costo_nombre;
		this.ano_eje = ano_eje;
		this.sec_ejec = sec_ejec;
		this.sec_func = sec_func;
		this.programa_ppto = programa_ppto;
		this.programa_ppto_nombre = programa_ppto_nombre;
		this.funcion = funcion;
		this.funcion_nombre = funcion_nombre;
		this.division_func = division_func;
		this.division_func_nombre = division_func_nombre;
		this.grupo_func = grupo_func;
		this.grupo_func_nombre = grupo_func_nombre;
		this.act_ai_obr = act_ai_obr;
		this.act_ai_obr_nombre = act_ai_obr_nombre;
		this.prod_proy = prod_proy;
		this.prod_proy_nombre = prod_proy_nombre;
		this.meta = meta;
		this.nombre_meta = nombre_meta;
		this.finalidad = finalidad;
		this.unidad_medida = unidad_medida;
		this.unidad_medida_nombre = unidad_medida_nombre;
	}

	public String getId_meta_presupuestal() {
		return id_meta_presupuestal;
	}

	public void setId_meta_presupuestal(String id_meta_presupuestal) {
		this.id_meta_presupuestal = id_meta_presupuestal;
	}

	public String getId_centro_costo() {
		return id_centro_costo;
	}

	public void setId_centro_costo(String id_centro_costo) {
		this.id_centro_costo = id_centro_costo;
	}

	public String getCentro_costo_nombre() {
		return centro_costo_nombre;
	}

	public void setCentro_costo_nombre(String centro_costo_nombre) {
		this.centro_costo_nombre = centro_costo_nombre;
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

	public String getPrograma_ppto() {
		return programa_ppto;
	}

	public void setPrograma_ppto(String programa_ppto) {
		this.programa_ppto = programa_ppto;
	}

	public String getPrograma_ppto_nombre() {
		return programa_ppto_nombre;
	}

	public void setPrograma_ppto_nombre(String programa_ppto_nombre) {
		this.programa_ppto_nombre = programa_ppto_nombre;
	}

	public String getFuncion() {
		return funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	public String getFuncion_nombre() {
		return funcion_nombre;
	}

	public void setFuncion_nombre(String funcion_nombre) {
		this.funcion_nombre = funcion_nombre;
	}

	public String getDivision_func() {
		return division_func;
	}

	public void setDivision_func(String division_func) {
		this.division_func = division_func;
	}

	public String getDivision_func_nombre() {
		return division_func_nombre;
	}

	public void setDivision_func_nombre(String division_func_nombre) {
		this.division_func_nombre = division_func_nombre;
	}

	public String getGrupo_func() {
		return grupo_func;
	}

	public void setGrupo_func(String grupo_func) {
		this.grupo_func = grupo_func;
	}

	public String getGrupo_func_nombre() {
		return grupo_func_nombre;
	}

	public void setGrupo_func_nombre(String grupo_func_nombre) {
		this.grupo_func_nombre = grupo_func_nombre;
	}

	public String getAct_ai_obr() {
		return act_ai_obr;
	}

	public void setAct_ai_obr(String act_ai_obr) {
		this.act_ai_obr = act_ai_obr;
	}

	public String getAct_ai_obr_nombre() {
		return act_ai_obr_nombre;
	}

	public void setAct_ai_obr_nombre(String act_ai_obr_nombre) {
		this.act_ai_obr_nombre = act_ai_obr_nombre;
	}

	public String getProd_proy() {
		return prod_proy;
	}

	public void setProd_proy(String prod_proy) {
		this.prod_proy = prod_proy;
	}

	public String getProd_proy_nombre() {
		return prod_proy_nombre;
	}

	public void setProd_proy_nombre(String prod_proy_nombre) {
		this.prod_proy_nombre = prod_proy_nombre;
	}

	public String getMeta() {
		return meta;
	}

	public void setMeta(String meta) {
		this.meta = meta;
	}

	public String getNombre_meta() {
		return nombre_meta;
	}

	public void setNombre_meta(String nombre_meta) {
		this.nombre_meta = nombre_meta;
	}

	public String getFinalidad() {
		return finalidad;
	}

	public void setFinalidad(String finalidad) {
		this.finalidad = finalidad;
	}

	public String getUnidad_medida() {
		return unidad_medida;
	}

	public void setUnidad_medida(String unidad_medida) {
		this.unidad_medida = unidad_medida;
	}

	public String getUnidad_medida_nombre() {
		return unidad_medida_nombre;
	}

	public void setUnidad_medida_nombre(String unidad_medida_nombre) {
		this.unidad_medida_nombre = unidad_medida_nombre;
	}

	@Override
	public String toString() {
		return "MetaPresupuestaria [id_meta_presupuestal=" + id_meta_presupuestal + ", id_centro_costo="
				+ id_centro_costo + ", centro_costo_nombre=" + centro_costo_nombre + ", ano_eje=" + ano_eje
				+ ", sec_ejec=" + sec_ejec + ", sec_func=" + sec_func + ", programa_ppto=" + programa_ppto
				+ ", programa_ppto_nombre=" + programa_ppto_nombre + ", funcion=" + funcion + ", funcion_nombre="
				+ funcion_nombre + ", division_func=" + division_func + ", division_func_nombre=" + division_func_nombre
				+ ", grupo_func=" + grupo_func + ", grupo_func_nombre=" + grupo_func_nombre + ", act_ai_obr="
				+ act_ai_obr + ", act_ai_obr_nombre=" + act_ai_obr_nombre + ", prod_proy=" + prod_proy
				+ ", prod_proy_nombre=" + prod_proy_nombre + ", meta=" + meta + ", nombre_meta=" + nombre_meta
				+ ", finalidad=" + finalidad + ", unidad_medida=" + unidad_medida + ", unidad_medida_nombre="
				+ unidad_medida_nombre + "]";
	}

}
