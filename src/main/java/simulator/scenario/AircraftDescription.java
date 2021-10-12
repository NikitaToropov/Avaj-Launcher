package simulator.scenario;

import simulator.flyable.Aircraft;
import simulator.flyable.AircraftFactory;
import simulator.flyable.Flyable;

class AircraftDescription {

    public static final String INVALID_AIRCRAFT_TYPE = "Неверный тип летательного средства. Такого %s нет среди известных.";
    public static final String INVALID_COORDINATE_VALUE = "Координата должна быть положительным числом, а было введено: ";
    public static final String INVALID_HEIGHT_VALUE = "Высота должна быть числом от 1 по 100, а было введено: ";

    protected static final int TYPE = 0;
    protected static final int NAME = 1;
    protected static final int LONGITUDE = 2;
    protected static final int LATITUDE = 3;
    protected static final int HEIGHT = 4;

    private AircraftDescription() {
    }

    public static Flyable createAircraftDescription(String[] aircraftDescriptionString) throws InvalidContentException {
        AircraftDescription aircraftDescription = new AircraftDescription();
        String type = aircraftDescription.processType(aircraftDescriptionString[TYPE]);
        String name = aircraftDescription.processName(aircraftDescriptionString[NAME]);
        int longitude = aircraftDescription.processLongitude(aircraftDescriptionString[LONGITUDE]);
        int latitude = aircraftDescription.processLatitude(aircraftDescriptionString[LATITUDE]);
        int height = aircraftDescription.processHeight(aircraftDescriptionString[HEIGHT]);
        return AircraftFactory.newAircraft(type, name, longitude, latitude, height);
    }

    private String processType(String type) throws InvalidContentException {
        if (Aircraft.TYPES.contains(type)) {
            return type;
        } else {
            throw new InvalidContentException(String.format(INVALID_AIRCRAFT_TYPE, type));
        }
    }

    private String processName(String name) {
        return name;
    }

    private int processLongitude(String longitudeString) throws NumberFormatException, InvalidContentException {
        int longitude = Integer.decode(longitudeString);
        if (longitude < 1) {
            throw new InvalidContentException(INVALID_COORDINATE_VALUE + longitude);
        }
        return longitude;
    }

    private int processLatitude(String latitudeString) throws NumberFormatException, InvalidContentException {
        int latitude = Integer.decode(latitudeString);
        if (latitude < 1) {
            throw new InvalidContentException(INVALID_COORDINATE_VALUE + latitude);
        }
        return latitude;
    }

    private int processHeight(String heightString) throws NumberFormatException, InvalidContentException {
        int height = Integer.decode(heightString);
        if (height <= 0 || height > 100) {
            throw new InvalidContentException(INVALID_HEIGHT_VALUE + height);
        }
        return height;
    }

}
