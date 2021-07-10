package pe.gob.inbp.siaf.component.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.gob.inbp.siaf.component.domain.Tabla_siaf;

public class Tabla_siafRowMapper implements RowMapper<Tabla_siaf>{

	@Override
	public Tabla_siaf mapRow(ResultSet rs, int rowNum) throws SQLException {
		Tabla_siaf tabla = new Tabla_siaf();
		tabla.setNombre_tabla(rs.getString("nombre_tabla"));
		return tabla;
	}

}
