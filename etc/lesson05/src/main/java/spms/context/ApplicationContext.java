package spms.context;

import org.reflections.Reflections;
import spms.annotation.Component;
import spms.vo.Member;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Set;

/**
 * IDE : IntelliJ IDEA
 * Created by minho on 2018. 9. 2..
 */
public class ApplicationContext {
    Hashtable<String, Object> objTable = new Hashtable<>();

    public Object getBean(String key) {
        return objTable.get(key);
    }

    public ApplicationContext(String propertiesPath) throws Exception {
        Properties props = new Properties();
        props.load(new FileReader(propertiesPath));

        prepareObject(props);
        prepareAnnotationObjects();
        injectDependency();
    }

    private void prepareAnnotationObjects() throws Exception {
        Reflections reflector = new Reflections("");
        Set<Class<?>> list = reflector.getTypesAnnotatedWith(Component.class);
        String key;
        for (Class<?> clazz : list) {
            key = clazz.getAnnotation(Component.class).value();
            objTable.put(key, clazz.newInstance());
        }
    }

    private void injectDependency() throws Exception {
        for (String key : objTable.keySet()) {
            if (!key.startsWith("jndi.")) {
                callSetter(objTable.get(key));
            }
        }
    }

    private void callSetter(Object object) throws Exception {
        Object dependency;
        for (Method m : object.getClass().getMethods()) {
            if (m.getName().startsWith("set")) {
                dependency = findObjectByType(m.getParameterTypes()[0]);
                if (dependency != null) {
                    System.out.println(object.getClass() + "에 " + dependency.getClass() + "주입");
                    m.invoke(object, dependency);
                }
            }
        }
    }

    private Object findObjectByType(Class<?> parameterType) {
        for (Object obj : objTable.values()) {
            if (parameterType.isInstance(obj)) {
                return obj;
            }
        }
        return null;
    }

    private void prepareObject(Properties props) throws Exception {
        Context ctx = new InitialContext();
        String key, value;
        for (Object item : props.keySet()) {
            key = (String)item;
            value = props.getProperty(key);
            if (key.startsWith("jndi.")) {
                objTable.put(key, ctx.lookup(value));
                continue;
            }
            objTable.put(key, Class.forName(value).newInstance());
        }
    }


}
