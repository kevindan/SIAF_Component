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
*/

-- CRITERIOS PARA FILTRAR CONSOLIDADOS
-- certificado
SELECT certificado from certificado WHERE ano_eje = '2021' AND sec_ejec = '000996' AND estado_envio = 'T' AND estado_registro = 'A'
-- certificado_secuencia
SELECT ano_eje, sec_ejec, certificado, secuencia, correlativo, cod_doc, num_doc, fecha_doc, month(fecha_doc) as mes, DAY(fecha_doc) as dia from certificado_secuencia WHERE ano_eje = '2021' AND sec_ejec = '000996' AND estado_envio = 'T' AND estado_registro = 'A' AND ind_certificacion = 'S'
-- certificado_fase
SELECT ano_eje, sec_ejec, certificado, secuencia, fuente_financ, ruc from certificado_fase WHERE ano_eje = '2021' AND sec_ejec = '000996' AND estado_envio = 'T' AND estado_registro = 'A' and es_compromiso = 'N'
-- certificado_meta
SELECT ano_eje, sec_ejec, certificado, secuencia, correlativo, id_clasificador, sec_func, monto_nacional FROM certificado_meta where ano_eje = '2021' AND sec_ejec = '000996' AND estado_envio = 'T' AND estado_registro = 'A'
-- valor del certificado para el testing

cerificado = '0000000010'
En el detalle debe tener al menos 95 registros


-- Para las notas modificatorias

ano_eje
sec_nota
secuencia
sec_func
fuente_financ
id_clasificador
fecha
notas
credito
anulacion

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


SELECT c.ano_eje,c.sec_ejec,c.certificado,cs.secuencia,cs.correlativo,cs.cod_doc,cs.num_doc,cs.fecha_doc,MONTH(cs.fecha_doc) as mes,DAY(cs.fecha_doc) as dia,cf.fuente_financ,cf.ruc,cm.sec_func,cc.id_clasificador,cc.monto_nacional,cf.es_compromiso,cs.ind_certificacion,cs.tipo_registro  from certificado as c INNER JOIN certificado_secuencia as cs ON c.ano_eje = cs.ano_eje AND c.sec_ejec = cs.sec_ejec AND c.certificado = cs.certificado INNER JOIN certificado_fase as cf ON cs.ano_eje = cf.ano_eje AND cs.sec_ejec = cf.sec_ejec AND cs.certificado = cf.certificado AND cs.secuencia = cf.secuencia INNER JOIN certificado_meta as cm ON cs.ano_eje = cm.ano_eje AND cs.sec_ejec = cm.sec_ejec AND cs.certificado = cm.certificado AND cs.secuencia = cm.secuencia AND cs.correlativo = cm.correlativo INNER JOIN certificado_clasif as cc ON cm.ano_eje = cc.ano_eje AND cm.sec_ejec = cc.sec_ejec AND cm.certificado = cc.certificado AND cm.secuencia = cc.secuencia AND cm.correlativo = cc.correlativo AND cm.id_clasificador = cc.id_clasificador WHERE c.ano_eje = '2021' and c.sec_ejec = '000996' and c.estado_envio = 'T' and c.estado_registro = 'A' and cs.estado_envio = 'T' and cs.estado_registro = 'A' and cf.estado_envio = 'T' and cf.estado_registro = 'A' and cm.estado_envio = 'T' and cm.estado_registro = 'A' and cc.estado_envio = 'T' and cc.estado_registro = 'A' order by c.ano_eje, c.sec_ejec, cs.certificado, cs.secuencia, cs.correlativo asc

-- QUERY PARA LOS COMMPOMISOS MENSUALES Y DEVENGADOS, GIRADOS Y PAGADOS

SELECT e.ano_eje, e.sec_ejec,e.expediente, es.secuencia, es.correlativo,es.cod_doc,es.num_doc,es.fecha_doc,MONTH(es.fecha_doc) as mes,DAY(es.fecha_doc) as dia,ef.fuente_financ,ef.ruc,em.sec_func,ec.id_clasificador,ec.monto_nacional,ef.ciclo, ef.fase FROM expediente as e inner join  expediente_secuencia as es ON e.ano_eje = es.ano_eje AND e.sec_ejec = es.sec_ejec AND e.expediente = es.expediente inner join expediente_fase as ef ON es.ano_eje = ef.ano_eje AND es.sec_ejec = ef.sec_ejec AND es.expediente = ef.expediente AND es.secuencia = ef.secuencia inner join expediente_meta as em ON es.ano_eje = em.ano_eje AND es.sec_ejec = em.sec_ejec AND es.expediente = em.expediente AND es.secuencia = em.secuencia AND es.correlativo = em.correlativo  inner join expediente_clasif as ec ON em.ano_eje = ec.ano_eje AND em.sec_ejec = ec.sec_ejec AND em.expediente = ec.expediente AND em.secuencia = ec.secuencia AND em.correlativo = ec.correlativo AND em.id_clasificador = ec.id_clasificador WHERE e.ano_eje = '2021' and e.sec_ejec = '000996' and e.estado_envio = 'A' and es.estado_envio = 'A' and ef.estado_envio = 'A' and em.estado_envio = 'A' and ec.estado_envio = 'A' and ef.ciclo = 'G' order by e.ano_eje, e.sec_ejec, es.expediente, es.secuencia, es.correlativo asc

-- QUERY PARA LAS MODIFICACIONES PRESUPUESTALES

SELECT nc.ano_eje,nc.sec_ejec,nc.sec_nota,nd.sec_func,nd.fuente_financ,nd.id_clasificador,ns.notas,ns.fecha,MONTH(ns.fecha) as mes,DAY(ns.fecha)as dia,nd.monto_a as credito,nd.monto_de as anulacion from nota_modificatoria_cab as nc INNER JOIN nota_modificatoria_sec as ns ON nc.ano_eje = ns.ano_eje AND nc.sec_ejec = ns.sec_ejec AND nc.sec_nota = ns.sec_nota INNER JOIN nota_modificatoria_det as nd ON nc.ano_eje = nd.ano_eje AND nc.sec_ejec = nd.sec_ejec AND nc.sec_nota = nd.sec_nota where ns.estado = 'A' and ns.estado_envio = 'A' AND nc.ano_eje = '2021' AND nc.sec_ejec = '000996' ORDER BY nc.sec_nota asc




nota_modificatoria_cab as nc
nota_modificatoria_det as nd
nota_modificatoria_sec as ns where estado = 'A' and estado_envio = 'A'


nc.ano_eje 
nc.sec_ejec
nc.sec_func
nc.sec_nota
nd.fuente_financ
nd.id_clasificador
ns.notas
ns.fecha
mes
dia
nd.monto_a as credito
nd.monto_de as anulacion


select * from certificado_secuencia where ano_eje+sec_ejec+certificado+secuencia+correlativo > '2021000996000000000200010001'


Actiuvidades pendientes para el formato 8

- Terminar el query de cargar para la modificacion presupuestal (consultas las uniones con Humberto)
- Implementar los métodos de carga de las tablas del siaf en la base de datos del sistema.
- Modificar los objetos para la carga del catalogo de clasificadores


IP: 192.168.1.180
usuario: sa
contraseña: brettsacuna$

-- registro siaf con error (sin fecha de documento)

SELECT * from expediente_secuencia WHERE ano_eje='2021' and sec_ejec='000996' and expediente='0000000026' and secuencia='0001' and correlativo='0001'


-- qwuery para eliminar

  
  delete from registro_siaf;
  delete from certificado_compromiso_anual;
  DBCC CHECKIDENT (registro_siaf, RESEED, 0);
  DBCC CHECKIDENT (certificado_compromiso_anual, RESEED, 0);


-- querys vfp


CREATE QUERY
SELECT * from usuario_entidad
CREATE QUERY
SELECT * from usuario_entidad
 w hj iioioioioh ji wju wiouioiiiiiuiii++lp}{
nw n nnj8y nb fj  n nj h nh8  b  nmjh j
 
m j + nlñp¿´pñ

p

SET EXCLUSIVE
SET EXCLUSIVE OFF
SET DEFAULT TO C:\SIAF_VFP\DATA
USE ?
SET EXCLUSIVE OFF
SET DEFAULT TO C:\SIAF_VFP\DATA
USE ?
BROWSE FOR ano_eje = '2021' AND sec_ejec = '000143'
CLOSE ALL
SET EXCLUSIVE OFF
SET DEFAULT TO C:\SIAF_VFP\DATA
SET EXCLUSIVE OFF
USE ?
BROWSE FOR ano_eje = '2021'
SET DEFAULT TO C:\SIAF_VFP\DATA
SET EXCLUSIVE OFF
USE ?
BROWSE FOR ano_eje = '2021' AND sec_ejec = '000996'
SELECT * from certificado WHERE ano_eje = '2021' AND sec_ejec = '000996'
SELECT * from certificado WHERE ano_eje = '2021' AND sec_ejec = '000996' AND estado_envio = 'T'
SELECT * from certificado WHERE ano_eje = '2021' AND sec_ejec = '000996' AND estado_envio = 'T' AND estado_registro = 'A'
--cabecera
SELECT * from certificado_secuencia WHERE ano_eje = '2021' AND sec_ejec = '000996' AND estado_envio = 'T' AND estado_registro = 'A'
SELECT * from certificado WHERE ano_eje = '2021' AND sec_ejec = '000996' AND estado_envio = 'T' AND estado_registro = 'A'
SELECT * from certificado_secuencia WHERE ano_eje = '2021' AND sec_ejec = '000996' AND estado_envio = 'T' AND estado_registro = 'A'
SELECT * from certificado_secuencia WHERE ano_eje = '2021' AND sec_ejec = '000996' AND estado_envio = 'T' AND estado_registro = 'A' AND ind_certificacion = 'S'
SELECT ano_eje, sec_ejec, certificado, secuencia, correlativo, cod_doc, num_doc, fecha_doc, month(fecha_doc) as mes, DAY(fecha_doc) as dia, tipo_registro from certificado_secuencia WHERE ano_eje = '2021' AND sec_ejec = '000996' AND estado_envio = 'T' AND estado_registro = 'A' AND ind_certificacion = 'S'
SELECT ano_eje, sec_ejec, certificado, secuencia, correlativo, cod_doc, num_doc, fecha_doc, month(fecha_doc) as mes, DAY(fecha_doc) as dia, tipo_registro from certificado_secuencia WHERE ano_eje = '2021' AND sec_ejec = '000996' AND estado_envio = 'T' AND estado_registro = 'A' AND ind_certificacion = 'S' ORDER BY certificad, secuencia, correlativo asc
SELECT ano_eje, sec_ejec, certificado, secuencia, correlativo, cod_doc, num_doc, fecha_doc, month(fecha_doc) as mes, DAY(fecha_doc) as dia, tipo_registro from certificado_secuencia WHERE ano_eje = '2021' AND sec_ejec = '000996' AND estado_envio = 'T' AND estado_registro = 'A' AND ind_certificacion = 'S' ORDER BY certificado, secuencia, correlativo asc
SELECT * from certificado_secuencia WHERE ano_eje = '2021' AND sec_ejec = '000996' AND estado_envio = 'T' AND estado_registro = 'A' AND ind_certificacion = 'S'
SELECT * from certificado_fase WHERE ano_eje = '2021' AND sec_ejec = '000996' AND estado_envio = 'T' AND estado_registro = 'A'
SELECT ano_eje, sec_ejec, certificado, secuencia, correlativo, fuente_financ, ruc from certificado_fase WHERE ano_eje = '2021' AND sec_ejec = '000996' AND estado_envio = 'T' AND estado_registro = 'A' an es_compromiso = 'N'
SELECT ano_eje, sec_ejec, certificado, secuencia, correlativo, fuente_financ, ruc from certificado_fase WHERE ano_eje = '2021' AND sec_ejec = '000996' AND estado_envio = 'T' AND estado_registro = 'A' and es_compromiso = 'N'
SELECT ano_eje, sec_ejec, certificado, secuencia, fuente_financ, ruc from certificado_fase WHERE ano_eje = '2021' AND sec_ejec = '000996' AND estado_envio = 'T' AND estado_registro = 'A' and es_compromiso = 'N'
SELECT * from certificado_clasif WHERE ano_eje = '2021' AND sec_ejec = '000996' AND estado_envio = 'T' AND estado_registro = 'A'
SELECT * from certificado_meta WHERE ano_eje = '2021' AND sec_ejec = '000996' AND estado_envio = 'T' AND estado_registro = 'A'
SELECT ano_eje, sec_ejec, certificado, secuencia, correlativo, id_clasificador, sec_func, monto_nacional from ano_eje = '2021' AND sec_ejec = '000996' AND estado_envio = 'T' AND estado_registro = 'A'
SELECT ano_eje, sec_ejec, certificado, secuencia, correlativo, id_clasificador, sec_func, monto_nacional FROM certificado_meta ano_eje = '2021' AND sec_ejec = '000996' AND estado_envio = 'T' AND estado_registro = 'A' AND monto_nacional < 0
SELECT ano_eje, sec_ejec, certificado, secuencia, correlativo, id_clasificador, sec_func, monto_nacional FROM certificado_meta where ano_eje = '2021' AND sec_ejec = '000996' AND estado_envio = 'T' AND estado_registro = 'A' AND monto_nacional < 0
SELECT ano_eje, sec_ejec, certificado, secuencia, correlativo, id_clasificador, sec_func, monto_nacional FROM certificado_meta where ano_eje = '2021' AND sec_ejec = '000996' AND estado_envio = 'T' AND estado_registro = 'A'
SELECT ano_eje, sec_ejec, certificado, secuencia, correlativo, cod_doc, num_doc, fecha_doc, month(fecha_doc) as mes, DAY(fecha_doc) as dia, tipo_registro from certificado_secuencia WHERE ano_eje = '2021' AND sec_ejec = '000996' AND estado_envio = 'T' AND estado_registro = 'A' AND ind_certificacion = 'S' ORDER BY certificado, secuencia, correlativo asc
SELECT ano_eje, sec_ejec, certificado, secuencia, correlativo, id_clasificador, sec_func, monto_nacional FROM certificado_meta where ano_eje = '2021' AND sec_ejec = '000996' AND estado_envio = 'T' AND estado_registro = 'A'
SELECT * from certificado_fase
SELECT * from expediente
SELECT * from expediente WHERE ano_eje = '2021'
SELECT * from expediente_fase WHERE ano_eje = '2021'
SELECT * from nota_modificatoria
SELECT * from nota_modificatoria_cab
SELECT * from nota_modificatoria_cab WHERE ano_eje = '2021'
SELECT * from nota_modificatoria_sec WHERE ano_eje = '2021'
SELECT * from nota_modificatoria_det WHERE ano_eje = '2021'
SELECT * from nota_modificatoria_cab WHERE ano_eje = '2021'
SELECT * from nota_modificatoria_sec WHERE ano_eje = '2021'
SELECT * from nota_modificatoria_sec WHERE ano_eje = '2021' ORDER BY sec_nota, secuencia asc
SELECT * from nota_modificatoria_det WHERE ano_eje = '2021' AND sec_nota = '0000000001'
SELECT * from nota_modificatoria_doc WHERE ano_eje = '2021' AND sec_nota = '0000000001'
SELECT * from nota_modificatoria_doc WHERE ano_eje = '2021'
SELECT * from gasto WHERE ano_eje = '2021'
SET DEFAULT TO C:\SIAF_VFP\DATA
SET EXCLUSIVE OFF
USE ?
BROWSE FOR ano_eje = '2021' AND sec_ejec = '2021'
BROWSE FOR ano_eje = '2021' AND sec_ejec = '000996'
BROWSE FOR ano_eje = '2021'
USE ?
BROWSE
BROWSE FOR ano_eje = '2021'
BROWSE FOR ano_eje = '2021' AND sec_ejec = '000996'
SELECT * from certificado_secuencia WHERE ano_eje = '2021' AND sec_ejec = '000996' ORDER BY certificado, secuencia correlativo ASC
SELECT * from certificado_secuencia WHERE ano_eje = '2021' AND sec_ejec = '000996' ORDER BY certificado, secuencia ,correlativo ASC
SET DEFAULT TO C:\SIAF_VFP\DATA
SET EXCLUSIVE OFF
USE ?
BROWSE
SET DEFAULT TO C:\SIAF_VFP\DATA
SET EXCLUSIVE OFF
USE ?
BROWSE
SELECT certificado from certificado WHERE ano_eje = '2021' AND sec_ejec = '000996' AND estado_envio = 'T' AND estado_registro = 'A'
SELECT * from certificado_secuencia
SELECT * from certificado_secuencia WHERE ano_eje = '2021' AND sec_ejec = '000996'
SELECT * from certificado_secuencia WHERE ano_eje = '2021' AND sec_ejec = '000996' ORDER BY certificado, secuencia, correlativo asc
SELECT * from certificado_secuencia WHERE ano_eje = '2021' AND sec_ejec = '000996' AND certificado = '0000000010' ORDER BY certificado, secuencia, correlativo asc
SELECT * from certificado_meta WHERE ano_eje = '2021' AND sec_ejec = '000996' AND certificado = '0000000010' ORDER BY certificado, secuencia, correlativo asc

SELECT c.ano_eje,c.sec_ejec,c.certificado,cs.secuencia,cs.correlativo,cs.cod_doc,cs.num_doc,cs.fecha_doc,MONTH(cs.fecha_doc) as mes,DAY(cs.fecha_doc) as dia,cf.fuente_finac,cf.ruc,cc.sec_func,cc.id_clasificador,cc.monto_nacional,cf.es_compromiso,cs.ind_certificacion  from certificado as c INNER JOIN certificado_secuencia as cs ON c.ano_eje = cs.ano_eje AND c.sec_ejec = cs.sec_ejec AND c.certificado = cs.certificado INNER JOIN certificado_fase as cf ON cs.ano_eje = cf.ano_eje AND cs.sec_ejec = cf.sec_ejec AND cs.certificado = cf.certificado AND cs.secuencia = cf.secuencia INNER JOIN certificado_meta as cm ON cs.ano_eje = cm.ano_eje AND cs.sec_ejec = cm.sec_ejec AND cs.certificado = cm.certificado AND cs.secuencia = cm.secuencia AND cs.correlativo = cm.correlativo INNER JOIN certificado_clasif as cc ON cm.ano_eje = cc.ano_eje AND cm.sec_ejec = cc.sec_ejec AND cm.certificado = cc.certificado AND cm.secuencia = cc.secuencia AND cm.correlativo = cc.correlativo AND cm.id_clasificador = cc.id_clasificador WHERE c.ano_eje = '2021' and c.sec_ejec = '000996' and c.estado_envio = 'T' and c.estado_registro = 'A' and cs.estado_envio = 'T' and cs.estado_registro = 'A' and cf.estado_envio = 'T' and cf.estado_registro = 'A' and cm.estado_envio = 'T' and cm.estado_registro = 'A' and cc.estado_envio = 'T' and cc.estado_registro = 'A' and c.certificado = '0000000010'
SELECT c.ano_eje,c.sec_ejec,c.certificado,cs.secuencia,cs.correlativo,cs.cod_doc,cs.num_doc,cs.fecha_doc,MONTH(cs.fecha_doc) as mes,DAY(cs.fecha_doc) as dia,cf.fuente_financ,cf.ruc,cc.sec_func,cc.id_clasificador,cc.monto_nacional,cf.es_compromiso,cs.ind_certificacion  from certificado as c INNER JOIN certificado_secuencia as cs ON c.ano_eje = cs.ano_eje AND c.sec_ejec = cs.sec_ejec AND c.certificado = cs.certificado INNER JOIN certificado_fase as cf ON cs.ano_eje = cf.ano_eje AND cs.sec_ejec = cf.sec_ejec AND cs.certificado = cf.certificado AND cs.secuencia = cf.secuencia INNER JOIN certificado_meta as cm ON cs.ano_eje = cm.ano_eje AND cs.sec_ejec = cm.sec_ejec AND cs.certificado = cm.certificado AND cs.secuencia = cm.secuencia AND cs.correlativo = cm.correlativo INNER JOIN certificado_clasif as cc ON cm.ano_eje = cc.ano_eje AND cm.sec_ejec = cc.sec_ejec AND cm.certificado = cc.certificado AND cm.secuencia = cc.secuencia AND cm.correlativo = cc.correlativo AND cm.id_clasificador = cc.id_clasificador WHERE c.ano_eje = '2021' and c.sec_ejec = '000996' and c.estado_envio = 'T' and c.estado_registro = 'A' and cs.estado_envio = 'T' and cs.estado_registro = 'A' and cf.estado_envio = 'T' and cf.estado_registro = 'A' and cm.estado_envio = 'T' and cm.estado_registro = 'A' and cc.estado_envio = 'T' and cc.estado_registro = 'A' and c.certificado = '0000000010'
SELECT c.ano_eje,c.sec_ejec,c.certificado,cs.secuencia,cs.correlativo,cs.cod_doc,cs.num_doc,cs.fecha_doc,MONTH(cs.fecha_doc) as mes,DAY(cs.fecha_doc) as dia,cf.fuente_financ,cf.ruc,cm.sec_func,cc.id_clasificador,cc.monto_nacional,cf.es_compromiso,cs.ind_certificacion  from certificado as c INNER JOIN certificado_secuencia as cs ON c.ano_eje = cs.ano_eje AND c.sec_ejec = cs.sec_ejec AND c.certificado = cs.certificado INNER JOIN certificado_fase as cf ON cs.ano_eje = cf.ano_eje AND cs.sec_ejec = cf.sec_ejec AND cs.certificado = cf.certificado AND cs.secuencia = cf.secuencia INNER JOIN certificado_meta as cm ON cs.ano_eje = cm.ano_eje AND cs.sec_ejec = cm.sec_ejec AND cs.certificado = cm.certificado AND cs.secuencia = cm.secuencia AND cs.correlativo = cm.correlativo INNER JOIN certificado_clasif as cc ON cm.ano_eje = cc.ano_eje AND cm.sec_ejec = cc.sec_ejec AND cm.certificado = cc.certificado AND cm.secuencia = cc.secuencia AND cm.correlativo = cc.correlativo AND cm.id_clasificador = cc.id_clasificador WHERE c.ano_eje = '2021' and c.sec_ejec = '000996' and c.estado_envio = 'T' and c.estado_registro = 'A' and cs.estado_envio = 'T' and cs.estado_registro = 'A' and cf.estado_envio = 'T' and cf.estado_registro = 'A' and cm.estado_envio = 'T' and cm.estado_registro = 'A' and cc.estado_envio = 'T' and cc.estado_registro = 'A' and c.certificado = '0000000010'
SELECT c.ano_eje,c.sec_ejec,c.certificado,cs.secuencia,cs.correlativo,cs.cod_doc,cs.num_doc,cs.fecha_doc,MONTH(cs.fecha_doc) as mes,DAY(cs.fecha_doc) as dia,cf.fuente_financ,cf.ruc,cm.sec_func,cc.id_clasificador,cc.monto_nacional,cf.es_compromiso,cs.ind_certificacion  from certificado as c INNER JOIN certificado_secuencia as cs ON c.ano_eje = cs.ano_eje AND c.sec_ejec = cs.sec_ejec AND c.certificado = cs.certificado INNER JOIN certificado_fase as cf ON cs.ano_eje = cf.ano_eje AND cs.sec_ejec = cf.sec_ejec AND cs.certificado = cf.certificado AND cs.secuencia = cf.secuencia INNER JOIN certificado_meta as cm ON cs.ano_eje = cm.ano_eje AND cs.sec_ejec = cm.sec_ejec AND cs.certificado = cm.certificado AND cs.secuencia = cm.secuencia AND cs.correlativo = cm.correlativo INNER JOIN certificado_clasif as cc ON cm.ano_eje = cc.ano_eje AND cm.sec_ejec = cc.sec_ejec AND cm.certificado = cc.certificado AND cm.secuencia = cc.secuencia AND cm.correlativo = cc.correlativo AND cm.id_clasificador = cc.id_clasificador WHERE c.ano_eje = '2021' and c.sec_ejec = '000996' and c.estado_envio = 'T' and c.estado_registro = 'A' and cs.estado_envio = 'T' and cs.estado_registro = 'A' and cf.estado_envio = 'T' and cf.estado_registro = 'A' and cm.estado_envio = 'T' and cm.estado_registro = 'A' and cc.estado_envio = 'T' and cc.estado_registro = 'A'
SELECT c.ano_eje,c.sec_ejec,c.certificado,cs.secuencia,cs.correlativo,cs.cod_doc,cs.num_doc,cs.fecha_doc,MONTH(cs.fecha_doc) as mes,DAY(cs.fecha_doc) as dia,cf.fuente_financ,cf.ruc,cm.sec_func,cc.id_clasificador,cc.monto_nacional,cf.es_compromiso,cs.ind_certificacion  from certificado as c INNER JOIN certificado_secuencia as cs ON c.ano_eje = cs.ano_eje AND c.sec_ejec = cs.sec_ejec AND c.certificado = cs.certificado INNER JOIN certificado_fase as cf ON cs.ano_eje = cf.ano_eje AND cs.sec_ejec = cf.sec_ejec AND cs.certificado = cf.certificado AND cs.secuencia = cf.secuencia INNER JOIN certificado_meta as cm ON cs.ano_eje = cm.ano_eje AND cs.sec_ejec = cm.sec_ejec AND cs.certificado = cm.certificado AND cs.secuencia = cm.secuencia AND cs.correlativo = cm.correlativo INNER JOIN certificado_clasif as cc ON cm.ano_eje = cc.ano_eje AND cm.sec_ejec = cc.sec_ejec AND cm.certificado = cc.certificado AND cm.secuencia = cc.secuencia AND cm.correlativo = cc.correlativo AND cm.id_clasificador = cc.id_clasificador WHERE c.ano_eje = '2021' and c.sec_ejec = '000996' and c.estado_envio = 'T' and c.estado_registro = 'A' and cs.estado_envio = 'T' and cs.estado_registro = 'A' and cf.estado_envio = 'T' and cf.estado_registro = 'A' and cm.estado_envio = 'T' and cm.estado_registro = 'A' and cc.estado_envio = 'T' and cc.estado_registro = 'A' order by ano_eje, sec_ejec, certificado, secuencia, correlativo asc
SELECT c.ano_eje,c.sec_ejec,c.certificado,cs.secuencia,cs.correlativo,cs.cod_doc,cs.num_doc,cs.fecha_doc,MONTH(cs.fecha_doc) as mes,DAY(cs.fecha_doc) as dia,cf.fuente_financ,cf.ruc,cm.sec_func,cc.id_clasificador,cc.monto_nacional,cf.es_compromiso,cs.ind_certificacion  from certificado as c INNER JOIN certificado_secuencia as cs ON c.ano_eje = cs.ano_eje AND c.sec_ejec = cs.sec_ejec AND c.certificado = cs.certificado INNER JOIN certificado_fase as cf ON cs.ano_eje = cf.ano_eje AND cs.sec_ejec = cf.sec_ejec AND cs.certificado = cf.certificado AND cs.secuencia = cf.secuencia INNER JOIN certificado_meta as cm ON cs.ano_eje = cm.ano_eje AND cs.sec_ejec = cm.sec_ejec AND cs.certificado = cm.certificado AND cs.secuencia = cm.secuencia AND cs.correlativo = cm.correlativo INNER JOIN certificado_clasif as cc ON cm.ano_eje = cc.ano_eje AND cm.sec_ejec = cc.sec_ejec AND cm.certificado = cc.certificado AND cm.secuencia = cc.secuencia AND cm.correlativo = cc.correlativo AND cm.id_clasificador = cc.id_clasificador WHERE c.ano_eje = '2021' and c.sec_ejec = '000996' and c.estado_envio = 'T' and c.estado_registro = 'A' and cs.estado_envio = 'T' and cs.estado_registro = 'A' and cf.estado_envio = 'T' and cf.estado_registro = 'A' and cm.estado_envio = 'T' and cm.estado_registro = 'A' and cc.estado_envio = 'T' and cc.estado_registro = 'A' order by c.ano_eje, c.sec_ejec, cs.certificado, cs.secuencia, cs.correlativo asc

SET DEFAULT TO C:\SIAF_VFP\DATA
SET EXCLUSIVE OFF
USE ?
USE expediente_fase
BROWSE FOR ano_eje = '2021' AND sec_ejec = '000996'
USE expediente_secuencia
BROWSE FOR ano_eje = '2021' AND sec_ejec = '000996'
SELECT estado_registro from expediente_secuencia
SELECT estado_registro from expediente
SELECT expediente from expediente
SELECT * from expediente WHERE ano_eje = '2021'
SELECT * from expediente_secuencia WHERE ano_eje = '2021'
SELECT * from expediente_fase WHERE ano_eje = '2021'
SELECT * from expediente_clasif WHERE ano_eje = '2021'
SELECT e.ano_eje, e.sec_ejec,e.expediente, es.secuencia, es.correlativo,cs.cod_doc,cs.num_doc,es.fecha_doc,MONTH(es.fecha_doc) as mes,DAY(es.fecha_doc) as dia, ,ef.fuente_financ,ef.ruc,em.sec_func,ec.id_clasificador,ec.monto_nacional,ef.ciclo, ef.fase FROM expediente as e inner join  expediente_secuencia as es ON e.ano_eje = es.ano_eje AND e.sec_ejec = es.sec_ejec AND e.expediente = es.expediente inner join expediente_fase as ef ON es.ano_eje = ef.ano_eje AND es.sec_ejec = ef.sec_ejec AND es.expediente = ef.expediente AND es.secuencia = ef.secuencia inner join expediente_meta as em ON es.ano_eje = em.ano_eje AND es.sec_ejec = em.sec_ejec AND es.expediente = em.expediente AND es.secuencia = em.secuencia AND es.correlativo = em.correlativo  inner join expediente_clasif as ec ON em.ano_eje = ec.ano_eje AND em.sec_ejec = ec.sec_ejec AND em.expediente = ec.expediente AND em.secuencia = ec.secuencia AND em.correlativo = ec.correlativo AND em.id_clasificador = ec.id_clasificador WHERE e.ano_eje = '2021' and e.sec_ejec = '000996' and e.estado_envio = 'A' and es.estado_envio = 'A' and ef.estado_envio = 'A' and em.estado_envio = 'A' and ec.estado_envio = 'A' order by e.ano_eje, e.sec_ejec, es.expediente, es.secuencia, es.correlativo asc
SELECT e.ano_eje, e.sec_ejec,e.expediente, es.secuencia, es.correlativo,es.cod_doc,es.num_doc,es.fecha_doc,MONTH(es.fecha_doc) as mes,DAY(es.fecha_doc) as dia,ef.fuente_financ,ef.ruc,em.sec_func,ec.id_clasificador,ec.monto_nacional,ef.ciclo, ef.fase FROM expediente as e inner join  expediente_secuencia as es ON e.ano_eje = es.ano_eje AND e.sec_ejec = es.sec_ejec AND e.expediente = es.expediente inner join expediente_fase as ef ON es.ano_eje = ef.ano_eje AND es.sec_ejec = ef.sec_ejec AND es.expediente = ef.expediente AND es.secuencia = ef.secuencia inner join expediente_meta as em ON es.ano_eje = em.ano_eje AND es.sec_ejec = em.sec_ejec AND es.expediente = em.expediente AND es.secuencia = em.secuencia AND es.correlativo = em.correlativo  inner join expediente_clasif as ec ON em.ano_eje = ec.ano_eje AND em.sec_ejec = ec.sec_ejec AND em.expediente = ec.expediente AND em.secuencia = ec.secuencia AND em.correlativo = ec.correlativo AND em.id_clasificador = ec.id_clasificador WHERE e.ano_eje = '2021' and e.sec_ejec = '000996' and e.estado_envio = 'A' and es.estado_envio = 'A' and ef.estado_envio = 'A' and em.estado_envio = 'A' and ec.estado_envio = 'A' order by e.ano_eje, e.sec_ejec, es.expediente, es.secuencia, es.correlativo asc
SELECT e.ano_eje, e.sec_ejec,e.expediente, es.secuencia, es.correlativo,es.cod_doc,es.num_doc,es.fecha_doc,MONTH(es.fecha_doc) as mes,DAY(es.fecha_doc) as dia,ef.fuente_financ,ef.ruc,em.sec_func,ec.id_clasificador,ec.monto_nacional,ef.ciclo, ef.fase FROM expediente as e inner join  expediente_secuencia as es ON e.ano_eje = es.ano_eje AND e.sec_ejec = es.sec_ejec AND e.expediente = es.expediente inner join expediente_fase as ef ON es.ano_eje = ef.ano_eje AND es.sec_ejec = ef.sec_ejec AND es.expediente = ef.expediente AND es.secuencia = ef.secuencia inner join expediente_meta as em ON es.ano_eje = em.ano_eje AND es.sec_ejec = em.sec_ejec AND es.expediente = em.expediente AND es.secuencia = em.secuencia AND es.correlativo = em.correlativo  inner join expediente_clasif as ec ON em.ano_eje = ec.ano_eje AND em.sec_ejec = ec.sec_ejec AND em.expediente = ec.expediente AND em.secuencia = ec.secuencia AND em.correlativo = ec.correlativo AND em.id_clasificador = ec.id_clasificador WHERE e.ano_eje = '2021' and e.sec_ejec = '000996' and e.estado_envio = 'A' and es.estado_envio = 'A' and ef.estado_envio = 'A' and em.estado_envio = 'A' and ec.estado_envio = 'A' and ef.ciclo = 'G' order by e.ano_eje, e.sec_ejec, es.expediente, es.secuencia, es.correlativo asc
SELECT * from expediente WHERE ano_eje = '2021'

SELECT * from expediente_secuencia WHERE ano_eje = '2021'
SELECT * from expediente_fase WHERE ano_eje = '2021'
SELECT * from expediente_meta WHERE ano_eje = '2021'
SELECT * from expediente_clasif WHERE ano_eje = '2021'
SELECT * from expediente_secuencia WHERE ano_eje = '2021'
SELECT * from expediente_fase WHERE ano_eje = '2021'
SET DEFAULT TO C:\SIAF_VFP\DATA
SET EXCLUSIVE OFF
USE ?
USE expediente_fase
BROWSE FOR ano_eje = '2021'
SELECT * from nota_modificatoria_cab
SELECT * from nota_modificatoria_cab WHERE ano_eje = '2021'
SELECT * from nota_modificatoria_cab WHERE ano_eje = '2021' AND sec_ejec = '000996'
SELECT * from nota_modificatoria_sec WHERE ano_eje = '2021' AND sec_ejec = '000996'
SELECT * from nota_modificatoria_sec WHERE ano_eje = '2021' AND sec_ejec = '000996' ORDER BY sec_nota, secuencia asc
SELECT * from nota_modificatoria_det WHERE ano_eje = '2021' AND sec_ejec = '000996' ORDER BY sec_nota, secuencia asc
SELECT * from nota_modificatoria_det WHERE ano_eje = '2021' AND sec_ejec = '000996' ORDER BY sec_nota asc
SELECT * from nota_modificatoria_sec WHERE ano_eje = '2021' AND sec_ejec = '000996' ORDER BY sec_nota, secuencia asc
SELECT * from nota_modificatoria_sec WHERE ano_eje = '2020' AND sec_ejec = '000996' ORDER BY sec_nota, secuencia asc
SELECT * from nota_modificatoria_sec WHERE ano_eje = '2021' AND sec_ejec = '000996' ORDER BY sec_nota, secuencia asc
SELECT * from nota_modificatoria_det WHERE ano_eje = '2021' AND sec_ejec = '000996' ORDER BY sec_nota asc
SELECT * from nota_modificatoria_doc WHERE ano_eje = '2021' AND sec_ejec = '000996' ORDER BY sec_nota asc
SELECT * from nota_modificatoria_doc
SET DEFAULT TO C:\SIAF_VFP\DATA
SET EXCLUSIVE OFF
USE expediente_fase
BROWSE
select * from certificado_secuencia where ano_eje+sec_ejec+certificado+secuencia+correlativo > '2021000996000000000200010001'
select * from certificado_secuencia where ano_eje+sec_ejec+certificado+secuencia+correlativo > '2021000996000000000201430001'
SET DEFAULT TO C:\SIAF_VFP\DATA
USE expediente_fase
SET DEFAULT TO C:\SIAF_VFP\DATA
SET EXCLUSIVE OFF
SELECT * from expediente_secuencia WHERE ano_eje=2021, sec_ejec=000996, expediente=0000000026, secuencia=0001, correlativo=0001
SELECT * from expediente_secuencia WHERE ano_eje=2021 and sec_ejec=000996 and expediente=0000000026 and secuencia=0001 and correlativo=0001
SELECT * from expediente_secuencia WHERE ano_eje='2021' and sec_ejec='000996' and expediente='0000000026' and secuencia='0001' and correlativo='0001'
SELECT * from expediente_secuencia WHERE ano_eje=2021 and sec_ejec=000996 and expediente=0000000026 and secuencia=0001 and correlativo=0001
SET DEFAULT TO C:\SIAF_VFP\DATA
SET EXCLUSIVE OFF
SELECT * from expediente_secuencia WHERE ano_eje=2021 and sec_ejec=000996 and expediente=0000000026 and secuencia=0001 and correlativo=0001
SELECT * from expediente_secuencia WHERE ano_eje='2021' and sec_ejec='000996' and expediente='0000000026' and secuencia='0001' and correlativo='0001'
SELECT * from nota_modificatoria_cab WHERE ano_eje = '2021' AND sec_ejec = '000996'
SELECT * from nota_modificatoria_det WHERE ano_eje = '2021' AND sec_ejec = '000996'
SELECT * from nota_modificatoria_sec WHERE ano_eje = '2021' AND sec_ejec = '000996'
SELECT * from nota_modificatoria_cab WHERE ano_eje = '2021' AND sec_ejec = '000996'
SELECT * from nota_modificatoria_det WHERE ano_eje = '2021' AND sec_ejec = '000996'
SELECT * from nota_modificatoria_sec WHERE ano_eje = '2021' AND sec_ejec = '000996'
SELECT * from nota_modificatoria_det WHERE ano_eje = '2021' AND sec_ejec = '000996'
SELECT * from nota_modificatoria_cab WHERE ano_eje = '2021' AND sec_ejec = '000996'
SELECT * from nota_modificatoria_sec WHERE ano_eje = '2021' AND sec_ejec = '000996'
SELECT nc.ano_eje,nc.sec_ejec,nc.sec_func,nc.sec_nota,nd.fuente_financ,nd.id_clasificador,ns.notas,ns.fecha,MONTH(ns.fecha) as mes,DAY(ns.fecha)as dia,nd.monto_a as credito,nd.monto_de as anulacion from nota_modificatoria_cab as nc INNER JOIN nota_modificatoria_sec as ns ON nc.ano_eje = ns.ano_eje AND nc.sec_ejec = ns.sec_ejec AND nc.sec_nota = ns.sec_nota INNER JOIN nota_modificatoria_det as nd ON nc.ano_eje = nd.ano_eje AND nc.sec_ejec = nd.sec_ejec AND nc.sec_nota = nd.sec_nota where ns.estado = 'A' and ns.estado_envio = 'A' AND nc.ano_eje = '2021' AND nc.sec_ejec = '000996' ORDER BY nc.sec_nota asc
SELECT nc.ano_eje,nc.sec_ejec,nc.sec_nota,nd.sec_func,nd.fuente_financ,nd.id_clasificador,ns.notas,ns.fecha,MONTH(ns.fecha) as mes,DAY(ns.fecha)as dia,nd.monto_a as credito,nd.monto_de as anulacion from nota_modificatoria_cab as nc INNER JOIN nota_modificatoria_sec as ns ON nc.ano_eje = ns.ano_eje AND nc.sec_ejec = ns.sec_ejec AND nc.sec_nota = ns.sec_nota INNER JOIN nota_modificatoria_det as nd ON nc.ano_eje = nd.ano_eje AND nc.sec_ejec = nd.sec_ejec AND nc.sec_nota = nd.sec_nota where ns.estado = 'A' and ns.estado_envio = 'A' AND nc.ano_eje = '2021' AND nc.sec_ejec = '000996' ORDER BY nc.sec_nota asc

