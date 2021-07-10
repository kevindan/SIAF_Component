package pe.gob.inbp.siaf.component.domain;

import java.io.Serializable;

public class MetaCadena implements Serializable {

	private static final long serialVersionUID = 1L;
	private String sec_func;
	private String programa_ppto;
	private String programa_ppto_nombre;
	private String prod_proy;
	private String prod_proy_nombre;
	private String act_ai_obr;
	private String act_ai_obr_nombre;

	public MetaCadena() {

	}

	public MetaCadena(String sec_func, String programa_ppto, String programa_ppto_nombre, String prod_proy,
			String prod_proy_nombre, String act_ai_obr, String act_ai_obr_nombre) {

		this.sec_func = sec_func;
		this.programa_ppto = programa_ppto;
		this.programa_ppto_nombre = programa_ppto_nombre;
		this.prod_proy = prod_proy;
		this.prod_proy_nombre = prod_proy_nombre;
		this.act_ai_obr = act_ai_obr;
		this.act_ai_obr_nombre = act_ai_obr_nombre;
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

	@Override
	public String toString() {
		return "MetaCadena [sec_func=" + sec_func + ", programa_ppto=" + programa_ppto + ", programa_ppto_nombre="
				+ programa_ppto_nombre + ", prod_proy=" + prod_proy + ", prod_proy_nombre=" + prod_proy_nombre
				+ ", act_ai_obr=" + act_ai_obr + ", act_ai_obr_nombre=" + act_ai_obr_nombre + "]";
	}

}
