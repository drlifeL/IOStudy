package IO.transformStream;

import java.io.*;

/**
 * 对象流，可以持久化对象和基本数据类型
 * ObjectInputStream   ObjectOutputStream
 * 注意：被序列化的对象必须实现serializable 接口
 * 如果我们不想某个对象被我们序列化我们可以在类中给其加上关键字：transient
 * eg:  private transient String name;  //该对象就不会被序列化
 */
public class ObjectStream {
    public static void main(String[] args) {
//        try {
//            test();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        ObjectToFile();//将对象持久化到本地  序列化
        readObjectFromFile("C:\\Users\\Administrator\\Desktop\\myObject.ser");//将本地的对象读取到内存中.反序列化
    }

    /**
     * 将自建Employee类序列化到内存中，而后读取出来
     */
    public static void test() throws IOException, ClassNotFoundException {
        Employee e = new Employee("张三", 20);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();//节点流是一个对象，为了方便其转换成一个数组，
        ObjectOutputStream oos = new ObjectOutputStream(baos);//对象转换流
        oos.writeObject(e);
        oos.flush();
        //将对象写入到字节数组中
        byte[] bytes = baos.toByteArray();//转换成字节数组
        oos.close();

        //使用包装流
        ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(
                        new ByteArrayInputStream(bytes)));
        Object objemp = ois.readObject();
        if (objemp instanceof Employee) {//防止类型转换错误，使用Instanceof运算符
            if (objemp != null) {
                Employee e1 = (Employee) objemp;
                System.out.println(e1.getName() + "-->" + e1.getAge());
            }
        }
        ois.close();
    }

    /**
     * 演示将对象序列化到本地
     */
    public static void ObjectToFile() {
        Employee obj = new Employee("李四", 20);//要序列化的对象
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();//方便我们将对象转换成字节数组
             ObjectOutputStream oos = new ObjectOutputStream(bos);//对象转换流
        ) {
            oos.writeObject(obj);
            oos.flush();
            OutputStream os = new BufferedOutputStream(//缓冲流
                    new FileOutputStream("C:\\Users\\Administrator\\Desktop\\myObject.ser"));//文件输出流
            os.write(bos.toByteArray());
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将持久到本地的对象，反序列化到内存中
     *
     * @param src 本地持久化文件的地址
     */
    public static void readObjectFromFile(String src) {
        try(InputStream is = new BufferedInputStream(new FileInputStream(src));
            ObjectInputStream ois = new ObjectInputStream(is);
        ) {
//            InputStream is = new BufferedInputStream(//包装流
//                    new FileInputStream(src));//文件读取流
//            ObjectInputStream ois = new ObjectInputStream(is);//对象转换流
            Object obj = ois.readObject();
            if (obj instanceof Employee) {
                Employee e = (Employee) obj;
                System.out.println(e.getName() + "-->" + e.getAge());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }


    }


}

class Employee implements Serializable {
    private transient String name;//加上transient关键字，该属性在序列的时候就不会被序列化
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Employee() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
