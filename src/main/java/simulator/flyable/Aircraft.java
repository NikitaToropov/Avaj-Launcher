package simulator.flyable;

import simulator.tower.Launcher;
import simulator.tower.WeatherProvider;
import simulator.tower.WeatherTower;

import java.io.IOException;
import java.util.*;

public abstract class Aircraft {

    public static final Set<String> TYPES = new HashSet<>(Arrays.asList("Baloon", "JetPlane", "Helicopter"));

    protected static final int LONGITUDE = 0;
    protected static final int LATITUDE = 1;
    protected static final int HEIGHT = 2;

    protected long id;
    protected String name;
    protected Coordinates coordinates;
    static private long idCounter;

    protected int[][] behavior;
    protected int weatherIndex;
    protected static Random random = new Random();
    protected String[][] messages;

    protected Aircraft(String name, Coordinates coordinates) {
        this.name = name;
        this.coordinates = coordinates;
        this.id = nextId();
    }

    public void move(WeatherTower weatherTower) throws IOException {
        String weather = weatherTower.getWeather(coordinates);
        weatherIndex = WeatherProvider.getWeatherIndex(weather);
        int height = coordinates.getHeight() + behavior[weatherIndex][HEIGHT];
        if (height > 100) {
            height = 100;
        }
        else if (height <= 0) {
            height = 0;
        }

        coordinates.setLongitude(coordinates.getLongitude() + behavior[weatherIndex][LONGITUDE]);
        coordinates.setLatitude(coordinates.getLatitude() + behavior[weatherIndex][LATITUDE]);
        coordinates.setHeight(height);

        say(weatherTower);

        if (height == 0) {
            Launcher.writer.write(getType() + "#" + getName() + "(" + getId() + ") landing.\n");
            weatherTower.landNewFlyable((Flyable) this);
        }
    }

    private long nextId() {
        return idCounter++;
    }

    public abstract String getType();

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public void say(WeatherTower weatherTower) throws IOException {
        int weatherIndex = WeatherProvider.getWeatherIndex(weatherTower.getWeather(coordinates)) % messages.length;
        int randomIndex = Math.abs(random.nextInt()) % messages[weatherIndex].length;
        Launcher.writer.write(getType() + "#" + getName() + "(" + getId() + "): " + messages[weatherIndex][randomIndex]
                //        + " [" + coordinates.getLongitude() + "; " + coordinates.getLatitude() + "; " + coordinates.getHeight() + "]"
                + "\n"
        );
    }

}
