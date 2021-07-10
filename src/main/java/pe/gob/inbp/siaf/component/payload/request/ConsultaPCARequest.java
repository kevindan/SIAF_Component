package pe.gob.inbp.siaf.component.payload.request;

import java.io.Serializable;
import java.util.List;

import pe.gob.inbp.siaf.component.domain.ClasifPcaReq;

public class ConsultaPCARequest implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<ClasifPcaReq> clasificadores;

	public ConsultaPCARequest() {

	}

	public ConsultaPCARequest(List<ClasifPcaReq> clasificadores) {

		this.clasificadores = clasificadores;
	}

	public List<ClasifPcaReq> getClasificadores() {
		return clasificadores;
	}

	public void setClasificadores(List<ClasifPcaReq> clasificadores) {
		this.clasificadores = clasificadores;
	}

}
