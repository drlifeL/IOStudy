package IO.transformStream;

import java.io.*;

/**
 * 数据转换流
 * DataOutputStream  DataInputStream
 * 可以将我们的基本数据类型转换成流对象并读取出来
 * 注意，读的顺序必须和写的顺序保持一致，否则有可能会抛出异常
 */
public class DataStream {
    public static void main(String[] args) throws IOException{
        demo();
    }

    /**
     * 演示数据转换流的使用，、
     * 使用我们的ByteArrayOutputStream 做为我们的节点流
     */
    public static void demo() throws IOException {
        //写入
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);//因为都是基于内存，会自动关，不管
        dos.writeUTF("张三");
        dos.writeInt(12);
        dos.writeBoolean(true);

        byte[] sources = bos.toByteArray();

        //读取,(如果不按照写入的顺序读取，会发生错误)
        ByteArrayInputStream bis = new ByteArrayInputStream(sources);
        DataInputStream dis = new DataInputStream(bis);

        String str = dis.readUTF();
        int age = dis.readInt();
        boolean flag = dis.readBoolean();

        System.out.println(str + "::" + age + "::" + flag);


    }
}
