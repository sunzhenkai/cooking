package pub.wii.cook.designpattern.start;

public class RubberDuck extends Duck {

    @Override
    public void fly() {
        // do nothing
    }

    @Override
    void display() {
        System.out.println("I am rubber duck");
    }
}
