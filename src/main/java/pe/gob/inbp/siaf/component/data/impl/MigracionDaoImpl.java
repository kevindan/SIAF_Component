package pe.gob.inbp.siaf.component.data.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.jacob.com.Variant;

import pe.gob.inbp.siaf.component.data.MigracionDao;
import pe.gob.inbp.siaf.component.domain.MigracionCertificado;
import pe.gob.inbp.siaf.component.domain.MigracionNotaModificatoria;
import pe.gob.inbp.siaf.component.domain.MigracionRegistroSiaf;
import pe.gob.inbp.siaf.component.payload.GenericResponse;
import pe.gob.inbp.siaf.component.utility.AdoUtility;
import pe.gob.inbp.siaf.component.vfp.Fields;
import pe.gob.inbp.siaf.component.vfp.Recordset;

@Repository
public class MigracionDaoImpl extends JdbcDaoSupport implements MigracionDao {

	@Value("${siaf.data}")
	private String folderSiafData;

	@Value("${siaf.data.mirror}")
	private String folderSiafDataMirror;

	@Value("${siaf.interfase}")
	private String folderSiafInterfase;

	private JdbcTemplate jdbctemplate;

	public MigracionDaoImpl(DataSource dataSource) {
		this.setDataSource(dataSource);
		this.jdbctemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public GenericResponse migrarTablas(String ano_eje, String sec_ejec) {
		GenericResponse response = new GenericResponse();
		List<Error> errores = new ArrayList<Error>();
		
		//1. PROCESO DE CARGA DE LOS REGISTROS DE CERTIFICACIÓN
		Integer cantCert = this.existeRegistros("certificado_compromiso_anual",ano_eje);
		System.out.println("Cantidad de registros entontrados: "+cantCert);
		if(cantCert > 0) {
			String secuencialCertificado = this.ultimoRegistroCertificacion(ano_eje);
			System.out.println("Secuencial: "+secuencialCertificado);
			if(!secuencialCertificado.equals("9000")) {
				Integer iRespCert = this.cargarCertificaciconCompromiso(ano_eje, sec_ejec, secuencialCertificado);
				System.out.println("Estado registro certificación: "+iRespCert);
				if(iRespCert == -1) {
					errores.add(new Error("Error al insertar registro de certificación"));
				}
			}else {
				errores.add(new Error("Error al consultar el último secuencial de certificados"));
			}
		}else if(cantCert == 0) {
			Integer iRespCert = this.cargarCertificaciconCompromiso(ano_eje, sec_ejec, null);
			System.out.println("Estado registro certificación: "+iRespCert);
			if(iRespCert == -1) {
				errores.add(new Error("Error al insertar registro de certificación"));
			}			
		}else if(cantCert < 0) {
			errores.add(new Error("Error al consultar existencia de certificados"));
		}
		
		//2. PROCESO DE CARGA DEL REGISTRO SIAF
		Integer cantRegSiaf = this.existeRegistros("registro_siaf", ano_eje);
		System.out.println("Cantidad de registros SIAF entontrados: "+cantRegSiaf);
		if(cantRegSiaf > 0) {
			String secuencialSiaf = this.ultimoRegistroSiaf(ano_eje);
			System.out.println("Secuencial SIAF: "+secuencialSiaf);
			if(!secuencialSiaf.equals("9000")) {
				Integer iRespRegSiaf = this.cargarRegistroSiaf(ano_eje, sec_ejec, secuencialSiaf);
				System.out.println("Estado registro de registro SIAF: "+iRespRegSiaf);
				if(iRespRegSiaf == -1) {
					errores.add(new Error("Error al insertar registro de registro SIAF"));
				}
			}else {
				errores.add(new Error("Error al consultar el último secuencial de registros SIAF"));
			}
		}else if(cantRegSiaf == 0) {
			Integer iRespRegSiaf = this.cargarRegistroSiaf(ano_eje, sec_ejec, null);
			System.out.println("Estado registro de registro SIAF: "+iRespRegSiaf);
			if(iRespRegSiaf == -1) {
				errores.add(new Error("Error al insertar registro de registro SIAF"));
			}			
		}else if(cantRegSiaf < 0) {
			errores.add(new Error("Error al consultar existencia de registro SIAF"));
		}
		
		//3. PROCESO DE CARGA DE LAS NOTAS DE MODIFICACION
		Integer cantNotaModif = this.existeRegistros("nota_modificatoria", ano_eje);
		System.out.println("Cantidad de registros de nota modificatoria entontrados: "+cantNotaModif);
		if(cantNotaModif > 0) {
			String secuencialNotaModif = this.ultimoRegistroNotaModificacion(ano_eje);
			System.out.println("Secuencial Nota Modificatoria: "+secuencialNotaModif);
			if(!secuencialNotaModif.equals("9000")) {
				Integer iRespRegNotaModif = this.cargarNotaModificatoria(ano_eje, sec_ejec, secuencialNotaModif);
				System.out.println("Estado registro de registro de nota modificatoria: "+iRespRegNotaModif);
				if(iRespRegNotaModif == -1) {
					errores.add(new Error("Error al insertar registro de nota modificatoria"));
				}
			}else {
				errores.add(new Error("Error al consultar el último secuencial de nota modificatoria"));
			}
		}else if(cantNotaModif == 0) {
			Integer iRespRegNotaModif = this.cargarNotaModificatoria(ano_eje, sec_ejec, null);
			System.out.println("Estado registro de nota modificatoria: "+iRespRegNotaModif);
			if(iRespRegNotaModif == -1) {
				errores.add(new Error("Error al insertar registro de Nota modificatoria"));
			}			
		}else if(cantNotaModif < 0) {
			errores.add(new Error("Error al consultar existencia de nota modificatoria"));
		}
		
		//4. PROCESO DE CARGA DE LA TABLA PRESUPUESTO
		//...
		
		if(errores.isEmpty()) {
			response.setCode("0000");
			response.setMessage("Se han cargado correctamente los registros en la base de datos");
			response.setData(null);
		}else {
			response.setCode("0001");
			response.setMessage("Se han cargado correctamente casi todas las tablas, revisar la lista de errores");
			response.setData(errores);			
		}		
		
		return response;
	}

	@Override
	public Integer cargarCertificaciconCompromiso(String ano_eje, String  sec_ejec, String secuencial) {		
		Integer iResp = 1;
		String connectionString = AdoUtility.setConnectionString(folderSiafDataMirror);
		Short sVariable = 3;
		String query = "";
		String sql1 = "SELECT c.ano_eje,"
				+ "c.sec_ejec,"
				+ "c.certificado,"
				+ "cs.secuencia,"
				+ "cs.correlativo,"
				+ "cs.cod_doc,"
				+ "cs.num_doc,"
				+ "cs.fecha_doc,"
				+ "MONTH(cs.fecha_doc) as mes,"
				+ "DAY(cs.fecha_doc) as dia,"
				+ "cf.fuente_financ,"
				+ "cf.ruc,"
				+ "cm.sec_func,"
				+ "cc.id_clasificador,"
				+ "cc.monto_nacional,"
				+ "cf.es_compromiso,"
				+ "cs.ind_certificacion,"
				+ "cs.tipo_registro  "
				+ "from certificado as c "
				+ "INNER JOIN certificado_secuencia as cs ON c.ano_eje = cs.ano_eje AND c.sec_ejec = cs.sec_ejec AND c.certificado = cs.certificado "
				+ "INNER JOIN certificado_fase as cf ON cs.ano_eje = cf.ano_eje AND cs.sec_ejec = cf.sec_ejec AND cs.certificado = cf.certificado AND cs.secuencia = cf.secuencia "
				+ "INNER JOIN certificado_meta as cm ON cs.ano_eje = cm.ano_eje AND cs.sec_ejec = cm.sec_ejec AND cs.certificado = cm.certificado AND cs.secuencia = cm.secuencia AND cs.correlativo = cm.correlativo "
				+ "INNER JOIN certificado_clasif as cc ON cm.ano_eje = cc.ano_eje AND cm.sec_ejec = cc.sec_ejec AND cm.certificado = cc.certificado AND cm.secuencia = cc.secuencia AND cm.correlativo = cc.correlativo AND cm.id_clasificador = cc.id_clasificador "
				+ "WHERE c.ano_eje = '"+ano_eje+"' "
				+ "and c.sec_ejec = '"+sec_ejec+"' "
				+ "and c.estado_envio = 'T' "
				+ "and c.estado_registro = 'A' "
				+ "and cs.estado_envio = 'T' "
				+ "and cs.estado_registro = 'A' "
				+ "and cf.estado_envio = 'T' "
				+ "and cf.estado_registro = 'A' "
				+ "and cm.estado_envio = 'T' "
				+ "and cm.estado_registro = 'A' "
				+ "and cc.estado_envio = 'T' "
				+ "and cc.estado_registro = 'A' "		
				+ "order by c.ano_eje, c.sec_ejec, cs.certificado, cs.secuencia, cs.correlativo asc ";
				
		String sql2 = "SELECT c.ano_eje,"
				+ "c.sec_ejec,"
				+ "c.certificado,"
				+ "cs.secuencia,"
				+ "cs.correlativo,"
				+ "cs.cod_doc,"
				+ "cs.num_doc,"
				+ "cs.fecha_doc,"
				+ "MONTH(cs.fecha_doc) as mes,"
				+ "DAY(cs.fecha_doc) as dia,"
				+ "cf.fuente_financ,"
				+ "cf.ruc,"
				+ "cm.sec_func,"
				+ "cc.id_clasificador,"
				+ "cc.monto_nacional,"
				+ "cf.es_compromiso,"
				+ "cs.ind_certificacion,"
				+ "cs.tipo_registro  "
				+ "from certificado as c "
				+ "INNER JOIN certificado_secuencia as cs ON c.ano_eje = cs.ano_eje AND c.sec_ejec = cs.sec_ejec AND c.certificado = cs.certificado "
				+ "INNER JOIN certificado_fase as cf ON cs.ano_eje = cf.ano_eje AND cs.sec_ejec = cf.sec_ejec AND cs.certificado = cf.certificado AND cs.secuencia = cf.secuencia "
				+ "INNER JOIN certificado_meta as cm ON cs.ano_eje = cm.ano_eje AND cs.sec_ejec = cm.sec_ejec AND cs.certificado = cm.certificado AND cs.secuencia = cm.secuencia AND cs.correlativo = cm.correlativo "
				+ "INNER JOIN certificado_clasif as cc ON cm.ano_eje = cc.ano_eje AND cm.sec_ejec = cc.sec_ejec AND cm.certificado = cc.certificado AND cm.secuencia = cc.secuencia AND cm.correlativo = cc.correlativo AND cm.id_clasificador = cc.id_clasificador "
				+ "WHERE c.ano_eje = '"+ano_eje+"' "
				+ "and c.sec_ejec = '"+sec_ejec+"' "
				+ "and c.estado_envio = 'T' "
				+ "and c.estado_registro = 'A' "
				+ "and cs.estado_envio = 'T' "
				+ "and cs.estado_registro = 'A' "
				+ "and cf.estado_envio = 'T' "
				+ "and cf.estado_registro = 'A' "
				+ "and cm.estado_envio = 'T' "
				+ "and cm.estado_registro = 'A' "
				+ "and cc.estado_envio = 'T' "
				+ "and cc.estado_registro = 'A' "
				+ "and cs.ano_eje+cs.sec_ejec+cs.certificado+cs.secuencia+cs.correlativo > '"+secuencial+"'"
				+ "order by c.ano_eje, c.sec_ejec, cs.certificado, cs.secuencia, cs.correlativo asc ";	
		
		if(secuencial == null) {
			query = sql1;
		}else {
			query = sql2;
		}
				
		Recordset rs = new Recordset();
		rs.Open(new Variant(query), new Variant(connectionString));
						
		if (!rs.getEOF()) {
			Fields fs = rs.getFields();
			
			rs.MoveFirst();
			
			while (!rs.getEOF()) {
				MigracionCertificado mCertificado = new MigracionCertificado();
			
				String pattern = "dd/MM/yyyy";
				SimpleDateFormat format = new SimpleDateFormat(pattern);
								
				mCertificado.setAno_eje(fs.getItem(0).getValue().getString().trim());
				mCertificado.setSec_ejec(fs.getItem(1).getValue().getString().trim());
				mCertificado.setCertificado(fs.getItem(2).getValue().getString().trim());
				mCertificado.setSecuencia(fs.getItem(3).getValue().getString().trim());
				mCertificado.setCorrelativo(fs.getItem(4).getValue().getString().trim());
				mCertificado.setCod_doc(fs.getItem(5).getValue().getString().trim());
				mCertificado.setNum_doc(fs.getItem(6).getValue().getString().trim());
				mCertificado.setFecha_doc(format.format(fs.getItem(7).getValue().getJavaDate()));
				mCertificado.setMes(fs.getItem(8).getValue().changeType(sVariable).getInt());
				mCertificado.setDia(fs.getItem(9).getValue().changeType(sVariable).getInt());
				mCertificado.setFuente_financ(fs.getItem(10).getValue().getString().trim());
				mCertificado.setRuc(fs.getItem(11).getValue().getString().trim());
				mCertificado.setSec_func(fs.getItem(12).getValue().getString().trim());
				mCertificado.setId_clasificador(fs.getItem(13).getValue().getString().trim());
				mCertificado.setMonto_nacional(fs.getItem(14).getValue().getDecimal());
				mCertificado.setEs_compromiso(fs.getItem(15).getValue().getString().trim());
				mCertificado.setInd_certificacion(fs.getItem(16).getValue().getString().trim());
				mCertificado.setTipo_registro(fs.getItem(17).getValue().getString().trim());
				
				iResp = this.insertaCertificacion(mCertificado);
				if(iResp != 1) {
					return iResp;
				}				
				rs.MoveNext();
			}	
		}	
		rs.Close();
		return iResp;
	}
	


	@Override
	public Integer cargarRegistroSiaf(String ano_eje, String sec_ejec, String secuencial) {
		Integer iResp = 1;
		String connectionString = AdoUtility.setConnectionString(folderSiafDataMirror);
		Short sVariable = 3;
		String query = "";
		String sql1 = "SELECT "
				+ "e.ano_eje, "
				+ "e.sec_ejec,"
				+ "e.expediente, "
				+ "es.secuencia, "
				+ "es.correlativo,"
				+ "es.cod_doc,"
				+ "es.num_doc,"
				+ "es.fecha_doc,"
				+ "MONTH(es.fecha_doc) as mes,"
				+ "DAY(es.fecha_doc) as dia,"
				+ "ef.fuente_financ,"
				+ "ef.ruc,"
				+ "em.sec_func,"
				+ "ec.id_clasificador,"
				+ "ec.monto_nacional,"
				+ "ef.ciclo, "
				+ "ef.fase "
				+ "FROM expediente as e inner join expediente_secuencia as es ON e.ano_eje = es.ano_eje AND e.sec_ejec = es.sec_ejec AND e.expediente = es.expediente "
				+ "inner join expediente_fase as ef ON es.ano_eje = ef.ano_eje AND es.sec_ejec = ef.sec_ejec AND es.expediente = ef.expediente AND es.secuencia = ef.secuencia "
				+ "inner join expediente_meta as em ON es.ano_eje = em.ano_eje AND es.sec_ejec = em.sec_ejec AND es.expediente = em.expediente AND es.secuencia = em.secuencia AND es.correlativo = em.correlativo  "
				+ "inner join expediente_clasif as ec ON em.ano_eje = ec.ano_eje AND em.sec_ejec = ec.sec_ejec AND em.expediente = ec.expediente AND em.secuencia = ec.secuencia AND em.correlativo = ec.correlativo AND em.id_clasificador = ec.id_clasificador "
				+ "WHERE e.ano_eje = '"+ano_eje+"' "
				+ "and e.sec_ejec = '"+sec_ejec+"' "
				+ "and e.estado_envio = 'A' "
				+ "and es.estado_envio = 'A' "
				+ "and ef.estado_envio = 'A' "
				+ "and em.estado_envio = 'A' "
				+ "and ec.estado_envio = 'A' "
				+ "and ef.ciclo = 'G' "				
				+ "order by e.ano_eje, e.sec_ejec, es.expediente, es.secuencia, es.correlativo asc ";

				
		String sql2 = "SELECT "
				+ "e.ano_eje, "
				+ "e.sec_ejec,"
				+ "e.expediente, "
				+ "es.secuencia, "
				+ "es.correlativo,"
				+ "es.cod_doc,"
				+ "es.num_doc,"
				+ "es.fecha_doc,"
				+ "MONTH(es.fecha_doc) as mes,"
				+ "DAY(es.fecha_doc) as dia,"
				+ "ef.fuente_financ,"
				+ "ef.ruc,"
				+ "em.sec_func,"
				+ "ec.id_clasificador,"
				+ "ec.monto_nacional,"
				+ "ef.ciclo, "
				+ "ef.fase "
				+ "FROM expediente as e inner join expediente_secuencia as es ON e.ano_eje = es.ano_eje AND e.sec_ejec = es.sec_ejec AND e.expediente = es.expediente "
				+ "inner join expediente_fase as ef ON es.ano_eje = ef.ano_eje AND es.sec_ejec = ef.sec_ejec AND es.expediente = ef.expediente AND es.secuencia = ef.secuencia "
				+ "inner join expediente_meta as em ON es.ano_eje = em.ano_eje AND es.sec_ejec = em.sec_ejec AND es.expediente = em.expediente AND es.secuencia = em.secuencia AND es.correlativo = em.correlativo  "
				+ "inner join expediente_clasif as ec ON em.ano_eje = ec.ano_eje AND em.sec_ejec = ec.sec_ejec AND em.expediente = ec.expediente AND em.secuencia = ec.secuencia AND em.correlativo = ec.correlativo AND em.id_clasificador = ec.id_clasificador "
				+ "WHERE e.ano_eje = '"+ano_eje+"' "
				+ "and e.sec_ejec = '"+sec_ejec+"' "
				+ "and e.estado_envio = 'A' "
				+ "and es.estado_envio = 'A' "
				+ "and ef.estado_envio = 'A' "
				+ "and em.estado_envio = 'A' "
				+ "and ec.estado_envio = 'A' "
				+ "and ef.ciclo = 'G' "
				+ "and es.ano_eje+es.sec_ejec+es.expediente+es.secuencia+es.correlativo > '"+secuencial+"'"
				+ "order by e.ano_eje, e.sec_ejec, es.expediente, es.secuencia, es.correlativo asc ";	
		
		if(secuencial == null) {
			query = sql1;
		}else {
			query = sql2;
		}
				
		Recordset rs = new Recordset();
		rs.Open(new Variant(query), new Variant(connectionString));
						
		if (!rs.getEOF()) {
			Fields fs = rs.getFields();
			
			rs.MoveFirst();
			
			while (!rs.getEOF()) {
				MigracionRegistroSiaf mRegistroSiaf = new MigracionRegistroSiaf();
			
				String pattern = "dd/MM/yyyy";
				SimpleDateFormat format = new SimpleDateFormat(pattern);
								
				mRegistroSiaf.setAno_eje(fs.getItem(0).getValue().getString().trim());
				mRegistroSiaf.setSec_ejec(fs.getItem(1).getValue().getString().trim());
				mRegistroSiaf.setExpediente(fs.getItem(2).getValue().getString().trim());
				mRegistroSiaf.setSecuencia(fs.getItem(3).getValue().getString().trim());
				mRegistroSiaf.setCorrelativo(fs.getItem(4).getValue().getString().trim());
				mRegistroSiaf.setCod_doc(fs.getItem(5).getValue().getString().trim());
				mRegistroSiaf.setNum_doc(fs.getItem(6).getValue().getString().trim());
				if(fs.getItem(7).getValue().getJavaDate() != null) {
					mRegistroSiaf.setFecha_doc(format.format(fs.getItem(7).getValue().getJavaDate()));
				}else {
					mRegistroSiaf.setFecha_doc(null);
				}				
				mRegistroSiaf.setMes(fs.getItem(8).getValue().changeType(sVariable).getInt());
				mRegistroSiaf.setDia(fs.getItem(9).getValue().changeType(sVariable).getInt());
				mRegistroSiaf.setFuente_financ(fs.getItem(10).getValue().getString().trim());
				mRegistroSiaf.setRuc(fs.getItem(11).getValue().getString().trim());
				mRegistroSiaf.setSec_func(fs.getItem(12).getValue().getString().trim());
				mRegistroSiaf.setId_clasificador(fs.getItem(13).getValue().getString().trim());
				mRegistroSiaf.setMonto_nacional(fs.getItem(14).getValue().getDecimal());
				mRegistroSiaf.setCiclo(fs.getItem(15).getValue().getString().trim());
				mRegistroSiaf.setFase(fs.getItem(16).getValue().getString().trim());
				
				iResp = this.insertaRegistroSiaf(mRegistroSiaf);
				if(iResp != 1) {
					return iResp;
				}				
				rs.MoveNext();
			}	
		}	
		rs.Close();
		return iResp;
	}


	@Override
	public Integer cargarNotaModificatoria(String ano_eje, String sec_ejec, String secuencial) {
		Integer iResp = 1;
		String connectionString = AdoUtility.setConnectionString(folderSiafDataMirror);
		Short sVariable = 3;
		String query = "";
		String sql1 = "SELECT "
				+ "nc.ano_eje,"
				+ "nc.sec_ejec,"
				+ "nc.sec_nota,"
				+ "nd.sec_func,"
				+ "nd.fuente_financ,"
				+ "nd.id_clasificador,"
				+ "ns.notas,"
				+ "ns.fecha,"
				+ "MONTH(ns.fecha) as mes,"
				+ "DAY(ns.fecha) as dia,"
				+ "nd.monto_a as credito,"
				+ "nd.monto_de as anulacion "
				+ "from nota_modificatoria_cab as nc "
				+ "INNER JOIN nota_modificatoria_sec as ns ON nc.ano_eje = ns.ano_eje AND nc.sec_ejec = ns.sec_ejec AND nc.sec_nota = ns.sec_nota "
				+ "INNER JOIN nota_modificatoria_det as nd ON nc.ano_eje = nd.ano_eje AND nc.sec_ejec = nd.sec_ejec AND nc.sec_nota = nd.sec_nota "
				+ "where ns.estado = 'A' "
				+ "and ns.estado_envio = 'A' "
				+ "AND nc.ano_eje = '"+ano_eje+"' "
				+ "AND nc.sec_ejec = '"+sec_ejec+"' "
				+ "ORDER BY nc.sec_nota asc ";


				
		String sql2 ="SELECT "
				+ "nc.ano_eje,"
				+ "nc.sec_ejec,"
				+ "nc.sec_nota,"
				+ "nd.sec_func,"
				+ "nd.fuente_financ,"
				+ "nd.id_clasificador,"
				+ "ns.notas,"
				+ "ns.fecha,"
				+ "MONTH(ns.fecha) as mes,"
				+ "DAY(ns.fecha) as dia,"
				+ "nd.monto_a as credito,"
				+ "nd.monto_de as anulacion "
				+ "from nota_modificatoria_cab as nc "
				+ "INNER JOIN nota_modificatoria_sec as ns ON nc.ano_eje = ns.ano_eje AND nc.sec_ejec = ns.sec_ejec AND nc.sec_nota = ns.sec_nota "
				+ "INNER JOIN nota_modificatoria_det as nd ON nc.ano_eje = nd.ano_eje AND nc.sec_ejec = nd.sec_ejec AND nc.sec_nota = nd.sec_nota "
				+ "where ns.estado = 'A' "
				+ "AND ns.estado_envio = 'A' "
				+ "AND nc.ano_eje = '"+ano_eje+"' "
				+ "AND nc.sec_ejec = '"+sec_ejec+"' "
				+ "AND nc.ano_eje+nc.sec_ejec+nc.sec_nota > '"+secuencial+"'"
				+ "ORDER BY nc.sec_nota asc ";	
		
		if(secuencial == null) {
			query = sql1;
		}else {
			query = sql2;
		}
				
		Recordset rs = new Recordset();
		rs.Open(new Variant(query), new Variant(connectionString));
						
		if (!rs.getEOF()) {
			Fields fs = rs.getFields();
			
			rs.MoveFirst();
			
			while (!rs.getEOF()) {
				MigracionNotaModificatoria mNotaModificatoria = new MigracionNotaModificatoria();
			
				String pattern = "dd/MM/yyyy";
				SimpleDateFormat format = new SimpleDateFormat(pattern);
								
				mNotaModificatoria.setAno_eje(fs.getItem(0).getValue().getString().trim());
				mNotaModificatoria.setSec_ejec(fs.getItem(1).getValue().getString().trim());
				mNotaModificatoria.setSec_nota(fs.getItem(2).getValue().getString().trim());
				mNotaModificatoria.setSec_func(fs.getItem(3).getValue().getString().trim());
				mNotaModificatoria.setFuente_financ(fs.getItem(4).getValue().getString().trim());
				mNotaModificatoria.setId_clasificador(fs.getItem(5).getValue().getString().trim());
				mNotaModificatoria.setNotas(fs.getItem(6).getValue().getString().trim());
				if(fs.getItem(7).getValue().getJavaDate() != null) {
					mNotaModificatoria.setFecha(format.format(fs.getItem(7).getValue().getJavaDate()));
				}else {
					mNotaModificatoria.setFecha(null);
				}				
				mNotaModificatoria.setMes(fs.getItem(8).getValue().changeType(sVariable).getInt());
				mNotaModificatoria.setDia(fs.getItem(9).getValue().changeType(sVariable).getInt());
				mNotaModificatoria.setCredito(fs.getItem(10).getValue().getDecimal());
				mNotaModificatoria.setAnulacion(fs.getItem(11).getValue().getDecimal());
				
				iResp = this.insertaNotaModificatoria(mNotaModificatoria);
				if(iResp != 1) {
					return iResp;
				}				
				rs.MoveNext();
			}	
		}	
		rs.Close();
		return iResp;
	}

	@Override
	public Integer cargarRegistrosPresupuesto(String ano_eje, String sec_ejec, String secuencial) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Integer insertaCertificacion(MigracionCertificado certificado) {
		Integer iResp = 1;		
		String sql = "INSERT INTO certificado_compromiso_anual("
				+ "           ano_eje"
				+ "		      ,sec_ejec"
				+ "           ,certificado"
				+ "           ,secuencia"
				+ "           ,correlativo"
				+ "           ,cod_doc"
				+ "           ,fecha_doc"
				+ "           ,num_doc"
				+ "           ,mes"
				+ "           ,dia"
				+ "           ,fuente_financ"
				+ "           ,ruc"
				+ "           ,id_clasificador"
				+ "           ,sec_func"
				+ "           ,monto_nacional"
				+ "           ,es_compromiso"
				+ "           ,ind_certificacion"
				+ "			  ,tipo_registro) "
				+ "     VALUES "
				+ "           (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			jdbctemplate.update(sql, new Object[] {certificado.getAno_eje(),certificado.getSec_ejec(),certificado.getCertificado(), certificado.getSecuencia(),
					certificado.getCorrelativo(),certificado.getCod_doc(),certificado.getFecha_doc(),certificado.getNum_doc(), certificado.getMes(), 
					certificado.getDia(),certificado.getFuente_financ(), certificado.getRuc(),certificado.getId_clasificador(), certificado.getSec_func(),
					certificado.getMonto_nacional(), certificado.getEs_compromiso(), certificado.getInd_certificacion(), certificado.getTipo_registro()});
			iResp = 1;
		} catch (Exception e) {			
			System.out.println(e.getMessage());
			iResp = 0;
		}
		return iResp;
	}	
	
	public Integer insertaRegistroSiaf(MigracionRegistroSiaf registroSiaf) {
		Integer iResp = 1;		
		String sql = "INSERT INTO registro_siaf"
				+ "           (ano_eje"
				+ "           ,sec_ejec"
				+ "           ,expediente"
				+ "           ,secuencia"
				+ "           ,correlativo"
				+ "           ,cod_doc"
				+ "           ,fecha_doc"
				+ "           ,num_doc"
				+ "           ,mes"
				+ "           ,dia"
				+ "           ,fuente_financ"
				+ "           ,ruc"
				+ "           ,id_clasificador"
				+ "           ,sec_func"
				+ "           ,monto_nacional"
				+ "           ,fase"
				+ "           ,ciclo)"
				+ "     VALUES "
				+ "           (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			jdbctemplate.update(sql, new Object[] {registroSiaf.getAno_eje(),registroSiaf.getSec_ejec(),registroSiaf.getExpediente(), registroSiaf.getSecuencia(),
					registroSiaf.getCorrelativo(),registroSiaf.getCod_doc(),registroSiaf.getFecha_doc(),registroSiaf.getNum_doc(), registroSiaf.getMes(), 
					registroSiaf.getDia(),registroSiaf.getFuente_financ(), registroSiaf.getRuc(),registroSiaf.getId_clasificador(), registroSiaf.getSec_func(),
					registroSiaf.getMonto_nacional(),registroSiaf.getFase(),registroSiaf.getCiclo()});
			iResp = 1;
		} catch (Exception e) {			
			System.out.println(e.getMessage());
			iResp = 0;
		}
		return iResp;
	}
	
	public Integer insertaNotaModificatoria(MigracionNotaModificatoria notaModificatoria) {
		Integer iResp = 1;		
		String sql = "INSERT INTO nota_modificatoria"
				+ "           (ano_eje"
				+ "           ,sec_ejec"
				+ "           ,sec_nota"
				+ "           ,fecha"
				+ "           ,notas"
				+ "           ,mes"
				+ "           ,dia"
				+ "           ,fuente_financ"
				+ "           ,id_clasificador"
				+ "           ,sec_func"
				+ "           ,anulacion"
				+ "           ,credito)"
				+ "     VALUES"
				+ "           (?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			jdbctemplate.update(sql, new Object[] {notaModificatoria.getAno_eje(),notaModificatoria.getSec_ejec(),notaModificatoria.getSec_nota(),
					notaModificatoria.getFecha(), notaModificatoria.getNotas(), notaModificatoria.getMes(), notaModificatoria.getDia(),
					notaModificatoria.getFuente_financ(), notaModificatoria.getId_clasificador(), notaModificatoria.getSec_func(), notaModificatoria.getAnulacion(),
					notaModificatoria.getCredito()});
			iResp = 1;
		} catch (Exception e) {			
			System.out.println(e.getMessage());
			iResp = 0;
		}
		return iResp;
	}
	
	public Integer existeRegistros(String nombre_tabla, String ano_eje) {
		Integer numeroRegistros = 0;
		try {			
			String query = "SELECT count(*) as cantidad FROM "+nombre_tabla+" where ano_eje = '"+ano_eje+"'";			
			numeroRegistros = getJdbcTemplate().queryForObject(query, Integer.class);									
		} catch (Exception e) {
			System.out.println(e.getMessage());			
			numeroRegistros= -1;
		}
		return numeroRegistros;		
	}
	
	public String ultimoRegistroCertificacion(String ano_eje) {
		String secuencial = "";
		try {			
			String query = " select top 1 (ano_eje+sec_ejec+certificado+secuencia+correlativo) as secuencial from certificado_compromiso_anual where ano_eje = '"+ano_eje+"' order by id_certificado_compromiso_anual desc";			
			secuencial = getJdbcTemplate().queryForObject(query, String.class);									
		} catch (Exception e) {
			System.out.println(e.getMessage());			
			secuencial= "9000";
		}
		return secuencial;		
	}
	
	public String ultimoRegistroSiaf(String ano_eje) {
		String secuencial = "";
		try {			
			String query = " select top 1 (ano_eje+sec_ejec+expediente+secuencia+correlativo) as secuencial from registro_siaf where ano_eje = '"+ano_eje+"' order by id_registro_siaf desc";			
			secuencial = getJdbcTemplate().queryForObject(query, String.class);									
		} catch (Exception e) {
			System.out.println(e.getMessage());			
			secuencial= "9000";
		}
		return secuencial;		
	}

	public String ultimoRegistroNotaModificacion(String ano_eje) {
		String secuencial = "";
		try {			
			String query = " select top 1 (ano_eje+sec_ejec+sec_nota) as secuencial from nota_modificatoria where ano_eje = '"+ano_eje+"' order by id_nota_modificatoria desc";			
			secuencial = getJdbcTemplate().queryForObject(query, String.class);									
		} catch (Exception e) {
			System.out.println(e.getMessage());			
			secuencial= "9000";
		}
		return secuencial;		
	}
	
}
