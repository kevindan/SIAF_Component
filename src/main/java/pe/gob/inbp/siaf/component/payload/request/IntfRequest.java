package pe.gob.inbp.siaf.component.payload.request;

import java.io.Serializable;
import java.util.List;

import pe.gob.inbp.siaf.component.domain.IntfDet09;

public class IntfRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String ano_eje;
	private String sec_ejec;
	private String ciclo;
	private String fase;
	private String estado;
	private String sec_ejec2;
	private String mes_eje;
	private String tipo_ope;
	private String mod_comp;
	private String tipo_proc;
	private String sec_area;
	private String cod_doc;
	private String serie_doc;
	private String num_doc;
	private String fec_doc;
	private String ps_ejec;
	private String ps_t_camb;
	private String tipo_id;
	private String ruc;
	private String origen;
	private String fte_fin;
	private String organismo;
	private String convenio;
	private String t_pago;
	private String t_recurs;
	private String t_compro;
	private String ano_cta;
	private String banco;
	private String cta_cte;
	private String tipo_giro;
	private String dcod_doc;
	private String dnum_doc;
	private String dfec_doc;
	private String dnombre;
	private String dmonto;
	private String dmonto_mn;
	private String moneda;
	private String t_cambio;
	private String ue_envio;
	private String ue_estado;
	private String intf_exp;
	private String intf_fase;
	private String intf_sec;
	private String intf_cor;
	private String intf_cer;
	private String intf_cerse;
	private List<IntfDet09> intf_det;

	public IntfRequest() {

	}

	public IntfRequest(String ano_eje, String sec_ejec, String ciclo, String fase, String estado, String sec_ejec2,
			String mes_eje, String tipo_ope, String mod_comp, String tipo_proc, String sec_area, String cod_doc,
			String serie_doc, String num_doc, String fec_doc, String ps_ejec, String ps_t_camb, String tipo_id,
			String ruc, String origen, String fte_fin, String organismo, String convenio, String t_pago,
			String t_recurs, String t_compro, String ano_cta, String banco, String cta_cte, String tipo_giro,
			String dcod_doc, String dnum_doc, String dfec_doc, String dnombre, String dmonto, String dmonto_mn,
			String moneda, String t_cambio, String ue_envio, String ue_estado, String intf_exp, String intf_fase,
			String intf_sec, String intf_cor, String intf_cer, String intf_cerse, List<IntfDet09> intf_det) {

		this.ano_eje = ano_eje;
		this.sec_ejec = sec_ejec;
		this.ciclo = ciclo;
		this.fase = fase;
		this.estado = estado;
		this.sec_ejec2 = sec_ejec2;
		this.mes_eje = mes_eje;
		this.tipo_ope = tipo_ope;
		this.mod_comp = mod_comp;
		this.tipo_proc = tipo_proc;
		this.sec_area = sec_area;
		this.cod_doc = cod_doc;
		this.serie_doc = serie_doc;
		this.num_doc = num_doc;
		this.fec_doc = fec_doc;
		this.ps_ejec = ps_ejec;
		this.ps_t_camb = ps_t_camb;
		this.tipo_id = tipo_id;
		this.ruc = ruc;
		this.origen = origen;
		this.fte_fin = fte_fin;
		this.organismo = organismo;
		this.convenio = convenio;
		this.t_pago = t_pago;
		this.t_recurs = t_recurs;
		this.t_compro = t_compro;
		this.ano_cta = ano_cta;
		this.banco = banco;
		this.cta_cte = cta_cte;
		this.tipo_giro = tipo_giro;
		this.dcod_doc = dcod_doc;
		this.dnum_doc = dnum_doc;
		this.dfec_doc = dfec_doc;
		this.dnombre = dnombre;
		this.dmonto = dmonto;
		this.dmonto_mn = dmonto_mn;
		this.moneda = moneda;
		this.t_cambio = t_cambio;
		this.ue_envio = ue_envio;
		this.ue_estado = ue_estado;
		this.intf_exp = intf_exp;
		this.intf_fase = intf_fase;
		this.intf_sec = intf_sec;
		this.intf_cor = intf_cor;
		this.intf_cer = intf_cer;
		this.intf_cerse = intf_cerse;
		this.intf_det = intf_det;
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

	public String getCiclo() {
		return ciclo;
	}

	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}

	public String getFase() {
		return fase;
	}

	public void setFase(String fase) {
		this.fase = fase;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getSec_ejec2() {
		return sec_ejec2;
	}

	public void setSec_ejec2(String sec_ejec2) {
		this.sec_ejec2 = sec_ejec2;
	}

	public String getMes_eje() {
		return mes_eje;
	}

	public void setMes_eje(String mes_eje) {
		this.mes_eje = mes_eje;
	}

	public String getTipo_ope() {
		return tipo_ope;
	}

	public void setTipo_ope(String tipo_ope) {
		this.tipo_ope = tipo_ope;
	}

	public String getMod_comp() {
		return mod_comp;
	}

	public void setMod_comp(String mod_comp) {
		this.mod_comp = mod_comp;
	}

	public String getTipo_proc() {
		return tipo_proc;
	}

	public void setTipo_proc(String tipo_proc) {
		this.tipo_proc = tipo_proc;
	}

	public String getSec_area() {
		return sec_area;
	}

	public void setSec_area(String sec_area) {
		this.sec_area = sec_area;
	}

	public String getCod_doc() {
		return cod_doc;
	}

	public void setCod_doc(String cod_doc) {
		this.cod_doc = cod_doc;
	}

	public String getSerie_doc() {
		return serie_doc;
	}

	public void setSerie_doc(String serie_doc) {
		this.serie_doc = serie_doc;
	}

	public String getNum_doc() {
		return num_doc;
	}

	public void setNum_doc(String num_doc) {
		this.num_doc = num_doc;
	}

	public String getFec_doc() {
		return fec_doc;
	}

	public void setFec_doc(String fec_doc) {
		this.fec_doc = fec_doc;
	}

	public String getPs_ejec() {
		return ps_ejec;
	}

	public void setPs_ejec(String ps_ejec) {
		this.ps_ejec = ps_ejec;
	}

	public String getPs_t_camb() {
		return ps_t_camb;
	}

	public void setPs_t_camb(String ps_t_camb) {
		this.ps_t_camb = ps_t_camb;
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

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getFte_fin() {
		return fte_fin;
	}

	public void setFte_fin(String fte_fin) {
		this.fte_fin = fte_fin;
	}

	public String getOrganismo() {
		return organismo;
	}

	public void setOrganismo(String organismo) {
		this.organismo = organismo;
	}

	public String getConvenio() {
		return convenio;
	}

	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}

	public String getT_pago() {
		return t_pago;
	}

	public void setT_pago(String t_pago) {
		this.t_pago = t_pago;
	}

	public String getT_recurs() {
		return t_recurs;
	}

	public void setT_recurs(String t_recurs) {
		this.t_recurs = t_recurs;
	}

	public String getT_compro() {
		return t_compro;
	}

	public void setT_compro(String t_compro) {
		this.t_compro = t_compro;
	}

	public String getAno_cta() {
		return ano_cta;
	}

	public void setAno_cta(String ano_cta) {
		this.ano_cta = ano_cta;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getCta_cte() {
		return cta_cte;
	}

	public void setCta_cte(String cta_cte) {
		this.cta_cte = cta_cte;
	}

	public String getTipo_giro() {
		return tipo_giro;
	}

	public void setTipo_giro(String tipo_giro) {
		this.tipo_giro = tipo_giro;
	}

	public String getDcod_doc() {
		return dcod_doc;
	}

	public void setDcod_doc(String dcod_doc) {
		this.dcod_doc = dcod_doc;
	}

	public String getDnum_doc() {
		return dnum_doc;
	}

	public void setDnum_doc(String dnum_doc) {
		this.dnum_doc = dnum_doc;
	}

	public String getDfec_doc() {
		return dfec_doc;
	}

	public void setDfec_doc(String dfec_doc) {
		this.dfec_doc = dfec_doc;
	}

	public String getDnombre() {
		return dnombre;
	}

	public void setDnombre(String dnombre) {
		this.dnombre = dnombre;
	}

	public String getDmonto() {
		return dmonto;
	}

	public void setDmonto(String dmonto) {
		this.dmonto = dmonto;
	}

	public String getDmonto_mn() {
		return dmonto_mn;
	}

	public void setDmonto_mn(String dmonto_mn) {
		this.dmonto_mn = dmonto_mn;
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

	public String getIntf_exp() {
		return intf_exp;
	}

	public void setIntf_exp(String intf_exp) {
		this.intf_exp = intf_exp;
	}

	public String getIntf_fase() {
		return intf_fase;
	}

	public void setIntf_fase(String intf_fase) {
		this.intf_fase = intf_fase;
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

	public String getIntf_cer() {
		return intf_cer;
	}

	public void setIntf_cer(String intf_cer) {
		this.intf_cer = intf_cer;
	}

	public String getIntf_cerse() {
		return intf_cerse;
	}

	public void setIntf_cerse(String intf_cerse) {
		this.intf_cerse = intf_cerse;
	}

	public List<IntfDet09> getIntf_det() {
		return intf_det;
	}

	public void setIntf_det(List<IntfDet09> intf_det) {
		this.intf_det = intf_det;
	}

	@Override
	public String toString() {
		return "IntfRequest [ano_eje=" + ano_eje + ", sec_ejec=" + sec_ejec + ", ciclo=" + ciclo + ", fase=" + fase
				+ ", estado=" + estado + ", sec_ejec2=" + sec_ejec2 + ", mes_eje=" + mes_eje + ", tipo_ope=" + tipo_ope
				+ ", mod_comp=" + mod_comp + ", tipo_proc=" + tipo_proc + ", sec_area=" + sec_area + ", cod_doc="
				+ cod_doc + ", serie_doc=" + serie_doc + ", num_doc=" + num_doc + ", fec_doc=" + fec_doc + ", ps_ejec="
				+ ps_ejec + ", ps_t_camb=" + ps_t_camb + ", tipo_id=" + tipo_id + ", ruc=" + ruc + ", origen=" + origen
				+ ", fte_fin=" + fte_fin + ", organismo=" + organismo + ", convenio=" + convenio + ", t_pago=" + t_pago
				+ ", t_recurs=" + t_recurs + ", t_compro=" + t_compro + ", ano_cta=" + ano_cta + ", banco=" + banco
				+ ", cta_cte=" + cta_cte + ", tipo_giro=" + tipo_giro + ", dcod_doc=" + dcod_doc + ", dnum_doc="
				+ dnum_doc + ", dfec_doc=" + dfec_doc + ", dnombre=" + dnombre + ", dmonto=" + dmonto + ", dmonto_mn="
				+ dmonto_mn + ", moneda=" + moneda + ", t_cambio=" + t_cambio + ", ue_envio=" + ue_envio
				+ ", ue_estado=" + ue_estado + ", intf_exp=" + intf_exp + ", intf_fase=" + intf_fase + ", intf_sec="
				+ intf_sec + ", intf_cor=" + intf_cor + ", intf_cer=" + intf_cer + ", intf_cerse=" + intf_cerse
				+ ", intf_det=" + intf_det + "]";
	}

}
