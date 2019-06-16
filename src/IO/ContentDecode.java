package IO;

/**
 * 文件的解码  字节数组---->字符串
 * 使用我们String的构造方法就可以完成
 */
public class ContentDecode {
    public static void main(String[] args) {
        String msg = "你好呀兄弟";
        byte[] bytes = msg.getBytes();

        String s = new String(bytes);
        System.out.println(s);

    }
}
