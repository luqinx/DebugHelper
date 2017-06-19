package chao.app.protocol.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *  反射工具类
 *
 *  @author chao.qin
 *  @since 51job6.0 2016/7/13
 */
public class ReflectUtil {

    public static Method getMethod(Class c,String method,Class... paramsTypes) {
        if (c  == null) {
            return null;
        }
        Method m;
        try {
            m = c.getDeclaredMethod(method,paramsTypes);
            m.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            m = null;
        }
        return m;
    }


    /**
     * 使用反射设置变量值
     *
     * @param o 被调用对象
     * @param fieldName 被调用对象的字段，一般是成员变量或静态变量，不可是常量！
     * @param value 值
     * @param <T> value类型，泛型
     *
     * todo 这个方法无法设置父类中的field，是个bug，后面如果需要再修改
     */
    public static <T> void setValue(Object o,String fieldName,T value) {
        try {
            Class c = o.getClass();
            Field f = c.getDeclaredField(fieldName);
            f.setAccessible(true);
            f.set(o, value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用反射获取变量值
     *
     * @param field 被调用对象的字段，一般是成员变量或静态变量，不可以是常量
     * @param <T> 返回类型，泛型
     * @return 值
     *
     */
    public static <T> T getValue(Object o,Field field) {
        try {
            return (T) field.get(o);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Field getFiled(Class c,String fieldName) {
        Field field = null;
        try {
            field = c.getDeclaredField(fieldName);
            field.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return field;
    }

    public static Object callMethod(Object o,Method m,Object... params) {
        Object result;
        try {
            result = m.invoke(o,params);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            result = null;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            result = null;
        }
        return result;
    }
}
