package IO.randomAccessFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 演示RandomAccessFile基本使用和访问
 * 该类既可以读也可以写
 * 构造器：
 * 第一个参数：里面传递一个文件对象或者一个文件地址
 * 第二个参数：字符串指定模式： r 只读，  rw 读写  ...
 */
public class randomAccessFile {
    public static void main(String[] args) throws IOException {
//        demo();
        partRead();
    }

    /**
     * 演示基本使用
     * 其基本方法和字节流的使用基本一致，重点关注seek(long )方法，
     */
    public static void demo() {
        String src = "G:\\JavaEE\\ssh\\spring_day03jdbcTemplate\\src\\cn\\dxxy\\c3p0\\_C3p0Demo.java";
        File file = new File(src);
        String model = "r";
        try {
            RandomAccessFile raf = new RandomAccessFile(file, model);
//            System.out.println(((char)(raf.read())));//读取一个字节
            int beginPos = 5;//开始读取的位置
            raf.seek(beginPos);//从第5个字节后开始读取
            int readSize = 500;//只读取1026个字节
            long size = file.length();
            int pageSize = (int) Math.ceil((double) size / readSize);
            int len;
            byte[] bytes = new byte[1024];
            while ((len = raf.read(bytes)) != -1) {
                if (len > readSize) {//读到多的了
                    System.out.println(new String(bytes, 0, readSize));
                    break;//跳出循环结束读取
                } else {//没有读到1026
                    System.out.println(new String(bytes, 0, len));
                    readSize -= len;
                }
            }

            System.out.println(size + "-->" + pageSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 演示分段读取
     */
    public static void partRead() throws IOException {
        String src = "G:\\JavaEE\\ssh\\spring_day03jdbcTemplate\\src\\cn\\dxxy\\c3p0\\_C3p0Demo.java";
        File file = new File(src);
        int beginPos = 0;//开始的位置
        long size = file.length();//文件大小
        int blockSize = 1300;//每段大小
        int partNum = (int) Math.ceil((double) size / blockSize);//共分了几段
        RandomAccessFile raf = new RandomAccessFile(file, "r");
        int len;
        byte[] bytes = new byte[1024];
        System.out.println(size + "--->" + partNum);
        int realSize;
        for (int i = 0; i < partNum; i++) {
            raf.seek(beginPos);//定位到开始的位置
            len = raf.read(bytes);

            if (len >= blockSize) {//构成了一整块
//                System.out.println(new String(bytes, 0, blockSize));
                realSize = blockSize;
                beginPos += blockSize;
            } else {
                realSize = len;
            }
            System.out.println(new String(bytes, 0, realSize));
            System.out.println("<-------------------------------->");

        }
    }

}
