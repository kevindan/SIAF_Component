package pe.gob.inbp.siaf.component.data;

import pe.gob.inbp.siaf.component.payload.GenericResponse;

public interface MigracionDao {
	
	GenericResponse migrarCompromisoRegistroSiafModifacion(String ano_eje, String  sec_ejec);
	GenericResponse migrarMetaClasificador(String ano_eje, String  sec_ejec);
	GenericResponse migrarPresupuesto(String ano_eje, String  sec_ejec);
	Integer cargarCertificaciconCompromiso(String ano_eje, String  sec_ejec, String secuencial);
	Integer cargarRegistroSiaf(String ano_eje, String  sec_ejec, String secuencial);
	Integer cargarNotaModificatoria(String ano_eje, String  sec_ejec, String secuencial);
	Integer cargarMeta(String ano_eje, String  sec_ejec, String secuencial);
	Integer cargarClasificador(String ano_eje, String secuencial);
	Integer cargarRegistrosPresupuesto(String ano_eje, String  sec_ejec, boolean vacio);
}
