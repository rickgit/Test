package edu.ptu.javatest;

import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class IoPerfomanceTest {
    @Test
    public void test001ObjectIO() {
        ArrayList<String> strings = initObject();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./object"));
            oos.writeObject(strings);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Test

    public void test002ReadObj() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./object"));
            Object o = ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test003BufferIo() {
        ArrayList<String> strings = initObject();
        try {
            PrintWriter oos = new PrintWriter(new OutputStreamWriter(new FileOutputStream("./object")));
            for (int i = 0; i < strings.size(); i++) {
                oos.println(strings.get(i));
            }
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void test004ReadBuffered() {
        try {
            ArrayList<String> newStr = new ArrayList<>();
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./object")));
            String str = null;
            while ((str = br.readLine()) != null) {
                newStr.add(str);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> initObject() {
        ArrayList<String> objects = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            objects.add("string: " + i);
        }
        return objects;
    }
}
