package IO;

import java.io.UnsupportedEncodingException;

/**
 * 文件的编码和解码
 * 中文在UTF-8中默认是三个字节
 * 英文一个
 */
public class ContentEncode {
    public static void main(String[] args) {
        String msg = "姓名年龄生日";//加一个英文字母是19
        byte[] bytes = msg.getBytes();//其默认是用工程的字符集
        System.out.println(bytes.length);//18
        //可以编码成其他的字符集
        try {
            bytes = msg.getBytes("GBK");
            System.out.println(bytes.length);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
