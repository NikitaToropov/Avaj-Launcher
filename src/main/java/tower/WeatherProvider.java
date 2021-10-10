package tower;

import flyable.Coordinates;

public class WeatherProvider {

    private WeatherProvider weatherProvider;

    private String weather;

    public WeatherProvider getProvider() {
        return weatherProvider == null ?
                new WeatherProvider()
                : null;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        return weather;
    }
}
