package pe.gob.inbp.siaf.component.domain;

public class MaestroDocumento {
	private String cod_doc;
	private String nombre;
	private String detalle;
	private String ind_serie;
	private String viaja_banco;
	private String abreviatura;
	private String ind_proveedor;

	public MaestroDocumento() {

	}

	public MaestroDocumento(String cod_doc, String nombre, String detalle, String ind_serie, String viaja_banco,
			String abreviatura, String ind_proveedor) {

		this.cod_doc = cod_doc;
		this.nombre = nombre;
		this.detalle = detalle;
		this.ind_serie = ind_serie;
		this.viaja_banco = viaja_banco;
		this.abreviatura = abreviatura;
		this.ind_proveedor = ind_proveedor;
	}

	public String getCod_doc() {
		return cod_doc;
	}

	public void setCod_doc(String cod_doc) {
		this.cod_doc = cod_doc;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getInd_serie() {
		return ind_serie;
	}

	public void setInd_serie(String ind_serie) {
		this.ind_serie = ind_serie;
	}

	public String getViaja_banco() {
		return viaja_banco;
	}

	public void setViaja_banco(String viaja_banco) {
		this.viaja_banco = viaja_banco;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getInd_proveedor() {
		return ind_proveedor;
	}

	public void setInd_proveedor(String ind_proveedor) {
		this.ind_proveedor = ind_proveedor;
	}

	@Override
	public String toString() {
		return "MaestroDocumento [cod_doc=" + cod_doc + ", nombre=" + nombre + ", detalle=" + detalle + ", ind_serie="
				+ ind_serie + ", viaja_banco=" + viaja_banco + ", abreviatura=" + abreviatura + ", ind_proveedor="
				+ ind_proveedor + "]";
	}

}
