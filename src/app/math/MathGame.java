package app.math;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Random;

public class MathGame extends JFrame implements ActionListener{
    private JTextField answerField;
    private JLabel num1Label, num2Label, resultLabel, feedbackLabel;
    private JMenuBar menuBar;
    private JButton submitButton, resetButton;
    private ConfigFile file = new ConfigFile();
    private int maxNum = 10, minNum = 0, num1, num2, result;
    String player = "";
    Random random = new Random();

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public void setMinNum(int minNum) {
        this.minNum = minNum;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public int getMinNum() {
        return minNum;
    }

    public String getPlayer() {

        if(!player.isEmpty()) {
            String firstLetter = player.substring(0, 1).toUpperCase();
            return firstLetter + player.substring(1).toLowerCase();
        }
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public MathGame() throws IOException {

        num1 = random.nextInt(minNum, maxNum);
        num2 = random.nextInt(maxNum - num1 + 1);
        result = num1 + num2;

        setTitle("MaHAHAte");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        file.readFile();

        JPanel mainPanel = new JPanel(new BorderLayout(25, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel gamePanel = new JPanel(new GridLayout(4, 3, 15, 15));
        gamePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // create MenuBar
        menuBar = new JMenuBar();
        JMenu config = new JMenu("Configurare");
        JMenuItem configMin = new JMenuItem("Numărul minim  ");
        JMenuItem configMax = new JMenuItem("Numărul maxim  ");
        JMenuItem configPlayer = new JMenuItem("Setează nume");
        JMenuItem config3 = new JMenuItem("Afișează rezultatul");
        JMenu aboutApp = new JMenu("Despre aplicație");
        JMenuItem despre = new JMenuItem("Despre ...");

        config.add(configMin);
        config.add(configMax);
        config.add(configPlayer);
        config.add(config3);
        aboutApp.add(despre);
        menuBar.add(config);
        menuBar.add(aboutApp);
        this.setJMenuBar(menuBar);

        // set configMin (Seteaza numarul minim)
        configMin.addActionListener(e -> {
            setMinNum(Integer.parseInt(JOptionPane.showInputDialog("Operațiile sunt generate de la " + getMinNum() + " la " +getMaxNum() +
                    ".\n\nIntrodu valoarea nouă pentru minim: ")));

            while (getMinNum() < 0 || getMinNum() > getMaxNum()) {
                JOptionPane.showMessageDialog(null, "Numărul trebuie să fie pozitiv și mai mic decât " + getMaxNum());
                setMinNum(Integer.parseInt(JOptionPane.showInputDialog("Introdu valoarea nouă pentru minim: ")));
            }
        });
        configMin.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0));

        // set configMax (Seteaza numarul maxim)
        configMax.addActionListener(e -> {
            setMaxNum(Integer.parseInt(JOptionPane.showInputDialog("Operațiile sunt generate de la " + getMinNum() + " la " +getMaxNum() +
                    ".\n\nIntrodu valoarea nouă pentru maxim: ")));

            while (getMaxNum() <= 1) {
                JOptionPane.showMessageDialog(null, "Numărul trebuie să fie mai mare sau egal cu 2");
                setMaxNum(Integer.parseInt(JOptionPane.showInputDialog("Introdu valoarea nouă pentru maxim: ")));
            }
        });
        configMax.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0));

        // set config3 (Afiseaza rezultatul)
        config3.addActionListener(e -> JOptionPane.showMessageDialog(null, "", "Glumă", JOptionPane.PLAIN_MESSAGE));

        // set aboutApp (Despre aplicatie)
        despre.addActionListener(e -> JOptionPane.showMessageDialog(null,
                """
                Basic Math game for kids with following features:

                - 2 random generated numbers and ask for result
                - Feedback based on result
                - Option to set maximum value for generated number (difficulty)

                _________________

                v1.01

                - Set focus for answerField
                - Use ENTER key as shortcut for submitButton
                - MaxNum is also max sum(result) beside generated numbers
                - Change random generator from function Math.random to class Random
                - Condition Random generator not to give same numbers as previous exercise

                v1.02

                - Set MenuBar
                - Add Config in MenuBar for MaxNumber, MinNumber
                - Solve a bug (if you enter max value = 1 program crash)
                - Solve bound for random (EX: for max = 2, you get only 0+0, 1+0 and 0+1)

                v1.03

                - Add AboutApp menu
                - Add config file for app to read&write settings which is saved at close of app
                - Remove initial set for MaxNum
                - If cfg missing/wrong data set min=0 & max=10
                - Add option of setting \"Player\" name to have personal feedback"""
                , "Despre ...", JOptionPane.INFORMATION_MESSAGE));

        configPlayer.addActionListener(e -> {
            setPlayer(JOptionPane.showInputDialog("Setează numele jucatorului astfel incat feedback-ul \n" +
                                                    "sa fie mai personal.\n\n" + "Introdu numele:"));


        });

        num1Label = new JLabel("" + num1, JLabel.CENTER);
        num1Label.setFont(num1Label.getFont().deriveFont(24f));
        gamePanel.add(num1Label);

        JLabel plusLabel = new JLabel("+", JLabel.CENTER);
        plusLabel.setFont(plusLabel.getFont().deriveFont(24f));
        gamePanel.add(plusLabel);

        num2Label = new JLabel("" + num2, JLabel.CENTER);
        num2Label.setFont(num2Label.getFont().deriveFont(24f));
        gamePanel.add(num2Label);

        JLabel equalsLabel = new JLabel("=", JLabel.CENTER);
        equalsLabel.setFont(equalsLabel.getFont().deriveFont(24f));
        gamePanel.add(equalsLabel);

    // create answer box
        answerField = new JTextField();
        answerField.setFont(answerField.getFont().deriveFont(24f));
        answerField.requestFocus();
        answerField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    actionPerformed(new ActionEvent(submitButton, ActionEvent.ACTION_PERFORMED, null));
                }
            }
        });
        gamePanel.add(answerField);

    //create submit button
        submitButton = new JButton("Verifică");
        submitButton.setFont(submitButton.getFont().deriveFont(24f));
        submitButton.addActionListener(this);
        gamePanel.add(submitButton);

        resultLabel = new JLabel("");
        resultLabel.setFont(resultLabel.getFont().deriveFont(24f));
        gamePanel.add(resultLabel);

        feedbackLabel = new JLabel("");
        feedbackLabel.setFont(feedbackLabel.getFont().deriveFont(16f));
        gamePanel.add(feedbackLabel);

        resultLabel = new JLabel("");
        resultLabel.setFont(resultLabel.getFont().deriveFont(24f));
        gamePanel.add(resultLabel);

        mainPanel.add(gamePanel, BorderLayout.CENTER);

//        JPanel feedbackPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
//
//        feedbackLabel = new JLabel("");
//        feedbackLabel.setFont(feedbackLabel.getFont().deriveFont(18f));
//        feedbackPanel.add(feedbackLabel);
//
//        gamePanel.add(feedbackPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton exitButton = new JButton("Închide");
        exitButton.setFont(exitButton.getFont().deriveFont(24f));
        exitButton.addActionListener(e -> System.exit(0));
        buttonPanel.add(exitButton);

        resetButton = new JButton("Resetare");
        resetButton.setFont(resetButton.getFont().deriveFont(24f));
        resetButton.addActionListener(this);
        buttonPanel.add(resetButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        file.readFile();
        minNum = file.getFileMinNum();
        maxNum = file.getFileMaxNum();
        player = file.getFileUser();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                file.writeFile(getMinNum(), getMaxNum(), getPlayer());
            }));
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            try {
                int answer = Integer.parseInt(answerField.getText());
                if (answer == result) {
                    feedbackLabel.setText("Corect. " + (getPlayer().isEmpty() ? "Bravo" : "Bravo ") +
                            getPlayer() + "!");
                    feedbackLabel.setForeground(Color.ORANGE);
//                    resultLabel.setText("Rezultat: " + result);
                    num1 = random.nextInt(minNum, maxNum);
                    num2 = random.nextInt(maxNum - num1 + 1);
                    while(Integer.parseInt(num1Label.getText()) == num1 &&
                    Integer.parseInt(num2Label.getText()) == num2) {
                        num1 = random.nextInt(minNum, maxNum);
                        num2 = random.nextInt(maxNum - num1 + 1);
                    }
                    result = num1 + num2;
                    num1Label.setText("" + num1);
                    num2Label.setText("" + num2);
                    answerField.setText("");
//                    resultLabel.setText("");
                } else {
                    feedbackLabel.setText("Mai încearcă, răspunsul nu este corect.");
                    feedbackLabel.setForeground(Color.BLUE);
                    answerField.setText("");
                }
            } catch (NumberFormatException ex) {
                feedbackLabel.setText("Răspunsul nu este valid. Încearcă să introduci un număr");
                feedbackLabel.setForeground(Color.RED);
            }
        } else if (e.getSource() == resetButton) {
            num1 = random.nextInt(minNum, maxNum);
            num2 = random.nextInt(maxNum - num1 + 1);
            result = num1 + num2;
            num1Label.setText("" + num1);
            num2Label.setText("" + num2);
            answerField.setText("");
//            resultLabel.setText("");
            feedbackLabel.setText("");
            feedbackLabel.setForeground(Color.BLACK);
        }
    }


// old method to ask user for input MaxNum (used to work constructor for MathGame(maxNum)
//    public static int getInputForMax() {
//
//        int maxNum = Integer.parseInt(JOptionPane.showInputDialog("Operații până la numărul: "));
//        while(maxNum <= 1){
//            JOptionPane.showMessageDialog(null,"Numărul trebuie să fie mai mare sau egal cu 2");
//            maxNum = Integer.parseInt(JOptionPane.showInputDialog("Operații până la numărul: "));
//        }
//        return maxNum;
//    }

    public static void main(String[] args) throws IOException {

        MathGame game = new MathGame();
    }

}

