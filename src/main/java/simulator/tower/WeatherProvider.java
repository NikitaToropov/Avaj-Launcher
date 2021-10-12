package simulator.tower;

import simulator.flyable.Coordinates;

import java.util.Random;


public class WeatherProvider {

    static private final WeatherProvider WEATHER_PROVIDER = new WeatherProvider();
    static private final String[] WEATHER = new String[]{"SUN", "RAIN", "FOG", "SNOW"};
    static private final Random RANDOM = new Random();

    private WeatherProvider() {
    }

    public static WeatherProvider getProvider() {
        return WEATHER_PROVIDER;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        int index = (coordinates.getLatitude() + coordinates.getHeight() + coordinates.getLongitude() + Math.abs(RANDOM.nextInt())) % WEATHER.length;
        return WEATHER[index];
    }

    public static int getWeatherIndex(String weatherName) throws IllegalArgumentException {
        for (int i = 0; i < WEATHER.length; i++) {
            if (WEATHER[i].equals(weatherName)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Не удалось понять погоду: " + weatherName);
    }

}

