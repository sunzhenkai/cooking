package pub.wii.cook.designpattern.start;

public class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("< Silence >");
    }
}
