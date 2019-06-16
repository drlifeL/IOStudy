package IO;

import org.apache.commons.io.FileUtils;

import java.io.File;

/**
 * 使用commonsIo工具
 * FileUtils的使用
 *
 */
public class UseCommonsIO {
    public static void main(String[] args) {

    }

    /**
     * 文件或者目录的大小
     * @param src
     * @return
     */
    public static long fileOrDirSize(String src) {
        return FileUtils.sizeOf(new File(src));
    }



}
