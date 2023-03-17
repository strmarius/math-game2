import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class MathGame extends JFrame implements ActionListener{
    private JTextField answerField;
    private JLabel num1Label, num2Label, resultLabel, feedbackLabel;
    private JMenuBar menuBar;
    private JButton submitButton, resetButton;
    private int maxNum, minNum, num1, num2, result;
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

    public MathGame(int maxNum) {

        this.maxNum = maxNum;
        num1 = random.nextInt(minNum, maxNum);
        num2 = random.nextInt(maxNum - num1 + 1);
        result = num1 + num2;

        setTitle("MaHAHAte");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(25, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel gamePanel = new JPanel(new GridLayout(4, 3, 15, 15));
        gamePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // create MenuBar
        menuBar = new JMenuBar();
        JMenu config = new JMenu("Configurare");
        JMenuItem config1 = new JMenuItem("Setează numărul maxim  ");
        JMenuItem config2 = new JMenuItem("Setează numărul minim  ");
        JMenuItem config3 = new JMenuItem("Afișează rezultatul");

        config.add(config1);
        config.add(config2);
        config.add(config3);
        menuBar.add(config);
        this.setJMenuBar(menuBar);

        // set config1 (Seteaza numarul maxim)
        config1.addActionListener(e -> {
            setMaxNum(Integer.parseInt(JOptionPane.showInputDialog("Operațiile sunt generate de la " + getMinNum() + " la " +getMaxNum() +
                    ".\n\nIntrodu valoarea nouă pentru maxim: ")));

            while (getMaxNum() <= 1) {
                JOptionPane.showMessageDialog(null, "Numărul trebuie să fie mai mare sau egal cu 2");
                setMaxNum(Integer.parseInt(JOptionPane.showInputDialog("Introdu valoarea nouă pentru maxim: ")));
            }
        });
        config1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0));
        // set config2 (Seteaza numarul minim)
        config2.addActionListener(e -> {
            setMinNum(Integer.parseInt(JOptionPane.showInputDialog("Operațiile sunt generate de la " + getMinNum() + " la " +getMaxNum() +
                    ".\n\nIntrodu valoarea nouă pentru minim: ")));

            while (getMinNum() < 0 || getMinNum() > getMaxNum()) {
                JOptionPane.showMessageDialog(null, "Numărul trebuie să fie pozitiv și mai mic decât " + getMaxNum());
                setMinNum(Integer.parseInt(JOptionPane.showInputDialog("Introdu valoarea nouă pentru minim: ")));
            }
        });
        config2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0));

        // set config3 (Afiseaza rezultatul)
        config3.addActionListener(e -> JOptionPane.showMessageDialog(null, "", "Glumă", JOptionPane.PLAIN_MESSAGE));


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
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            try {
                int answer = Integer.parseInt(answerField.getText());
                if (answer == result) {
                    feedbackLabel.setText("Corect. Bravo!");
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

    public static int getInputForMax() {

        int maxNum = Integer.parseInt(JOptionPane.showInputDialog("Operații până la numărul: "));
        while(maxNum <= 1){
            JOptionPane.showMessageDialog(null,"Numărul trebuie să fie mai mare sau egal cu 2");
            maxNum = Integer.parseInt(JOptionPane.showInputDialog("Operații până la numărul: "));
        }
        return maxNum;
    }


    public static void main(String[] args) {

        MathGame game = new MathGame(getInputForMax());
    }

}

