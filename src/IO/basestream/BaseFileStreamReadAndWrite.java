package IO.basestream;

import java.io.*;
import java.util.Arrays;

/**
 * 读取文件的基本操作
 * 字节流
 * 使用流对象
 * 理解操作步骤：
 * 1.创建源
 * 2.选择流
 * 3.操作
 * 4.释放资源
 * read()如果是读取一个字节时候是返回字符的数据
 * read(byte[] b) 如果读取一个字节数组的话，返回的是字节数组的长度
 **/
public class BaseFileStreamReadAndWrite {
    public static void main(String[] args) {
//        File file = new File("C:\\Users\\Administrator\\Desktop\\Java开发实习生面试问题.txt");
        //字节输入流 FileInputStream
//        ReadyFileTest(file);//入门测试
//        readFileToConsole(file);//从硬盘中读取文件并输出到控制台，使用read
//        readFileToConsole2(file);//从硬盘中读取文件并输出到控制台，使用read(byte[]bytes)

        //字节输出流 FileOutputStream
//        File src = new File("C:\\Users\\Administrator\\Desktop\\源文件.txt");
//        writeStreamToFile(src);//将流中的数据写入到指定文件之中。

        //结合文件输入输入流，Copy文件
//        File src = new File("C:\\Users\\Administrator\\Desktop\\Java开发实习生面试问题.txt");
//        File dest = new File("C:\\Users\\Administrator\\Desktop\\副本.txt");
//        copyFile(src, dest);//复制文件

        File src = new File("C:\\Users\\Administrator\\Desktop\\C\\A");
        System.out.println(src.getPath());
        System.out.println(src.getAbsolutePath());
        File dest = new File("C:\\Users\\Administrator\\Desktop\\C");
        System.out.println(dest.getName());

        //注意这个当拷贝的目录和粘贴的目录一致的时候，会造成数据覆盖混乱，写的并不是很完美
        copyDir(src, dest);//递归的复制文件夹


    }

    /**
     * 初步学习流
     *
     * @param file
     */
    public static void ReadyFileTest(File file) {
        try {
            //选择流
            InputStream in = new FileInputStream(file);
            //读取
            int data1 = in.read();
            int data2 = in.read();
            int data3 = in.read();
            System.out.println(data1 + "::" + data2 + "::" + data3);
            //如果读取到末端返回-1
            //关闭资源
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 以字节流的方式读取txt文件
     * 并将内容打印输出到控制台。
     * 使用 read()
     *
     * @param src
     */
    public static void readFileToConsole(File src) {
        InputStream in = null;
        try {
            in = new FileInputStream(src);
            int data;
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((data = in.read()) != -1) {
                bytes[len++] = (byte) data;
                if (len == 1024) {
                    System.out.println(new String(bytes, "GBK"));
                    len = 0;
                    Arrays.fill(bytes, (byte) 0);
                }
            }
            System.out.println(new String(bytes, 0, len, "GBK"));//为了防止读取的最后一段内容的时候其空间没有那么大
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 采取一次读取一个字符数组的方法
     * read(byte[] bytes)
     * 这个方法会帮我们处理最后一个字节的不输出的问题
     *
     * @param src
     */
    public static void readFileToConsole2(File src) {
        InputStream in = null;
        try {
            in = new FileInputStream(src);
            int len;
            byte[] bytes = new byte[1024];
            while ((len = in.read(bytes)) != -1) {
                //此处必须如此写，不然会出现读到上次读出来没有处理好的数据，参照自己的上一个程序，不然就需要每次都重新填充数组，费时费力。
                System.out.println(new String(bytes, 0, len, "GBK"));
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (null != in)
                    in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     */
    public static void writeStreamToFile(File src) {
        FileOutputStream fis = null;
        String msg = "你好啊,IO输出流\r\n";
        byte[] bytes = msg.getBytes();
        int len;
        try {
            //FileOutputStream(File file, boolean append) 其构造方法重载了一个append参数，
            //默认为false,表示写入的时候先清空文件中的内容在写，如果为true，表示将流中的数据追加到源文件中
            fis = new FileOutputStream(src, true);
            fis.write(bytes);//将文件写入到流中
            fis.flush();//刷新该流,主要是防止最后一次写入的时候有数据没有写入到流中，当然，也可以不写
            //应该关闭流的时候，会在刷新一次流
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 结合输入输出流，练习复制文件
     * 步骤：
     * 1.将文件转换成流的形式
     * 2.就源文件中的数据读取到字节数组中
     * 3.将字节数组中的数据写入到输出流中
     * 4.关闭流
     *
     * @param src
     * @param dest
     */
    public static void copyFile(File src, File dest) {
        InputStream in = null;
        OutputStream out = null;
        try {
            /*
            步骤：
                1.将文件转换成流的形式
                2.就源文件中的数据读取到字节数组中
                3.将字节数组中的数据写入到输出流中
                4.关闭流  原则：先打开的后关闭，且一定要分别关闭，这样才不会造成影响！
             */
            in = new FileInputStream(src);
            out = new FileOutputStream(dest);
            int len;
            byte[] bytes = new byte[1024];
            while ((len = in.read(bytes)) != -1) {//读取文件数据到数组中
                out.write(bytes, 0, len);//将数组中的数据写入到文件中
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();//先关输出流
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                in.close();//在关输入流
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 递归的复制一个文件夹
     *
     * @param src
     * @param dest
     */
    public static void copyDir(File src, File dest) {
        if (src == null || !src.exists()) return;
        dest = new File(dest.getAbsolutePath() + "\\" + src.getName());
        if (src.isDirectory()) {
            if (!dest.exists()) {
                dest.mkdirs();
            }
            for (File f : src.listFiles()) {
                copyDir(f, dest);
            }
        } else {
            copyFile(src, dest);
        }

    }


}
