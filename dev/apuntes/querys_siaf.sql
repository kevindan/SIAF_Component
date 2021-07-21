/*
Modulo Certificado

certificado  : cabecera
certificado_fase : fuente ruc 
certificado_secuencia : nro de documento fecha cod_doc
certificado_clasif : clasififcador 
certificado_meta : clasifficador meta

Modulo Registro SIAF

expediente : cabecera
expediente_fase : fuente ruc 
expediente_secuencia : nro de documento fecha cod_doc
expediente_clasif  : clasififcador 
expediente_meta : clasifficador meta
expediente_documento ; informacion cheque 
expediente_t6 : devoluciones 
expediente_nota : glosa

como se enlazan 
ano_eje + sec_ejec + expediente + secuencia + correlativo

notas de modificacion

nota_modificatoria_cab as nc
nota_modificatoria_det as nd
nota_modificatoria_sec as ns where estado = 'A' and estado_envio = 'A'

*/

-- SETEAR LA BASE DE DATOS SIAF

SET DEFAULT TO C:\SIAF_VFP\DATA

-- ESTABLECER UNA CONEXIÓN QUE NO SEA EXCLUSIVA

SET EXCLUSIVE OFF

-------------------------------------------------

SELECT c.ano_eje,c.sec_ejec,c.certificado,cs.secuencia,cs.correlativo,cs.cod_doc,cs.num_doc,cs.fecha_doc,MONTH(cs.fecha_doc) as mes,DAY(cs.fecha_doc) as dia,cf.fuente_financ,cf.ruc,cm.sec_func,cc.id_clasificador,cc.monto_nacional,cf.es_compromiso,cs.ind_certificacion,cs.tipo_registro  from certificado as c INNER JOIN certificado_secuencia as cs ON c.ano_eje = cs.ano_eje AND c.sec_ejec = cs.sec_ejec AND c.certificado = cs.certificado INNER JOIN certificado_fase as cf ON cs.ano_eje = cf.ano_eje AND cs.sec_ejec = cf.sec_ejec AND cs.certificado = cf.certificado AND cs.secuencia = cf.secuencia INNER JOIN certificado_meta as cm ON cs.ano_eje = cm.ano_eje AND cs.sec_ejec = cm.sec_ejec AND cs.certificado = cm.certificado AND cs.secuencia = cm.secuencia AND cs.correlativo = cm.correlativo INNER JOIN certificado_clasif as cc ON cm.ano_eje = cc.ano_eje AND cm.sec_ejec = cc.sec_ejec AND cm.certificado = cc.certificado AND cm.secuencia = cc.secuencia AND cm.correlativo = cc.correlativo AND cm.id_clasificador = cc.id_clasificador WHERE c.ano_eje = '2021' and c.sec_ejec = '000996' and c.estado_envio = 'T' and c.estado_registro = 'A' and cs.estado_envio = 'T' and cs.estado_registro = 'A' and cf.estado_envio = 'T' and cf.estado_registro = 'A' and cm.estado_envio = 'T' and cm.estado_registro = 'A' and cc.estado_envio = 'T' and cc.estado_registro = 'A' order by c.ano_eje, c.sec_ejec, cs.certificado, cs.secuencia, cs.correlativo asc

-- QUERY PARA LOS COMMPOMISOS MENSUALES Y DEVENGADOS, GIRADOS Y PAGADOS

SELECT e.ano_eje, e.sec_ejec,e.expediente, es.secuencia, es.correlativo,es.cod_doc,es.num_doc,es.fecha_doc,MONTH(es.fecha_doc) as mes,DAY(es.fecha_doc) as dia,ef.fuente_financ,ef.ruc,em.sec_func,ec.id_clasificador,ec.monto_nacional,ef.ciclo, ef.fase FROM expediente as e inner join  expediente_secuencia as es ON e.ano_eje = es.ano_eje AND e.sec_ejec = es.sec_ejec AND e.expediente = es.expediente inner join expediente_fase as ef ON es.ano_eje = ef.ano_eje AND es.sec_ejec = ef.sec_ejec AND es.expediente = ef.expediente AND es.secuencia = ef.secuencia inner join expediente_meta as em ON es.ano_eje = em.ano_eje AND es.sec_ejec = em.sec_ejec AND es.expediente = em.expediente AND es.secuencia = em.secuencia AND es.correlativo = em.correlativo  inner join expediente_clasif as ec ON em.ano_eje = ec.ano_eje AND em.sec_ejec = ec.sec_ejec AND em.expediente = ec.expediente AND em.secuencia = ec.secuencia AND em.correlativo = ec.correlativo AND em.id_clasificador = ec.id_clasificador WHERE e.ano_eje = '2021' and e.sec_ejec = '000996' and e.estado_envio = 'A' and es.estado_envio = 'A' and ef.estado_envio = 'A' and em.estado_envio = 'A' and ec.estado_envio = 'A' and ef.ciclo = 'G' order by e.ano_eje, e.sec_ejec, es.expediente, es.secuencia, es.correlativo asc

-- QUERY PARA LAS MODIFICACIONES PRESUPUESTALES

SELECT nc.ano_eje,nc.sec_ejec,nc.sec_nota,nd.sec_func,nd.fuente_financ,nd.id_clasificador,ns.notas,ns.fecha,MONTH(ns.fecha) as mes,DAY(ns.fecha)as dia,nd.monto_a as credito,nd.monto_de as anulacion from nota_modificatoria_cab as nc INNER JOIN nota_modificatoria_sec as ns ON nc.ano_eje = ns.ano_eje AND nc.sec_ejec = ns.sec_ejec AND nc.sec_nota = ns.sec_nota INNER JOIN nota_modificatoria_det as nd ON nc.ano_eje = nd.ano_eje AND nc.sec_ejec = nd.sec_ejec AND nc.sec_nota = nd.sec_nota where ns.estado = 'A' and ns.estado_envio = 'A' AND nc.ano_eje = '2021' AND nc.sec_ejec = '000996' ORDER BY nc.sec_nota asc

-- Para el PIA inicial

ano_eje
sec_ejec
fuente_finac
pia
id_clasificador

-- Métodos a considerar desarrollo del formato 8

1. apertuarMes(Integer id_unidad_organica) -> Se debe hacer un registro con todos los clasificadores de gasto relacionadas a las fuentes de financiamiento según metas asignadas a la unidad organica.
   Se debe tomr en cuenta el año y mes actual. Adicional a ello se debe copiar la programacion del mes anterior y hecer un registro a modo de actualización para el mes actual y los meses posteriores.
   De esta manera, se tendrá una trazabilidad en los registros. Se recomienda que la actualización de la apertua del mes sea en la misma funcion.

2. getFormato8(Long id_fortmato8) -> Consulta un regitro del formato 8 para ser actualizado.
3. registrarPorgramacion(formato8 formato8) -> Este método debe realizar el registro a modo de actualización de la programación de gasto por cada unidad orgánica. 


Actiuvidades pendientes para el formato 8

- Terminar el query de cargar para la modificacion presupuestal (consultas las uniones con Humberto)
- Implementar los métodos de carga de las tablas del siaf en la base de datos del sistema.
- Modificar los objetos para la carga del catalogo de clasificadores


--  CREDENCIALES BASE DE DATOS DESARROLLO

IP: 192.168.1.180
usuario: sa
contraseña: brettsacuna$

-- registro siaf con error (sin fecha de documento)

SELECT * from expediente_secuencia WHERE ano_eje='2021' and sec_ejec='000996' and expediente='0000000026' and secuencia='0001' and correlativo='0001'


-- Query para eliminar registros SQL Server y reinicir 
  
  delete from registro_siaf;
  delete from certificado_compromiso_anual;
  delete from nota_modificatoria;
  DBCC CHECKIDENT (registro_siaf, RESEED, 0);
  DBCC CHECKIDENT (certificado_compromiso_anual, RESEED, 0);
  DBCC CHECKIDENT (nota_modificatoria, RESEED, 0);

--  Query para sacara la data de presupuesto

  SELECT ano_eje, sec_ejec, sec_func, fuente_financ, id_clasificador, presupuesto FROM gasto WHERE ano_eje = '2021' AND sec_ejec = '000996'