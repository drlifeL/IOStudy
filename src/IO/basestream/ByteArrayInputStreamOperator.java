package IO.basestream;

import java.io.*;

/**
 * 演示ByteArrayInputStream和outPutStream 的操作
 * <p>
 * 这个流和其他流的不同点是，这个流是读取的是字节数组，并且不需要关闭，因为是虚拟机管理，都在内存中，虽然有关闭的方法但是是一个空方法，没有实现
 * 且其ByteArrayOutputStream也是将数据首先读取到内存中，如果需要拿出来需要调用toByteArray()方法，返回一个字节数组
 * 或者toString()将其转换成字符串 或者使用writerTo（OutputStream out）
 */
public class ByteArrayInputStreamOperator {

    public static void main(String[] args) {
//        String s = "This is Test Method!";
//        readToConsole(s.getBytes());

//        writerByteArrayOutputStream(s.getBytes());
        File read = new File("C:\\Users\\Administrator\\Desktop\\常用快捷键IDEA.txt");
        File out = new File("C:\\Users\\Administrator\\Desktop\\副本.txt");
        copyFile(out, helpCopyFile(read));

    }

    /**
     * 除了其源变成了字节数组以外，其他的没有什么变化，其内置数组是用来放置源的
     * 且不需要关闭流资源，由java虚拟机管理
     *
     * @param bytes
     */
    public static void readToConsole(byte[] bytes) {
        InputStream in = null;
        try {
            in = new ByteArrayInputStream(bytes);
            int len;
            byte[] buf = new byte[1024];
            //将内存中的数据读取到数组中去，不还是在内存中吗，这里可能是为了方便我们的网络需求而定的，就是网络传递如果是传递我们的byte数组会比直接传递文件要好点吧....
            while ((len = in.read(buf)) != -1) {
                System.out.println(new String(buf, 0, len));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //不需要关闭了，所以不用管
        }
    }

    /**
     * 演示ByteArrayOutputStream
     * 其内部有一个缓冲数组我们不需要对其做任何处理
     * 需要注意的是我们的缓冲区大小最好不要指定太大，因为占用的是我们的内存
     * 因为我们要使用其类的特有方法，所有我们就不用多态了（父类引用指向子类对象）
     */
    public static void writerByteArrayOutputStream(byte[] bytes) {
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            out.write(bytes);
            byte[] buf = out.toByteArray();//可以将其
            System.out.println(new String(buf));
            System.out.println(out.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 模拟服务器
     *
     * @param src 需要复制的文件
     */
    public static byte[] helpCopyFile(File src) {
        InputStream in = null;
        ByteArrayOutputStream bos = null;
        try {
            in = new FileInputStream(src);
            //因为这里不知道我们这个文件的具体长度是多少，所以我们需要将其读取到一个ByteArray流中，使用其中的方法进行返回
            bos = new ByteArrayOutputStream();
            int len;
            byte[] buf = new byte[1024];
            while ((len = in.read(buf)) != -1) {
                bos.write(buf, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bos.toByteArray();

    }


    /**
     * 假设服务器给我们传递了一个Byte数组，我们需要将其还原成原来的样子
     * <p>
     * 当然，我们可以直接一次性的将字节数组中的内容读取到文件中
     * 也可以借助ByteArrayInputStream 先让其读入到内存中，然后分段读入到文件中
     *
     * @param file  还原的文件
     * @param bytes 模拟服务器传递过来的字节码文件
     */
    public static void copyFile(File file, byte[] bytes) {
        OutputStream out = null;
        ByteArrayInputStream bis = null;
        try {
            out = new FileOutputStream(file);
            bis = new ByteArrayInputStream(bytes);
            int len;
            byte[] buf = new byte[1024];
            while ((len = bis.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
        } catch (Exception e) {
            try {
                out.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

}
