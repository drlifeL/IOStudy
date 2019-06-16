package IO.decoratestream;

import java.io.*;

/**
 * 字符缓冲流 和字节缓冲流一致
 * BufferedReader
 * BufferedWriter
 */
public class BufferedReaderAndBufferedWriter {
    public static void main(String[] args) {
        copy("C:\\Users\\Administrator\\Desktop\\常用快捷键IDEA.txt", "C:\\Users\\Administrator\\Desktop\\副本1.txt");
    }

    /**
     * 使用字节缓冲流
     * readLine（）读到换行符停止，但是不将换行符写到内容中
     * 如果读到末尾，没有内容了则返回null
     * writerLine() 写入一行，自动帮我们加换行符
     *
     * @param src
     * @param dest
     */
    public static void copy(String src, String dest) {
        try (BufferedReader bf = new BufferedReader(new FileReader(src));
             BufferedWriter bw = new BufferedWriter(new FileWriter(dest))) {
            String s;
            while ((s = bf.readLine()) != null) {
                bw.write(s);
                bw.newLine();//写一个换行符到文件中去
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
