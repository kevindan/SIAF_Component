package pe.gob.inbp.siaf.component.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.inbp.siaf.component.service.AttributesService;

@RestController
@RequestMapping("api/v1/cargas")
public class CargasController {
	
	@Autowired
	private AttributesService attributesService;
	
	@GetMapping("/cargaMetaPresupuestaria/{anio}/{cod_unid_ejec}")
	public String cargaMetaPresupuestaria(@PathVariable ("anio") String anio,
			@PathVariable ("cod_unid_ejec") String cod_unid_ejec)throws Exception {
		return attributesService.cargaMetaPresupuestaria(anio, cod_unid_ejec);
	}
	
	@GetMapping("/cargaClasificadorGasto/{anio}")
	public String cargaClasificadorGasto(@PathVariable ("anio") String anio)throws Exception {
		return attributesService.cargaClasificadorGasto(anio);
	}
	
	@GetMapping("/cargaPresupuesto/{anio}/{cod_unid_ejec}")
	public String cargaClasificadorGasto(@PathVariable ("anio") String anio,
			@PathVariable ("cod_unid_ejec") String cod_unid_ejec)throws Exception {
		return attributesService.cargaPresupuesto(anio, cod_unid_ejec);
	}

}
