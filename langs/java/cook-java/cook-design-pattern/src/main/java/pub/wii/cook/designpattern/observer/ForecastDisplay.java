package pub.wii.cook.designpattern.observer;

public class ForecastDisplay implements Observer, DisplayElement {
    private float pressure;

    @Override
    public void display() {

    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        this.pressure = pressure;
    }
}
