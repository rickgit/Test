package edu.ptu.javatest._80_storage._80_file.classfile;

import java.lang.reflect.Field;

public class RefectTest {

    public static Object getRefFieldObj(Object object, Class clazz, String name) {
        try {
            Field declaredField = clazz.getDeclaredField(name);
            declaredField.setAccessible(true);
            return declaredField.get(object);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
