package edu.ptu.javatest._80_storage._80_file._04_probuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

import org.junit.Assert;
import org.junit.Test;

//https://developers.google.com/protocol-buffers/docs/encoding
//（tag length value）编码， （tag, length, value)的组合
//[配合gRpc使用](https://grpc.io/docs/languages/java/quickstart/)

/**
 * com.google.protobuf.WireFormat#WIRETYPE_VARINT
 * com.google.protobuf.WireFormat#makeTag(int, int)  tag的byte值
 *
 * Type	Meaning	            Used For
 * 0	Varint	            int32, int64, uint32, uint64, sint32, sint64, bool, enum         (fieldindex << 3) | 0
 * 1	64-bit	            fixed64, sfixed64, double                                        (fieldindex << 3) | 1
 * 2	Length-delimited	string, bytes, embedded messages, packed repeated fields         (fieldindex << 3) | 2
 * 3	Start group	        groups(deprecated)
 * 4	End group	        groups(deprecated)
 * 5	32-bit	            fixed32, sfixed32, float                                         (fieldindex << 3) | 5
 */


public class ProtocTest {
    @Test
    public void test() {
//        PersonBean.
//        UserModel.User.parseFrom();
        ProtoModel.User.Builder builder = ProtoModel.User.newBuilder();
        builder.setId(-1);//负数占用10个字节(￣▽￣)"
        builder.setName("rick");
        ProtoModel.User build = builder.build();
        for (byte b : build.toByteArray()) {
            System.out.print(String.format("%8s", Integer.toBinaryString(b)).replace(" ", "0") + " ");
        }
        byte[] bytes = build.toByteArray();
        Assert.assertEquals(bytes[0], toProtoByteTag(1,0));//tag id
        Assert.assertEquals(String.format("%8s", Integer.toBinaryString(bytes[1])).replace(" ", "0")
                , toProtoByteString(build.getId()));//value
        Assert.assertEquals(bytes[2], toProtoByteTag(2,2));//tag
        Assert.assertEquals(bytes[3], "rick".length());//length
        Assert.assertEquals(bytes[4], 'r');//value
        Assert.assertEquals(bytes[5], 'i');//value
        Assert.assertEquals(bytes[6], 'c');//value
        Assert.assertEquals(bytes[7], 'k');//value
        //value
        try {
            ProtoModel.User user = ProtoModel.User.parseFrom(bytes);
//            Assert.assertTrue(user.getId() == 23);
            Assert.assertEquals(user.getName(), "rick");
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println();


//        System.out.println(toProtoByteString(505100));
//        System.out.println(Integer.toBinaryString(0x8cea1e));
    }

    public static byte toProtoByteTag(int fieldindex,int type) {

         return makeTag(fieldindex,type);
    }

    static byte makeTag(final int fieldNumber, final int wireType) {
        return (byte) ((fieldNumber << 3) | wireType);
    }

    /**
     *  0    1(2)   2(4)    3(6)     4(8)
     *  +      ^+  ^ +     ^+
     *  |      ||  | |     ||        ^
     *  v      ||  | |     ||        |
     * -1(1) +--+  | |     ||        |
     *          |  | |     ||        |
     *          |  | |     ||        |
     *          |  | |     ||        |
     * -2(3) <--+  | |     ||        |
     *        -----+ |     ||        |
     *               |     ||        |
     *               |     ||        |
     * -3(5) <-------+     ||        |
     *      +---------------+        |
     *                      |        |
     *                      |        |
     * -4(7) <--------------+        |
     *       +-----------------------+
     * @return
     */
    public static int getZigZagSint(int sign){
       return Math.abs(sign)*2-(sign<0?1:0);
    }
    public static String toProtoByteString(int javaint) {

        //负数需要用sint类型
        //sint使用zigzag
        javaint=getZigZagSint(javaint);


        String binaryString = Integer.toBinaryString(javaint);
        StringBuffer sb = new StringBuffer();
        int index = binaryString.length() - 7;
        //step 1: to 7bit
        //step 2: reverse
        //step 3: add
        while (true) {
            if (index + 7 > 0) {//数据都是右对齐，高位补0
                sb.append(index < 0 ? "0" : "1");
                sb.append(String.format("%7s", binaryString.substring(index < 0 ? 0 : index, index + 7 > binaryString.length() ? binaryString.length() : (index + 7))).replace(" ", "0"));
            } else if (index == binaryString.length() - 7)
                sb.append("1").append(String.format("%7s", binaryString.substring(index < 0 ? 0 : index)).replace(" ", "0"));
            else break;
            index -= 7;
        }
        //reverse
        return sb.toString();
    }
}
