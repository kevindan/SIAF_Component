package pe.gob.inbp.siaf.component.service;

import java.util.List;

import pe.gob.inbp.siaf.component.domain.MaestroDocumento;
import pe.gob.inbp.siaf.component.domain.TipoOperacion;
import pe.gob.inbp.siaf.component.payload.GenericResponse;

public interface MaestrosService {
	GenericResponse getProgramaPpto(String ano_eje);
	List<MaestroDocumento> getMaestroDocumentoAll();
	GenericResponse getFuenteFinancAll(String ano_eje);
	GenericResponse getBuscarFuenteFinanciamiento(String anio, String codigo_fuente);
	GenericResponse getTipoOperacionComp(String anio);

}
