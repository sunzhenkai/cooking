package pub.wii.cook.designpattern.start;

public abstract class Duck {
    public void quack() {
        System.out.println("quack quack!");
    }

    public void swim() {
        System.out.println("I am swimming!");
    }

    public void fly() {
        System.out.println("I am flying!");
    }

    abstract void display();
}
