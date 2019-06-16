package IO;

import java.io.File;

/**
 * IO学习
 * 递归统计文件夹大小
 */
public class FileInfo {
    public static void main(String[] args) {
        File src = new File("D:\\Maven");
        System.out.println(src.length());
        System.out.println("文件的大小是：" + countFileSize(src));


    }

    /**
     * 利用文件类来递归的统计文件的大小
     * 使用到file.length()
     * windows 默认的统计大小是不计算文件夹，一个文件夹的大小是4096B
     *
     * @return
     */
    public static long countFileSize(File src) {
        //如果不存在
        if (null == src || !src.exists()) return 0;
        long len = src.isFile() ? src.length() : 0;
        if (src.isDirectory()) {
            for (File f : src.listFiles()) {
                len += countFileSize(f);
            }
        }
        return len;
    }

}
