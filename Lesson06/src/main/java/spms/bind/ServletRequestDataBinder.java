package spms.bind;

import javax.servlet.ServletRequest;
import java.lang.reflect.Method;
import java.sql.Date;
import java.util.Set;

/**
 * IDE : IntelliJ IDEA
 * Created by minho on 2018. 8. 2..
 */
public class ServletRequestDataBinder {
    public static Object bind(ServletRequest request, Class<?> dataType, String dataName) throws Exception {
        if (isPrimitiveType(dataType)) {
            return createValueObject(dataType, request.getParameter(dataName));
        }

        Set<String> paramNames = request.getParameterMap().keySet();
        Object dataObj = dataType.newInstance();
        Method setter;

        for (String paramName : paramNames) {
            setter = findSetter(dataType, paramName);
            if (setter != null) {
                setter.invoke(dataObj, createValueObject(setter.getParameterTypes()[0], request.getParameter(paramName)));
            }
        }
        return dataObj;
    }

    private static Method findSetter(Class<?> type, String name) {
        Method[] methods = type.getMethods();
        String propName;
        for (Method m : methods) {
            if (!m.getName().startsWith("set")) continue;
            propName = m.getName().substring(3);
            if (propName.toLowerCase().equals(name.toLowerCase())) {
                return m;
            }
        }
        return null;
    }

    private static Object createValueObject(Class<?> dataType, String value) {
        if(dataType.getName().equals("int") || dataType == Integer.class) {
            return new Integer(value);
        } else if (dataType.getName().equals("long") || dataType == Long.class) {
            return new Long(value);
        } else if (dataType.getName().equals("float") || dataType == Float.class) {
            return new Float(value);
        } else if (dataType.getName().equals("double") || dataType == Double.class) {
            return new Double(value);
        } else if (dataType.getName().equals("boolean") || dataType == Boolean.class) {
            return new Boolean(value);
        } else if (dataType == Date.class) {
            return Date.valueOf(value);
        } else {
            return value;
        }
    }

    private static boolean isPrimitiveType(Class<?> dataType) {
        if(dataType.getName().equals("int") || dataType == Integer.class ||
        dataType.getName().equals("long") || dataType == Long.class ||
        dataType.getName().equals("float") || dataType == Float.class ||
        dataType.getName().equals("double") || dataType == Double.class ||
        dataType.getName().equals("boolean") || dataType == Boolean.class ||
        dataType == Date.class || dataType == String.class) {
            return true;
        }
        return false;
    }
}
