package simulator;

import simulator.scenario.InvalidContentException;
import simulator.security.MD5Exception;
import simulator.security.MD5Parser;
import simulator.tower.Launcher;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            handleInput(args);
            new Launcher().start(args);
        } catch (RuntimeException | IOException | InvalidContentException e) {
            e.printStackTrace();
        }


    }

    private static void handleInput(String[] args) throws IOException, MD5Exception, InvalidContentException {
        if (args.length == 1) {
            System.out.println(MD5Parser.getMd5Hash("MD5 код для вашего файла " + args[0]));
        } else if (args.length == 2) {
            MD5Parser.validateFile(args[0], args[1]);
        }
    }

    public static void greeting() {
        System.out.println("Hello my comrades!");
    }

}
