package edu.ptu.javatest._80_storage._80_file._03_json;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import org.junit.Test;

import java.util.Map;

public class GsonTest {
    @Test
    public void test(){
        LinkedTreeMap o = (LinkedTreeMap) new Gson().fromJson("{}", Object.class);//LinkedTreeMap
        System.out.println(o);
    }

    @Test
    public void testPerson(){
        Person o = (Person) new Gson().fromJson("{\"name\":\"Rick\"}", Person.class);//LinkedTreeMap
        System.out.println(o);
    }
    public static class Person{
        public String name;
    }
}
