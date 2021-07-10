package pe.gob.inbp.siaf.component.utility;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AdoUtility {
	public static String setMethodName(String _method) {
		return _method.substring(0, 1).toUpperCase() + _method.substring(1);
	}
	
	public static <T> void setValueToObject(Object _obj, Class<T> _class, String _methodNameForField,
			String _dataType, String _input) {
		try {
			Method method = _class.getMethod(_methodNameForField, Class.forName(_dataType));
			T t = _class.cast(_obj);
			method.invoke(t, _input);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	public static String setConnectionString(String folder) {
		return "Driver={Microsoft Visual FoxPro Driver};SourceType=DBF;SourceDB=" + folder + ";Exclusive=No;Collate=Machine;NULL=NO;DELETED=NO;BACKGROUNDFETCH=NO;";
	}
}
