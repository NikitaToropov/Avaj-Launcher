package simulator.tower;

import simulator.flyable.Coordinates;

import java.io.IOException;

public class WeatherTower extends Tower {

    public String getWeather(Coordinates coordinates) {
        return (WeatherProvider.getProvider().getCurrentWeather(coordinates));
    }

    void changeWeather() throws IOException {
        this.conditionsChanged();
    }
}
