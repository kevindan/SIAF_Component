package pe.gob.inbp.siaf.component.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.inbp.siaf.component.data.MaestrosDao;
import pe.gob.inbp.siaf.component.domain.Fuente_financ;
import pe.gob.inbp.siaf.component.domain.MaestroDocumento;
import pe.gob.inbp.siaf.component.domain.ProgramaPpto;
import pe.gob.inbp.siaf.component.domain.TipoOperacion;
import pe.gob.inbp.siaf.component.payload.GenericResponse;
import pe.gob.inbp.siaf.component.service.MaestrosService;

@Service
public class MaestrosServiceImpl implements MaestrosService {

	@Autowired
	private MaestrosDao maestrosDao;
	
	@Override
	public GenericResponse getProgramaPpto(String ano_eje) {
		List<ProgramaPpto> lstProgPpto = new ArrayList<ProgramaPpto>();
		GenericResponse response = new GenericResponse();
		try {
			lstProgPpto = maestrosDao.getProgramaPptoAll(ano_eje);
			response.setCode("0000");
			response.setMessage("Consulta Exitosa");
			response.setData(lstProgPpto);
		} catch (Exception e) {
			response.setCode("9000");
			response.setMessage("Error en la consulta");
		}
		return response;
	}

	@Override
	public List<MaestroDocumento> getMaestroDocumentoAll() {		
		return maestrosDao.getMaestroDocumentoAll();
	}

	@Override
	public GenericResponse getFuenteFinancAll(String ano_eje) {
		List<Fuente_financ> lstFuenteFinanc = new ArrayList<Fuente_financ>();
		GenericResponse response = new GenericResponse();
		try {
			lstFuenteFinanc= maestrosDao.getFuenteFinancAll(ano_eje);
			response.setCode("0000");
			response.setMessage("Consulta Exitosa");
			response.setData(lstFuenteFinanc);
		} catch (Exception e) {
			response.setCode("9000");
			response.setMessage("Error en la consulta");
		}
		return response;
	}

	@Override
	public GenericResponse getBuscarFuenteFinanciamiento(String anio, String codigo_fuente) {
		Fuente_financ fuenteFinanc = new Fuente_financ();
		GenericResponse response = new GenericResponse();
		try {
			fuenteFinanc= maestrosDao.getBuscarFuenteFinanciamiento(anio, codigo_fuente);
			response.setCode("0000");
			response.setMessage("Consulta Exitosa");
			response.setData(fuenteFinanc);
		} catch (Exception e) {
			response.setCode("9000");
			response.setMessage("Error en la consulta");
		}
		return response;
	}

	@Override
	public GenericResponse getTipoOperacionComp(String anio) {
		List<TipoOperacion> lstTipoOperacion = new ArrayList<TipoOperacion>();
		GenericResponse response = new GenericResponse();
		try {
			lstTipoOperacion = maestrosDao.getTipoOperacionComp(anio);
			response.setCode("0000");
			response.setMessage("Consulta Exitosa");
			response.setData(lstTipoOperacion);
		} catch (Exception e) {
			response.setCode("9000");
			response.setMessage("Error en la consulta");
		}
		return response;
	}


}
