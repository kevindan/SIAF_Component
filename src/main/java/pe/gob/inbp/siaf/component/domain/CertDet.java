package pe.gob.inbp.siaf.component.domain;

import java.io.Serializable;

public class CertDet implements Serializable {

	private static final long serialVersionUID = 1L;

	private String clasifniv1;
	private String clasifniv2;
	private String clasifniv3;
	private String clasifniv4;
	private String clasifniv5;
	private String clasifniv6;
	private String sec_func;
	private String monto;
	private String monto_mn;
	private String id_clasifi;

	public CertDet() {

	}

	public CertDet(String clasifniv1, String clasifniv2, String clasifniv3, String clasifniv4, String clasifniv5,
			String clasifniv6, String sec_func, String monto, String monto_mn, String id_clasifi) {

		this.clasifniv1 = clasifniv1;
		this.clasifniv2 = clasifniv2;
		this.clasifniv3 = clasifniv3;
		this.clasifniv4 = clasifniv4;
		this.clasifniv5 = clasifniv5;
		this.clasifniv6 = clasifniv6;
		this.sec_func = sec_func;
		this.monto = monto;
		this.monto_mn = monto_mn;
		this.id_clasifi = id_clasifi;
	}

	public String getClasifniv1() {
		return clasifniv1;
	}

	public void setClasifniv1(String clasifniv1) {
		this.clasifniv1 = clasifniv1;
	}

	public String getClasifniv2() {
		return clasifniv2;
	}

	public void setClasifniv2(String clasifniv2) {
		this.clasifniv2 = clasifniv2;
	}

	public String getClasifniv3() {
		return clasifniv3;
	}

	public void setClasifniv3(String clasifniv3) {
		this.clasifniv3 = clasifniv3;
	}

	public String getClasifniv4() {
		return clasifniv4;
	}

	public void setClasifniv4(String clasifniv4) {
		this.clasifniv4 = clasifniv4;
	}

	public String getClasifniv5() {
		return clasifniv5;
	}

	public void setClasifniv5(String clasifniv5) {
		this.clasifniv5 = clasifniv5;
	}

	public String getClasifniv6() {
		return clasifniv6;
	}

	public void setClasifniv6(String clasifniv6) {
		this.clasifniv6 = clasifniv6;
	}

	public String getSec_func() {
		return sec_func;
	}

	public void setSec_func(String sec_func) {
		this.sec_func = sec_func;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public String getMonto_mn() {
		return monto_mn;
	}

	public void setMonto_mn(String monto_mn) {
		this.monto_mn = monto_mn;
	}

	public String getId_clasifi() {
		return id_clasifi;
	}

	public void setId_clasifi(String id_clasifi) {
		this.id_clasifi = id_clasifi;
	}

	@Override
	public String toString() {
		return "CertDet [clasifniv1=" + clasifniv1 + ", clasifniv2=" + clasifniv2 + ", clasifniv3=" + clasifniv3
				+ ", clasifniv4=" + clasifniv4 + ", clasifniv5=" + clasifniv5 + ", clasifniv6=" + clasifniv6
				+ ", sec_func=" + sec_func + ", monto=" + monto + ", monto_mn=" + monto_mn + ", id_clasifi="
				+ id_clasifi + "]";
	}

}
