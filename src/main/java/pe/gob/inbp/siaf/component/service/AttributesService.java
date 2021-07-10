package pe.gob.inbp.siaf.component.service;

import java.util.List;

import pe.gob.inbp.siaf.component.domain.ClasificadorMeta;
import pe.gob.inbp.siaf.component.domain.ClasificadorMetaCarga;
import pe.gob.inbp.siaf.component.domain.ClasificadorMontoConsolidado;
import pe.gob.inbp.siaf.component.domain.ClasificadorMontoConsolidadoPim;
import pe.gob.inbp.siaf.component.domain.ExpedienteFase;
import pe.gob.inbp.siaf.component.domain.MetaPresupuestaria;
import pe.gob.inbp.siaf.component.domain.NotaModificatoria;
import pe.gob.inbp.siaf.component.domain.Tabla_siaf;
import pe.gob.inbp.siaf.component.payload.GenericResponse;
import pe.gob.inbp.siaf.component.payload.request.CertRequest;
import pe.gob.inbp.siaf.component.payload.request.ConsultaPCARequest;
import pe.gob.inbp.siaf.component.payload.request.GetMetaRequest;
import pe.gob.inbp.siaf.component.payload.request.IntfRequest;

public interface AttributesService {

	List<ExpedienteFase> getConsultaExpedienteSiaf(String anio, String cod_siaf, String cod_unid_ejec);
	List<MetaPresupuestaria> getMetaPresupuestaria(String anio, String cod_unid_ejec);
	List<MetaPresupuestaria> getMetaPresupuestaria(GetMetaRequest request);
	List<ClasificadorMeta> getClasificadorMeta(String anio, String cod_unid_ejec, String sec_func, String fuente_financ);
	List<ClasificadorMetaCarga> getClasificadorMetaCarga(String anio, String cod_unid_ejec);
	List<NotaModificatoria> getNotaModificatoria(String anio, String cod_unid_ejec, String sec_func, String fuente_financ,String id_clasificador);
	List<Tabla_siaf> getTablasSiaf();
	List<Tabla_siaf> migrarTablasSiaf();
	String cargaMetaPresupuestaria(String anio, String cod_unid_ejec) throws Exception;
	String cargaClasificadorGasto(String anio) throws Exception;
	String cargaPresupuesto(String anio, String cod_unid_ejec) throws Exception;
	GenericResponse registrarCert_cab(CertRequest certRequest);
	GenericResponse getEstadoSolicitudCertCab(String secuencial);
	GenericResponse registrarIntf_cab(IntfRequest intfRequest);
	GenericResponse consultaSaldoPca(ConsultaPCARequest consultaPcaRequest);	
	GenericResponse consultaCertCompAnual(String anio, String cod_unid_ejec, String num_ccp);
	GenericResponse getClasificadorCodigo(String anio, String cod_unid_ejec, String sec_func, String fuente_financ, String id_clasificador);
	GenericResponse getMetaCodigo(String anio, String cod_unid_ejec, String sec_func);
	GenericResponse getCertificadoEstado(String anio, String cod_unid_ejec, String certificado, String secuencia, String correlativo);
	GenericResponse validaSaldoClasificador(String ano_eje, String fuente_finac, String sec_ejec, String id_clasifi, String sec_func, String monto);
	List<ClasificadorMontoConsolidado> getClasificadorMontoCertificado(String fecha_inicio, String fecha_fin);
	List<ClasificadorMontoConsolidado> getClasificadorMontoCompromisoAnual(String fecha_inicio, String fecha_fin);
	List<ClasificadorMontoConsolidado> getClasificadorMontoCompromisoMensual(String fecha_inicio, String fecha_fin);
	List<ClasificadorMontoConsolidado> getClasificadorMontoDevengado(String fecha_inicio, String fecha_fin);
	List<ClasificadorMontoConsolidado> getClasificadorMontoGirado(String fecha_inicio, String fecha_fin);
	List<ClasificadorMontoConsolidado> getClasificadorMontoPagado(String fecha_inicio, String fecha_fin);
	List<ClasificadorMontoConsolidadoPim> getClasificadorMontoPiaPim(String anio);

}
