package simulator.tower;

import simulator.flyable.Flyable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Tower {
    public static final String TOWER_SAYS_UNREGISTERED = "Tower Says: %s#%s(%d) unregistered from weather tower.\n";
    public static final String TOWER_SAYS_REGISTERED = "Tower Says: %s#%s(%d) registered to weather tower.\n";
    private final List<Flyable> observers = new ArrayList<>();
    private List<Flyable> landed;

    public void register(Flyable flyable) throws IOException {
        observers.add(flyable);
        Launcher.writer.write(String.format(TOWER_SAYS_REGISTERED, flyable.getType(), flyable.getName(), flyable.getId()));
    }

    public void unregister(Flyable flyable) {
        observers.remove(flyable);
    }

    public void landNewFlyable(Flyable flyable) throws IOException {
        landed.add(flyable);
        Launcher.writer.write(String.format(TOWER_SAYS_UNREGISTERED, flyable.getType(), flyable.getName(), flyable.getId()));
    }

    protected void conditionsChanged() throws IOException {
        landed = new ArrayList<>();
        for (Flyable observer : observers) {
            observer.updateConditions(); // +всех приземелнных заносит в лист landed
        }
        for (Flyable observer : landed) {
            unregister(observer); //удаляет всех севших после хода
        }
    }
}
