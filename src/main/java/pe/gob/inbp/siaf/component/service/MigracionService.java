package pe.gob.inbp.siaf.component.service;

import pe.gob.inbp.siaf.component.payload.GenericResponse;

public interface MigracionService {
	GenericResponse migrarTablas(String ano_eje, String  sec_ejec);
}
