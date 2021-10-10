package flyable;

import tower.WeatherTower;

public interface Flyable {
    void updateConditions();
    void registerTower(WeatherTower tower);
}
