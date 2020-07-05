package edu.ptu.androidtest.io;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

/**
 * Created by WangAnshu on 2016/6/14.
 */
public class FileTest {
    @Test
    public void testOutput() {
        Writer writer = null;
        try {
            OutputStream os = new FileOutputStream("a.txt");
            writer = new OutputStreamWriter(os,"UTF-8");
            writer.write("'");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
