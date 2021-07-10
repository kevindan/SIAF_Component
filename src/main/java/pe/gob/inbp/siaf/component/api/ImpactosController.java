package pe.gob.inbp.siaf.component.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.inbp.siaf.component.payload.GenericResponse;
import pe.gob.inbp.siaf.component.payload.request.CertRequest;
import pe.gob.inbp.siaf.component.payload.request.IntfRequest;
import pe.gob.inbp.siaf.component.service.AttributesService;

@RestController
@RequestMapping("api/v1/impactos")
public class ImpactosController {
	@Autowired
	private AttributesService attributesService;
	
	@PostMapping("/registraCert")
	public GenericResponse solicitaCertificado(@RequestBody CertRequest certRequest) {
		return attributesService.registrarCert_cab(certRequest);		
	}	

	@PostMapping("/registraIntf")
	public GenericResponse registrarCompromisoMensual(@RequestBody IntfRequest intfRequest) {
		return attributesService.registrarIntf_cab(intfRequest);
	}
}
