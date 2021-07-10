package pe.gob.inbp.siaf.component.domain;

import java.io.Serializable;

public class ClasifPcaReq implements Serializable {

	private static final long serialVersionUID = 1L;

	private String ano_eje;
	private String fuente_finac;
	private String sec_ejec;
	private String id_clasifi;

	public ClasifPcaReq() {

	}

	public ClasifPcaReq(String ano_eje, String fuente_finac, String sec_ejec, String id_clasifi) {

		this.ano_eje = ano_eje;
		this.fuente_finac = fuente_finac;
		this.sec_ejec = sec_ejec;
		this.id_clasifi = id_clasifi;
	}

	public String getAno_eje() {
		return ano_eje;
	}

	public void setAno_eje(String ano_eje) {
		this.ano_eje = ano_eje;
	}

	public String getFuente_finac() {
		return fuente_finac;
	}

	public void setFuente_finac(String fuente_finac) {
		this.fuente_finac = fuente_finac;
	}

	public String getSec_ejec() {
		return sec_ejec;
	}

	public void setSec_ejec(String sec_ejec) {
		this.sec_ejec = sec_ejec;
	}

	public String getId_clasifi() {
		return id_clasifi;
	}

	public void setId_clasifi(String id_clasifi) {
		this.id_clasifi = id_clasifi;
	}

	@Override
	public String toString() {
		return "ClasifPcaReq [ano_eje=" + ano_eje + ", fuente_finac=" + fuente_finac + ", sec_ejec=" + sec_ejec
				+ ", id_clasifi=" + id_clasifi + "]";
	}

}
