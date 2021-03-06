package pe.gob.inbp.siaf.component.service;

import pe.gob.inbp.siaf.component.payload.GenericResponse;

public interface MigracionService {
	GenericResponse migrarCompromisoRegistroSiafModifacion(String ano_eje, String  sec_ejec);
	GenericResponse migrarMetaClasificador(String ano_eje, String  sec_ejec);
	GenericResponse migrarPresupuesto(String ano_eje, String  sec_ejec);
}
