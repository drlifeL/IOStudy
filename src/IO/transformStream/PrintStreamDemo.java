package IO.transformStream;

import java.io.*;


/**
 * 测试打印流
 * printStream
 * 以及PrintWriter
 * 我们的System.out是一个流对象，我们让其默认打印到控制台的方法
 * 变成打印到我们的文件中去
 */
public class PrintStreamDemo {
    public static void main(String[] args) throws FileNotFoundException {
//        usePrintStream();//基本使用
//        useSystemOutStreamToFile();//更改控制台的输出到文件
        usePrintWriter();
    }

    /**
     * 演示使用打印流
     * PrintStream(OutputStream out, boolean autoFlush)
     * 创建一个新的打印流。
     */
    public static void usePrintStream() {

        try {
            PrintStream print = new PrintStream(
                    new BufferedOutputStream(
                            new FileOutputStream("C:\\Users\\Administrator\\Desktop\\print.txt")));
            print.print("哈哈哈哈哈哈哈");//将这个内容打印到我们的文件中
            print.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更改System.out的输出源头
     * 让其不在是控制台，而是我们的文件
     */
    public static void useSystemOutStreamToFile() throws FileNotFoundException {
        PrintStream printStream = new PrintStream(
                new BufferedOutputStream(
                        new FileOutputStream("C:\\Users\\Administrator\\Desktop\\print.txt")), true);
        //重定向输出端
        System.setOut(printStream);//使用System的静态方法，改变其流的路径当然我们也可以将其修改回去
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
        }
        printStream.close();
//        printStream.flush();//可以在构造函数里面指定让其自动刷新
        //将其改回来,如果使用匿名对象的话，必须加上自动刷新，否则出不来，要不然就不加BufferedOutputStream
        System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(FileDescriptor.out)), true));
        System.out.println("i am come back!");

    }

    /**
     * 演示printWriter
     * 其实大部分还是同printWriter
     * 不同点在于该类多了两个构造器，可以将其写到Writer流中
     */
    public static void usePrintWriter() {

        try (PrintWriter pw = new PrintWriter(new FileWriter("C:\\Users\\Administrator\\Desktop\\副本1.txt"))) {
           pw.println("哈哈哈");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
