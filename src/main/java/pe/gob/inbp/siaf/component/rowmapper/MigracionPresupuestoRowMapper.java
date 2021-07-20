package pe.gob.inbp.siaf.component.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.gob.inbp.siaf.component.domain.MigracionPresupuesto;

public class MigracionPresupuestoRowMapper implements RowMapper<MigracionPresupuesto> {

	@Override
	public MigracionPresupuesto mapRow(ResultSet rs, int rowNum) throws SQLException {
		MigracionPresupuesto mPresupuesto = new MigracionPresupuesto();
		mPresupuesto.setId_presupuesto(rs.getLong("id_presupuesto"));
		mPresupuesto.setId_clasificador_gasto(rs.getLong("id_clasificador_gasto"));
		mPresupuesto.setId_meta(rs.getLong("id_meta"));
		mPresupuesto.setAno_eje(rs.getString("ano_eje"));
		mPresupuesto.setSec_ejec(rs.getString("sec_ejec"));
		mPresupuesto.setSec_func(rs.getString("sec_func"));
		mPresupuesto.setId_clasificador(rs.getString("id_clasificador"));
		mPresupuesto.setFuente_financ(rs.getString("fuente_financ"));
		mPresupuesto.setMonto_inicial(rs.getBigDecimal("monto_inicial"));
		return mPresupuesto;
	}

}
