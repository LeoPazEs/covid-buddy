import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public abstract class ReflectHelper {
    public static Stack<Method> getMethods(Class<?> type)
    {
        Stack<Method> result = new Stack<Method>();
        for (Class<?> c = type; c != null; c = c.getSuperclass())
        {
            Method[] methods = c.getDeclaredMethods();
            for (Method method : methods)
            {
                result.add(method);
            }
        }    
        return result;
    }

    public static List<Field> getAllFields(Class<?> type) {
        List<Field> fields = new ArrayList<Field>();
        for (Class<?> c = type; c != null; c = c.getSuperclass()) {
            for (Field field : c.getDeclaredFields()){
                if (!java.lang.reflect.Modifier.isStatic(field.getModifiers()))
                    fields.add(field);
            }
        }
        return fields;
    } 

    public static Field getField(Class<?> type, String fieldName) {
        for (Class<?> c = type; c != null; c = c.getSuperclass()) {
            for (Field field : c.getDeclaredFields()){
                if (!java.lang.reflect.Modifier.isStatic(field.getModifiers()) && field.getName().equalsIgnoreCase(fieldName))
                    return field;
            }
        }
        return null;
    }
    
    public static Object runGetter(Field field, Object o)
    {
        for (Method method : getMethods(o.getClass()))
        {
           
            if ((method.getName().startsWith("get")) && (method.getName().length() == (field.getName().length() + 3)))
            {
                
                if (method.getName().toLowerCase().endsWith(field.getName().toLowerCase()))
                {
                    
                    try {
                        return method.invoke(o);
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                        throw new IllegalArgumentException(e.getMessage(), e);
                    }
                }
            }
        }
        return null;
    } 

    public static Object runMethod(String methodName, Object o){ 
        try {
            return o.getClass().getMethod(methodName).invoke(o);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println(e.getMessage());
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    } 


    public static boolean isWrapperType(Class<?> clazz) {
        Set<Class<?>> WRAPPER_TYPES = new HashSet<Class<?>>(Arrays.asList(Boolean.class, Character.class, Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class, Void.class));
        return WRAPPER_TYPES.contains(clazz);
    }
}
