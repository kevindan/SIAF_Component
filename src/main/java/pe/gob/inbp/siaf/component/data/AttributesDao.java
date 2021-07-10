package pe.gob.inbp.siaf.component.data;

import java.util.List;

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

public interface AttributesDao {
	
	List<ExpedienteFase> getConsultaExpedienteSiaf(String anio, String cod_siaf, String cod_unid_ejec);
	List<MetaPresupuestaria> getMetaPresupuestaria(String anio, String cod_unid_ejec);
	List<MetaPresupuestaria> getMetaPresupuestaria(GetMetaRequest request);
	List<Tabla_siaf> getTablasSiaf();
	List<ClasificadorMeta> getClasificadorMeta(String anio, String cod_unid_ejec, String sec_func, String fuente_financ);
	List<ClasificadorMetaCarga> getClasificadorMetaCarga(String anio, String cod_unid_ejec);
	List<NotaModificatoria> getNotaModificatoria(String anio, String cod_unid_ejec, String sec_func, String fuente_financ,String id_clasificador);
	String cargaMetaPresupuestaria(String anio, String cod_unid_ejec) throws Exception;
	String cargaClasificadorGasto(String anio) throws Exception;
	String cargaPresupuesto(String anio, String cod_unid_ejec) throws Exception;
	GenericResponse registrarCert_cab(CertRequest certRequest);
	CertCabConsulta getEstadoSolicitudCertCab(String secuencial);
	GenericResponse registrarIntf_cab(IntfRequest intfRequest);
	List<ClasifPcaResp> consultaSaldoPca(ConsultaPCARequest consultaPcaRequest);
	List<CertCompAnual> consultaCertCompAnual(String anio, String cod_unid_ejec, String num_ccp);
	ClasificadorCodigo getClasificadorCodigo(String anio, String cod_unid_ejec, String sec_func, String fuente_financ, String id_clasificador);
	MetaCadena getMetaCodigo(String anio, String cod_unid_ejec, String sec_func);
	CertificadoEstado getCertificadoEstado(String anio, String cod_unid_ejec, String certificado, String secuencia, String correlativo);
	GenericResponse validaSaldoClasificador(String ano_eje, String fuente_finac, String sec_ejec, String id_clasifi, String sec_func, String monto);
	List<ClasificadorMontoConsolidado> getClasificadorMontoCertificado(String fecha_inicio, String fecha_fin);
	List<ClasificadorMontoConsolidado> getClasificadorMontoCompromisoAnual(String fecha_inicio, String fecha_fin);
	List<ClasificadorMontoConsolidado> getClasificadorMontoCompromisoMensual(String fecha_inicio, String fecha_fin);
	List<ClasificadorMontoConsolidado> getClasificadorMontoDevengado(String fecha_inicio, String fecha_fin);
	List<ClasificadorMontoConsolidado> getClasificadorMontoGirado(String fecha_inicio, String fecha_fin);
	List<ClasificadorMontoConsolidado> getClasificadorMontoPagado(String fecha_inicio, String fecha_fin);
	List<ClasificadorMontoConsolidadoPim> getClasificadorMontoPiaPim(String anio);

	
	
}
