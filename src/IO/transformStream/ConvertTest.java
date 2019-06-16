package IO.transformStream;

import java.io.*;
import java.net.URL;

/**
 * 通过使用转换流和结合我们的System.in 和System.out
 * 实现类似Scanner的功能，System.in是一个字节输入流，
 * System.out是一个字节输出流
 * 将字节流转换成字符流InputStreamReader
 * 将字符流转换成字节流OutputStreamWriter
 */
public class ConvertTest {
    public static void main(String[] args) {
//        printToConsole();//通过键盘输入字节流，在通过转换流集合标准输出流，将输入的内容打印打控制台
//        demo();//第一次模拟
//        downloadJPG();//下载图片
        printCode();//下载源码
    }

    /**
     * 接收键盘输入，并通过System.out打印到控制台
     * 使用新特性+转换流+缓冲流
     */
    public static void printToConsole() {
        try (BufferedReader bf =
                     new BufferedReader(
                             new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {
            String msg = "";
            while (!msg.equals("exit")) {
                msg = bf.readLine();//循环读取
                bw.write(msg);//写出
                bw.newLine();//换行
                bw.flush();//必须手动刷新一下！因为缓冲流的原因
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用我们的URL类，结合我们的转换流，下载网页的源代码
     */
    public static void demo() {
        try {
            InputStream is = new URL("http://www.baidu.com").openStream();//其返回一个流对象，我们读取
            int len;
            while ((len = is.read()) != -1) {
                System.out.print((char) (len));//这里会出现部分乱码，原因是中文在UTF-8中占用3个字节，而我们这里是将中文一个个的分开输出，所有会出现乱码，改进一下
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * 使用标准的方法读取网络流并下载图片到本地。
     * 包装流
     */
    public static void downloadJPG() {
        String url = "https://ss1.bdstatic.com/5aAHeD3nKgcUp2HgoI7O1ygwehsv/media/ch1/jpg/fuqinjiepc.jpg";
        try (InputStream is = new BufferedInputStream(new URL(url).openStream());
             OutputStream os = new BufferedOutputStream(new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\earth.png")))) {
            int len;
            byte[] bytes = new byte[1024];
            while ((len = is.read(bytes)) != -1) {
                os.write(bytes, 0, len);
            }
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();//如果抛出了403，则代表服务器不让我们爬取。在这里我们的爬取百度的图片是成功爬取到的。
        }
    }

    /**
     * 下载百度首页的源码到本地
     * <p>
     * 缓冲流  转换流   网络流
     */
    public static void printCode() {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new URL("http://www.baidu.com").openStream()));
             BufferedWriter bw = new BufferedWriter(
                     new OutputStreamWriter(
                             new FileOutputStream("C:\\Users\\Administrator\\Desktop\\baidu.html")))
        ) {
            String msg;
            while ((msg = br.readLine()) != null) {
                bw.write(msg);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
