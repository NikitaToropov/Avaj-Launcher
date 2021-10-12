package simulator.scenario;

import simulator.flyable.Flyable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ScenarioParser {

    protected static final String INVALID_NUMBER_STRING_FORMAT = "Строка '%s' не подходит для конверсии в число: неверный формат, или число слишком большое.";
    protected static final String INVALID_DATA_SIMULATION_STRING = "Нет данных о числе симуляций для запуска.";
    protected static final String INVALID_SIMULATION_NUMBER = "Заданное число строк '%s' для не позволяет запустить симуляцию. Введите неотрицательное число.";
    protected static final String INVALID_STRING_ARGUMENT_MISSING = "Неверный формат строки:\n'%s'\nНе хватает параметров.";
    protected static final String INVALID_STRING_ARGUMENT_EXCESS = "Неверный формат строки:\n'%s'\nЛишние параметры.";

    private final List<Flyable> flyableList;
    private int simulationRepetition;

    private ScenarioParser() {
        flyableList = new LinkedList<>();
    }

    public static ScenarioParser createScenario(String pathString) throws InvalidContentException, FileNotFoundException {
        ScenarioParser scenarioParser = new ScenarioParser();
        Scanner scanner = new Scanner(new File(pathString));
        if (scanner.hasNextLine()) {
            scenarioParser.simulationRepetition = scenarioParser.readSimulationRepetition(scanner.nextLine());
        } else {
            throw new InvalidContentException(INVALID_DATA_SIMULATION_STRING);
        }

        while (scanner.hasNextLine()) {
            scenarioParser.readAircraftDescription(scanner.nextLine().split("\\s+"));
        }
        return scenarioParser;
    }

    void readAircraftDescription(String[] splitString) throws InvalidContentException {
        if (splitString.length != 5) {
            if (splitString.length > 5) {
                throw new InvalidContentException(String.format(INVALID_STRING_ARGUMENT_MISSING, ", " + Arrays.toString(splitString)));
            } else {
                throw new InvalidContentException(INVALID_STRING_ARGUMENT_EXCESS);
            }
        }
        flyableList.add(AircraftDescription.createAircraftDescription(splitString));
    }

    int readSimulationRepetition(String firstLine) throws NumberFormatException, InvalidContentException {
        int simulationRepetition;
        try {
            simulationRepetition = Integer.decode(firstLine);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(String.format(INVALID_NUMBER_STRING_FORMAT, firstLine));
        }
        if (simulationRepetition < 0) {
            throw new InvalidContentException(String.format(INVALID_SIMULATION_NUMBER, firstLine));
        }
        return simulationRepetition;
    }

    public int getSimulationRepetition() {
        return simulationRepetition;
    }

    public List<Flyable> getFlyableList() {
        return new LinkedList<>(flyableList);
    }
}
