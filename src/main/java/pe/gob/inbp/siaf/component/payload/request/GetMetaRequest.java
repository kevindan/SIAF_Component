package pe.gob.inbp.siaf.component.payload.request;

import java.io.Serializable;

public class GetMetaRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String anio; 
	private String cod_unid_ejec; 
	private String codExcluidos;
	
	public GetMetaRequest() {
		
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public String getCod_unid_ejec() {
		return cod_unid_ejec;
	}

	public void setCod_unid_ejec(String cod_unid_ejec) {
		this.cod_unid_ejec = cod_unid_ejec;
	}

	public String getCodExcluidos() {
		return codExcluidos;
	}

	public void setCodExcluidos(String codExcluidos) {
		this.codExcluidos = codExcluidos;
	}

	@Override
	public String toString() {
		return "getMetaRequest [anio=" + anio + ", cod_unid_ejec=" + cod_unid_ejec + ", codExcluidos=" + codExcluidos
				+ "]";
	}	

}
