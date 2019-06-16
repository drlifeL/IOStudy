package IO.decoratestream;

import IO.util.FIleUtils;

import java.io.*;

/**
 * 字节缓冲流，用于提升性能
 * BufferedInputStream & BufferedOutputStream
 */
public class BufferedInputStreamAndBufferedOutputStream {
    public static void main(String[] args) {
        String src = "C:\\Users\\Administrator\\Desktop\\常用快捷键IDEA.txt";
        String dest = "C:\\Users\\Administrator\\Desktop\\副本1.txt";
        copy(src, dest);
    }

    /**
     * 复制文件，使用字节缓冲流
     * <p>
     * 原理就等同于我们ByteArrayInputStream ...
     * 他在我们内存中建立了一个缓冲空间，默认是满8k在调用操作系统给我们进行IO读写
     * 这样就大大的加快我们的操作脚步！效率提升会比较高！
     * 复制一个1G的电影，原来的方法：6963
     * 加上缓冲流2263
     * 效率快接近三倍
     *
     * @param src  源
     * @param dest 目的地
     */
    public static void copy(String src, String dest) {
        //为了方便我们可以在BufferedInputStream里面传递一个FileInputStream的匿名对象，
        //他的close方法会帮我们关闭流！
        //也可以使用我们JDK7的新特性,这里使用我们的新特性来进行演示
        try (InputStream in = new BufferedInputStream(new FileInputStream(src));
             OutputStream out = new BufferedOutputStream(new FileOutputStream(dest))) {
//            使用缓冲流，传递我们需要加速的流
            int len;
            byte[] bytes = new byte[1024];
            while ((len = in.read(bytes)) != -1) {
                out.write(bytes, 0, len);
            }
            out.flush();//注意，因为这里的BufferedOutputStream没有刷新！，数据没有达到8k，就会造成这样。
            //如果单独使用BufferedOutOutputStream 如果是调用了他本身的close方法是不会出现没有刷新的情况的，上次是因为我们调用其FileInputStream 流的方法才导致了无法刷新！
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
