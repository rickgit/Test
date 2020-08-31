package edu.ptu.androidtest._30_storage._40_byte;

import com.twitter.serial.serializer.ObjectSerializer;
import com.twitter.serial.serializer.SerializationContext;
import com.twitter.serial.stream.SerializerInput;
import com.twitter.serial.stream.SerializerOutput;
import com.twitter.serial.stream.bytebuffer.ByteBufferSerial;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class SerialObj {

   public static class SubObject   {
        public static final SubObjectSerializer SERIALIZER = new SubObjectSerializer();

       public SubObject()  {

       }

       private static final class SubObjectSerializer extends ObjectSerializer<SubObject> {

           @Override
           protected void serializeObject(SerializationContext context, SerializerOutput output, SubObject object) throws IOException {

           }

           @Override
           protected SubObject deserializeObject(SerializationContext context, SerializerInput input, int versionNumber) throws IOException, ClassNotFoundException {
               return null;
           }
       }
    }
    public static final ObjectSerializer<SerialObj> SERIALIZER = new ExampleObjectSerializer();

    public final int num;
    public final SubObject obj;

    public SerialObj(int num, @NotNull SubObject obj) {
        this.num = num;
        this.obj = obj;
    }

    private static final class ExampleObjectSerializer extends ObjectSerializer<SerialObj> {
        @Override
        protected void serializeObject(@NotNull SerializationContext context, @NotNull SerializerOutput output,
                                       @NotNull SerialObj object) throws IOException {
            output
                    .writeInt(object.num) // first field
                    .writeObject(context,object.obj, SubObject.SERIALIZER); // second field
        }

        @Override
        @NotNull
        protected SerialObj deserializeObject(@NotNull SerializationContext context, @NotNull SerializerInput input,
                                              int versionNumber) throws IOException, ClassNotFoundException {
            final int num = input.readInt(); // first field
            final SubObject obj = input.readObject(context, SubObject.SERIALIZER); // second field
            return new SerialObj(num, obj);
        }
    }

    @Test
    public void testSerial() throws IOException, ClassNotFoundException {
        ByteBufferSerial bbs = new ByteBufferSerial();
        byte[] bytes = bbs.toByteArray(new SerialObj(2, new SubObject()), SerialObj.SERIALIZER);
        SerialObj serialTest = bbs.fromByteArray(bytes, SerialObj.SERIALIZER);
        Assert.assertEquals(serialTest.num,2);
    }
}
