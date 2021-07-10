package pe.gob.inbp.siaf.component.data;

import pe.gob.inbp.siaf.component.payload.GenericResponse;

public interface MigracionDao {
	
	GenericResponse migrarTablas(String ano_eje, String  sec_ejec);
	Integer cargarCertificaciconCompromiso(String ano_eje, String  sec_ejec, String secuencial);
	Integer cargarRegistroSiaf(String ano_eje, String  sec_ejec);
	Integer cargarNotaModificatoria(String ano_eje, String  sec_ejec);

}
