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
		Integer cantCert = this.existeCertificados("certificado_compromiso_anual",ano_eje);
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
		//3. PROCESO DE CARGA DE LAS NOTAS DE MODIFICACION
		//4. PROCESO DE CARGA DE LA TABLA PRESUPUESTO
		
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
	
	public Integer existeCertificados(String nombre_tabla, String ano_eje) {
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

	@Override
	public Integer cargarRegistroSiaf(String ano_eje, String sec_ejec) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer cargarNotaModificatoria(String ano_eje, String sec_ejec) {
		// TODO Auto-generated method stub
		return null;
	}

}
