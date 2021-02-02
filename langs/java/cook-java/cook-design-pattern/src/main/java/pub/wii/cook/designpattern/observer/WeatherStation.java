package pub.wii.cook.designpattern.observer;

public class WeatherStation {
    WeatherData weatherData = new WeatherData();
    CurrentConditionDisplay currentConditionDisplay = new CurrentConditionDisplay(weatherData);

}
