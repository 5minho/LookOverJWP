package spms.context;

import org.reflections.Reflections;
import spms.annotation.Component;
import sun.jvm.hotspot.debugger.win32.coff.COMDATSelectionTypes;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Set;

/**
 * IDE : IntelliJ IDEA
 * Created by minho on 2018. 8. 3..
 */
public class ApplicationContext {
    Hashtable<String, Object> objTable = new Hashtable<>();

    public Object getBean(String key) {
        return objTable.get(key);
    }

    public ApplicationContext(String propertiesPath) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileReader(propertiesPath));

        prepareObjects(properties);
        prepareAnnotationObjects();
        injectionDependency();
    }

    private void prepareAnnotationObjects() throws Exception {
        Reflections reflections = new Reflections("");
        Set<Class<?>> list = reflections.getTypesAnnotatedWith(Component.class);
        String key;
        for (Class<?> clazz : list) {
            key = clazz.getAnnotation(Component.class).value();
            objTable.put(key, clazz.newInstance());
        }
    }

    private void prepareObjects(Properties properties) throws Exception {
        Context context = new InitialContext();
        String key = null;
        String value = null;

        for (Object item : properties.keySet()) {
            key = (String)item;
            value = properties.getProperty(key);
            if (key.startsWith("jndi.")) {
                objTable.put(key, context.lookup(value));
                continue;
            }
            objTable.put(key, Class.forName(value).newInstance());
        }
    }

    private void injectionDependency() throws Exception {
        for (String key : objTable.keySet()) {
            if (!key.startsWith("jndi.")) {
                callSetter(objTable.get(key));
            }
        }
    }

    private void callSetter(Object object) throws Exception {
        Object dependency = null;
        for (Method method : object.getClass().getMethods()) {
            if (method.getName().startsWith("set")) {
                dependency = findObjectByType(method.getParameterTypes()[0]);
                if (dependency != null) {
                    method.invoke(object, dependency);
                }
            }
        }
    }

    private Object findObjectByType(Class<?> parameterType) {
        for (Object object : objTable.values()) {
            if (parameterType.isInstance(object)) {
                return object;
            }
        }
        return null;
    }


}
