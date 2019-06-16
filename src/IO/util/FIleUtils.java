package IO.util;

import java.io.*;

/**
 * 建立一个自己的文件工具类
 * 以流为接口
 */
public class FIleUtils {
    public static void main(String[] args) throws FileNotFoundException {
        InputStream in = new FileInputStream("C:\\Users\\Administrator\\Desktop\\常用快捷键IDEA.txt");
        OutputStream out = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\副本.txt");
//        copyFile(in, out);//给我传递一个文件的输入和输出流。我帮你复制文件
//        byte[] bytes = fileToBytes(in);//文件转byte数组
//        bytesToFile(bytes, out);//byte数组转文件
        //演示jdk 7新特征 try with resource 当我们试用完流以后自动帮我们关闭流资源
        copy("C:\\Users\\Administrator\\Desktop\\常用快捷键IDEA.txt",
                "C:\\Users\\Administrator\\Desktop\\副本1.txt");
    }
    //文件到文件
    /**
     * 给我一个输入和输出流，帮你复制文件
     *
     * @param in
     * @param out
     */
    public static void copyFile(InputStream in, OutputStream out) {
        if (in == null || out == null) return;
        int len;
        byte[] bytes = new byte[1024];
        try {
            while ((len = in.read(bytes)) != -1) {
                out.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            close(in, out);
            closeable(in, out);
        }
    }

    /**
     * 给我一个文件我帮你转换成字节数组返回回来
     * 文件到字节数组
     * @param in 文件
     * @return 一个字节数组
     */
    public static byte[] fileToBytes(InputStream in) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int len;
        byte[] bytes = new byte[1024];
        try {
            while ((len = in.read(bytes)) != -1) {
                out.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            close(in, null);
            closeable(in);//利用其父类接口+可变参数，实现一个方法，关闭任意类型流
        }
        return out.toByteArray();
    }

    /**
     * 给我一个字节数组和文件，我帮你把字节数组里面的内容写到文件中去
     * 字节数组到文件
     * @param bytes 字节数组
     * @param out   要被写入的流文件
     */
    public static void bytesToFile(byte[] bytes, OutputStream out) {
        InputStream in = new ByteArrayInputStream(bytes);
        int len;
        byte[] buf = new byte[1024];
        try {
            while ((len = in.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            close(null, out);//因为我们不知道要关闭流的具体类型，有时候只能传递null
            closeable(out);//利用其父类接口+可变参数，实现一个方法，关闭任意类型流
        }
    }

    /**
     * 将关闭的资源的代码进行封装，方便我们阅读
     * 先关闭输入流，在关闭输出流
     * 我们这个关闭的代码还是不够输出，有时候，我们可能只需要关闭一个流，
     * 或者说我们需要关闭多个流，并且我们流的属性可能还不同
     * 这个时候我们该怎么办呐，观察我们的InputStream 和OutputStream 发现他们都实现Closeable这个接口
     * 根据面向对象的对象，我们可以使用其父类接口来new 子类对象，我们就可以使用Closeable这个借口来作为我们的参数
     * 但这只是解决了我们关闭流的类型问题，如果有多个流，怎么办那？这时我们可以使用可变参数...来实现这个目标
     * 请看代码
     * 方法概括1
     * @param is 要关闭输入流
     * @param os 要关闭的输出流
     * @see #closeable(Closeable...)
     */
    public static void close(InputStream is, OutputStream os) {
        try {
            if (os != null)
                os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (is != null)
                is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param clas 需要关闭的流对象
     *             <p>
     *             关闭流的方法
     *             无论任何流，
     *             实现两个流的父类接口
     *             就可以关闭我们的任意一个流对象
     *
     *             方法概括2
     */
    public static void closeable(Closeable... clas) {
        for (Closeable c : clas) {
            try {
                c.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 新特性：
     * 在jdk7,java自己给我们提供了一种关闭输入和输出流的方法
     * 其语法结构是：
     * try.. with ..resourse
     * try(写上我们的流对象[多个使用分号隔开]){
     * 操作语句
     * }catch(异常声明){
     * 异常处理
     * }
     * 当我们的流处理完毕后,java会自己去通知操作系统来关闭我们的资源
     * 就不用我们自己手动关闭了。
     */
    public static void copy(String src, String dest) {
        try (InputStream in = new FileInputStream(src); OutputStream out = new FileOutputStream(dest)) {
            int len;
            byte[] bytes = new byte[1024];
            while ((len = in.read(bytes)) != -1) {
                out.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
