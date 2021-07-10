package pe.gob.inbp.siaf.component.data.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.jacob.com.Variant;

import pe.gob.inbp.siaf.component.data.MaestrosDao;
import pe.gob.inbp.siaf.component.domain.Fuente_financ;
import pe.gob.inbp.siaf.component.domain.MaestroDocumento;
import pe.gob.inbp.siaf.component.domain.ProgramaPpto;
import pe.gob.inbp.siaf.component.domain.TipoOperacion;
import pe.gob.inbp.siaf.component.utility.AdoUtility;
import pe.gob.inbp.siaf.component.vfp.Field;
import pe.gob.inbp.siaf.component.vfp.Fields;
import pe.gob.inbp.siaf.component.vfp.Recordset;

@Repository
public class MaestrosDaoImpl extends JdbcDaoSupport implements MaestrosDao {

	@Value("${siaf.data}")
	private String folderSiafData;

	@Value("${siaf.data.mirror}")
	private String folderSiafDataMirror;

	@Value("${siaf.interfase}")
	private String folderSiafInterfase;

	public MaestrosDaoImpl(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	@Override
	public List<MaestroDocumento> getMaestroDocumentoAll() {
		List<MaestroDocumento> listMaestroDetalle = new ArrayList<MaestroDocumento>();

		String connectionString = AdoUtility.setConnectionString(folderSiafDataMirror);
		String query = "select * from maestro_documento";

		Recordset rs = new Recordset();
		rs.Open(new Variant(query), new Variant(connectionString));

		Fields fs = rs.getFields();

		rs.MoveFirst();
		while (!rs.getEOF()) {
			MaestroDocumento maestroDetalle = new MaestroDocumento();
			for (int i = 0; i < fs.getCount(); i++) {
				Field f = fs.getItem(i);

				String methodName = "set" + AdoUtility.setMethodName(f.getName());
				AdoUtility.setValueToObject(maestroDetalle, MaestroDocumento.class, methodName, "java.lang.String",
						f.getValue().getString().trim());
			}
			listMaestroDetalle.add(maestroDetalle);
			rs.MoveNext();
		}
		rs.Close();
		return listMaestroDetalle;
	}

	@Override
	public List<ProgramaPpto> getProgramaPptoAll(String ano_eje) {
		List<ProgramaPpto> lstProgPpto = new ArrayList<ProgramaPpto>();

		String connectionString = AdoUtility.setConnectionString(folderSiafDataMirror);
		String query = "SELECT programa_ppto as programa, nombre as programa_nombre from programa_ppto_nombre WHERE ano_eje = '"
				+ ano_eje + "'";

		Recordset rs = new Recordset();
		rs.Open(new Variant(query), new Variant(connectionString));

		if (!rs.getEOF()) {
			Fields fs = rs.getFields();

			rs.MoveFirst();

			while (!rs.getEOF()) {
				ProgramaPpto progPpto = new ProgramaPpto();

				progPpto.setPrograma_ppto(fs.getItem(0).getValue().getString().trim());
				progPpto.setPrograma_ppto_nombre(fs.getItem(1).getValue().getString().trim());

				lstProgPpto.add(progPpto);
				rs.MoveNext();
			}
		}
		rs.Close();
		return lstProgPpto;
	}

	@Override
	public List<Fuente_financ> getFuenteFinancAll(String ano_eje) {
		List<Fuente_financ> lstFuenteFinanc = new ArrayList<Fuente_financ>();

		String connectionString = AdoUtility.setConnectionString(folderSiafDataMirror);
		String query = "SELECT DISTINCT mpp.fuente_financ, f.nombre as fuente_financ_nombre FROM mpp_pca_x_especifica as mpp INNER JOIN fuente_financ as f ON mpp.ano_eje = f.ano_eje AND mpp.fuente_financ = f.fuente_financ   WHERE (mpp.monto_asignado-mpp.monto_comprometido) > 0 AND mpp.ano_eje = '"
				+ ano_eje + "'";

		Recordset rs = new Recordset();
		rs.Open(new Variant(query), new Variant(connectionString));

		if (!rs.getEOF()) {
			Fields fs = rs.getFields();

			rs.MoveFirst();

			while (!rs.getEOF()) {
				Fuente_financ fuenteFinanc = new Fuente_financ();

				fuenteFinanc.setFuente_financ(fs.getItem(0).getValue().getString().trim());
				fuenteFinanc.setFuente_financ_nombre(fs.getItem(1).getValue().getString().trim());

				lstFuenteFinanc.add(fuenteFinanc);
				rs.MoveNext();
			}
		}
		rs.Close();
		return lstFuenteFinanc;
	}

	@Override
	public Fuente_financ getBuscarFuenteFinanciamiento(String anio, String codigo_fuente) {
		Fuente_financ fuenteFinanc = new Fuente_financ();

		String connectionString = AdoUtility.setConnectionString(folderSiafDataMirror);
		String query = "SELECT fuente_financ, " + "nombre " + "from fuente_financ " + "WHERE ano_eje = '" + anio + "' "
				+ "AND fuente_financ = '" + codigo_fuente + "'";

		Recordset rs = new Recordset();
		rs.Open(new Variant(query), new Variant(connectionString));

		if (!rs.getEOF()) {
			Fields fs = rs.getFields();

			rs.MoveFirst();

			while (!rs.getEOF()) {

				fuenteFinanc.setFuente_financ(fs.getItem(0).getValue().getString().trim());
				fuenteFinanc.setFuente_financ_nombre(fs.getItem(1).getValue().getString().trim());

				rs.MoveNext();
			}
		}
		rs.Close();
		return fuenteFinanc;
	}

	@Override
	public List<TipoOperacion> getTipoOperacionComp(String anio) {
		List<TipoOperacion> lstTipoOpe = new ArrayList<TipoOperacion>();

		String connectionString = AdoUtility.setConnectionString(folderSiafDataMirror);
		String query = "SELECT tipo_operacion, " + "nombre " + "from tipo_operacion " + "WHERE ano_eje = '" + anio
				+ "' " + "AND estado = 'A' " + "AND ambito = 'G' " + "AND ciclo = 'G' "
				+ "AND Es_compromiso_anual = 'S'";

		Recordset rs = new Recordset();
		rs.Open(new Variant(query), new Variant(connectionString));

		if (!rs.getEOF()) {
			Fields fs = rs.getFields();

			rs.MoveFirst();

			while (!rs.getEOF()) {

				TipoOperacion tipooperacion = new TipoOperacion();

				tipooperacion.setTipo_operacion(fs.getItem(0).getValue().getString().trim());
				tipooperacion.setTipo_operacion_nombre(fs.getItem(1).getValue().getString().trim());

				lstTipoOpe.add(tipooperacion);

				rs.MoveNext();
			}
		}
		rs.Close();
		return lstTipoOpe;
	}

}
