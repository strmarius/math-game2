package app.math;

import java.io.IOException;
import java.util.Random;

public class RandomGenerator {

    RandomGenerator() throws IOException {
        minNum = file.getFileMinNum();
        maxNum = file.getFileMaxNum();
    }
    private int num1, num2;
    private Random random = new Random();
    private ConfigFile file = new ConfigFile();
    private int minNum, maxNum;

    public int generate1() {
        return num1 = random.nextInt(maxNum - minNum) + minNum;
    }

    public int generate2() {
        return num2 = random.nextInt(maxNum - num1 + 1);
    }

    public int getNum1() {
        return num1;
    }

    public int getNum2() {
        return num2;
    }

    public int getResult() {
        return num1 + num2;
    }

    public int getMinNum() {
        return this.minNum;
    }

    public void setMinNum(int minNum) {
        this.minNum = minNum;
    }

    public int getMaxNum() {
        return this.maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

}
