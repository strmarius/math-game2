package app.math;

import java.io.*;
import java.util.Properties;

public class ConfigFile {

    private int fileMinNum, fileMaxNum;
    private String fileUser;

    ConfigFile() throws IOException {
        readFile();
    }
    public void setFileMinNum(int fileMinNum) {
        this.fileMinNum = fileMinNum;
    }

    public int getFileMinNum() {
        return fileMinNum;
    }

    public int getFileMaxNum() {
        return fileMaxNum;
    }

    public void setFileMaxNum(int fileMaxNum) {
        this.fileMaxNum = fileMaxNum;
    }

    public String getFileUser() {
        return fileUser;
    }

    public void setFileUser(String fileUser) {
        this.fileUser = "" + fileUser;
    }

    public void readFile() throws IOException {

        File file = new File("config.properties");

        try {
            FileReader reader = new FileReader(file);
            Properties prop = new Properties();
            prop.load(reader);

            if(prop.getProperty("minNum").isEmpty() || prop.getProperty("minNum") == null) {
                setFileMinNum(0);
            } else {
                setFileMinNum(Integer.parseInt(prop.getProperty("minNum")));
            }

            if(prop.getProperty("maxNum").isEmpty() || prop.getProperty("maxNum") == null) {
                setFileMaxNum(10);
            } else {
                setFileMaxNum(Integer.parseInt(prop.getProperty("maxNum")));
            }
            setFileUser(prop.getProperty("player"));

            reader.close();

        } catch (FileNotFoundException | NullPointerException | NumberFormatException e) {
            file.createNewFile();

            FileWriter writer = new FileWriter(file);

            writer.write("minNum=\n");
            writer.write("maxNum=\n");
            writer.write("player=\n");

            writer.close();
            readFile();
        }
    }

    public void writeFile(int min, int max, String player) {

        File file = new File("config.properties");

        try {
            FileWriter writer = new FileWriter(file);

            writer.write(String.format("minNum=%d%n", min));
            writer.write(String.format("maxNum=%d%n", max));
            writer.write(String.format("player=%s%n", player));

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


