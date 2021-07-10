package pe.gob.inbp.siaf.component.data;

import java.util.List;

import pe.gob.inbp.siaf.component.domain.Fuente_financ;
import pe.gob.inbp.siaf.component.domain.MaestroDocumento;
import pe.gob.inbp.siaf.component.domain.ProgramaPpto;
import pe.gob.inbp.siaf.component.domain.TipoOperacion;

public interface MaestrosDao {	
	List<ProgramaPpto> getProgramaPptoAll(String ano_eje);
	List<MaestroDocumento> getMaestroDocumentoAll();
	List<Fuente_financ> getFuenteFinancAll(String ano_eje);
	Fuente_financ getBuscarFuenteFinanciamiento(String anio, String codigo_fuente);
	List<TipoOperacion> getTipoOperacionComp(String anio);

}
