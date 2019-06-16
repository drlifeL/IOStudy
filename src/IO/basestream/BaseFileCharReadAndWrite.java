package IO.basestream;

import java.io.*;

/**
 * Reader
 * 文件字符流
 * 其读出来的不是一个个的字节
 * 读出来的是每一个字符
 * 并且Writer可以直接把字符串读入到文件中，不需要我们将其转换成字符数组，其追加属性还是在构造方法中传递boolean值
 */
public class BaseFileCharReadAndWrite {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\Administrator\\Desktop\\常用快捷键IDEA.txt");
//        readCharToConsole(file);//从文件中读取数据到控制台

        File dest = new File("C:\\Users\\Administrator\\Desktop\\副本.txt");

        writeCharToDest(dest);//将流中的文件输出到磁盘

//        copyFileInReader(file, dest);

    }

    /**
     * 读取字符到控制台
     * 文件字符流（专门用来读取字符串  只能读）
     * 并且他还会自动识别我们的编码形式
     * 其每次读出来是一个char字符
     */
    public static void readCharToConsole(File src) {
        Reader reader = null;
        try {
            reader = new FileReader(src);
            char[] chars = new char[1024];
            int len;
            while ((len = reader.read(chars)) != -1) {
                System.out.println(new String(chars, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将流中的数据读取到文件中去
     *
     * @param dest 输出文件的位置
     */
    public static void writeCharToDest(File dest) {
        Writer writer = null;
        String src = "你好啊大兄弟，这是使用字符流读取出来的数据！\r\n";
        try {
            writer = new FileWriter(dest);
            //写法一。可以将其转换成一个字符数组
            char[] chars = src.toCharArray();
            writer.write(chars);
            //写法二。也可以直接传递字符串
            writer.write(src);
            //写法三。如果我们有多个需要添加的对象，每次都去调用write比较麻烦，这时我们就可以使用append
            //append每次向文件写数据的时候又会返回一个write对象，我们可以利用他进行链式编程
            writer.append(src).append(src).append(src);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 演示字节流的文件拷贝
     *
     * @param src
     * @param dest
     */
    public static void copyFileInReader(File src, File dest) {
        Writer writer = null;
        Reader reader = null;
        try {
            reader = new FileReader(src);
            writer = new FileWriter(dest);
            int len;
            char[] chars = new char[1024];
            while ((len = reader.read(chars)) != -1) {
                writer.write(chars, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //分开关闭，先开后关
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
