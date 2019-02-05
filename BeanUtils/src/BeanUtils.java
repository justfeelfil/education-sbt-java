import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
     * Scans object "from" for all getters.
     * If object "to" contains correspondent setter, it will invoke it to set property value for "to" which equals to the property of "from".
     * <p/>
     * The type in setter should be compatible to the value returned by getter (if not, no invocation performed).
     * Compatible means that parameter type in setter should be the same or be superclass of the return type of the getter.
     * <p/>
     * The method takes care only about public methods.
     *
     * @param to    Object which properties will be set
     * @param from  Object which properties will be used to get values.
     */

    public class BeanUtils {
        public static void assign(Object to, Object from) throws InvocationTargetException, IllegalAccessException {
            List<Method> settersMethods = settersMethods(to.getClass());
            List<Method> gettersMethods = gettersMethods(from.getClass());
            Map<Method, Method> SetGetMap = combineMethods(settersMethods, gettersMethods);
            for (Map.Entry<Method, Method> setGet : SetGetMap.entrySet()){
                setGet.getKey().invoke(to, setGet.getValue().invoke(from));
            }
    }

    private static List<Method> settersMethods(Class<?> aClass) {
            List<Method> methods = new ArrayList<Method>();
            for (Method method : aClass.getMethods()){
                if (isSetter(method)) {
                    methods.add(method);
                }
            }
            return methods;
    }

    private static List<Method> gettersMethods(Class<?> aClass) {
            List<Method> methods = new ArrayList<Method>();
            for (Method method : aClass.getMethods()){
                if (isGetter(method)){
                    methods.add(method);
                }
            }
            return methods;
    }

    private static boolean isSetter(Method method) {
            if(!method.getName().startsWith("set"))       return false;
            if(method.getParameterTypes().length != 0)    return false;
            if(void.class.equals(method.getReturnType())) return false;
            return true;
    }

    private static boolean isGetter(Method method) {
        if(!method.getName().startsWith("get"))       return false;
        if(method.getParameterTypes().length != 0)    return false;
        if(void.class.equals(method.getReturnType())) return false;
        return true;
    }

    private static Map<Method, Method> combineMethods(List<Method> toClassMethods, List<Method> fromClassMethods) {
        Map<Method, Method> setGetMap = new HashMap<>();
        for (Method setMethod : toClassMethods) {
            for (Method getMethod : fromClassMethods)
                if (combineArgName(setMethod, getMethod) ) {
                    setGetMap.put(setMethod,getMethod);
                }
        }
        return setGetMap;
    }

    private static boolean combineArgName(Method setMethod, Method getMethod) {
            return setMethod.getName().substring(1).equals(getMethod.getName().substring(1))&&
                Arrays.equals(setMethod.getParameterTypes(), new Class<?>[] {getMethod.getReturnType()});

    }
}