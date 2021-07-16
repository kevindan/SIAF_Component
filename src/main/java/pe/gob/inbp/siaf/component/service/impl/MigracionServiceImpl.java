package pe.gob.inbp.siaf.component.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.inbp.siaf.component.data.MigracionDao;
import pe.gob.inbp.siaf.component.payload.GenericResponse;
import pe.gob.inbp.siaf.component.service.MigracionService;

@Service
public class MigracionServiceImpl implements MigracionService {

	@Autowired
	private MigracionDao migracionDao;
	
	@Override
	public GenericResponse migrarCompromisoRegistroSiafModifacion(String ano_eje, String sec_ejec) {		
		return migracionDao.migrarCompromisoRegistroSiafModifacion(ano_eje, sec_ejec);
	}

}
