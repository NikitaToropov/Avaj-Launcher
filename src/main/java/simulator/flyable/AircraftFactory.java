package simulator.flyable;

import java.lang.reflect.InvocationTargetException;

public abstract class AircraftFactory {

    public static final String WRONG_AIRCRAFT_EXCEPTION_TEXT = "Проблемы генерации летательного средства типа \"%s\"";

    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) throws IllegalArgumentException {
        Coordinates coordinates = new Coordinates(longitude, latitude, height);
        try {
            return (Flyable) Class.forName("simulator.flyable." + type)
                    .getDeclaredConstructor(String.class, Coordinates.class)
                    .newInstance(name, coordinates);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            throw new IllegalArgumentException(String.format(new String(WRONG_AIRCRAFT_EXCEPTION_TEXT), type));
        }
    }
}
