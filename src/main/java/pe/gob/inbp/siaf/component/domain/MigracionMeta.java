package pe.gob.inbp.siaf.component.domain;

import java.io.Serializable;

public class MigracionMeta implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id_meta;
	private String act_ai_obr;
	private String act_ai_obr_nombre;
	private String ano_eje;
	private String division_func;
	private String division_func_nombre;
	private String finalidad;
	private String funcion;
	private String funcion_nombre;
	private String grupo_func;
	private String grupo_func_nombre;
	private String meta;
	private String nombre_meta;
	private String prod_proy;
	private String prod_proy_nombre;
	private String programa_ppto;
	private String programa_ppto_nombre;
	private String sec_ejec;
	private String sec_func;
	private String unidad_medida;
	private String unidad_medida_nombre;
	private Integer id_periodo;

	public MigracionMeta() {

	}

	public MigracionMeta(Long id_meta, String act_ai_obr, String act_ai_obr_nombre, String ano_eje,
			String division_func, String division_func_nombre, String finalidad, String funcion, String funcion_nombre,
			String grupo_func, String grupo_func_nombre, String meta, String nombre_meta, String prod_proy,
			String prod_proy_nombre, String programa_ppto, String programa_ppto_nombre, String sec_ejec,
			String sec_func, String unidad_medida, String unidad_medida_nombre, Integer id_periodo) {

		this.id_meta = id_meta;
		this.act_ai_obr = act_ai_obr;
		this.act_ai_obr_nombre = act_ai_obr_nombre;
		this.ano_eje = ano_eje;
		this.division_func = division_func;
		this.division_func_nombre = division_func_nombre;
		this.finalidad = finalidad;
		this.funcion = funcion;
		this.funcion_nombre = funcion_nombre;
		this.grupo_func = grupo_func;
		this.grupo_func_nombre = grupo_func_nombre;
		this.meta = meta;
		this.nombre_meta = nombre_meta;
		this.prod_proy = prod_proy;
		this.prod_proy_nombre = prod_proy_nombre;
		this.programa_ppto = programa_ppto;
		this.programa_ppto_nombre = programa_ppto_nombre;
		this.sec_ejec = sec_ejec;
		this.sec_func = sec_func;
		this.unidad_medida = unidad_medida;
		this.unidad_medida_nombre = unidad_medida_nombre;
		this.id_periodo = id_periodo;
	}

	public Long getId_meta() {
		return id_meta;
	}

	public void setId_meta(Long id_meta) {
		this.id_meta = id_meta;
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

	public String getAno_eje() {
		return ano_eje;
	}

	public void setAno_eje(String ano_eje) {
		this.ano_eje = ano_eje;
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

	public String getFinalidad() {
		return finalidad;
	}

	public void setFinalidad(String finalidad) {
		this.finalidad = finalidad;
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

	public Integer getId_periodo() {
		return id_periodo;
	}

	public void setId_periodo(Integer id_periodo) {
		this.id_periodo = id_periodo;
	}

	@Override
	public String toString() {
		return "MigracionMeta [id_meta=" + id_meta + ", act_ai_obr=" + act_ai_obr + ", act_ai_obr_nombre="
				+ act_ai_obr_nombre + ", ano_eje=" + ano_eje + ", division_func=" + division_func
				+ ", division_func_nombre=" + division_func_nombre + ", finalidad=" + finalidad + ", funcion=" + funcion
				+ ", funcion_nombre=" + funcion_nombre + ", grupo_func=" + grupo_func + ", grupo_func_nombre="
				+ grupo_func_nombre + ", meta=" + meta + ", nombre_meta=" + nombre_meta + ", prod_proy=" + prod_proy
				+ ", prod_proy_nombre=" + prod_proy_nombre + ", programa_ppto=" + programa_ppto
				+ ", programa_ppto_nombre=" + programa_ppto_nombre + ", sec_ejec=" + sec_ejec + ", sec_func=" + sec_func
				+ ", unidad_medida=" + unidad_medida + ", unidad_medida_nombre=" + unidad_medida_nombre
				+ ", id_periodo=" + id_periodo + "]";
	}

}
