package simulator.tower;

import simulator.flyable.Flyable;
import simulator.scenario.InvalidContentException;
import simulator.scenario.ScenarioParser;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Launcher {

    String OUTPUT_FILENAME = "simulation.txt";
    static public BufferedWriter writer;

    public void start(String[] args) throws InvalidContentException, IOException {
        List<Flyable> flyableList;

        writer = null;

        ScenarioParser reader = readInputFile(args[0]);
        flyableList = reader.getFlyableList();

        WeatherTower tower = new WeatherTower();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILENAME))) {
            Launcher.writer = writer;
            registerAll(flyableList, tower);
            doIterations(tower, reader.getSimulationRepetition());
        } catch (IOException e) {
            throw new IOException(String.format("Какие-то проблемы с выходным файлом \"%s\"", OUTPUT_FILENAME) );
        }
    }

    public ScenarioParser readInputFile(String filename) throws InvalidContentException, FileNotFoundException {
        ScenarioParser scenarioParser;
        scenarioParser = ScenarioParser.createScenario(filename);
        return scenarioParser;
    }

    private void registerAll(List<Flyable> flyableList, WeatherTower tower) throws IOException {
        for (Flyable flyable : flyableList) {
            tower.register(flyable);
            flyable.registerTower(tower);
        }
    }

    private void doIterations(WeatherTower tower, int iterations) throws IOException {
        for (int i = 0; i < iterations; i++) {
            tower.changeWeather();
        }
    }
}
