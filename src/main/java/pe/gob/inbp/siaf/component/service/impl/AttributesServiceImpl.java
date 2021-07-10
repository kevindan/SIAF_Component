package pe.gob.inbp.siaf.component.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import pe.gob.inbp.siaf.component.data.AttributesDao;
import pe.gob.inbp.siaf.component.domain.CertCabConsulta;
import pe.gob.inbp.siaf.component.domain.CertCompAnual;
import pe.gob.inbp.siaf.component.domain.CertDet;
import pe.gob.inbp.siaf.component.domain.CertificadoEstado;
import pe.gob.inbp.siaf.component.domain.ClasifPcaResp;
import pe.gob.inbp.siaf.component.domain.ClasifSaldoPimReq;
import pe.gob.inbp.siaf.component.domain.ClasifSaldoPimResp;
import pe.gob.inbp.siaf.component.domain.ClasificadorCodigo;
import pe.gob.inbp.siaf.component.domain.ClasificadorMeta;
import pe.gob.inbp.siaf.component.domain.ClasificadorMetaCarga;
import pe.gob.inbp.siaf.component.domain.ClasificadorMontoConsolidado;
import pe.gob.inbp.siaf.component.domain.ClasificadorMontoConsolidadoPim;
import pe.gob.inbp.siaf.component.domain.ExpedienteFase;
import pe.gob.inbp.siaf.component.domain.MetaCadena;
import pe.gob.inbp.siaf.component.domain.MetaPresupuestaria;
import pe.gob.inbp.siaf.component.domain.NotaModificatoria;
import pe.gob.inbp.siaf.component.domain.Tabla_siaf;
import pe.gob.inbp.siaf.component.payload.GenericResponse;
import pe.gob.inbp.siaf.component.payload.request.CertRequest;
import pe.gob.inbp.siaf.component.payload.request.ConsultaPCARequest;
import pe.gob.inbp.siaf.component.payload.request.GetMetaRequest;
import pe.gob.inbp.siaf.component.payload.request.IntfRequest;
import pe.gob.inbp.siaf.component.service.AttributesService;

@Service
public class AttributesServiceImpl implements AttributesService {
	@Value("${siaf.data}")
	private String folderSiafData;

	@Value("${siaf.data.mirror}")
	private String folderSiafDataBk;

	@Autowired
	private AttributesDao attributesDao;

	@Override
	public List<ExpedienteFase> getConsultaExpedienteSiaf(String anio, String cod_siaf, String cod_unid_ejec) {
		return attributesDao.getConsultaExpedienteSiaf(anio, cod_siaf, cod_unid_ejec);
	}

	@Override
	public List<MetaPresupuestaria> getMetaPresupuestaria(String anio, String cod_unid_ejec) {
		return attributesDao.getMetaPresupuestaria(anio, cod_unid_ejec);
	}

	@Override
	public List<Tabla_siaf> getTablasSiaf() {
		return attributesDao.getTablasSiaf();
	}

	@Override
	public List<Tabla_siaf> migrarTablasSiaf() {
		List<Tabla_siaf> lstTablasPrev = new ArrayList<Tabla_siaf>();
		List<Tabla_siaf> lstTablasResult = new ArrayList<Tabla_siaf>();

		lstTablasPrev = this.getTablasSiaf();
		if (!lstTablasPrev.isEmpty()) {
			for (int i = 0; i < lstTablasPrev.size(); i++) {
				Tabla_siaf tabla = new Tabla_siaf();
				tabla.setNombre_tabla(lstTablasPrev.get(i).getNombre_tabla());
				File fileOrigen = new File(folderSiafData + lstTablasPrev.get(i).getNombre_tabla() + ".dbf");
				File fileDestino = new File(folderSiafDataBk + lstTablasPrev.get(i).getNombre_tabla() + ".dbf");
				try {
					InputStream in = new FileInputStream(fileOrigen);
					OutputStream out = new FileOutputStream(fileDestino);

					byte[] buf = new byte[1024];
					int len;
					while ((len = in.read(buf)) > 0) {
						out.write(buf, 0, len);
					}
					in.close();
					out.close();
					tabla.setStatus("0000");

				} catch (IOException ioe) {
					ioe.printStackTrace();
					tabla.setStatus("9000");
				}
				lstTablasResult.add(tabla);
			}
		}
		return lstTablasResult;
	}

	@Override
	public String cargaMetaPresupuestaria(String anio, String cod_unid_ejec) throws Exception {
		return attributesDao.cargaMetaPresupuestaria(anio, cod_unid_ejec);
	}

	@Override
	public String cargaClasificadorGasto(String anio) throws Exception {
		return attributesDao.cargaClasificadorGasto(anio);
	}

	@Override
	public String cargaPresupuesto(String anio, String cod_unid_ejec) throws Exception {
		return attributesDao.cargaPresupuesto(anio, cod_unid_ejec);
	}

	@Override
	public List<ClasificadorMeta> getClasificadorMeta(String anio, String cod_unid_ejec, String sec_func,
			String fuente_financ) {
		return attributesDao.getClasificadorMeta(anio, cod_unid_ejec, sec_func, fuente_financ);
	}

	@Override
	public List<ClasificadorMetaCarga> getClasificadorMetaCarga(String anio, String cod_unid_ejec) {
		return attributesDao.getClasificadorMetaCarga(anio, cod_unid_ejec);
	}

	@Override
	public List<NotaModificatoria> getNotaModificatoria(String anio, String cod_unid_ejec, String sec_func,
			String fuente_financ, String id_clasificador) {
		return attributesDao.getNotaModificatoria(anio, cod_unid_ejec, sec_func, fuente_financ, id_clasificador);
	}

	@Override
	public GenericResponse registrarCert_cab(CertRequest certRequest) {
		return attributesDao.registrarCert_cab(certRequest);
	}

	@Override
	public GenericResponse getEstadoSolicitudCertCab(String secuencial) {
		GenericResponse response = new GenericResponse();
		CertCabConsulta consulta;
		try {
			consulta = attributesDao.getEstadoSolicitudCertCab(secuencial);
			if (!(consulta.getSecuencial() == null)) {
				response.setCode("0000");
				response.setMessage("Consulta satisfactoria");
				response.setData(consulta);
			} else {
				response.setCode("6000");
				response.setMessage("No se ha encontrado el secuencial");
				response.setData(null);
			}
		} catch (Exception e) {
			response.setCode("9000");
			response.setMessage(e.getMessage());
			response.setData(null);
		}
		return response;
	}

	@Override
	public GenericResponse registrarIntf_cab(IntfRequest intfRequest) {
		return attributesDao.registrarIntf_cab(intfRequest);
	}

	@Override
	public GenericResponse consultaSaldoPca(ConsultaPCARequest consultaPcaRequest) {
		GenericResponse response = new GenericResponse();
		List<ClasifPcaResp> clasif = new ArrayList<ClasifPcaResp>();
		try {
			clasif = attributesDao.consultaSaldoPca(consultaPcaRequest);
			if (!(clasif.isEmpty())) {
				response.setCode("0000");
				response.setMessage("Consulta satisfactoria");
				response.setData(clasif);
			} else {
				response.setCode("6000");
				response.setMessage("No se ha encontrado data");
				response.setData(null);
			}
		} catch (Exception e) {
			response.setCode("9000");
			response.setMessage(e.getMessage());
			response.setData(null);
		}
		return response;
	}

	@Override
	public GenericResponse consultaCertCompAnual(String anio, String cod_unid_ejec, String num_ccp) {
		GenericResponse response = new GenericResponse();
		List<CertCompAnual> certCompAnual = new ArrayList<CertCompAnual>();
		try {
			certCompAnual = attributesDao.consultaCertCompAnual(anio, cod_unid_ejec, num_ccp);
			if (!(certCompAnual.isEmpty())) {
				response.setCode("0000");
				response.setMessage("Consulta satisfactoria");
				response.setData(certCompAnual);
			} else {
				response.setCode("6000");
				response.setMessage("No se ha encontrado data");
				response.setData(null);
			}
		} catch (Exception e) {
			response.setCode("9000");
			response.setMessage(e.getMessage());
			response.setData(null);
		}
		return response;
	}

	@Override
	public GenericResponse getClasificadorCodigo(String anio, String cod_unid_ejec, String sec_func,
			String fuente_financ, String id_clasificador) {
		GenericResponse response = new GenericResponse();
		ClasificadorCodigo clasificador = new ClasificadorCodigo();
		try {
			clasificador = attributesDao.getClasificadorCodigo(anio, cod_unid_ejec, sec_func, fuente_financ,
					id_clasificador);
			if (!(clasificador.getCodigo_clasificador() == null)) {
				response.setCode("0000");
				response.setMessage("Consulta satisfactoria");
				response.setData(clasificador);
			} else {
				response.setCode("6000");
				response.setMessage("No se ha encontrado data");
				response.setData(null);
			}
		} catch (Exception e) {
			response.setCode("9000");
			response.setMessage(e.getMessage());
			response.setData(null);
		}
		return response;
	}

	@Override
	public List<MetaPresupuestaria> getMetaPresupuestaria(GetMetaRequest request) {
		return attributesDao.getMetaPresupuestaria(request);
	}

	@Override
	public GenericResponse getMetaCodigo(String anio, String cod_unid_ejec, String sec_func) {
		GenericResponse response = new GenericResponse();
		MetaCadena meta = new MetaCadena();
		try {
			meta = attributesDao.getMetaCodigo(anio, cod_unid_ejec, sec_func);
			if (!(meta.getSec_func() == null)) {
				response.setCode("0000");
				response.setMessage("Consulta satisfactoria");
				response.setData(meta);
			} else {
				response.setCode("6000");
				response.setMessage("No se ha encontrado data");
				response.setData(null);
			}
		} catch (Exception e) {
			response.setCode("9000");
			response.setMessage(e.getMessage());
			response.setData(null);
		}
		return response;
	}

	@Override
	public GenericResponse getCertificadoEstado(String anio, String cod_unid_ejec, String certificado, String secuencia,
			String correlativo) {
		GenericResponse response = new GenericResponse();
		CertificadoEstado cert = new CertificadoEstado();
		try {
			cert = attributesDao.getCertificadoEstado(anio, cod_unid_ejec, certificado, secuencia, correlativo);
			if (!(cert.getEstado_envio() == null)) {
				response.setCode("0000");
				response.setMessage("Consulta satisfactoria");
				response.setData(cert);
			} else {
				response.setCode("6000");
				response.setMessage("No se ha encontrado data");
				response.setData(null);
			}
		} catch (Exception e) {
			response.setCode("9000");
			response.setMessage(e.getMessage());
			response.setData(null);
		}
		return response;
	}

	@Override
	public GenericResponse validaSaldoClasificador(String ano_eje, String fuente_finac, String sec_ejec,
			String id_clasifi, String sec_func, String monto) {
		return attributesDao.validaSaldoClasificador(ano_eje, fuente_finac, sec_ejec, id_clasifi, sec_func, monto);
	}

	@Override
	public List<ClasificadorMontoConsolidado> getClasificadorMontoCertificado(String fecha_inicio, String fecha_fin) {		
		return attributesDao.getClasificadorMontoCertificado(fecha_inicio, fecha_fin);
	}

	@Override
	public List<ClasificadorMontoConsolidado> getClasificadorMontoCompromisoAnual(String fecha_inicio,
			String fecha_fin) {
		
		return attributesDao.getClasificadorMontoCompromisoAnual(fecha_inicio, fecha_fin);
	}

	@Override
	public List<ClasificadorMontoConsolidado> getClasificadorMontoCompromisoMensual(String fecha_inicio,
			String fecha_fin) {
		
		return attributesDao.getClasificadorMontoCompromisoMensual(fecha_inicio, fecha_fin);
	}

	@Override
	public List<ClasificadorMontoConsolidado> getClasificadorMontoDevengado(String fecha_inicio, String fecha_fin) {
		
		return attributesDao.getClasificadorMontoDevengado(fecha_inicio, fecha_fin);
	}

	@Override
	public List<ClasificadorMontoConsolidado> getClasificadorMontoGirado(String fecha_inicio, String fecha_fin) {
		
		return attributesDao.getClasificadorMontoGirado(fecha_inicio, fecha_fin);
	}

	@Override
	public List<ClasificadorMontoConsolidado> getClasificadorMontoPagado(String fecha_inicio, String fecha_fin) {
		
		return attributesDao.getClasificadorMontoPagado(fecha_inicio, fecha_fin);
	}

	@Override
	public List<ClasificadorMontoConsolidadoPim> getClasificadorMontoPiaPim(String anio) {
		
		return attributesDao.getClasificadorMontoPiaPim(anio);
	}


}
