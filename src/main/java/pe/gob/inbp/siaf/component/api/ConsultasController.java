package pe.gob.inbp.siaf.component.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.inbp.siaf.component.domain.CertDet;
import pe.gob.inbp.siaf.component.domain.ClasifSaldoPimReq;
import pe.gob.inbp.siaf.component.domain.ClasifSaldoPimResp;
import pe.gob.inbp.siaf.component.domain.ClasificadorCodigo;
import pe.gob.inbp.siaf.component.domain.ClasificadorMeta;
import pe.gob.inbp.siaf.component.domain.ClasificadorMetaCarga;
import pe.gob.inbp.siaf.component.domain.ClasificadorMontoConsolidado;
import pe.gob.inbp.siaf.component.domain.ClasificadorMontoConsolidadoPim;
import pe.gob.inbp.siaf.component.domain.ExpedienteFase;
import pe.gob.inbp.siaf.component.domain.MetaPresupuestaria;
import pe.gob.inbp.siaf.component.domain.NotaModificatoria;
import pe.gob.inbp.siaf.component.payload.GenericResponse;
import pe.gob.inbp.siaf.component.payload.request.ConsultaPCARequest;
import pe.gob.inbp.siaf.component.payload.request.GetClasificadoresMetaRango;
import pe.gob.inbp.siaf.component.payload.request.GetMetaRequest;
import pe.gob.inbp.siaf.component.service.AttributesService;

@RestController
@RequestMapping("api/v1/consultas")
public class ConsultasController {

	@Autowired
	private AttributesService attributesService;
	
	@GetMapping("/getMetaPresupuestaria/{anio}/{cod_unid_ejec}")
	public GenericResponse getMetaPresupuestaria(@PathVariable ("anio") String anio,
			@PathVariable ("cod_unid_ejec") String cod_unid_ejec) {
		GenericResponse result = new GenericResponse();
		
		List<MetaPresupuestaria> lstMetaPresupuestaria = attributesService.getMetaPresupuestaria(anio, cod_unid_ejec);
		
		if(!lstMetaPresupuestaria.isEmpty()) {
			result.setCode("0000");
			result.setMessage("Se han encontrado datos para mostrar");
			result.setData(lstMetaPresupuestaria);
		}else{
			result.setCode("6000");
			result.setMessage("No se han encontrado datos para mostrar");
			result.setData(null);
		}				
		return result;
	}	
	
	@PostMapping("/getMetaPresupuestaria/")
	public GenericResponse getMetaPresupuestariaExcluido(@RequestBody GetMetaRequest request) {
		GenericResponse result = new GenericResponse();
		
		List<MetaPresupuestaria> lstMetaPresupuestaria = attributesService.getMetaPresupuestaria(request);
		
		if(!lstMetaPresupuestaria.isEmpty()) {
			result.setCode("0000");
			result.setMessage("Se han encontrado datos para mostrar");
			result.setData(lstMetaPresupuestaria);
		}else{
			result.setCode("6000");
			result.setMessage("No se han encontrado datos para mostrar");
			result.setData(null);
		}				
		return result;
	}	

	@GetMapping("/getClasificadorMeta/{anio}/{cod_unid_ejec}/{sec_func}/{fuente_financ}")
	public GenericResponse getClasificadorMeta(@PathVariable ("anio") String anio,
			@PathVariable ("cod_unid_ejec") String cod_unid_ejec, @PathVariable ("sec_func") String sec_func, @PathVariable ("fuente_financ") String fuente_financ) {
		GenericResponse result = new GenericResponse();
		
		List<ClasificadorMeta> lstClasificadores = attributesService.getClasificadorMeta(anio, cod_unid_ejec, sec_func,fuente_financ);
		
		if(!lstClasificadores.isEmpty()) {
			result.setCode("0000");
			result.setMessage("Se han encontrado datos para mostrar");
			result.setData(lstClasificadores);
		}else{
			result.setCode("6000");
			result.setMessage("No se han encontrado datos para mostrar");
			result.setData(null);
		}				
		return result;
	}
	
	@PostMapping("/getConsolidadoCertificado/")
	public GenericResponse getConsolidadoCertificado(@RequestBody GetClasificadoresMetaRango request) {
		GenericResponse result = new GenericResponse();
		
		List<ClasificadorMontoConsolidado> lstConsolidado = attributesService.getClasificadorMontoCertificado(request.getFecha_inicio(), request.getFecha_fin());
		
		if(!lstConsolidado.isEmpty()) {
			result.setCode("0000");
			result.setMessage("Se han encontrado datos para mostrar");
			result.setData(lstConsolidado);
		}else{
			result.setCode("6000");
			result.setMessage("No se han encontrado datos para mostrar");
			result.setData(null);
		}				
		return result;
	}
	
	@PostMapping("/getConsolidadoCompromisoAnual/")
	public GenericResponse getConsolidadoCompromisoAnual(@RequestBody GetClasificadoresMetaRango request) {
		GenericResponse result = new GenericResponse();
		
		List<ClasificadorMontoConsolidado> lstConsolidado = attributesService.getClasificadorMontoCompromisoAnual(request.getFecha_inicio(), request.getFecha_fin());
		
		if(!lstConsolidado.isEmpty()) {
			result.setCode("0000");
			result.setMessage("Se han encontrado datos para mostrar");
			result.setData(lstConsolidado);
		}else{
			result.setCode("6000");
			result.setMessage("No se han encontrado datos para mostrar");
			result.setData(null);
		}				
		return result;
	}
	
	@PostMapping("/getConsolidadoCompromisoMensual/")
	public GenericResponse getConsolidadoCompromisoMensual(@RequestBody GetClasificadoresMetaRango request) {
		GenericResponse result = new GenericResponse();
		
		List<ClasificadorMontoConsolidado> lstConsolidado = attributesService.getClasificadorMontoCompromisoMensual(request.getFecha_inicio(), request.getFecha_fin());
		
		if(!lstConsolidado.isEmpty()) {
			result.setCode("0000");
			result.setMessage("Se han encontrado datos para mostrar");
			result.setData(lstConsolidado);
		}else{
			result.setCode("6000");
			result.setMessage("No se han encontrado datos para mostrar");
			result.setData(null);
		}				
		return result;
	}
	
	@PostMapping("/getConsolidadoDevengado/")
	public GenericResponse getConsolidadoDevengado(@RequestBody GetClasificadoresMetaRango request) {
		GenericResponse result = new GenericResponse();
		
		List<ClasificadorMontoConsolidado> lstConsolidado = attributesService.getClasificadorMontoDevengado(request.getFecha_inicio(), request.getFecha_fin());
		
		if(!lstConsolidado.isEmpty()) {
			result.setCode("0000");
			result.setMessage("Se han encontrado datos para mostrar");
			result.setData(lstConsolidado);
		}else{
			result.setCode("6000");
			result.setMessage("No se han encontrado datos para mostrar");
			result.setData(null);
		}				
		return result;
	}
	
	@PostMapping("/getConsolidadoGirado/")
	public GenericResponse getConsolidadoGirado(@RequestBody GetClasificadoresMetaRango request) {
		GenericResponse result = new GenericResponse();
		
		List<ClasificadorMontoConsolidado> lstConsolidado = attributesService.getClasificadorMontoGirado(request.getFecha_inicio(), request.getFecha_fin());
		
		if(!lstConsolidado.isEmpty()) {
			result.setCode("0000");
			result.setMessage("Se han encontrado datos para mostrar");
			result.setData(lstConsolidado);
		}else{
			result.setCode("6000");
			result.setMessage("No se han encontrado datos para mostrar");
			result.setData(null);
		}				
		return result;
	}
	
	@PostMapping("/getConsolidadoPagado/")
	public GenericResponse getConsolidadoPagado(@RequestBody GetClasificadoresMetaRango request) {
		GenericResponse result = new GenericResponse();
		
		List<ClasificadorMontoConsolidado> lstConsolidado = attributesService.getClasificadorMontoPagado(request.getFecha_inicio(), request.getFecha_fin());
		
		if(!lstConsolidado.isEmpty()) {
			result.setCode("0000");
			result.setMessage("Se han encontrado datos para mostrar");
			result.setData(lstConsolidado);
		}else{
			result.setCode("6000");
			result.setMessage("No se han encontrado datos para mostrar");
			result.setData(null);
		}				
		return result;
	}
	
	@GetMapping("/getConsolidadoPiaPim/{ano_eje}")
	public GenericResponse getConsolidadoPiaPim(@PathVariable ("ano_eje") String ano_eje) {
		GenericResponse result = new GenericResponse();
		
		List<ClasificadorMontoConsolidadoPim> lstConsolidado = attributesService.getClasificadorMontoPiaPim(ano_eje);
		
		if(!lstConsolidado.isEmpty()) {
			result.setCode("0000");
			result.setMessage("Se han encontrado datos para mostrar");
			result.setData(lstConsolidado);
		}else{
			result.setCode("6000");
			result.setMessage("No se han encontrado datos para mostrar");
			result.setData(null);
		}				
		return result;
	}
		
	@GetMapping("/getClasificadorMetaCarga/{anio}/{cod_unid_ejec}")
	public GenericResponse getClasificadorMeta(@PathVariable ("anio") String anio, @PathVariable ("cod_unid_ejec") String cod_unid_ejec) {
		GenericResponse result = new GenericResponse();
		
		List<ClasificadorMetaCarga> lstClasificadores = attributesService.getClasificadorMetaCarga(anio, cod_unid_ejec);
		
		if(!lstClasificadores.isEmpty()) {
			result.setCode("0000");
			result.setMessage("Se han encontrado datos para mostrar");
			result.setData(lstClasificadores);
		}else{
			result.setCode("6000");
			result.setMessage("No se han encontrado datos para mostrar");
			result.setData(null);
		}				
		return result;
	}
	
	@GetMapping("/getNotaModificatoria/{anio}/{cod_unid_ejec}/{sec_func}/{fuente_financ}/{id_clasificador}")
	public GenericResponse getNotaModificatoria(@PathVariable ("anio") String anio,
			@PathVariable ("cod_unid_ejec") String cod_unid_ejec, @PathVariable ("sec_func") String sec_func, 
			@PathVariable ("fuente_financ") String fuente_financ,@PathVariable ("id_clasificador") String id_clasificador) {
		GenericResponse result = new GenericResponse();
		
		List<NotaModificatoria> lstNotaModificatoria= attributesService.getNotaModificatoria(anio, cod_unid_ejec, sec_func, fuente_financ, id_clasificador);
		
		if(!lstNotaModificatoria.isEmpty()) {
			result.setCode("0000");
			result.setMessage("Se han encontrado datos para mostrar");
			result.setData(lstNotaModificatoria);
		}else{
			result.setCode("6000");
			result.setMessage("No se han encontrado datos para mostrar");
			result.setData(null);
		}				
		return result;
	}
	
	@GetMapping("/getClasificadorCodigo/{anio}/{cod_unid_ejec}/{sec_func}/{fuente_financ}/{id_clasificador}")
	public GenericResponse getClasificadorCodigo(@PathVariable ("anio") String anio,
			@PathVariable ("cod_unid_ejec") String cod_unid_ejec, @PathVariable ("sec_func") String sec_func, 
			@PathVariable ("fuente_financ") String fuente_financ,@PathVariable ("id_clasificador") String id_clasificador) {
		GenericResponse result = new GenericResponse();
		
		result = attributesService.getClasificadorCodigo(anio, cod_unid_ejec, sec_func, fuente_financ, id_clasificador);
		
		
		return result;
	}
	
	@GetMapping("/getMetaCadena/{anio}/{cod_unid_ejec}/{sec_func}")
	public GenericResponse getMetaCadena(@PathVariable ("anio") String anio,
			@PathVariable ("cod_unid_ejec") String cod_unid_ejec, @PathVariable ("sec_func") String sec_func) {
		GenericResponse result = new GenericResponse();		
		result = attributesService.getMetaCodigo(anio, cod_unid_ejec, sec_func);
		return result;
	}
	
	
	@GetMapping("/getConsultaEstadoSiaf/{cod_unid_ejec}/{anio}/{cod_siaf}")
	public GenericResponse getConsultaEstadoSiaf(@PathVariable("cod_unid_ejec") String cod_unid_ejec,
			@PathVariable("anio") String anio, @PathVariable("cod_siaf") String cod_siaf) {
		GenericResponse result = new GenericResponse();

		List<ExpedienteFase> lstExpediente = attributesService.getConsultaExpedienteSiaf(anio, cod_siaf, cod_unid_ejec);

		if (!lstExpediente.isEmpty()) {
			result.setCode("0000");
			result.setMessage("Se ha encontrado el expediente");
			result.setData(lstExpediente);
		} else {
			result.setCode("6000");
			result.setMessage("No se ha encontrado el expediente");
			result.setData(null);
		}
		return result;
	}
	
	@GetMapping("/getConsultaCertCompAnual/{anio}/{cod_unid_ejec}/{num_ccp}")
	public GenericResponse getConsultaSecuencial(@PathVariable("anio") String anio, @PathVariable("cod_unid_ejec") String cod_unid_ejec, @PathVariable("num_ccp") String num_ccp) {		
		return attributesService.consultaCertCompAnual(anio, cod_unid_ejec, num_ccp);
	}
	
	@GetMapping("/getConsultaEstadoCertCab/{secuencial}")
	public GenericResponse getConsultaCertCab(@PathVariable("secuencial") String secuencial) {		
		return attributesService.getEstadoSolicitudCertCab(secuencial);
	}
	
	@GetMapping("/getCertificadoEstado/{anio}/{cod_unid_ejec}/{certificado}/{secuencia}/{correlativo}")
	public GenericResponse getCertificadoEstado(@PathVariable("anio") String anio, @PathVariable("cod_unid_ejec") String cod_unid_ejec, 
			@PathVariable("certificado") String certificado, @PathVariable("secuencia") String secuencia, @PathVariable("correlativo") String correlativo) {		
		return attributesService.getCertificadoEstado(anio, cod_unid_ejec, certificado, secuencia, correlativo);
	}
	
	@GetMapping("/getValidaClasificador/{ano_eje}/{fuente_financ}/{sec_ejec}/{id_clasifi}/{sec_func}/{monto}")
	public GenericResponse getValidaClasificador(@PathVariable("ano_eje") String ano_eje, @PathVariable("fuente_financ") String fuente_financ, 
			@PathVariable("sec_ejec") String sec_ejec, @PathVariable("id_clasifi") String id_clasifi, @PathVariable("sec_func") String sec_func, @PathVariable("monto") String monto) {		
		return attributesService.validaSaldoClasificador(ano_eje, fuente_financ, sec_ejec, id_clasifi, sec_func, monto);
	}

	/* Números de expedientes SIAF:
	 * 
	 * 0000000002
	 * 0000000107
	 * 0000000105
	 * 0000000219
	 * 0000000620
	 * 0000000549
	 * 0000000242
	 * 0000000841
	 * 0000000748
	 * 0000000335
	 * 0000000675
	 * 0000000399
	 */

}
