package IO.装饰者模式;

/**
 * 模拟咖啡
 * 装饰设计模式一般分为四个组件
 * 1.抽象组件：需要装饰的抽象对象（接口或抽象父类）
 * 2.具体组件：需要装饰的对象
 * 3.抽象装饰类：包含了对抽象组件的引用以及装饰者共有的办法
 * 4.具体装饰类：被装饰的对象
 */
public class DecorateTest02 {
    public static void main(String[] args) {
        Drink coffee = new Coffee();

        Drink suger = new Suger(coffee);

        System.out.println(coffee.info() + " 金额:" + coffee.cost());
        System.out.println(suger.info() + " 金额:" + suger.cost());

        Drink milk = new Mink(suger);
        System.out.println(milk.info() + " 金额：" + milk.cost());

    }
}

//抽象组件
interface Drink {
    int a = 0;

    double cost();//费用

    String info();//说明
}

//具体组件
class Coffee implements Drink {

    private String name = "原味咖啡";

    @Override
    public double cost() {
        return 10;
    }

    @Override
    public String info() {
        return name;
    }
}

//抽象装饰类
abstract class Decorate implements Drink {
    //对抽象组件的引用
    private Drink drink;

    public Decorate(Drink drink) {
        this.drink = drink;
    }

    @Override
    public double cost() {
        return this.drink.cost();
    }

    @Override
    public String info() {
        return this.drink.info();
    }
}

class Mink extends Decorate {
    public Mink(Drink drink) {
        super(drink);
    }

    @Override
    public double cost() {
        //加了牛奶以后，我们的价格要多20块
        return super.cost() + 20;
    }

    @Override
    public String info() {
        return super.info() + " + 牛奶";
    }
}

class Suger extends Decorate {

    public Suger(Drink drink) {
        super(drink);
    }

    @Override
    public double cost() {
        return super.cost() + 30;
    }

    @Override
    public String info() {
        return super.info() + " + 糖";
    }
}


