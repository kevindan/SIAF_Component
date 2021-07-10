package pe.gob.inbp.siaf.component.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.inbp.siaf.component.payload.GenericResponse;
import pe.gob.inbp.siaf.component.service.AttributesService;
import pe.gob.inbp.siaf.component.service.MigracionService;

@RestController
@RequestMapping("api/v1/migracion")
public class MigracionController {
	
	@Autowired
	private MigracionService migracionService;
	
	@GetMapping("/migarTablas/{ano_eje}/{sec_ejec}")
	public GenericResponse migrarRegistros(@PathVariable ("ano_eje") String ano_eje, @PathVariable ("sec_ejec") String sec_ejec) {		
		
		return migracionService.migrarTablas(ano_eje, sec_ejec);
	}	


}
