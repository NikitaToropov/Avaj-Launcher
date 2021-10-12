package simulator.flyable;

import simulator.tower.WeatherTower;

import java.io.IOException;

public interface Flyable {

    void updateConditions() throws IOException;
    void registerTower(WeatherTower weatherTower);

    String getName();
    String getType();
    long getId();
}
