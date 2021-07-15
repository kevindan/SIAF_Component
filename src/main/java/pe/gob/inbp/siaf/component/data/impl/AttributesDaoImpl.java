package pe.gob.inbp.siaf.component.data.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.jacob.com.Variant;

import pe.gob.inbp.siaf.component.data.AttributesDao;
import pe.gob.inbp.siaf.component.domain.CertCabConsulta;
import pe.gob.inbp.siaf.component.domain.CertCompAnual;
import pe.gob.inbp.siaf.component.domain.CertCompAnualDetalle;
import pe.gob.inbp.siaf.component.domain.CertDet;
import pe.gob.inbp.siaf.component.domain.CertificadoEstado;
import pe.gob.inbp.siaf.component.domain.ClasifPcaReq;
import pe.gob.inbp.siaf.component.domain.ClasifPcaResp;
import pe.gob.inbp.siaf.component.domain.ClasifSaldoPimReq;
import pe.gob.inbp.siaf.component.domain.ClasifSaldoPimResp;
import pe.gob.inbp.siaf.component.domain.ClasificadorCodigo;
import pe.gob.inbp.siaf.component.domain.ClasificadorGasto;
import pe.gob.inbp.siaf.component.domain.ClasificadorMeta;
import pe.gob.inbp.siaf.component.domain.ClasificadorMetaCarga;
import pe.gob.inbp.siaf.component.domain.ClasificadorMontoConsolidado;
import pe.gob.inbp.siaf.component.domain.ClasificadorMontoConsolidadoPim;
import pe.gob.inbp.siaf.component.domain.Error;
import pe.gob.inbp.siaf.component.domain.ExpedienteFase;
import pe.gob.inbp.siaf.component.domain.MetaCadena;
import pe.gob.inbp.siaf.component.domain.MetaPresupuestaria;
import pe.gob.inbp.siaf.component.domain.NotaModificatoria;
import pe.gob.inbp.siaf.component.domain.Presupuesto;
import pe.gob.inbp.siaf.component.domain.Tabla_siaf;
import pe.gob.inbp.siaf.component.payload.GenericResponse;
import pe.gob.inbp.siaf.component.payload.request.CertRequest;
import pe.gob.inbp.siaf.component.payload.request.ConsultaPCARequest;
import pe.gob.inbp.siaf.component.payload.request.GetMetaRequest;
import pe.gob.inbp.siaf.component.payload.request.IntfRequest;
import pe.gob.inbp.siaf.component.rowmapper.Tabla_siafRowMapper;
import pe.gob.inbp.siaf.component.utility.AdoUtility;
import pe.gob.inbp.siaf.component.vfp.Command;
import pe.gob.inbp.siaf.component.vfp.CommandTypeEnum;
import pe.gob.inbp.siaf.component.vfp.Connection;
import pe.gob.inbp.siaf.component.vfp.Field;
import pe.gob.inbp.siaf.component.vfp.Fields;
import pe.gob.inbp.siaf.component.vfp.Recordset;

@Repository
public class AttributesDaoImpl extends JdbcDaoSupport implements AttributesDao {

	@Value("${siaf.data}")
	private String folderSiafData;
	
	@Value("${siaf.data.mirror}")
	private String folderSiafDataMirror;
	
	@Value("${siaf.interfase}")
	private String folderSiafInterfase;
	
	private  JdbcTemplate jdbctemplate;
	
	public AttributesDaoImpl(DataSource dataSource) {
		this.setDataSource(dataSource);
		this.jdbctemplate = new JdbcTemplate(dataSource);
			
	}
	
	@Override
	public List<ExpedienteFase> getConsultaExpedienteSiaf(String anio, String cod_siaf, String cod_unid_ejec) {
	List<ExpedienteFase> lstExpediente = new ArrayList<ExpedienteFase>();
		
		String connectionString = AdoUtility.setConnectionString(folderSiafDataMirror);
		String query = "SELECT es.ano_eje,"
				+ "es.sec_ejec,"
				+ "es.secuencia,"
				+ "es.correlativo,"
				+ "es.expediente,"
				+ "es.ciclo,"
				+ "es.fase,"
				+ "es.monto_nacional,"
				+ "es.cod_doc,"
				+ "md.nombre as documento_nombre,"
				+ "es.num_doc,"
				+ "es.fecha_doc,"
				+ "es.estado,"
				+ "es.estado_envio "
				+ "FROM expediente_secuencia as es "
				+ "INNER JOIN maestro_documento as md ON es.cod_doc = md.cod_doc "
				+ "WHERE es.ciclo = 'G' "
				+ "and es.expediente = '"+cod_siaf+"' "
				+ "and es.ano_eje = '"+anio+"' "
				+ "AND es.sec_ejec = '"+cod_unid_ejec+"' "
				+ "order by es.secuencia,es.correlativo asc";
	
		Recordset rs = new Recordset();
		rs.Open(new Variant(query), new Variant(connectionString));
						
		if (!rs.getEOF()) {
			Fields fs = rs.getFields();
			
			rs.MoveFirst();
			
			while (!rs.getEOF()) {
				ExpedienteFase exp = new ExpedienteFase();

				String pattern = "dd/MM/yyyy";
				SimpleDateFormat format = new SimpleDateFormat(pattern);
				
				exp.setAno_eje(fs.getItem(0).getValue().getString().trim());
				exp.setSec_ejec(fs.getItem(1).getValue().getString().trim());
				exp.setSecuencia(fs.getItem(2).getValue().getString().trim());
				exp.setCorrelativo(fs.getItem(3).getValue().getString().trim());
				exp.setExpediente(fs.getItem(4).getValue().getString().trim());
				exp.setCiclo(fs.getItem(5).getValue().getString().trim());
				exp.setFase(fs.getItem(6).getValue().getString().trim());
				exp.setMonto_nacional(fs.getItem(7).getValue().getDecimal());				
				exp.setCod_doc(fs.getItem(8).getValue().getString().trim());
				exp.setDocumento_nombre(fs.getItem(9).getValue().getString().trim());
				exp.setNum_doc(fs.getItem(10).getValue().getString().trim());
				exp.setFecha_doc(format.format(fs.getItem(11).getValue().getJavaDate()));
				exp.setEstado(fs.getItem(12).getValue().getString().trim());
				exp.setEstado_envio(fs.getItem(13).getValue().getString().trim());
				lstExpediente.add(exp);
				rs.MoveNext();
			}	
		}	
		rs.Close();
		return lstExpediente;
	}

	@Override
	public List<MetaPresupuestaria> getMetaPresupuestaria(String anio, String cod_unid_ejec) {
	List<MetaPresupuestaria> lstMetaPresupuestaria = new ArrayList<MetaPresupuestaria>();
		
		String connectionString = AdoUtility.setConnectionString(folderSiafDataMirror);
		String query = "SELECT A.ano_eje,"
				+ "A.sec_ejec,"
				+ "A.sec_func,"
				+ "A.PROGRAMA_PPTO programa_ppto, "
				+ "PPN.NOMBRE AS programa_ppto_nombre,"
				+ "A.funcion,"
				+ "LTRIM(RTRIM(B.NOMBRE)) funcion_nombre, " 
				+ "A.programa as division_func,"
				+ "LTRIM(RTRIM(P.NOMBRE)) division_func_nombre, "
				+ "A.sub_programa as grupo_func, "
				+ "LTRIM(RTRIM(S.NOMBRE)) grupo_func_nombre, "
				+ "A.componente as act_ai_obr, "
				+ "C.nombre as act_ai_obr_nombre,"
				+ "A.act_proy as prod_proy, "
				+ "ap.nombre as prod_proy_nombre,"
				+ "A.meta,"
				+ "LTRIM(RTRIM(F.NOMBRE)) nombre_meta,"
				+ "A.finalidad, "
				+ "A.unidad_medida, "
				+ "LTRIM(RTRIM(U.NOMBRE)) unidad_medida_nombre "
				+ "FROM META A "
				+ "INNER JOIN FUNCION B ON A.ANO_EJE=B.ANO_EJE AND A.FUNCION=B.FUNCION "
				+ "INNER JOIN PROGRAMA_NOMBRE P on A.ANO_EJE=P.ANO_EJE AND A.PROGRAMA=P.PROGRAMA "
				+ "INNER JOIN SUB_PROGRAMA_NOMBRE S on A.ANO_EJE=S.ANO_EJE AND A.SUB_PROGRAMA=S.SUB_PROGRAMA "
				+ "INNER JOIN FINALIDAD F on A.ANO_EJE=F.ANO_EJE AND A.FINALIDAD=F.FINALIDAD "
				+ "INNER JOIN UNIDAD_MEDIDA U ON A.UNIDAD_MEDIDA = U.UNIDAD_MEDIDA "
				+ "INNER JOIN componente_nombre as C ON A.ANO_EJE = C.ANO_EJE AND A.COMPONENTE = C.COMPONENTE "
				+ "INNER JOIN act_proy_nombre as ap ON A.ANO_EJE = AP.ANO_EJE AND A.ACT_PROY = AP.ACT_PROY "
				+ "INNER JOIN PROGRAMA_PPTO_NOMBRE AS PPN ON A.ANO_EJE = PPN.ANO_EJE AND A.PROGRAMA_PPTO = PPN.PROGRAMA_PPTO "
				+ "WHERE A.ANO_EJE='"+anio+"' AND A.SEC_EJEC='"+cod_unid_ejec+"' "
				+ "ORDER BY A.ano_eje,A.sec_ejec,A.sec_func asc";
		
		Recordset rs = new Recordset();
		rs.Open(new Variant(query), new Variant(connectionString));
						
		if (!rs.getEOF()) {
			Fields fs = rs.getFields();
			
			rs.MoveFirst();
			
			while (!rs.getEOF()) {
				MetaPresupuestaria meta = new MetaPresupuestaria();
			
				meta.setAno_eje(fs.getItem(0).getValue().getString().trim());
				meta.setSec_ejec(fs.getItem(1).getValue().getString().trim());
				meta.setSec_func(fs.getItem(2).getValue().getString().trim());
				meta.setPrograma_ppto(fs.getItem(3).getValue().getString().trim());
				meta.setPrograma_ppto_nombre(fs.getItem(4).getValue().getString().trim());				
				meta.setFuncion(fs.getItem(5).getValue().getString().trim());
				meta.setFuncion_nombre(fs.getItem(6).getValue().getString().trim());				
				meta.setDivision_func(fs.getItem(7).getValue().getString().trim());
				meta.setDivision_func_nombre(fs.getItem(8).getValue().getString().trim());				
				meta.setGrupo_func(fs.getItem(9).getValue().getString().trim());
				meta.setGrupo_func_nombre(fs.getItem(10).getValue().getString().trim());				
				meta.setAct_ai_obr(fs.getItem(11).getValue().getString().trim());
				meta.setAct_ai_obr_nombre(fs.getItem(12).getValue().getString().trim());
				meta.setProd_proy(fs.getItem(13).getValue().getString().trim());
				meta.setProd_proy_nombre(fs.getItem(14).getValue().getString().trim());								
				meta.setMeta(fs.getItem(15).getValue().getString().trim());
				meta.setNombre_meta(fs.getItem(16).getValue().getString().trim());
				meta.setFinalidad(fs.getItem(17).getValue().getString().trim());
				meta.setUnidad_medida(fs.getItem(18).getValue().getString().trim());
				meta.setUnidad_medida_nombre(fs.getItem(19).getValue().getString().trim());
														
				lstMetaPresupuestaria.add(meta);
				rs.MoveNext();
			}		
		}
		rs.Close();
		return lstMetaPresupuestaria;
	}
	
	@Override
	public List<MetaPresupuestaria> getMetaPresupuestaria(GetMetaRequest request) {
	List<MetaPresupuestaria> lstMetaPresupuestaria = new ArrayList<MetaPresupuestaria>();
		
		String connectionString = AdoUtility.setConnectionString(folderSiafDataMirror);
		String query = "SELECT A.ano_eje,"
				+ "A.sec_ejec,"
				+ "A.sec_func,"
				+ "A.PROGRAMA_PPTO programa_ppto, "
				+ "PPN.NOMBRE AS programa_ppto_nombre,"
				+ "A.funcion,"
				+ "LTRIM(RTRIM(B.NOMBRE)) funcion_nombre, " 
				+ "A.programa as division_func,"
				+ "LTRIM(RTRIM(P.NOMBRE)) division_func_nombre, "
				+ "A.sub_programa as grupo_func, "
				+ "LTRIM(RTRIM(S.NOMBRE)) grupo_func_nombre, "
				+ "A.componente as act_ai_obr, "
				+ "C.nombre as act_ai_obr_nombre,"
				+ "A.act_proy as prod_proy, "
				+ "ap.nombre as prod_proy_nombre,"
				+ "A.meta,LTRIM(RTRIM(F.NOMBRE)) nombre_meta,"
				+ "A.finalidad, "
				+ "A.unidad_medida, "
				+ "LTRIM(RTRIM(U.NOMBRE)) unidad_medida_nombre "
				+ "FROM META A "
				+ "INNER JOIN FUNCION B ON A.ANO_EJE=B.ANO_EJE AND A.FUNCION=B.FUNCION "
				+ "INNER JOIN PROGRAMA_NOMBRE P on A.ANO_EJE=P.ANO_EJE AND A.PROGRAMA=P.PROGRAMA "
				+ "INNER JOIN SUB_PROGRAMA_NOMBRE S on A.ANO_EJE=S.ANO_EJE AND A.SUB_PROGRAMA=S.SUB_PROGRAMA "
				+ "INNER JOIN FINALIDAD F on A.ANO_EJE=F.ANO_EJE AND A.FINALIDAD=F.FINALIDAD "
				+ "INNER JOIN UNIDAD_MEDIDA U ON A.UNIDAD_MEDIDA = U.UNIDAD_MEDIDA "
				+ "INNER JOIN componente_nombre as C ON A.ANO_EJE = C.ANO_EJE AND A.COMPONENTE = C.COMPONENTE "
				+ "INNER JOIN act_proy_nombre as ap ON A.ANO_EJE = AP.ANO_EJE AND A.ACT_PROY = AP.ACT_PROY "
				+ "INNER JOIN PROGRAMA_PPTO_NOMBRE AS PPN ON A.ANO_EJE = PPN.ANO_EJE AND A.PROGRAMA_PPTO = PPN.PROGRAMA_PPTO "
				+ "WHERE A.ANO_EJE='"+request.getAnio()+"' "
				+ "AND A.SEC_EJEC='"+request.getCod_unid_ejec()+"' "
				+ "AND A.SEC_FUNC not in ("+request.getCodExcluidos()+") "
				+ "ORDER BY A.ano_eje,A.sec_ejec,A.sec_func asc";
		
		System.out.println("SQL : "+query);
		
		Recordset rs = new Recordset();
		rs.Open(new Variant(query), new Variant(connectionString));
						
		if (!rs.getEOF()) {
			Fields fs = rs.getFields();
			
			rs.MoveFirst();
			
			while (!rs.getEOF()) {
				MetaPresupuestaria meta = new MetaPresupuestaria();
			
				meta.setAno_eje(fs.getItem(0).getValue().getString().trim());
				meta.setSec_ejec(fs.getItem(1).getValue().getString().trim());
				meta.setSec_func(fs.getItem(2).getValue().getString().trim());
				meta.setPrograma_ppto(fs.getItem(3).getValue().getString().trim());
				meta.setPrograma_ppto_nombre(fs.getItem(4).getValue().getString().trim());				
				meta.setFuncion(fs.getItem(5).getValue().getString().trim());
				meta.setFuncion_nombre(fs.getItem(6).getValue().getString().trim());				
				meta.setDivision_func(fs.getItem(7).getValue().getString().trim());
				meta.setDivision_func_nombre(fs.getItem(8).getValue().getString().trim());				
				meta.setGrupo_func(fs.getItem(9).getValue().getString().trim());
				meta.setGrupo_func_nombre(fs.getItem(10).getValue().getString().trim());				
				meta.setAct_ai_obr(fs.getItem(11).getValue().getString().trim());
				meta.setAct_ai_obr_nombre(fs.getItem(12).getValue().getString().trim());
				meta.setProd_proy(fs.getItem(13).getValue().getString().trim());
				meta.setProd_proy_nombre(fs.getItem(14).getValue().getString().trim());								
				meta.setMeta(fs.getItem(15).getValue().getString().trim());
				meta.setNombre_meta(fs.getItem(16).getValue().getString().trim());
				meta.setFinalidad(fs.getItem(17).getValue().getString().trim());
				meta.setUnidad_medida(fs.getItem(18).getValue().getString().trim());
				meta.setUnidad_medida_nombre(fs.getItem(19).getValue().getString().trim());
														
				lstMetaPresupuestaria.add(meta);
				rs.MoveNext();
			}		
		}
		rs.Close();
		return lstMetaPresupuestaria;
	}

	@Override
	public List<Tabla_siaf> getTablasSiaf() {
		List<Tabla_siaf> lstTablas = new  ArrayList<Tabla_siaf>();
		String sql = "select distinct nombre_tabla from tabla_siaf";
		try {
			RowMapper<Tabla_siaf> rowMapper = new Tabla_siafRowMapper(); 
					lstTablas = getJdbcTemplate().query(sql, rowMapper);
		} catch (Exception e) {
			logger.error(e.getMessage());			
		}		
		return lstTablas;
	}
	
	@Override
	public List<ClasificadorMetaCarga> getClasificadorMetaCarga(String anio, String cod_unid_ejec) {
		List<ClasificadorMetaCarga> lstClasificador = new ArrayList<ClasificadorMetaCarga>();
		
		String connectionString = AdoUtility.setConnectionString(folderSiafDataMirror);
		String query = "SELECT "
				+ "g.ano_eje,"
				+ "g.sec_ejec,"
				+ "g.sec_func,"
				+ "g.fuente_financ,"
				+ "ff.nombre fuente_financiamiento_nombre,"
				+ "ed.tipo_transaccion,"
				+ "LTRIM(RTRIM(tt.descripcion)) tipo_transaccion_nombre,"
				+ "ed.generica,"
				+ "ge.descripcion generica_nombre,"
				+ "ed.subgenerica,"
				+ "sg.descripcion subgenerica_nombre,"
				+ "ed.subgenerica_det,"
				+ "sgd.descripcion subgenerica_det_nombre,"
				+ "ed.especifica,"
				+ "e.descripcion especifica_nombre,"
				+ "ed.especifica_det,"
				+ "ed.tipo_transaccion+'.'+ed.generica+'.'+ed.subgenerica+ ed.subgenerica_det+'.'+ed.especifica+ed.especifica_det as cod_clasificador , "
				+ "ed.id_clasificador,"
				+ "ed.descripcion as nombre_clasificador "
				+ "from gasto as g "
				+ "INNER JOIN fuente_financ as ff ON g.ano_eje = ff.ano_eje AND g.fuente_financ=ff.fuente_financ "
				+ "INNER JOIN especifica_det as ed ON g.ano_eje = ed.ano_eje AND g.id_clasificador=ed.id_clasificador "
				+ "INNER JOIN tipo_transaccion as tt ON ed.ano_eje = tt.ano_eje AND ed.tipo_transaccion = tt.tipo_transaccion "
				+ "INNER JOIN generica ge ON ed.ano_eje = ge.ano_eje AND ed.tipo_transaccion = ge.tipo_transaccion AND ed.generica = ge.generica "
				+ "INNER JOIN subgenerica sg ON ed.ano_eje = sg.ano_eje AND ed.tipo_transaccion = sg.tipo_transaccion AND ed.generica = sg.generica AND ed.subgenerica = sg.subgenerica  "
				+ "INNER JOIN subgenerica_det sgd ON ed.ano_eje = sgd.ano_eje AND ed.tipo_transaccion = sgd.tipo_transaccion AND ed.generica = sgd.generica AND ed.subgenerica = sgd.subgenerica AND ed.subgenerica_det = sgd.subgenerica_det "
				+ "INNER JOIN especifica e ON ed.ano_eje = e.ano_eje AND ed.tipo_transaccion = e.tipo_transaccion AND ed.generica = e.generica AND ed.subgenerica = e.subgenerica and ed.subgenerica_det = e.subgenerica_det AND ed.especifica = e.especifica "
				+ "WHERE g.ano_eje = '"+anio+"' "
				+ "AND g.sec_ejec = '"+cod_unid_ejec+"' "
				+ "ORDER BY g.ano_eje, g.sec_func, ed.tipo_transaccion, ed.generica, ed.subgenerica, ed.subgenerica_det, ed.especifica, ed.especifica_det asc";
		
		Recordset rs = new Recordset();
		rs.Open(new Variant(query), new Variant(connectionString));
						
		if (!rs.getEOF()) {
			Fields fs = rs.getFields();
			
			rs.MoveFirst();
			
			while (!rs.getEOF()) {
				ClasificadorMetaCarga clasificador = new ClasificadorMetaCarga();
			
				clasificador.setAno_eje(fs.getItem(0).getValue().getString().trim());
				clasificador.setSec_ejec(fs.getItem(1).getValue().getString().trim());
				clasificador.setSec_func(fs.getItem(2).getValue().getString().trim());
				
				clasificador.setFuente_financ(fs.getItem(3).getValue().getString().trim());
				clasificador.setFuente_financ_nombre(fs.getItem(4).getValue().getString().trim());
				clasificador.setTipo_transaccion(fs.getItem(5).getValue().getString().trim());
				clasificador.setTipo_transaccion_nombre(fs.getItem(6).getValue().getString().trim());
				clasificador.setGenerica(fs.getItem(7).getValue().getString().trim());
				clasificador.setGenerica_nombre(fs.getItem(8).getValue().getString().trim());
				clasificador.setSubgenerica(fs.getItem(9).getValue().getString().trim());
				clasificador.setSubgenerica_nombre(fs.getItem(10).getValue().getString().trim());
				clasificador.setSubgenerica_det(fs.getItem(11).getValue().getString().trim());
				clasificador.setSubgenerica_det_nombre(fs.getItem(12).getValue().getString().trim());
				clasificador.setEspecifica(fs.getItem(13).getValue().getString().trim());
				clasificador.setEspecifica_nombre(fs.getItem(14).getValue().getString().trim());
				clasificador.setEspecifica_det(fs.getItem(15).getValue().getString().trim());
				clasificador.setCod_clasificador(fs.getItem(16).getValue().getString().trim());
				clasificador.setId_clasificador(fs.getItem(17).getValue().getString().trim());
				clasificador.setNombre_clasificador(fs.getItem(18).getValue().getString().trim());
								
				lstClasificador.add(clasificador);
				rs.MoveNext();
			}	
		}
		rs.Close();
		return lstClasificador;
	}

	@Override
	public List<ClasificadorMeta> getClasificadorMeta(String anio, String cod_unid_ejec, String sec_func, String fuente_financ) {
		List<ClasificadorMeta> lstClasificador = new ArrayList<ClasificadorMeta>();
		
		String connectionString = AdoUtility.setConnectionString(folderSiafDataMirror);
		String query = "SELECT g.fuente_financ,"
				+ "ff.nombre fuente_financiamiento_nombre,"
				+ "ed.tipo_transaccion,"
				+ "LTRIM(RTRIM(tt.descripcion)) tipo_transaccion_nombre,"
				+ "ed.generica,"
				+ "ge.descripcion generica_nombre,"
				+ "ed.subgenerica,"
				+ "sg.descripcion subgenerica_nombre,"
				+ "ed.subgenerica_det,"
				+ "sgd.descripcion subgenerica_det_nombre,"
				+ "ed.especifica,"
				+ "e.descripcion especifica_nombre,"
				+ "ed.especifica_det,"
				+ "ed.tipo_transaccion+'.'+ed.generica+'.'+ed.subgenerica+ ed.subgenerica_det+'.'+ed.especifica+ed.especifica_det as cod_clasificador , "
				+ "ed.id_clasificador,"
				+ "ed.descripcion as nombre_clasificador, "
				+ "g.presupuesto pia, "
				+ "g.modificacion, "
				+ "g.presupuesto+g.modificacion pim,  "
				+ "g.compromiso, "
				+ "g.devengado, "
				+ "g.girado, "
				+ "g.pagado, "
				+ "g.monto_certificado, "
				+ "g.monto_comprometido_anual, "
				+ "(mpp.monto_asignado - mpp.monto_comprometido) as saldo_pca "
				+ "from gasto as g "
				+ "INNER JOIN fuente_financ as ff ON g.ano_eje = ff.ano_eje AND g.fuente_financ=ff.fuente_financ "
				+ "INNER JOIN especifica_det as ed ON g.ano_eje = ed.ano_eje AND g.id_clasificador=ed.id_clasificador "
				+ "INNER JOIN tipo_transaccion as tt ON ed.ano_eje = tt.ano_eje AND ed.tipo_transaccion = tt.tipo_transaccion "
				+ "INNER JOIN generica ge ON ed.ano_eje = ge.ano_eje AND ed.tipo_transaccion = ge.tipo_transaccion AND ed.generica = ge.generica "
				+ "INNER JOIN subgenerica sg ON ed.ano_eje = sg.ano_eje AND ed.tipo_transaccion = sg.tipo_transaccion AND ed.generica = sg.generica AND ed.subgenerica = sg.subgenerica  "
				+ "INNER JOIN subgenerica_det sgd ON ed.ano_eje = sgd.ano_eje AND ed.tipo_transaccion = sgd.tipo_transaccion AND ed.generica = sgd.generica AND ed.subgenerica = sgd.subgenerica AND ed.subgenerica_det = sgd.subgenerica_det "
				+ "INNER JOIN especifica e ON ed.ano_eje = e.ano_eje AND ed.tipo_transaccion = e.tipo_transaccion AND ed.generica = e.generica AND ed.subgenerica = e.subgenerica and ed.subgenerica_det = e.subgenerica_det AND ed.especifica = e.especifica "
				
				+ "LEFT OUTER JOIN mpp_pca_x_especifica mpp ON e.ano_eje = mpp.ano_eje AND g.sec_ejec = mpp.sec_ejec AND g.fuente_financ = mpp.fuente_financ AND ed.id_clasificador = mpp.id_clasificador "
				
				+ "WHERE g.ano_eje = '"+anio+"' "
				+ "AND g.sec_ejec = '"+cod_unid_ejec+"' "
				+ "AND g.sec_func = '"+sec_func+"' "
				+ "AND g.fuente_financ = '"+fuente_financ+"' "
				+ "ORDER BY ed.tipo_transaccion, ed.generica, ed.subgenerica, ed.subgenerica_det, ed.especifica, ed.especifica_det asc";
		
		Recordset rs = new Recordset();
		rs.Open(new Variant(query), new Variant(connectionString));
						
		if (!rs.getEOF()) {
			Fields fs = rs.getFields();
			
			rs.MoveFirst();
			
			while (!rs.getEOF()) {
				ClasificadorMeta clasificador = new ClasificadorMeta();
			
				clasificador.setFuente_financ(fs.getItem(0).getValue().getString().trim());
				clasificador.setFuente_financ_nombre(fs.getItem(1).getValue().getString().trim());
				clasificador.setTipo_transaccion(fs.getItem(2).getValue().getString().trim());
				clasificador.setTipo_transaccion_nombre(fs.getItem(3).getValue().getString().trim());
				clasificador.setGenerica(fs.getItem(4).getValue().getString().trim());
				clasificador.setGenerica_nombre(fs.getItem(5).getValue().getString().trim());
				clasificador.setSubgenerica(fs.getItem(6).getValue().getString().trim());
				clasificador.setSubgenerica_nombre(fs.getItem(7).getValue().getString().trim());
				clasificador.setSubgenerica_det(fs.getItem(8).getValue().getString().trim());
				clasificador.setSubgenerica_det_nombre(fs.getItem(9).getValue().getString().trim());
				clasificador.setEspecifica(fs.getItem(10).getValue().getString().trim());
				clasificador.setEspecifica_nombre(fs.getItem(11).getValue().getString().trim());
				clasificador.setEspecifica_det(fs.getItem(12).getValue().getString().trim());
				clasificador.setCod_clasificador(fs.getItem(13).getValue().getString().trim());
				clasificador.setId_clasificador(fs.getItem(14).getValue().getString().trim());
				clasificador.setNombre_clasificador(fs.getItem(15).getValue().getString().trim());
				clasificador.setPia(fs.getItem(16).getValue().getDecimal());				
				clasificador.setModificacion(fs.getItem(17).getValue().getDecimal());
				clasificador.setPim(fs.getItem(18).getValue().getDecimal());
				clasificador.setCompromiso(fs.getItem(19).getValue().getDecimal());
				clasificador.setDevengado(fs.getItem(20).getValue().getDecimal());
				clasificador.setGirado(fs.getItem(21).getValue().getDecimal());
				clasificador.setPagado(fs.getItem(22).getValue().getDecimal());				
				clasificador.setMonto_certificado(fs.getItem(23).getValue().getDecimal());
				clasificador.setMonto_comprometido_anual(fs.getItem(24).getValue().getDecimal());
				clasificador.setSaldo_pca(fs.getItem(25).getValue().getDecimal());
								
				lstClasificador.add(clasificador);
				rs.MoveNext();
			}	
		}	
		rs.Close();
		return lstClasificador;
	}

	@Override
	public String cargaMetaPresupuestaria(String anio, String cod_unid_ejec)throws Exception {		
		String msg = "0000";
		String connectionString = AdoUtility.setConnectionString(folderSiafDataMirror);
		String query = "SELECT "
				+ "A.ano_eje+A.sec_ejec+A.sec_func,"
				+ "A.ano_eje,"
				+ "A.sec_ejec,"
				+ "A.sec_func,"
				+ "A.PROGRAMA_PPTO programa_ppto, "
				+ "PPN.NOMBRE AS programa_ppto_nombre,"
				+ "A.funcion,"
				+ "LTRIM(RTRIM(B.NOMBRE)) funcion_nombre, " 
				+ "A.programa as division_func,"
				+ "LTRIM(RTRIM(P.NOMBRE)) division_func_nombre, "
				+ "A.sub_programa as grupo_func, "
				+ "LTRIM(RTRIM(S.NOMBRE)) grupo_func_nombre, "
				+ "A.componente as act_ai_obr, "
				+ "C.nombre as act_ai_obr_nombre,"
				+ "A.act_proy as prod_proy, "
				+ "ap.nombre as prod_proy_nombre,"
				+ "A.meta,LTRIM(RTRIM(F.NOMBRE)) nombre_meta,"
				+ "A.finalidad, "
				+ "A.unidad_medida, "
				+ "LTRIM(RTRIM(U.NOMBRE)) unidad_medida_nombre "
				+ "FROM META A "
				+ "INNER JOIN FUNCION B ON A.ANO_EJE=B.ANO_EJE AND A.FUNCION=B.FUNCION "
				+ "INNER JOIN PROGRAMA_NOMBRE P on A.ANO_EJE=P.ANO_EJE AND A.PROGRAMA=P.PROGRAMA "
				+ "INNER JOIN SUB_PROGRAMA_NOMBRE S on A.ANO_EJE=S.ANO_EJE AND A.SUB_PROGRAMA=S.SUB_PROGRAMA "
				+ "INNER JOIN FINALIDAD F on A.ANO_EJE=F.ANO_EJE AND A.FINALIDAD=F.FINALIDAD "
				+ "INNER JOIN UNIDAD_MEDIDA U ON A.UNIDAD_MEDIDA = U.UNIDAD_MEDIDA "
				+ "INNER JOIN componente_nombre as C ON A.ANO_EJE = C.ANO_EJE AND A.COMPONENTE = C.COMPONENTE "
				+ "INNER JOIN act_proy_nombre as ap ON A.ANO_EJE = AP.ANO_EJE AND A.ACT_PROY = AP.ACT_PROY "
				+ "INNER JOIN PROGRAMA_PPTO_NOMBRE AS PPN ON A.ANO_EJE = PPN.ANO_EJE AND A.PROGRAMA_PPTO = PPN.PROGRAMA_PPTO "
				+ "WHERE A.ANO_EJE='"+anio+"' AND A.SEC_EJEC='"+cod_unid_ejec+"' "
				+ "ORDER BY A.ano_eje,A.sec_ejec,A.sec_func asc";
		
		Recordset rs = new Recordset();
		rs.Open(new Variant(query), new Variant(connectionString));
						
		if (!rs.getEOF()) {
			Fields fs = rs.getFields();
			
			rs.MoveFirst();
			
			while (!rs.getEOF()) {
				MetaPresupuestaria meta = new MetaPresupuestaria();
			
				meta.setId_meta_presupuestal(fs.getItem(0).getValue().getString().trim());
				meta.setAno_eje(fs.getItem(1).getValue().getString().trim());
				meta.setSec_ejec(fs.getItem(2).getValue().getString().trim());
				meta.setSec_func(fs.getItem(3).getValue().getString().trim());
				meta.setPrograma_ppto(fs.getItem(4).getValue().getString().trim());
				meta.setPrograma_ppto_nombre(fs.getItem(5).getValue().getString().trim());				
				meta.setFuncion(fs.getItem(6).getValue().getString().trim());
				meta.setFuncion_nombre(fs.getItem(7).getValue().getString().trim());				
				meta.setDivision_func(fs.getItem(8).getValue().getString().trim());
				meta.setDivision_func_nombre(fs.getItem(9).getValue().getString().trim());				
				meta.setGrupo_func(fs.getItem(10).getValue().getString().trim());
				meta.setGrupo_func_nombre(fs.getItem(11).getValue().getString().trim());				
				meta.setAct_ai_obr(fs.getItem(12).getValue().getString().trim());
				meta.setAct_ai_obr_nombre(fs.getItem(13).getValue().getString().trim());
				meta.setProd_proy(fs.getItem(14).getValue().getString().trim());
				meta.setProd_proy_nombre(fs.getItem(15).getValue().getString().trim());								
				meta.setMeta(fs.getItem(16).getValue().getString().trim());
				meta.setNombre_meta(fs.getItem(17).getValue().getString().trim());
				meta.setFinalidad(fs.getItem(18).getValue().getString().trim());
				meta.setUnidad_medida(fs.getItem(19).getValue().getString().trim());
				meta.setUnidad_medida_nombre(fs.getItem(20).getValue().getString().trim());
				
				msg = this.insertarMetaPresupuestaria(meta);
				if(msg.equals("9000")) {
					return msg;
				}
				rs.MoveNext();
			}	
		}	
		rs.Close();
		return msg;
	}
	
	public String insertarMetaPresupuestaria(MetaPresupuestaria meta) throws Exception {		
		String rpta = "0000";
		System.out.println("Trama a ingresar : "+meta.toString());
		String sql = "INSERT INTO meta_presupuestal("
				+"	id_meta_presupuestal,ano_eje, sec_ejec, sec_func, programa_ppto, programa_ppto_nombre, "
				+ "funcion, funcion_nombre, division_func, division_func_nombre,"
				+ " grupo_func, grupo_func_nombre, act_ai_obr, act_ai_obr_nombre,"
				+ " prod_proy, prod_proy_nombre, meta, nombre_meta, finalidad,"
				+ " unidad_medida, unidad_medida_nombre)" + 
				"	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			jdbctemplate.update(sql, new Object[] {meta.getId_meta_presupuestal(),meta.getAno_eje(),meta.getSec_ejec(), meta.getSec_func(),meta.getPrograma_ppto(),meta.getPrograma_ppto_nombre(),
					meta.getFuncion(), meta.getFuncion_nombre(), meta.getDivision_func(), meta.getDivision_func_nombre(),
					meta.getGrupo_func(), meta.getGrupo_func_nombre(), meta.getAct_ai_obr(), meta.getAct_ai_obr_nombre(),
					meta.getProd_proy(), meta.getProd_proy_nombre(), meta.getMeta(), meta.getNombre_meta(), meta.getFinalidad(),
					meta.getUnidad_medida(), meta.getUnidad_medida_nombre()
			});
		} catch (Exception e) {			
			System.out.println("Error : "+e.getMessage());
			rpta = "9000";
		}
		return rpta;
	}

	@Override
	public String cargaClasificadorGasto(String anio) throws Exception {
		String msg = "0000";	
		String connectionString = AdoUtility.setConnectionString(folderSiafDataMirror);
		String query = "SELECT 	ed.ano_eje+ltrim(rtrim(ed.tipo_transaccion))+ltrim(rtrim(ed.generica))+ltrim(rtrim(ed.subgenerica))+ltrim(rtrim(ed.subgenerica_det))+ltrim(rtrim(ed.especifica))+ltrim(rtrim(ed.especifica_det)) as id_clasificador_gasto,"
				+ "ed.ano_eje, "
				+ "ed.tipo_transaccion,"
				+ "LTRIM(RTRIM(tt.descripcion)) tipo_transaccion_nombre,"
				+ "ed.generica,"
				+ "ge.descripcion generica_nombre,"
				+ "ed.subgenerica,"
				+ "sg.descripcion subgenerica_nombre,"
				+ "ed.subgenerica_det,"
				+ "sgd.descripcion subgenerica_det_nombre,"
				+ "ed.especifica,"
				+ "e.descripcion especifica_nombre,"
				+ "ed.especifica_det,"
				+ "ed.id_clasificador,"
				+ "ed.descripcion as nombre_clasificador "
				+ "from especifica_det as ed "
				+ "INNER JOIN tipo_transaccion as tt ON ed.ano_eje = tt.ano_eje AND ed.tipo_transaccion = tt.tipo_transaccion "
				+ "INNER JOIN generica ge ON ed.ano_eje = ge.ano_eje AND ed.tipo_transaccion = ge.tipo_transaccion AND ed.generica = ge.generica "
				+ "INNER JOIN subgenerica sg ON ed.ano_eje = sg.ano_eje AND ed.tipo_transaccion = sg.tipo_transaccion AND ed.generica = sg.generica AND ed.subgenerica = sg.subgenerica "
				+ "INNER JOIN subgenerica_det sgd ON ed.ano_eje = sgd.ano_eje AND ed.tipo_transaccion = sgd.tipo_transaccion AND ed.generica = sgd.generica AND ed.subgenerica = sgd.subgenerica AND ed.subgenerica_det = sgd.subgenerica_det "
				+ "INNER JOIN especifica e ON ed.ano_eje = e.ano_eje AND ed.tipo_transaccion = e.tipo_transaccion AND ed.generica = e.generica AND ed.subgenerica = e.subgenerica and ed.subgenerica_det = e.subgenerica_det AND ed.especifica = e.especifica "
				+ "WHERE ed.ano_eje = '"+anio+"' "
				+ "ORDER BY ed.ano_eje, ed.tipo_transaccion, ed.generica, ed.subgenerica, ed.subgenerica_det, ed.especifica, ed.especifica_det asc";
		
		Recordset rs = new Recordset();
		rs.Open(new Variant(query), new Variant(connectionString));
						
		if (!rs.getEOF()) {
			Fields fs = rs.getFields();
			
			rs.MoveFirst();
			
			while (!rs.getEOF()) {
				ClasificadorGasto clasificador = new ClasificadorGasto();
			
				clasificador.setId_clasificador_gasto(fs.getItem(0).getValue().getString().trim());
				clasificador.setAno_eje(fs.getItem(1).getValue().getString().trim());
				clasificador.setTipo_transaccion(fs.getItem(2).getValue().getString().trim());
				clasificador.setTipo_transaccion_nombre(fs.getItem(3).getValue().getString().trim());
				clasificador.setGenerica(fs.getItem(4).getValue().getString().trim());
				clasificador.setGenerica_nombre(fs.getItem(5).getValue().getString().trim());
				clasificador.setSubgenerica(fs.getItem(6).getValue().getString().trim());
				clasificador.setSubgenerica_nombre(fs.getItem(7).getValue().getString().trim());
				clasificador.setSubgenerica_det(fs.getItem(8).getValue().getString().trim());
				clasificador.setSubgenerica_det_nombre(fs.getItem(9).getValue().getString().trim());
				clasificador.setEspecifica(fs.getItem(10).getValue().getString().trim());
				clasificador.setEspecifica_nombre(fs.getItem(11).getValue().getString().trim());
				clasificador.setEspecifica_det(fs.getItem(12).getValue().getString().trim());
				clasificador.setId_clasificador(fs.getItem(13).getValue().getString().trim());
				clasificador.setNombre_clasificador(fs.getItem(14).getValue().getString().trim());
												
				msg = this.insertaClasificadorGasto(clasificador);
				if(msg.equals("9000")) {
					return msg;
				}
				
				rs.MoveNext();
			}	
		}	
		rs.Close();
		return msg;
	}
	
	public String insertaClasificadorGasto(ClasificadorGasto clasificador) throws Exception {		
		String rpta = "0000";
		String sql = "INSERT INTO clasificador_gasto("
				+ "id_clasificador_gasto, "
				+ "ano_eje, "
				+ "tipo_transaccion, "
				+ "tipo_transaccion_nombre, "
				+ "generica, "
				+ "generica_nombre, "
				+ "subgenerica, "
				+ "subgenerica_nombre, "
				+ "subgenerica_det, "
				+ "subgenerica_det_nombre, "
				+ "especifica, "
				+ "especifica_nombre, "
				+ "especifica_det, "
				+ "id_clasificador, "
				+ "nombre_clasificador) " 
				+ "	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			jdbctemplate.update(sql, new Object[] {
					clasificador.getId_clasificador_gasto(),clasificador.getAno_eje(),clasificador.getTipo_transaccion(),
					clasificador.getTipo_transaccion_nombre(), clasificador.getGenerica(), clasificador.getGenerica_nombre(),
					clasificador.getSubgenerica(), clasificador.getSubgenerica_nombre(),clasificador.getSubgenerica_det(),
					clasificador.getSubgenerica_det_nombre(),clasificador.getEspecifica(), clasificador.getEspecifica_nombre(),
					clasificador.getEspecifica_det(), clasificador.getId_clasificador(), clasificador.getNombre_clasificador()
			
			});
		} catch (Exception e) {			
			System.out.println("Error : "+e.getMessage());
			rpta = "9000";
		}
		return rpta;
	}

	@Override
	public String cargaPresupuesto(String anio, String cod_unid_ejec) throws Exception {
		String msg = "0000";	
		String connectionString = AdoUtility.setConnectionString(folderSiafDataMirror);
		String query = "SELECT (m.ano_eje+m.sec_ejec+m.sec_func+ed.ano_eje+ltrim(rtrim(ed.tipo_transaccion))+ltrim(rtrim(ed.generica))+ltrim(rtrim(ed.subgenerica))+ltrim(rtrim(ed.subgenerica_det))+ltrim(rtrim(ed.especifica))+ltrim(rtrim(ed.especifica_det))+g.fuente_financ) as id_presupuesto,"
				+ "(m.ano_eje+m.sec_ejec+m.sec_func) as id_meta_presupuestal, "
				+ "ed.ano_eje+ltrim(rtrim(ed.tipo_transaccion))+ltrim(rtrim(ed.generica))+ltrim(rtrim(ed.subgenerica))+ltrim(rtrim(ed.subgenerica_det))+ltrim(rtrim(ed.especifica))+ltrim(rtrim(ed.especifica_det)) as id_clasificador_gasto, "
				+ "g.fuente_financ, "
				+ "LTRIM(RTRIM(ff.nombre)) fuente_financ_nombre, "
				+ "g.presupuesto as monto "
				+ "from gasto as g "
				+ "INNER JOIN meta as m ON g.ano_eje = m.ano_eje AND g.sec_ejec=m.sec_ejec AND g.sec_func=m.sec_func "
				+ "INNER JOIN fuente_financ as ff ON g.ano_eje = ff.ano_eje AND g.fuente_financ=ff.fuente_financ "
				+ "INNER JOIN especifica_det as ed ON g.ano_eje = ed.ano_eje AND g.id_clasificador=ed.id_clasificador "
				+ "WHERE g.ano_eje = '"+anio+"' "
				+ "AND g.sec_ejec ='"+cod_unid_ejec+"' "
				+ "ORDER BY id_meta_presupuestal,id_clasificador_gasto asc";
		
		Recordset rs = new Recordset();
		rs.Open(new Variant(query), new Variant(connectionString));
						
		if (!rs.getEOF()) {
			Fields fs = rs.getFields();
			
			rs.MoveFirst();
			
			while (!rs.getEOF()) {
				Presupuesto presupuesto = new Presupuesto();
			
				presupuesto.setId_presupuesto(fs.getItem(0).getValue().getString().trim());
				presupuesto.setId_meta_presupuestal(fs.getItem(1).getValue().getString().trim());
				presupuesto.setId_clasificador_gasto(fs.getItem(2).getValue().getString().trim());
				presupuesto.setFuente_financ(fs.getItem(3).getValue().getString().trim());
				presupuesto.setFuente_financ_nombre(fs.getItem(4).getValue().getString().trim());
				presupuesto.setMonto(fs.getItem(5).getValue().getDecimal());		
												
				msg = this.insertaPresupuesto(presupuesto);
				if(msg.equals("9000")) {
					return msg;
				}				
				rs.MoveNext();
			}	
		}	
		rs.Close();
		return msg;
	}
	
	public String insertaPresupuesto(Presupuesto presupuesto) throws Exception {		
		String rpta = "0000";
		String sql = "INSERT INTO presupuesto" + 
				"(id_presupuesto, "
				+ "id_meta_presupuestal, "
				+ "id_clasificador_gasto, "
				+ "fuente_financ, "
				+ "fuente_financ_nombre, "
				+ "monto, "
				+ "es_pia) " + 
				"VALUES(?, ?, ?, ?, ?, ?, 1)";
		try {
			jdbctemplate.update(sql, new Object[] {
					presupuesto.getId_presupuesto(),presupuesto.getId_meta_presupuestal(),presupuesto.getId_clasificador_gasto(),
					presupuesto.getFuente_financ(),presupuesto.getFuente_financ_nombre(),presupuesto.getMonto()
			});
		} catch (Exception e) {			
			System.out.println("Error : "+e.getMessage());
			rpta = "9000";
		}
		return rpta;
	}

	@Override
	public List<NotaModificatoria> getNotaModificatoria(String anio, String cod_unid_ejec, String sec_func,
		String fuente_financ, String id_clasificador) {
		List<NotaModificatoria> lstNotaModificatoria= new ArrayList<NotaModificatoria>();
		
		String connectionString = AdoUtility.setConnectionString(folderSiafDataMirror);
		String query = "SELECT n.sec_nota,"
				+ "n.monto_a as credito, "
				+ "n.monto_de as anulacion, "
				+ "LTRIM(RTRIM(ns.notas)) as notas,"
				+ "ns.fecha "
				+ "from nota_modificatoria_det as n "
				+ "INNER JOIN nota_modificatoria_sec as ns ON n.ano_eje = ns.ano_eje AND n.sec_ejec = ns.sec_ejec AND n.sec_nota = ns.sec_nota "
				+ "WHERE n.ano_eje = '"+anio+"' AND n.sec_ejec = '"+cod_unid_ejec+"' AND n.sec_func = '"+sec_func+"' AND n.id_clasificador = '"+id_clasificador+"' AND n.fuente_financ = '"+fuente_financ+"' "
				+ "order by n.sec_nota asc "; 
	
		Recordset rs = new Recordset();
		rs.Open(new Variant(query), new Variant(connectionString));
						
		if (!rs.getEOF()) {
			Fields fs = rs.getFields();
			
			rs.MoveFirst();
			
			while (!rs.getEOF()) {
				NotaModificatoria nota = new NotaModificatoria();

				String pattern = "dd/MM/yyyy";
				SimpleDateFormat format = new SimpleDateFormat(pattern);
				
				nota.setSec_nota(fs.getItem(0).getValue().getString().trim());
				nota.setCredito(fs.getItem(1).getValue().getDecimal());
				nota.setAnulacion(fs.getItem(2).getValue().getDecimal());
				nota.setNotas(fs.getItem(3).getValue().getString().trim());				
				nota.setFecha(format.format(fs.getItem(4).getValue().getJavaDate()));
				
				lstNotaModificatoria.add(nota);
				rs.MoveNext();
			}	
		}	
		rs.Close();
		return lstNotaModificatoria;
	}


	@Override
	public GenericResponse registrarCert_cab(CertRequest certRequest) {		
		String msg = "0000";
		GenericResponse respose = new GenericResponse();
		String connectionString = AdoUtility.setConnectionString(folderSiafInterfase);		
		Connection c = new Connection();
		c.setConnectionString(connectionString);
		c.Open();
		Command comm = new Command();
		comm.setActiveConnection(c);
		comm.setCommandType(CommandTypeEnum.adCmdText);
		String nuevoSecuencial = getNuevoSecuencial("cert_cab");
		List<Error> errores = new ArrayList<Error>();
		System.out.println(certRequest.toString());
		try {
			
			if(this.getExisteDocumento(certRequest.getCod_doc(), certRequest.getNum_doc(), certRequest.getAno_eje()) == true) {
				respose.setCode("0001");
				respose.setMessage("Existe un registro con el mismo codigo y número de documento");
				respose.setData(null);				
				return respose;
			}
			
			if(certRequest.getEs_comp().equals("N")) {
				//1. VALIDAR SALDO PCA GENERAL X CLASIFICADOR
				List<CertDet> lstagrupado = this.agrupaClasificadores(certRequest.getCert_det());
				
				for(int i = 0; i < lstagrupado.size(); i++) {
					ClasifSaldoPimReq clasifSaldoPim = new ClasifSaldoPimReq();
					clasifSaldoPim.setAno_eje(certRequest.getAno_eje());
					clasifSaldoPim.setFuente_finac(certRequest.getFte_fin());
					clasifSaldoPim.setId_clasifi(lstagrupado.get(i).getId_clasifi());
					clasifSaldoPim.setSec_ejec(certRequest.getSec_ejec());
					clasifSaldoPim.setSec_func(certRequest.getCert_det().get(i).getSec_func());
					
					ClasifSaldoPimResp clasifiSaldoPimResp = consultaSaldosClasif(clasifSaldoPim);
					Double monto = Double.parseDouble(lstagrupado.get(i).getMonto());
	
					System.out.println("Objeto : "+clasifiSaldoPimResp.toString());
					System.out.println("saldo pim : "+clasifiSaldoPimResp.getSaldo_pim());
					System.out.println("saldo pca : "+clasifiSaldoPimResp.getSaldo_pca());

					if(clasifiSaldoPimResp.getSaldo_pim() == null && clasifiSaldoPimResp.getSaldo_pca() == null ) {
						respose.setCode("0001");
						respose.setMessage("No existe PCA para la fuente de financiamiento "+certRequest.getFte_fin()+" y el clasificador "+lstagrupado.get(i).getId_clasifi());
						respose.setData(null);				
						return respose;
					}
					
					
					if(clasifiSaldoPimResp.getSaldo_pca().doubleValue() < monto) {
						errores.add(new Error("El clasificador '"+lstagrupado.get(i).getId_clasifi()+"' no cuenta con saldo PCA suficiente para cerftificar"));
					}
				}		
				
				//2. VALIDAR SALDOS PIM Y PCA POR CADA UNO DE LOS CLASIFICADORES
				
				for(int i = 0; i < certRequest.getCert_det().size(); i++) {
					ClasifSaldoPimReq clasifSaldoPim = new ClasifSaldoPimReq();
					clasifSaldoPim.setAno_eje(certRequest.getAno_eje());
					clasifSaldoPim.setFuente_finac(certRequest.getFte_fin());
					clasifSaldoPim.setId_clasifi(certRequest.getCert_det().get(i).getId_clasifi());
					clasifSaldoPim.setSec_ejec(certRequest.getSec_ejec());
					clasifSaldoPim.setSec_func(certRequest.getCert_det().get(i).getSec_func());
					
					ClasifSaldoPimResp clasifiSaldoPimResp = consultaSaldosClasif(clasifSaldoPim);
					
					if(clasifiSaldoPimResp.getSaldo_pim().doubleValue() > 0) {
						if(clasifiSaldoPimResp.getSaldo_pim().doubleValue() > clasifiSaldoPimResp.getSaldo_pca().doubleValue()) {
							if(Double.parseDouble(certRequest.getCert_det().get(i).getMonto()) > clasifiSaldoPimResp.getSaldo_pca().doubleValue()) {
								errores.add(new Error("El clasificador '"+certRequest.getCert_det().get(i).getId_clasifi()+"' de la meta '"+certRequest.getCert_det().get(i).getSec_func()+"' exede el valor del PCA permitido"));
							}
						}else if(clasifiSaldoPimResp.getSaldo_pim().doubleValue() < clasifiSaldoPimResp.getSaldo_pca().doubleValue()) {
							if(Double.parseDouble(certRequest.getCert_det().get(i).getMonto()) > clasifiSaldoPimResp.getSaldo_pim().doubleValue()) {
								errores.add(new Error("El clasificador '"+certRequest.getCert_det().get(i).getId_clasifi()+"' de la meta '"+certRequest.getCert_det().get(i).getSec_func()+"' exede su saldo PIM"));
							}						
						}
					}else {
						errores.add(new Error("El clasificador '"+certRequest.getCert_det().get(i).getId_clasifi()+"' de la meta '"+certRequest.getCert_det().get(i).getSec_func()+"' no cuenta con saldo PIM"));
					}				
					
				}
				
				if(!errores.isEmpty()) {
					respose.setCode("0001");
					respose.setMessage("Se han encontrado errores en los clasificadores a certificar ==> "+errores.toString());
					respose.setData(errores);				
					return respose;
				}
			}
									
			// 3. INSERTAR LA CABECERA
			comm.setCommandText("INSERT INTO CERT_CAB(Secuencial, Cod_doc, Num_doc, Ano_eje, Sec_ejec, Estado, Es_comp, Tipo_ope, Gloza, Fec_doc, Fte_fin, Moneda, "
					+ "T_cambio, Ue_envio, Ue_estado, Disp_legal, Tipo_certi, ruc, siaf_cer, intf_cer, intf_sec, intf_cor, tipo_id) "
					+ "VALUES ('"+nuevoSecuencial+"', '"+certRequest.getCod_doc()+"', '"+certRequest.getNum_doc()+"', '"+certRequest.getAno_eje()+"', '"+certRequest.getSec_ejec()+"', '"+certRequest.getEstado()+"', '"+certRequest.getEs_comp()+"', '"+certRequest.getTipo_ope()+"', "
					+ "'"+certRequest.getGloza()+"', {^"+certRequest.getFec_doc()+"}, '"+certRequest.getFte_fin()+"', '"+certRequest.getMoneda()+"', "+certRequest.getT_cambio()+", '"+certRequest.getUe_envio()+"', '"+certRequest.getUe_estado()+"', '"+certRequest.getDisp_legal()+"', '"+certRequest.getTipo_certi()+"',"
					+ "'"+certRequest.getRuc()+"','"+certRequest.getSiaf_cer()+"','"+certRequest.getIntf_cer()+"','"+certRequest.getIntf_sec()+"','"+certRequest.getIntf_cor()+"','"+certRequest.getTipo_id()+"')");
			comm.Execute();
			// 4. INSERTAR EL DETALLE
			for(int i = 0; i < certRequest.getCert_det().size(); i++) {
				comm.setCommandText("INSERT INTO cert_det(secuencial, clasifniv1, clasifniv2, clasifniv3, "
						+ "clasifniv4, clasifniv5, clasifniv6, sec_func, monto, "
						+ "monto_mn, id_clasifi) "
						+ "values ('"+nuevoSecuencial+"','"+certRequest.getCert_det().get(i).getClasifniv1()+"','"+certRequest.getCert_det().get(i).getClasifniv2()+"',' "+certRequest.getCert_det().get(i).getClasifniv3()+"' "
						+ ",' "+certRequest.getCert_det().get(i).getClasifniv4()+"',' "+certRequest.getCert_det().get(i).getClasifniv5()+"',' "+certRequest.getCert_det().get(i).getClasifniv6()+"','"+certRequest.getCert_det().get(i).getSec_func()+"',"+certRequest.getCert_det().get(i).getMonto()
						+ ","+certRequest.getCert_det().get(i).getMonto_mn()+",'"+certRequest.getCert_det().get(i).getId_clasifi()+"')");
				comm.Execute();
			}
			c.Close();
			respose.setCode("0000");
			respose.setMessage("Se ha registrado la solicitud del proceso de Certificación");
			respose.setData(nuevoSecuencial);
		} catch (Exception e) {
			e.printStackTrace();
			respose.setCode("9000");
			respose.setMessage(e.getMessage());
			respose.setData(null);
			
		}		
		return respose;
	}
	
	private String getNuevoSecuencial(String nombre_tabla) {
		Integer numeroRegistros = 0;
		String nuevoSec = "";
		String antSec = "";
		String cadenaCeros = "";
		Integer nuevonumero = 0;
		Integer canDig = 0;
		Integer cantCeros = 0;
		try {				

			numeroRegistros = getInterfaseRegistrosExistentes(nombre_tabla);
			//System.out.println("Registros existentes : "+numeroRegistros);
			if(numeroRegistros>0) {
				// Coger el último secuencial
				String connectionString = AdoUtility.setConnectionString(folderSiafInterfase);
				String query = "SELECT MAX(secuencial) as ultimoSecuencial FROM "+nombre_tabla;				
				Recordset rs = new Recordset();
				rs.Open(new Variant(query), new Variant(connectionString));								
				if (!rs.getEOF()) {
					Fields fs = rs.getFields();				
					rs.MoveFirst();				
					while (!rs.getEOF()) {									
						antSec = (fs.getItem(0).getValue().getString().trim());
						//System.out.println("Secuencial anterior : "+antSec);
						rs.MoveNext();
					}	
				}
				rs.Close();
				nuevonumero = extraeNumeros(antSec)+1;
				canDig = Integer.toString(nuevonumero).length();
				cantCeros = 10-canDig;	
					
					if(cantCeros > 0) {
						for(int i = 0; i < cantCeros; i++) {
							cadenaCeros = cadenaCeros + "0";
						}				
					}
					nuevoSec = "EBSIAF-"+cadenaCeros+nuevonumero;
					//System.out.println("Nuevo secuencial : "+nuevoSec);
			}else {
				nuevoSec = "EBSIAF-0000000001";	
			}
													
		} catch (Exception e) {
			e.getMessage();
		}
		//System.out.println("Nuevo secuencial : "+nuevoSec);
		return nuevoSec;
	}
	
	private Integer extraeNumeros(String secuencial) {
		char[] cadena = secuencial.toCharArray();
		String n = "";
		Integer numero = 0;
		for(int i = 0; i < cadena.length; i++) {
			if(Character.isDigit(cadena[i])) {
				n+=cadena[i];
			}
		}			
		numero = Integer.parseInt(n);
		return numero;
	}
	
	
	private Integer getInterfaseRegistrosExistentes(String nombre_tabla) {
		Integer numeroRegistros = 0;
		try {
			String connectionString = AdoUtility.setConnectionString(folderSiafInterfase);
			String query = "SELECT count(*) as cantidad FROM "+nombre_tabla;
			
			Recordset rs = new Recordset();
			rs.Open(new Variant(query), new Variant(connectionString));
			Short sVariable = 3;
							
			if (!rs.getEOF()) {
				Fields fs = rs.getFields();				
				rs.MoveFirst();				
				while (!rs.getEOF()) {									
					numeroRegistros=(fs.getItem(0).getValue().changeType(sVariable).getInt());
					//System.out.println("Numero de registros : "+numeroRegistros);
					rs.MoveNext();
				}	
			}
			rs.Close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return numeroRegistros;
	}

	@Override
	public CertCabConsulta getEstadoSolicitudCertCab(String secuencial) {
		CertCabConsulta consulta = new CertCabConsulta();
		String connectionString = AdoUtility.setConnectionString(folderSiafInterfase);
		Integer numeroRegistros = getInterfaseRegistrosExistentes("cert_cab");
		
		if(numeroRegistros > 0) {
			String query = "SELECT secuencial,"
					+ "estado,"
					+ "ue_envio,"
					+ " ue_estado, "
					+ "siaf_cer, "
					+ "siaf_sec, "
					+ "siaf_cor   "
					+ "from cert_cab"
					+ " WHERE secuencial = '"+secuencial+"'"; 
		
			Recordset rs = new Recordset();
			rs.Open(new Variant(query), new Variant(connectionString));
							
			if (!rs.getEOF()) {
				Fields fs = rs.getFields();				
				rs.MoveFirst();				
				while (!rs.getEOF()) {				
					consulta.setSecuencial(fs.getItem(0).getValue().getString().trim());
					consulta.setEstado(fs.getItem(1).getValue().getString().trim());
					consulta.setUe_envio(fs.getItem(2).getValue().getString().trim());
					consulta.setUe_estado(fs.getItem(3).getValue().getString().trim());
					consulta.setSiaf_cer(fs.getItem(4).getValue().getString().trim());
					consulta.setSiaf_sec(fs.getItem(5).getValue().getString().trim());
					consulta.setSiaf_cor(fs.getItem(6).getValue().getString().trim());
					rs.MoveNext();
				}	
			}
			rs.Close();
		}
		return consulta;
	}

	@Override
	public GenericResponse registrarIntf_cab(IntfRequest intfRequest) {
		String msg = "0000";
		GenericResponse respose = new GenericResponse();
		String connectionString = AdoUtility.setConnectionString(folderSiafInterfase);		
		Connection c = new Connection();
		c.setConnectionString(connectionString);
		c.Open();
		Command comm = new Command();
		comm.setActiveConnection(c);
		comm.setCommandType(CommandTypeEnum.adCmdText);
		String nuevoSecuencial = getNuevoSecuencial("intf_cab");
		if(intfRequest.getDmonto().equals("")) {
			intfRequest.setDmonto(null);
		}
		
		if(intfRequest.getDmonto_mn().equals("")) {
			intfRequest.setDmonto_mn(null);
		}

		try {
			// 1. INSERTAR LA CABECERA
			String sql1 =  "insert into intf_cab(secuencial, ano_eje, sec_ejec, ciclo, "
					+ " fase, estado, sec_ejec2, mes_eje, "
					+ " tipo_ope, mod_comp, tipo_proc, sec_area, "
					+ " cod_doc, serie_doc, num_doc, fec_doc, "
					+ " ps_ejec, ps_t_camb, tipo_id, ruc, "
					+ " origen, fte_fin, organismo, convenio, "
					+ " t_pago, t_recurs, t_compro, ano_cta, "
					+ " banco, cta_cte, tipo_giro, dcod_doc, "
					+ " dnum_doc, dfec_doc, dnombre, dmonto, "
					+ " dmonto_mn, moneda, t_cambio, ue_envio, "
					+ " ue_estado, intf_exp, intf_fase, intf_sec, "
					+ " intf_cor, intf_cer, intf_cerse) "
					+ " values('"+nuevoSecuencial+"','"+intfRequest.getAno_eje()+"','"+intfRequest.getSec_ejec()+"','"+intfRequest.getCiclo()+"', "
					+ " '"+intfRequest.getFase()+"','"+intfRequest.getEstado()+"','"+intfRequest.getSec_ejec2()+"','"+intfRequest.getMes_eje()+"', "
					+ " '"+intfRequest.getTipo_ope()+"','"+intfRequest.getMod_comp()+"','"+intfRequest.getTipo_proc()+"','"+intfRequest.getSec_area()+"' ,"
					+ " '"+intfRequest.getCod_doc()+"','"+intfRequest.getSerie_doc()+"','"+intfRequest.getNum_doc()+"',{^"+intfRequest.getFec_doc()+"}, "
					+ " '"+intfRequest.getPs_ejec()+"',"+intfRequest.getPs_t_camb()+",'"+intfRequest.getTipo_id()+"','"+intfRequest.getRuc()+"', "
					+ " '"+intfRequest.getOrigen()+"','"+intfRequest.getFte_fin()+"','"+intfRequest.getOrganismo()+"','"+intfRequest.getConvenio()+"', "
					+ " '"+intfRequest.getT_pago()+"','"+intfRequest.getT_recurs()+"','"+intfRequest.getT_compro()+"','"+intfRequest.getAno_cta()+"', "
					+ " '"+intfRequest.getBanco()+"','"+intfRequest.getCta_cte()+"','"+intfRequest.getTipo_giro()+"' ,'"+intfRequest.getDcod_doc()+"', "
					+ " '"+intfRequest.getDnum_doc()+"',{^"+intfRequest.getFec_doc()+"},'"+intfRequest.getDnombre()+"',"+intfRequest.getDmonto()+", "
					+ " "+intfRequest.getDmonto_mn()+", '"+intfRequest.getMoneda()+"', "+intfRequest.getT_cambio()+", '"+intfRequest.getUe_envio()+"', "
					+ " '"+intfRequest.getUe_estado()+"','"+intfRequest.getIntf_exp()+"','"+intfRequest.getIntf_fase()+"','"+intfRequest.getIntf_sec()+"', "
					+ " '"+intfRequest.getIntf_cor()+"','"+intfRequest.getIntf_cer()+"','"+intfRequest.getIntf_cerse()+"')";
			comm.setCommandText(sql1);
			comm.Execute();
			// 2. INSERTAR EL DETALLE
			for(int i = 0; i < intfRequest.getIntf_det().size(); i++) {
				comm.setCommandText("insert into intf_det09(secuencial, id_clasifi, clasifniv1, clasifniv2, "
						+ " clasifniv3, clasifniv4, clasifniv5, clasifniv6, "
						+ " sec_func, monto, monto_mn) "
						+ " values('"+nuevoSecuencial+"','"+intfRequest.getIntf_det().get(i).getId_clasifi()+"','"+intfRequest.getIntf_det().get(i).getClasifniv1()+"','"+intfRequest.getIntf_det().get(i).getClasifniv2()+"', "
						+ " '"+intfRequest.getIntf_det().get(i).getClasifniv3()+"','"+intfRequest.getIntf_det().get(i).getClasifniv4()+"','"+intfRequest.getIntf_det().get(i).getClasifniv5()+"','"+intfRequest.getIntf_det().get(i).getClasifniv6()+"', "
						+ " '"+intfRequest.getIntf_det().get(i).getSec_func()+"',"+intfRequest.getIntf_det().get(i).getMonto()+","+intfRequest.getIntf_det().get(i).getMonto_mn()+")");
				comm.Execute();
			}
			c.Close();
			respose.setCode("0000");
			respose.setMessage("Se ha registrado la solicitud de Registro SIAF");
			respose.setData(nuevoSecuencial);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "9000";
		}		
		return respose;
	}
	
	private Integer getPCARegistrosExistentes(String ano_eje, String sec_ejec, String fuente_financ, String id_clasificador) {
		Integer numeroRegistros = 0;
		try {
			String connectionString = AdoUtility.setConnectionString(folderSiafInterfase);
			String query = "SELECT count(*) as cantidad FROM mpp_pca_x_especifica "
					+ " WHERE ano_eje = '"+ano_eje+"' "
					+ " AND sec_ejec = '"+sec_ejec+"' "
					+ " AND fuente_financ = '"+fuente_financ+"' "
					+ " AND id_clasificador = '"+id_clasificador+"'";
			
			Recordset rs = new Recordset();
			rs.Open(new Variant(query), new Variant(connectionString));
			Short sVariable = 3;
							
			if (!rs.getEOF()) {
				Fields fs = rs.getFields();				
				rs.MoveFirst();				
				while (!rs.getEOF()) {									
					numeroRegistros=(fs.getItem(0).getValue().changeType(sVariable).getInt());
					//System.out.println("Numero de registros : "+numeroRegistros);
					rs.MoveNext();
				}	
			}
			rs.Close();
		} catch (Exception e) { 			
			e.printStackTrace();
		}
		return numeroRegistros;
	}

	@Override
	public List<ClasifPcaResp> consultaSaldoPca(ConsultaPCARequest consultaPcaRequest) {
		List<ClasifPcaResp> lstClasifPcaResp= new ArrayList<ClasifPcaResp>();
		
		if(!consultaPcaRequest.getClasificadores().isEmpty()) {
			for(int i = 0; i < consultaPcaRequest.getClasificadores().size(); i++) {
				
				String connectionString = AdoUtility.setConnectionString(folderSiafDataMirror);
				String query = "SELECT id_clasificador, "
						+ " monto_asignado, "
						+ " monto_comprometido, "
						+ " (monto_asignado - monto_comprometido) as saldo_pca "
						+ " from mpp_pca_x_especifica "
						+ " WHERE ano_eje = '"+consultaPcaRequest.getClasificadores().get(i).getAno_eje()+"' "
						+ " AND sec_ejec = '"+consultaPcaRequest.getClasificadores().get(i).getSec_ejec()+"' "
						+ " AND fuente_financ = '"+consultaPcaRequest.getClasificadores().get(i).getFuente_finac()+"' "
						+ " AND id_clasificador = '"+consultaPcaRequest.getClasificadores().get(i).getId_clasifi()+"' ";
			
				Recordset rs = new Recordset();
				rs.Open(new Variant(query), new Variant(connectionString));
								
				if (!rs.getEOF()) {
					Fields fs = rs.getFields();
					
					rs.MoveFirst();
					
					while (!rs.getEOF()) {
						ClasifPcaResp clasif = new ClasifPcaResp();

						clasif.setId_clasificador(fs.getItem(0).getValue().getString().trim());
						clasif.setMonto_asignado(fs.getItem(1).getValue().getDecimal());
						clasif.setMonto_comprometido(fs.getItem(2).getValue().getDecimal());
						clasif.setSaldo_pca(fs.getItem(3).getValue().getDecimal());			
						
						lstClasifPcaResp.add(clasif);
						
						rs.MoveNext();
					}	
				}
				rs.Close();
			}
		}	
		return lstClasifPcaResp;
	}
	

	private ClasifSaldoPimResp consultaSaldosClasif(ClasifSaldoPimReq clasifSaldoPim) {
		ClasifSaldoPimResp resp = new ClasifSaldoPimResp();
		
		String connectionString = AdoUtility.setConnectionString(folderSiafDataMirror);
		String query = "SELECT ((g.presupuesto+g.modificacion)-g.monto_certificado) saldo_pim, "
				+ "(mpp.monto_asignado - mpp.monto_comprometido) as saldo_pca  "
				+ "from gasto g "
				+ "LEFT OUTER join mpp_pca_x_especifica mpp ON g.ano_eje = mpp.ano_eje AND g.sec_ejec = mpp.sec_ejec AND g.fuente_financ = mpp.fuente_financ AND g.id_clasificador = mpp.id_clasificador  "
				+ "WHERE g.ano_eje = '"+clasifSaldoPim.getAno_eje()+"' "
				+ "AND g.fuente_financ = '"+clasifSaldoPim.getFuente_finac()+"' "
				+ "AND g.sec_ejec = '"+clasifSaldoPim.getSec_ejec()+"' "
				+ "AND g.id_clasificador = '"+clasifSaldoPim.getId_clasifi()+"' "
				+ "AND g.sec_func = '"+clasifSaldoPim.getSec_func()+"'";
		
		Recordset rs = new Recordset();
		rs.Open(new Variant(query), new Variant(connectionString));
						
		if (!rs.getEOF()) {
			Fields fs = rs.getFields();			
			rs.MoveFirst();			
			while (!rs.getEOF()) {
				
				resp.setSaldo_pim(fs.getItem(0).getValue().getDecimal());
				resp.setSaldo_pca(fs.getItem(1).getValue().getDecimal());
				rs.MoveNext();
			}	
		}
		rs.Close();
		return resp;
	}
	

	private List<CertDet> agrupaClasificadores(List<CertDet> cert_det) {
		GenericResponse response = new GenericResponse();
		List<CertDet> certsTmp = new ArrayList<CertDet>();
		boolean marcador = false;
		certsTmp.clear();
			
		for(int i = 0; i < cert_det.size(); i++) {
			if(!certsTmp.isEmpty()) {
				for(int j = 0; j < certsTmp.size(); j++) {
					if(cert_det.get(i).getId_clasifi().equals(certsTmp.get(j).getId_clasifi())) {
						certsTmp.get(j).setMonto(String.valueOf(Double.parseDouble(cert_det.get(i).getMonto()) + Double.parseDouble(certsTmp.get(j).getMonto())));
						marcador = true;
					}
				}
				if(marcador == false) {
					certsTmp.add(cert_det.get(i));					
				}
				marcador = false;
			}else {
				certsTmp.add(cert_det.get(i));
			}
		}
		return certsTmp;
	}

	@Override
	public List<CertCompAnual> consultaCertCompAnual(String anio, String cod_unid_ejec, String num_ccp) {
		GenericResponse response = new GenericResponse();
		List<CertCompAnual> lstcertCompAnual = new ArrayList<CertCompAnual>();
		
		String connectionString = AdoUtility.setConnectionString(folderSiafDataMirror);
		String query = "SELECT cs.certificado,"
				+ "cs.secuencia,"
				+ "cs.correlativo,"
				+ "cs.cod_doc,"
				+ "cs.num_doc,"
				+ "cs.fecha_doc, "
				+ "cs.estado_registro, "
				+ "cs.estado_envio, "
				+ "cs.ind_certificacion "
				+ "from certificado_secuencia as cs "
				+ "INNER JOIN certificado as c on cs.ano_eje = c.ano_eje AND cs.certificado = c.certificado "
				+ "WHERE c.ano_eje = '"+anio+"' "
				+ "AND c.sec_ejec = '"+cod_unid_ejec+"' "
				+ "AND c.certificado = '"+num_ccp+"' "
				+ "ORDER BY cs.certificado, cs.secuencia, cs.correlativo asc";
	
		Recordset rs = new Recordset();
		rs.Open(new Variant(query), new Variant(connectionString));
						
		if (!rs.getEOF()) {
			Fields fs = rs.getFields();
			
			rs.MoveFirst();
			
			while (!rs.getEOF()) {
				CertCompAnual certComp = new CertCompAnual();

				String pattern = "dd/MM/yyyy";
				SimpleDateFormat format = new SimpleDateFormat(pattern);
								
				certComp.setCertificado(fs.getItem(0).getValue().getString().trim());
				certComp.setSecuencia(fs.getItem(1).getValue().getString().trim());
				certComp.setCorrelativo(fs.getItem(2).getValue().getString().trim());
				certComp.setCod_doc(fs.getItem(3).getValue().getString().trim());
				certComp.setNum_doc(fs.getItem(4).getValue().getString().trim());
				certComp.setFecha_doc(format.format(fs.getItem(5).getValue().getJavaDate()));
				certComp.setEstado_registro(fs.getItem(6).getValue().getString().trim());
				certComp.setEstado_envio(fs.getItem(7).getValue().getString().trim());
				certComp.setInd_certificacion(fs.getItem(8).getValue().getString().trim());
				
				List<CertCompAnualDetalle> detalle = new ArrayList<CertCompAnualDetalle>();				
				
				detalle = consultaCertCompAnualDetalle(anio, cod_unid_ejec, num_ccp, certComp.getSecuencia(), certComp.getCorrelativo());				
				certComp.setDetalle(detalle);
				
				lstcertCompAnual.add(certComp);			
				rs.MoveNext();
			}
		}
		rs.Close();
		return lstcertCompAnual;
	}
	
	public List<CertCompAnualDetalle> consultaCertCompAnualDetalle(String anio,String cod_unid_ejec, String num_ccp, String secuencia, String correlativo) {
	
		List<CertCompAnualDetalle> detalle = new ArrayList<CertCompAnualDetalle>();
		
		String connectionString = AdoUtility.setConnectionString(folderSiafDataMirror);
		String query = "SELECT clf.certificado, "
				+ "clf.secuencia, "
				+ "clf.correlativo, "
				+ "clf.id_clasificador, "
				+ "cm.sec_func, "
				+ "clf.monto, "
				+ "clf.monto_nacional,"
				+ "clf.estado_registro,"
				+ "clf.estado_envio "
				+ "from certificado_clasif as clf "
				+ "INNER JOIN certificado_meta as cm ON clf.ano_eje = cm.ano_eje AND clf.sec_ejec = cm.sec_ejec AND clf.certificado = cm.certificado AND clf.secuencia = cm.secuencia AND clf.correlativo = cm.correlativo "
				+ "WHERE clf.sec_ejec = '"+cod_unid_ejec+"' "
				+ "and clf.certificado = '"+num_ccp+"' "
				+ "AND clf.secuencia = '"+secuencia+"' "
				+ "AND clf.correlativo = '"+correlativo+"' "
				+ "AND clf.ano_eje = '"+anio+"'";
	
		Recordset rs = new Recordset();
		rs.Open(new Variant(query), new Variant(connectionString));
						
		if (!rs.getEOF()) {
			Fields fs = rs.getFields();
			
			rs.MoveFirst();
			
			while (!rs.getEOF()) {
				CertCompAnualDetalle certCompDet = new CertCompAnualDetalle();
				
				certCompDet.setCertificado(fs.getItem(0).getValue().getString().trim());
				certCompDet.setSecuencia(fs.getItem(1).getValue().getString().trim());
				certCompDet.setCorrelativo(fs.getItem(2).getValue().getString().trim());
				certCompDet.setId_clasificador(fs.getItem(3).getValue().getString().trim());
				certCompDet.setSec_func(fs.getItem(4).getValue().getString().trim());
				certCompDet.setMonto(fs.getItem(5).getValue().getDecimal());
				certCompDet.setMonto_nacional(fs.getItem(6).getValue().getDecimal());
				certCompDet.setEstado_registro(fs.getItem(7).getValue().getString().trim());
				certCompDet.setEstado_envio(fs.getItem(8).getValue().getString().trim());
				
				detalle.add(certCompDet);
			
				rs.MoveNext();
			}				
		}
		rs.Close();
		return detalle;
	}
	
	
	@Override
	public ClasificadorCodigo getClasificadorCodigo(String anio, String cod_unid_ejec, String sec_func, String fuente_financ, String id_clasificador) {
		ClasificadorCodigo clasificador = new ClasificadorCodigo();
		
		String connectionString = AdoUtility.setConnectionString(folderSiafDataMirror);
		String query = "SELECT  ed.tipo_transaccion+'.'+ed.generica+'.'+ed.subgenerica+ed.subgenerica_det+'.'+ed.especifica+ed.especifica_det as cod_clasificador,"
				+ "ed.descripcion as nombre "
				+ "from gasto as g "
				+ "INNER JOIN especifica_det as ed ON g.ano_eje = ed.ano_eje AND g.id_clasificador=ed.id_clasificador "									
				+ "WHERE g.ano_eje = '"+anio+"' "
				+ "AND g.sec_ejec = '"+cod_unid_ejec+"' "
				+ "AND g.sec_func = '"+sec_func+"' "
				+ "AND g.fuente_financ = '"+fuente_financ+"' "
				+ "AND g.id_clasificador = '"+id_clasificador+"' "
				+ "ORDER BY ed.tipo_transaccion, ed.generica, ed.subgenerica, ed.subgenerica_det, ed.especifica, ed.especifica_det asc";
		
		Recordset rs = new Recordset();
		rs.Open(new Variant(query), new Variant(connectionString));
						
		if (!rs.getEOF()) {
			Fields fs = rs.getFields();
			
			rs.MoveFirst();
			
			while (!rs.getEOF()) {
							
				clasificador.setCodigo_clasificador(fs.getItem(0).getValue().getString().trim());
				clasificador.setCodigo_nombre(fs.getItem(1).getValue().getString().trim());
				rs.MoveNext();
			}	
		}
		rs.Close();
		return clasificador;
	}

	@Override
	public MetaCadena getMetaCodigo(String anio, String cod_unid_ejec, String sec_func) {
	MetaCadena meta = new MetaCadena();
		
		String connectionString = AdoUtility.setConnectionString(folderSiafDataMirror);
		String query = "SELECT "
				+ "A.sec_func,"
				+ "A.PROGRAMA_PPTO programa_ppto, "
				+ "PPN.NOMBRE AS programa_ppto_nombre, "
				+ "A.act_proy as prod_proy, "
				+ "ap.nombre as prod_proy_nombre, "
				+ "A.componente as act_ai_obr, "
				+ "C.nombre as act_ai_obr_nombre "
				+ "FROM META A "
				+ "INNER JOIN componente_nombre as C ON A.ANO_EJE = C.ANO_EJE AND A.COMPONENTE = C.COMPONENTE "
				+ "INNER JOIN act_proy_nombre as ap ON A.ANO_EJE = AP.ANO_EJE AND A.ACT_PROY = AP.ACT_PROY "
				+ "INNER JOIN PROGRAMA_PPTO_NOMBRE AS PPN ON A.ANO_EJE = PPN.ANO_EJE AND A.PROGRAMA_PPTO = PPN.PROGRAMA_PPTO "
				+ "WHERE A.ANO_EJE='"+anio+"' "
				+ "AND A.SEC_EJEC='"+cod_unid_ejec+"' "
				+ "AND A.SEC_fUNC='"+sec_func+"' ";
		
		Recordset rs = new Recordset();
		rs.Open(new Variant(query), new Variant(connectionString));
						
		if (!rs.getEOF()) {
			Fields fs = rs.getFields();
			
			rs.MoveFirst();
			
			while (!rs.getEOF()) {
							
				meta.setSec_func(fs.getItem(0).getValue().getString().trim());
				meta.setPrograma_ppto(fs.getItem(1).getValue().getString().trim());
				meta.setPrograma_ppto_nombre(fs.getItem(2).getValue().getString().trim());	
				meta.setProd_proy(fs.getItem(3).getValue().getString().trim());
				meta.setProd_proy_nombre(fs.getItem(4).getValue().getString().trim());				
				meta.setAct_ai_obr(fs.getItem(5).getValue().getString().trim());
				meta.setAct_ai_obr_nombre(fs.getItem(6).getValue().getString().trim());				

				rs.MoveNext();
			}		
		}	
		rs.Close();
		return meta;
	}

	@Override
	public CertificadoEstado getCertificadoEstado(String anio, String cod_unid_ejec, String certificado,
			String secuencia, String correlativo) {
		CertificadoEstado cert = new CertificadoEstado();
		
		String connectionString = AdoUtility.setConnectionString(folderSiafDataMirror);
		String query = "SELECT estado_registro, "
				+ "estado_envio "
				+ "from certificado_secuencia "
				+ "WHERE ano_eje = '"+anio+"' "
				+ "AND sec_ejec = '"+cod_unid_ejec+"' "
				+ "AND certificado = '"+certificado+"' "
				+ "AND secuencia = '"+secuencia+"' "
				+ "AND correlativo = '"+correlativo+"' ";
		
		Recordset rs = new Recordset();
		rs.Open(new Variant(query), new Variant(connectionString));
						
		if (!rs.getEOF()) {
			Fields fs = rs.getFields();
			
			rs.MoveFirst();
			
			while (!rs.getEOF()) {
							
				cert.setEstado_registro(fs.getItem(0).getValue().getString().trim());
				cert.setEstado_envio(fs.getItem(1).getValue().getString().trim());
				System.out.println("Respuesta : "+cert);
				rs.MoveNext();
			}		
		}
		rs.Close();
		return cert;
	}
	
	@Override
	public GenericResponse validaSaldoClasificador(	String ano_eje, String fuente_finac, String sec_ejec, String id_clasifi, String sec_func, String monto) {
		GenericResponse respose = new GenericResponse();
		List<Error> errores = new ArrayList<Error>();
		
		ClasifSaldoPimReq clasifiReq = new ClasifSaldoPimReq();
		clasifiReq.setAno_eje(ano_eje);
		clasifiReq.setFuente_finac(fuente_finac);
		clasifiReq.setSec_ejec(sec_ejec);
		clasifiReq.setId_clasifi(id_clasifi);
		clasifiReq.setSec_func(sec_func);
		
		
		// VALIDAR PCA GENERAL
		ClasifSaldoPimResp clasifiSaldoPimResp = consultaSaldosClasif(clasifiReq);
		Double dMonto = 0.0; 
		
		if(clasifiSaldoPimResp.getSaldo_pim() == null) {
			respose.setCode("6000");
			respose.setMessage("No se han encontrado registros con los datos ingresados");
			return respose;
		}		
		
		if(monto.equals("")) {
			respose.setCode("0001");
			respose.setMessage("El monto ingresado no el válido");
			return respose;
		}
		
		dMonto = Double.parseDouble(monto);
			
		if(clasifiSaldoPimResp.getSaldo_pca().doubleValue() < dMonto) {
			errores.add(new Error("El clasificador '"+id_clasifi+"' no cuenta con saldo PCA suficiente para cerftificar"));
		}
		
		//1. VALIDAR SALDO PCA GENERAL X CLASIFICADOR
				
		if(clasifiSaldoPimResp.getSaldo_pim().doubleValue() > 0) {
			if(clasifiSaldoPimResp.getSaldo_pim().doubleValue() > clasifiSaldoPimResp.getSaldo_pca().doubleValue()) {
				if(dMonto > clasifiSaldoPimResp.getSaldo_pca().doubleValue()) {
					errores.add(new Error("El clasificador '"+id_clasifi+"' de la meta '"+sec_func+"' exede el valor del PCA permitido"));
				}
			}else if(clasifiSaldoPimResp.getSaldo_pim().doubleValue() < clasifiSaldoPimResp.getSaldo_pca().doubleValue()) {
				if(dMonto > clasifiSaldoPimResp.getSaldo_pim().doubleValue()) {
					errores.add(new Error("El clasificador '"+id_clasifi+"' de la meta '"+sec_func+"' exede su saldo PIM"));
				}						
			}
		}else {
			errores.add(new Error("El clasificador '"+id_clasifi+"' de la meta '"+sec_func+"' no cuenta con saldo PIM"));
		}
		
		if(!errores.isEmpty()) {
			respose.setCode("0001");
			respose.setMessage("Se han encontrado errores en los clasificadores a certificar ==> "+errores.toString());
			respose.setData(errores);				
			return respose;
		}
		respose.setCode("0000");
		respose.setMessage("¡Habilitado para certificar!");
		respose.setData(clasifiSaldoPimResp);
		return respose;
	}
	
	private boolean getExisteDocumento(String cod_doc, String numero_documento, String ano_eje) {
		Integer numeroRegistros = 0;
		boolean existe = false;
		try {
			String connectionString = AdoUtility.setConnectionString(folderSiafDataMirror);
			String query = "SELECT count(*) from certificado_secuencia WHERE ano_eje = '"+ano_eje+"' AND cod_doc = '"+cod_doc+"' AND num_doc = '"+numero_documento+"'";
			
			Recordset rs = new Recordset();
			rs.Open(new Variant(query), new Variant(connectionString));
			Short sVariable = 3;
							
			if (!rs.getEOF()) {
				Fields fs = rs.getFields();				
				rs.MoveFirst();				
				while (!rs.getEOF()) {									
					numeroRegistros=(fs.getItem(0).getValue().changeType(sVariable).getInt());
					//System.out.println("Numero de registros : "+numeroRegistros);
					rs.MoveNext();
				}	
			}
			rs.Close();
			if(numeroRegistros > 0) {
				existe = true;
			}else {
				existe = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return existe;
	}	

	@Override
	public  List<ClasificadorMontoConsolidado> getClasificadorMontoCertificado(String fecha_inicio, String fecha_fin) {
		List<ClasificadorMontoConsolidado> lstConsolidado = new ArrayList<ClasificadorMontoConsolidado>();
			
		String connectionString = AdoUtility.setConnectionString(folderSiafDataMirror);
		String query =  "SELECT\r\n"
				+ "  cm.ano_eje,\r\n"
				+ "  month(cs.fecha_doc) as periodo_mensual,\r\n"
				+ "  cm.sec_func,\r\n"
				+ "  cf.fuente_financ,\r\n"
				+ "  cm.id_clasificador,\r\n"
				+ "  sum(cm.monto_nacional) as monto\r\n"
				+ "FROM\r\n"
				+ "  certificado_meta cm\r\n"
				+ "  INNER JOIN certificado_secuencia as cs ON cm.ano_eje = cs.ano_eje\r\n"
				+ "  AND cm.sec_ejec = cs.sec_ejec\r\n"
				+ "  AND cm.certificado = cs.certificado\r\n"
				+ "  AND cm.secuencia = cs.secuencia\r\n"
				+ "  AND cm.correlativo = cs.correlativo\r\n"
				+ "  INNER JOIN certificado_fase as cf ON cm.ano_eje = cf.ano_eje\r\n"
				+ "  AND cm.sec_ejec = cf.sec_ejec\r\n"
				+ "  AND cm.certificado = cf.certificado\r\n"
				+ "  AND cm.secuencia = cf.secuencia\r\n"
				+ "WHERE\r\n"
				+ "  cm.ano_eje = '"+fecha_inicio.substring(6)+"'\r\n"
				+ "  AND cm.sec_ejec = '000996'\r\n"
				+ "  AND cf.es_compromiso = 'N'\r\n"
				+ "  AND cs.ind_certificacion = 'S'\r\n"
				+ "  AND cm.estado_registro = 'A'\r\n"
				+ "  AND cf.estado_registro = 'A'\r\n"
				+ "  AND cs.estado_registro = 'A'\r\n"
				+ "  AND cs.fecha_doc >= CTOD('"+fecha_inicio+"')\r\n"
				+ "  AND cs.fecha_doc <= CTOD('"+fecha_fin+"')\r\n"
				+ "GROUP BY\r\n"
				+ "  cm.ano_eje,\r\n"
				+ "  periodo_mensual,\r\n"
				+ "  cm.sec_func,\r\n"
				+ "  cf.fuente_financ,\r\n"
				+ "  cm.id_clasificador\r\n"
				+ "ORDER BY cm.ano_eje,periodo_mensual,cm.sec_func,cf.fuente_financ,cm.id_clasificador asc ";
		
		Recordset rs = new Recordset();
		rs.Open(new Variant(query), new Variant(connectionString));
		Short sVariable = 3;
		if (!rs.getEOF()) {
			Fields fs = rs.getFields();
			
			rs.MoveFirst();
			
			while (!rs.getEOF()) {
				
				ClasificadorMontoConsolidado clasificador = new ClasificadorMontoConsolidado();
				
				clasificador.setAno_eje(fs.getItem(0).getValue().getString().trim());
				clasificador.setPeriodo_mensual(fs.getItem(1).getValue().changeType(sVariable).getInt());
				clasificador.setSec_func(fs.getItem(2).getValue().getString().trim());
				clasificador.setFuente_financ(fs.getItem(3).getValue().getString().trim());
				clasificador.setId_clasificador(fs.getItem(4).getValue().getString().trim());
				clasificador.setMonto(fs.getItem(5).getValue().getDecimal());
				System.out.println("Respuesta : "+clasificador);
				rs.MoveNext();
				lstConsolidado.add(clasificador);
			}		
		}
		rs.Close();
		return lstConsolidado;
	}

	@Override
	public List<ClasificadorMontoConsolidado> getClasificadorMontoCompromisoAnual(String fecha_inicio,
			String fecha_fin) {
		List<ClasificadorMontoConsolidado> lstConsolidado = new ArrayList<ClasificadorMontoConsolidado>();
		
		String connectionString = AdoUtility.setConnectionString(folderSiafDataMirror);
		String query =  "SELECT\r\n"
				+ "  cm.ano_eje,\r\n"
				+ "  month(cs.fecha_doc) as periodo_mensual,\r\n"
				+ "  cm.sec_func,\r\n"
				+ "  cf.fuente_financ,\r\n"
				+ "  cm.id_clasificador,\r\n"
				+ "  sum(cm.monto_nacional) as monto\r\n"
				+ "FROM\r\n"
				+ "  certificado_meta cm\r\n"
				+ "  INNER JOIN certificado_secuencia as cs ON cm.ano_eje = cs.ano_eje\r\n"
				+ "  AND cm.sec_ejec = cs.sec_ejec\r\n"
				+ "  AND cm.certificado = cs.certificado\r\n"
				+ "  AND cm.secuencia = cs.secuencia\r\n"
				+ "  AND cm.correlativo = cs.correlativo\r\n"
				+ "  INNER JOIN certificado_fase as cf ON cm.ano_eje = cf.ano_eje\r\n"
				+ "  AND cm.sec_ejec = cf.sec_ejec\r\n"
				+ "  AND cm.certificado = cf.certificado\r\n"
				+ "  AND cm.secuencia = cf.secuencia\r\n"
				+ "WHERE\r\n"
				+ "  cm.ano_eje = '"+fecha_inicio.substring(6)+"'\r\n"
				+ "  AND cm.sec_ejec = '000996'\r\n"
				+ "  AND cf.es_compromiso = 'S'\r\n"
				+ "  AND cs.ind_certificacion = 'N'\r\n"
				+ "  AND cm.estado_registro = 'A'\r\n"
				+ "  AND cf.estado_registro = 'A'\r\n"
				+ "  AND cs.estado_registro = 'A'\r\n"
				+ "  AND cs.fecha_doc >= CTOD('"+fecha_inicio+"')\r\n"
				+ "  AND cs.fecha_doc <= CTOD('"+fecha_fin+"')\r\n"
				+ "GROUP BY\r\n"
				+ "  cm.ano_eje,\r\n"
				+ "  periodo_mensual,\r\n"
				+ "  cm.sec_func,\r\n"
				+ "  cf.fuente_financ,\r\n"
				+ "  cm.id_clasificador\r\n"
				+ "ORDER BY cm.ano_eje,periodo_mensual,cm.sec_func,cf.fuente_financ,cm.id_clasificador asc ";
		
		Recordset rs = new Recordset();
		rs.Open(new Variant(query), new Variant(connectionString));
		Short sVariable = 3;
		if (!rs.getEOF()) {
			Fields fs = rs.getFields();
			
			rs.MoveFirst();
			
			while (!rs.getEOF()) {
				
				ClasificadorMontoConsolidado clasificador = new ClasificadorMontoConsolidado();
				
				clasificador.setAno_eje(fs.getItem(0).getValue().getString().trim());
				clasificador.setPeriodo_mensual(fs.getItem(1).getValue().changeType(sVariable).getInt());
				clasificador.setSec_func(fs.getItem(2).getValue().getString().trim());
				clasificador.setFuente_financ(fs.getItem(3).getValue().getString().trim());
				clasificador.setId_clasificador(fs.getItem(4).getValue().getString().trim());
				clasificador.setMonto(fs.getItem(5).getValue().getDecimal());
				System.out.println("Respuesta : "+clasificador);
				rs.MoveNext();
				lstConsolidado.add(clasificador);
			}		
		}
		rs.Close();
		return lstConsolidado;
	}

	@Override
	public List<ClasificadorMontoConsolidado> getClasificadorMontoCompromisoMensual(String fecha_inicio,
			String fecha_fin) {
		List<ClasificadorMontoConsolidado> lstConsolidado = new ArrayList<ClasificadorMontoConsolidado>();
		
		String connectionString = AdoUtility.setConnectionString(folderSiafDataMirror);
		String query =  "SELECT\r\n"
				+ "  em.ano_eje,\r\n"
				+ "  month(es.fecha_doc) as periodo_mensual,\r\n"
				+ "  em.sec_func,\r\n"
				+ "  ef.fuente_financ,\r\n"
				+ "  em.id_clasificador,\r\n"
				+ "  sum(em.monto_nacional) as monto\r\n"
				+ "from\r\n"
				+ "  expediente_meta em\r\n"
				+ "  INNER JOIN expediente_secuencia as es ON em.ano_eje = es.ano_eje\r\n"
				+ "  AND em.sec_ejec = es.sec_ejec\r\n"
				+ "  AND em.expediente = es.expediente\r\n"
				+ "  AND em.secuencia = es.secuencia\r\n"
				+ "  AND em.correlativo = es.correlativo\r\n"
				+ "  AND em.ciclo = es.ciclo\r\n"
				+ "  AND em.fase = es.fase\r\n"
				+ "  INNER JOIN expediente_fase as ef ON em.ano_eje = ef.ano_eje\r\n"
				+ "  AND em.sec_ejec = ef.sec_ejec\r\n"
				+ "  AND em.expediente = ef.expediente\r\n"
				+ "  AND em.secuencia = ef.secuencia\r\n"
				+ "  AND em.ciclo = ef.ciclo\r\n"
				+ "  AND em.fase = ef.fase\r\n"
				+ "WHERE\r\n"
				+ "  em.ano_eje = '"+fecha_inicio.substring(6)+"'\r\n"
				+ "  AND em.sec_ejec = '000996'\r\n"
				+ "  AND em.ciclo = 'G'\r\n"
				+ "  AND em.fase = 'C'\r\n"
				+ "  AND em.estado_envio = 'A'\r\n"
				+ "  AND es.fecha_doc >= CTOD('"+fecha_inicio+"')\r\n"
				+ "  AND es.fecha_doc <= CTOD('"+fecha_fin+"')\r\n"
				+ "GROUP BY\r\n"
				+ "  em.ano_eje,\r\n"
				+ "  periodo_mensual,\r\n"
				+ "  em.sec_func,\r\n"
				+ "  ef.fuente_financ,\r\n"
				+ "  em.id_clasificador\r\n"
				+ "ORDER BY em.ano_eje,periodo_mensual,em.sec_func,ef.fuente_financ,em.id_clasificador asc \r\n";

		
		Recordset rs = new Recordset();
		rs.Open(new Variant(query), new Variant(connectionString));
		Short sVariable = 3;
		if (!rs.getEOF()) {
			Fields fs = rs.getFields();
			
			rs.MoveFirst();
			
			while (!rs.getEOF()) {
				
				ClasificadorMontoConsolidado clasificador = new ClasificadorMontoConsolidado();
				
				clasificador.setAno_eje(fs.getItem(0).getValue().getString().trim());
				clasificador.setPeriodo_mensual(fs.getItem(1).getValue().changeType(sVariable).getInt());
				clasificador.setSec_func(fs.getItem(2).getValue().getString().trim());
				clasificador.setFuente_financ(fs.getItem(3).getValue().getString().trim());
				clasificador.setId_clasificador(fs.getItem(4).getValue().getString().trim());
				clasificador.setMonto(fs.getItem(5).getValue().getDecimal());
				System.out.println("Respuesta : "+clasificador);
				rs.MoveNext();
				lstConsolidado.add(clasificador);
			}		
		}
		rs.Close();
		return lstConsolidado;
	}

	@Override
	public List<ClasificadorMontoConsolidado> getClasificadorMontoDevengado(String fecha_inicio, String fecha_fin) {
		List<ClasificadorMontoConsolidado> lstConsolidado = new ArrayList<ClasificadorMontoConsolidado>();
		
		String connectionString = AdoUtility.setConnectionString(folderSiafDataMirror);
		String query =  "SELECT\r\n"
				+ "  em.ano_eje,\r\n"
				+ "  month(es.fecha_doc) as periodo_mensual,\r\n"
				+ "  em.sec_func,\r\n"
				+ "  ef.fuente_financ,\r\n"
				+ "  em.id_clasificador,\r\n"
				+ "  sum(em.monto_nacional) as monto\r\n"
				+ "from\r\n"
				+ "  expediente_meta em\r\n"
				+ "  INNER JOIN expediente_secuencia as es ON em.ano_eje = es.ano_eje\r\n"
				+ "  AND em.sec_ejec = es.sec_ejec\r\n"
				+ "  AND em.expediente = es.expediente\r\n"
				+ "  AND em.secuencia = es.secuencia\r\n"
				+ "  AND em.correlativo = es.correlativo\r\n"
				+ "  AND em.ciclo = es.ciclo\r\n"
				+ "  AND em.fase = es.fase\r\n"
				+ "  INNER JOIN expediente_fase as ef ON em.ano_eje = ef.ano_eje\r\n"
				+ "  AND em.sec_ejec = ef.sec_ejec\r\n"
				+ "  AND em.expediente = ef.expediente\r\n"
				+ "  AND em.secuencia = ef.secuencia\r\n"
				+ "  AND em.ciclo = ef.ciclo\r\n"
				+ "  AND em.fase = ef.fase\r\n"
				+ "WHERE\r\n"
				+ "  em.ano_eje = '"+fecha_inicio.substring(6)+"'\r\n"
				+ "  AND em.sec_ejec = '000996'\r\n"
				+ "  AND em.ciclo = 'G'\r\n"
				+ "  AND em.fase = 'D'\r\n"
				+ "  AND em.estado_envio = 'A'\r\n"
				+ "  AND es.fecha_doc >= CTOD('"+fecha_inicio+"')\r\n"
				+ "  AND es.fecha_doc <= CTOD('"+fecha_fin+"')\r\n"
				+ "GROUP BY\r\n"
				+ "  em.ano_eje,\r\n"
				+ "  periodo_mensual,\r\n"
				+ "  em.sec_func,\r\n"
				+ "  ef.fuente_financ,\r\n"
				+ "  em.id_clasificador\r\n"
				+ "ORDER BY em.ano_eje,periodo_mensual,em.sec_func,ef.fuente_financ,em.id_clasificador asc \r\n";

		
		Recordset rs = new Recordset();
		rs.Open(new Variant(query), new Variant(connectionString));
		Short sVariable = 3;
		if (!rs.getEOF()) {
			Fields fs = rs.getFields();
			
			rs.MoveFirst();
			
			while (!rs.getEOF()) {
				
				ClasificadorMontoConsolidado clasificador = new ClasificadorMontoConsolidado();
				
				clasificador.setAno_eje(fs.getItem(0).getValue().getString().trim());
				clasificador.setPeriodo_mensual(fs.getItem(1).getValue().changeType(sVariable).getInt());
				clasificador.setSec_func(fs.getItem(2).getValue().getString().trim());
				clasificador.setFuente_financ(fs.getItem(3).getValue().getString().trim());
				clasificador.setId_clasificador(fs.getItem(4).getValue().getString().trim());
				clasificador.setMonto(fs.getItem(5).getValue().getDecimal());
				System.out.println("Respuesta : "+clasificador);
				rs.MoveNext();
				lstConsolidado.add(clasificador);
			}		
		}	
		rs.Close();
		return lstConsolidado;
	}

	@Override
	public List<ClasificadorMontoConsolidado> getClasificadorMontoGirado(String fecha_inicio, String fecha_fin) {
		List<ClasificadorMontoConsolidado> lstConsolidado = new ArrayList<ClasificadorMontoConsolidado>();
		
		String connectionString = AdoUtility.setConnectionString(folderSiafDataMirror);
		String query =  "SELECT\r\n"
				+ "  em.ano_eje,\r\n"
				+ "  month(es.fecha_doc) as periodo_mensual,\r\n"
				+ "  em.sec_func,\r\n"
				+ "  ef.fuente_financ,\r\n"
				+ "  em.id_clasificador,\r\n"
				+ "  sum(em.monto_nacional) as monto\r\n"
				+ "from\r\n"
				+ "  expediente_meta em\r\n"
				+ "  INNER JOIN expediente_secuencia as es ON em.ano_eje = es.ano_eje\r\n"
				+ "  AND em.sec_ejec = es.sec_ejec\r\n"
				+ "  AND em.expediente = es.expediente\r\n"
				+ "  AND em.secuencia = es.secuencia\r\n"
				+ "  AND em.correlativo = es.correlativo\r\n"
				+ "  AND em.ciclo = es.ciclo\r\n"
				+ "  AND em.fase = es.fase\r\n"
				+ "  INNER JOIN expediente_fase as ef ON em.ano_eje = ef.ano_eje\r\n"
				+ "  AND em.sec_ejec = ef.sec_ejec\r\n"
				+ "  AND em.expediente = ef.expediente\r\n"
				+ "  AND em.secuencia = ef.secuencia\r\n"
				+ "  AND em.ciclo = ef.ciclo\r\n"
				+ "  AND em.fase = ef.fase\r\n"
				+ "WHERE\r\n"
				+ "  em.ano_eje = '"+fecha_inicio.substring(6)+"'\r\n"
				+ "  AND em.sec_ejec = '000996'\r\n"
				+ "  AND em.ciclo = 'G'\r\n"
				+ "  AND em.fase = 'G'\r\n"
				+ "  AND em.estado_envio = 'A'\r\n"
				+ "  AND es.fecha_doc >= CTOD('"+fecha_inicio+"')\r\n"
				+ "  AND es.fecha_doc <= CTOD('"+fecha_fin+"')\r\n"
				+ "GROUP BY\r\n"
				+ "  em.ano_eje,\r\n"
				+ "  periodo_mensual,\r\n"
				+ "  em.sec_func,\r\n"
				+ "  ef.fuente_financ,\r\n"
				+ "  em.id_clasificador\r\n"
				+ "ORDER BY em.ano_eje,periodo_mensual,em.sec_func,ef.fuente_financ,em.id_clasificador asc \r\n";

		
		Recordset rs = new Recordset();
		rs.Open(new Variant(query), new Variant(connectionString));
		Short sVariable = 3;
		if (!rs.getEOF()) {
			Fields fs = rs.getFields();
			
			rs.MoveFirst();
			
			while (!rs.getEOF()) {
				
				ClasificadorMontoConsolidado clasificador = new ClasificadorMontoConsolidado();
				
				clasificador.setAno_eje(fs.getItem(0).getValue().getString().trim());
				clasificador.setPeriodo_mensual(fs.getItem(1).getValue().changeType(sVariable).getInt());
				clasificador.setSec_func(fs.getItem(2).getValue().getString().trim());
				clasificador.setFuente_financ(fs.getItem(3).getValue().getString().trim());
				clasificador.setId_clasificador(fs.getItem(4).getValue().getString().trim());
				clasificador.setMonto(fs.getItem(5).getValue().getDecimal());
				System.out.println("Respuesta : "+clasificador);
				rs.MoveNext();
				lstConsolidado.add(clasificador);
			}		
		}	
		rs.Close();
		return lstConsolidado;
	}

	@Override
	public List<ClasificadorMontoConsolidado> getClasificadorMontoPagado(String fecha_inicio, String fecha_fin) {
		List<ClasificadorMontoConsolidado> lstConsolidado = new ArrayList<ClasificadorMontoConsolidado>();
		
		String connectionString = AdoUtility.setConnectionString(folderSiafDataMirror);
		String query =  "SELECT\r\n"
				+ "  em.ano_eje,\r\n"
				+ "  month(es.fecha_doc) as periodo_mensual,\r\n"
				+ "  em.sec_func,\r\n"
				+ "  ef.fuente_financ,\r\n"
				+ "  em.id_clasificador,\r\n"
				+ "  sum(em.monto_nacional) as monto\r\n"
				+ "from\r\n"
				+ "  expediente_meta em\r\n"
				+ "  INNER JOIN expediente_secuencia as es ON em.ano_eje = es.ano_eje\r\n"
				+ "  AND em.sec_ejec = es.sec_ejec\r\n"
				+ "  AND em.expediente = es.expediente\r\n"
				+ "  AND em.secuencia = es.secuencia\r\n"
				+ "  AND em.correlativo = es.correlativo\r\n"
				+ "  AND em.ciclo = es.ciclo\r\n"
				+ "  AND em.fase = es.fase\r\n"
				+ "  INNER JOIN expediente_fase as ef ON em.ano_eje = ef.ano_eje\r\n"
				+ "  AND em.sec_ejec = ef.sec_ejec\r\n"
				+ "  AND em.expediente = ef.expediente\r\n"
				+ "  AND em.secuencia = ef.secuencia\r\n"
				+ "  AND em.ciclo = ef.ciclo\r\n"
				+ "  AND em.fase = ef.fase\r\n"
				+ "WHERE\r\n"
				+ "  em.ano_eje = '"+fecha_inicio.substring(6)+"'\r\n"
				+ "  AND em.sec_ejec = '000996'\r\n"
				+ "  AND em.ciclo = 'G'\r\n"
				+ "  AND em.fase = 'P'\r\n"
				+ "  AND em.estado_envio = 'A'\r\n"
				+ "  AND es.fecha_doc >= CTOD('"+fecha_inicio+"')\r\n"
				+ "  AND es.fecha_doc <= CTOD('"+fecha_fin+"')\r\n"
				+ "GROUP BY\r\n"
				+ "  em.ano_eje,\r\n"
				+ "  periodo_mensual,\r\n"
				+ "  em.sec_func,\r\n"
				+ "  ef.fuente_financ,\r\n"
				+ "  em.id_clasificador\r\n"
				+ "ORDER BY em.ano_eje,periodo_mensual,em.sec_func,ef.fuente_financ,em.id_clasificador asc \r\n";

		
		Recordset rs = new Recordset();
		rs.Open(new Variant(query), new Variant(connectionString));
		Short sVariable = 3;
		if (!rs.getEOF()) {
			Fields fs = rs.getFields();
			
			rs.MoveFirst();
			
			while (!rs.getEOF()) {
				
				ClasificadorMontoConsolidado clasificador = new ClasificadorMontoConsolidado();
				
				clasificador.setAno_eje(fs.getItem(0).getValue().getString().trim());
				clasificador.setPeriodo_mensual(fs.getItem(1).getValue().changeType(sVariable).getInt());
				clasificador.setSec_func(fs.getItem(2).getValue().getString().trim());
				clasificador.setFuente_financ(fs.getItem(3).getValue().getString().trim());
				clasificador.setId_clasificador(fs.getItem(4).getValue().getString().trim());
				clasificador.setMonto(fs.getItem(5).getValue().getDecimal());
				System.out.println("Respuesta : "+clasificador);
				rs.MoveNext();
				lstConsolidado.add(clasificador);
			}		
		}	
		rs.Close();
		return lstConsolidado;
	}

	@Override
	public List<ClasificadorMontoConsolidadoPim> getClasificadorMontoPiaPim(String anio) {
		List<ClasificadorMontoConsolidadoPim> lstClasificador = new ArrayList<ClasificadorMontoConsolidadoPim>();
		
		String connectionString = AdoUtility.setConnectionString(folderSiafDataMirror);
		String query = " SELECT "
				+ " g.ano_eje,"
				+ " 0 as periodo_mensual,"
				+ " g.sec_func,"
				+ " g.fuente_financ,"
				+ " g.id_clasificador,"	
				+ " g.presupuesto pia, "
				+ " g.presupuesto+g.modificacion pim  "
				+ " from gasto as g "
				+ " WHERE g.ano_eje = '"+anio+"' "
				+ " ORDER BY g.ano_eje, g.sec_func, g.fuente_financ, g.id_clasificador asc";
		
		Recordset rs = new Recordset();
		rs.Open(new Variant(query), new Variant(connectionString));
						
		if (!rs.getEOF()) {
			Fields fs = rs.getFields();
			
			rs.MoveFirst();
			
			while (!rs.getEOF()) {
				ClasificadorMontoConsolidadoPim clasificador = new ClasificadorMontoConsolidadoPim();
			
				clasificador.setAno_eje(fs.getItem(0).getValue().getString().trim());	
				clasificador.setPeriodo_mensual(0);
				clasificador.setSec_func(fs.getItem(2).getValue().getString().trim());
				clasificador.setFuente_financ(fs.getItem(3).getValue().getString().trim());
				clasificador.setId_clasificador(fs.getItem(4).getValue().getString().trim());
				clasificador.setPia(fs.getItem(5).getValue().getDecimal());	
				clasificador.setPim(fs.getItem(6).getValue().getDecimal());
								
				lstClasificador.add(clasificador);
				rs.MoveNext();
			}	
		}	
		rs.Close();
		return lstClasificador;
	}
}
