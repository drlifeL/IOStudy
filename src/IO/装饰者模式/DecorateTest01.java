package IO.装饰者模式;

/**
 * <p>
 * 实现放大器对声音的放大功能
 * 注意：没有人的声音，放大器是不起作用的，我们必须建立在
 * 人的声音的上，具体体现在构造方法中
 * 又因为人和放大器都有发声的功能，这个时候我们就可以声明一个接口
 * 将其抽取并声明
 */
public class DecorateTest01 {
    public static void main(String[] args) {
        Person p = new Person();
        p.say();

        //扩音器用来放大人声，简单的装饰
        Amplifier am = new Amplifier(p);
        am.say();
    }
}

interface Say {
    void say();
}

class Person implements Say {

    private int voice = 10;

    public int getVoice() {
        return voice;
    }

    public void setVoice(int voice) {
        this.voice = voice;
    }

    @Override
    public void say() {
        //可以省略this
        System.out.println("人的声音为：" + getVoice());
    }
}

class Amplifier implements Say {

    private Person p;

    public Amplifier(Person p) {
        this.p = p;
    }

    @Override
    public void say() {
        //扩音器对人的声音放大了1000倍
        System.out.println("人的声音为：" + p.getVoice() * 1000);
        System.out.println("噪音！");
    }
}