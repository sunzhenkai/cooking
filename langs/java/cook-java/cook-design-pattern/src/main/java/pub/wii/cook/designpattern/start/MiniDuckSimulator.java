package pub.wii.cook.designpattern.start;

public class MiniDuckSimulator {
    public static void main(String[] args) {
        DuckV2 mallard = new MallardDuckV2();
        mallard.display();
        mallard.performFly();
        mallard.performQuack();
    }
}
