package pub.wii.cook.designpattern.start;

public class MallardDuckV2 extends DuckV2 {

    public MallardDuckV2() {
        flyBehavior = new FlyWithWings();
        quackBehavior = new Quack();
    }

    @Override
    public void display() {
        System.out.println("I'm a real Mallard duck");
    }
}
