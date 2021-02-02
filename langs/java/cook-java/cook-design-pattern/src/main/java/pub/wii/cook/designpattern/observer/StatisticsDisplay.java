package pub.wii.cook.designpattern.observer;

public class StatisticsDisplay implements Observer, DisplayElement {
    private float minTemperature;
    private float maxTemperature;
    private float minHumidity;
    private float maxHumidity;

    @Override
    public void display() {
        System.out.printf("Weather Statistics: temperature [%f, %f]F, humidity [%f, %f]%%",
                minTemperature, maxTemperature, minHumidity, maxHumidity);
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        this.minTemperature = Math.min(this.minTemperature, temp);
        this.maxTemperature = Math.max(this.maxTemperature, temp);

        this.minHumidity = Math.min(this.minHumidity, temp);
        this.maxHumidity = Math.max(this.maxHumidity, temp);
    }
}
