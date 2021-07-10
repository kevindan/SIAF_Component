package pe.gob.inbp.siaf.component.payload.request;

import java.io.Serializable;
import java.util.List;

import pe.gob.inbp.siaf.component.domain.CertDet;

public class CertRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cod_doc;
	private String num_doc;
	private String ano_eje;
	private String sec_ejec;
	private String estado;
	private String es_comp;
	private String tipo_ope;
	private String gloza;
	private String fec_doc;
	private String fte_fin;
	private String moneda;
	private String t_cambio;
	private String ue_envio;
	private String ue_estado;
	private String disp_legal;
	private String tipo_certi;
	private String tipo_id;
	private String ruc;
	private String siaf_cer;
	private String intf_cer;
	private String intf_sec;
	private String intf_cor;
	private List<CertDet> cert_det;

	public CertRequest() {

	}

	public CertRequest(String cod_doc, String num_doc, String ano_eje, String sec_ejec, String estado, String es_comp,
			String tipo_ope, String gloza, String fec_doc, String fte_fin, String moneda, String t_cambio,
			String ue_envio, String ue_estado, String disp_legal, String tipo_certi, String tipo_id, String ruc,
			String siaf_cer, String intf_cer, String intf_sec, String intf_cor, List<CertDet> cert_det) {

		this.cod_doc = cod_doc;
		this.num_doc = num_doc;
		this.ano_eje = ano_eje;
		this.sec_ejec = sec_ejec;
		this.estado = estado;
		this.es_comp = es_comp;
		this.tipo_ope = tipo_ope;
		this.gloza = gloza;
		this.fec_doc = fec_doc;
		this.fte_fin = fte_fin;
		this.moneda = moneda;
		this.t_cambio = t_cambio;
		this.ue_envio = ue_envio;
		this.ue_estado = ue_estado;
		this.disp_legal = disp_legal;
		this.tipo_certi = tipo_certi;
		this.tipo_id = tipo_id;
		this.ruc = ruc;
		this.siaf_cer = siaf_cer;
		this.intf_cer = intf_cer;
		this.intf_sec = intf_sec;
		this.intf_cor = intf_cor;
		this.cert_det = cert_det;
	}

	public String getCod_doc() {
		return cod_doc;
	}

	public void setCod_doc(String cod_doc) {
		this.cod_doc = cod_doc;
	}

	public String getNum_doc() {
		return num_doc;
	}

	public void setNum_doc(String num_doc) {
		this.num_doc = num_doc;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEs_comp() {
		return es_comp;
	}

	public void setEs_comp(String es_comp) {
		this.es_comp = es_comp;
	}

	public String getTipo_ope() {
		return tipo_ope;
	}

	public void setTipo_ope(String tipo_ope) {
		this.tipo_ope = tipo_ope;
	}

	public String getGloza() {
		return gloza;
	}

	public void setGloza(String gloza) {
		this.gloza = gloza;
	}

	public String getFec_doc() {
		return fec_doc;
	}

	public void setFec_doc(String fec_doc) {
		this.fec_doc = fec_doc;
	}

	public String getFte_fin() {
		return fte_fin;
	}

	public void setFte_fin(String fte_fin) {
		this.fte_fin = fte_fin;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getT_cambio() {
		return t_cambio;
	}

	public void setT_cambio(String t_cambio) {
		this.t_cambio = t_cambio;
	}

	public String getUe_envio() {
		return ue_envio;
	}

	public void setUe_envio(String ue_envio) {
		this.ue_envio = ue_envio;
	}

	public String getUe_estado() {
		return ue_estado;
	}

	public void setUe_estado(String ue_estado) {
		this.ue_estado = ue_estado;
	}

	public String getDisp_legal() {
		return disp_legal;
	}

	public void setDisp_legal(String disp_legal) {
		this.disp_legal = disp_legal;
	}

	public String getTipo_certi() {
		return tipo_certi;
	}

	public void setTipo_certi(String tipo_certi) {
		this.tipo_certi = tipo_certi;
	}

	public String getTipo_id() {
		return tipo_id;
	}

	public void setTipo_id(String tipo_id) {
		this.tipo_id = tipo_id;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getSiaf_cer() {
		return siaf_cer;
	}

	public void setSiaf_cer(String siaf_cer) {
		this.siaf_cer = siaf_cer;
	}

	public String getIntf_cer() {
		return intf_cer;
	}

	public void setIntf_cer(String intf_cer) {
		this.intf_cer = intf_cer;
	}

	public String getIntf_sec() {
		return intf_sec;
	}

	public void setIntf_sec(String intf_sec) {
		this.intf_sec = intf_sec;
	}

	public String getIntf_cor() {
		return intf_cor;
	}

	public void setIntf_cor(String intf_cor) {
		this.intf_cor = intf_cor;
	}

	public List<CertDet> getCert_det() {
		return cert_det;
	}

	public void setCert_det(List<CertDet> cert_det) {
		this.cert_det = cert_det;
	}

	@Override
	public String toString() {
		return "CertRequest [cod_doc=" + cod_doc + ", num_doc=" + num_doc + ", ano_eje=" + ano_eje + ", sec_ejec="
				+ sec_ejec + ", estado=" + estado + ", es_comp=" + es_comp + ", tipo_ope=" + tipo_ope + ", gloza="
				+ gloza + ", fec_doc=" + fec_doc + ", fte_fin=" + fte_fin + ", moneda=" + moneda + ", t_cambio="
				+ t_cambio + ", ue_envio=" + ue_envio + ", ue_estado=" + ue_estado + ", disp_legal=" + disp_legal
				+ ", tipo_certi=" + tipo_certi + ", tipo_id=" + tipo_id + ", ruc=" + ruc + ", siaf_cer=" + siaf_cer
				+ ", intf_cer=" + intf_cer + ", intf_sec=" + intf_sec + ", intf_cor=" + intf_cor + ", cert_det="
				+ cert_det + "]";
	}
		

}
