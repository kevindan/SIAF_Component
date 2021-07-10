package pe.gob.inbp.siaf.component.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.inbp.siaf.component.payload.GenericResponse;
import pe.gob.inbp.siaf.component.service.MaestrosService;

@RestController
@RequestMapping("api/v1/maestros")
public class MaestrosController {
	
	@Autowired
	private MaestrosService maestrosService;

	@GetMapping("/getFuenteFinanc/{anio}")
	public GenericResponse getFuenteFinanc(@PathVariable ("anio") String anio) {
		return maestrosService.getFuenteFinancAll(anio);
	}
	
	@GetMapping("/getBuscarFuenteFinanciamiento/{anio}/{codigo_fuente}")
	public GenericResponse getBuscarFuenteFinanciamiento(@PathVariable ("anio") String anio, @PathVariable ("codigo_fuente") String codigo_fuente) {
		return maestrosService.getBuscarFuenteFinanciamiento(anio, codigo_fuente);
	}

	@GetMapping("/getPrograpaPpto/{anio}")
	public GenericResponse getProgramaPptopNombre(@PathVariable ("anio") String anio) {
		return maestrosService.getProgramaPpto(anio);
	}

	@GetMapping("/getMaestroDocumento")
	public GenericResponse getMaestroDocumento() {
		return new GenericResponse("0000", "Lista de Mestros de documentos", maestrosService.getMaestroDocumentoAll());
	}

	@GetMapping("/getTipoOperacionComp/{anio}")
	public GenericResponse getTipoOperacionComp(@PathVariable ("anio") String anio) {
		return new GenericResponse("0000", "Lista de tipo de operación para compromiso anual", maestrosService.getTipoOperacionComp(anio));
	}
}
