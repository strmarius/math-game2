package app.math;

import java.io.IOException;

public class MathGame {

    ConfigFile file;
    RandomGenerator random;
    UI ui;

    public MathGame() throws IOException {

        ui = new UI();
        random = new RandomGenerator();
        file = new ConfigFile();
        ui.setPlayer(file.getFileUser());

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            file.writeFile(ui.getMinNum(), ui.getMaxNum(), ui.getPlayer());
        }));
    }

    public static void main(String[] args) throws IOException {

        new MathGame();
    }
}

