package app.math;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class UI extends JFrame {

    Color black = new Color(245, 251, 239);
    Color orange = new Color(80, 61, 66);
    Color white = new Color(116, 139, 117);
    Color grey = new Color(146, 173, 148);
    Font font = new Font(Font.MONOSPACED, Font.BOLD, 22);

    private JLabel num1Label, num2Label, feedbackLabel, scoreLabel, tableScore;
    private JButton submitButton, resetButton;
    private JTextField answerLabel;
    private AboutAppText about = new AboutAppText();
    private RandomGenerator random = new RandomGenerator();
    private String player = "";
    private int minNum, maxNum;
    protected int correctScore, incorrectScore;

    UI() throws IOException {
        this.setTitle("MaHAHAte"); //set title
        this.setSize(500, 500); //set size
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE); //exit on close
        this.setLayout(null); //make layout manager by bounds
        this.setLocationRelativeTo(null);

        //set icon
        ImageIcon icon = new ImageIcon("src/icon.png");
        this.setIconImage(icon.getImage());

        //set background
        this.getContentPane().setBackground(black);

        //scoreTable
        scoreLabel = new JLabel("Scor:");
        scoreLabel.setFont(font.deriveFont(18F));
        scoreLabel.setForeground(orange);
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        scoreLabel.setVerticalAlignment(JLabel.CENTER);
        scoreLabel.setBounds(200, 5, 100, 20);
        scoreLabel.setPreferredSize(new Dimension(500,30));
        this.add(scoreLabel, BorderLayout.PAGE_START);

        tableScore = new JLabel("Corect: " + correctScore +  "                  Gresit: " + incorrectScore);
        tableScore.setFont(font.deriveFont(13F));
        tableScore.setForeground(orange);
        tableScore.setHorizontalAlignment(JLabel.CENTER);
        tableScore.setVerticalAlignment(JLabel.CENTER);
        tableScore.setBounds(50, 25, 400, 20);
        this.add(tableScore, BorderLayout.BEFORE_LINE_BEGINS);

    //set labels for numbers and operands
        //num1
        num1Label = new JLabel("" + random.generate1());
        num1Label.setFont(font.deriveFont(55F));
        num1Label.setForeground(orange);
        num1Label.setHorizontalAlignment(JLabel.CENTER);
        num1Label.setVerticalAlignment(JLabel.CENTER);
        num1Label.setBounds(50,100,100,50);
        this.add(num1Label, BorderLayout.LINE_START);

        //+
        JLabel plus = new JLabel("+");
        plus.setFont(font);
        plus.setForeground(orange);
        plus.setHorizontalAlignment(JLabel.CENTER);
        plus.setVerticalAlignment(JLabel.CENTER);
        plus.setBounds(200, 100, 100, 50);
        this.add(plus, BorderLayout.CENTER);

        //num2
        num2Label = new JLabel("" + random.generate2());
        num2Label.setFont(font.deriveFont(55F));
        num2Label.setForeground(orange);
        num2Label.setHorizontalAlignment(JLabel.CENTER);
        num2Label.setVerticalAlignment(JLabel.CENTER);
        num2Label.setBounds(350, 100, 100, 50);
        this.add(num2Label, BorderLayout.LINE_END);

//        //=
//        JLabel equal = new JLabel();
//        equal.setFont(font);
//        equal.setForeground(orange);
//        equal.setHorizontalTextPosition(JLabel.CENTER);
//        equal.setVerticalAlignment(JLabel.CENTER);
//        equal.setBounds(244, 155, 100, 50);
//        this.add(equal);

        //answer box
        answerLabel = new JTextField();
        answerLabel.requestFocus();
        answerLabel.setFont(font.deriveFont(40F));
        answerLabel.setHorizontalAlignment(JLabel.CENTER);
        answerLabel.setBounds(170, 200, 160, 50);
        answerLabel.setBackground(grey);
        answerLabel.setForeground(orange);
        answerLabel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    actionPerformed(new ActionEvent(submitButton, ActionEvent.ACTION_PERFORMED, null));
                }
            }
        });
        answerLabel.setBorder(BorderFactory.createEmptyBorder());
        this.add(answerLabel);

    //set menuBar
        JMenuBar menuBar = new JMenuBar();
        JMenu config = new JMenu("Configurare");
        menuBar.add(config);
        JMenu aboutApp = new JMenu("Despre aplicație");
        menuBar.add(aboutApp);

        //Configurare menu
        //minNum
        JMenuItem configMin = new JMenuItem("Numărul minim  ");
        configMin.addActionListener(e -> {
            int minInput = Integer.parseInt(JOptionPane.showInputDialog("Operațiile sunt generate de la "
                    + random.getMinNum() + " la " + random.getMaxNum() + ".\n\nIntrodu valoarea nouă pentru minim: "));
            while (minInput < 0 || minInput > random.getMaxNum()) {
                JOptionPane.showMessageDialog(null, "Numărul trebuie să fie pozitiv și mai mic decât "
                        + random.getMaxNum());
                minInput = (Integer.parseInt(JOptionPane.showInputDialog("Introdu valoarea nouă pentru minim: ")));
            }
            random.setMinNum(minInput);
        });
        configMin.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0));
        config.add(configMin);

        //maxNum
        JMenuItem configMax = new JMenuItem("Numărul maxim  ");
        configMax.addActionListener(e -> {
            int maxInput = Integer.parseInt(JOptionPane.showInputDialog("Operațiile sunt generate de la " + random.getMinNum() + " la " + random.getMaxNum() +
                    ".\n\nIntrodu valoarea nouă pentru maxim: "));
            while (maxInput < 2 || maxInput <= random.getMinNum()) {
                JOptionPane.showMessageDialog(null, "Numărul trebuie să fie mai mare sau egal cu 2");
                maxInput = (Integer.parseInt(JOptionPane.showInputDialog("Introdu valoarea nouă pentru maxim: ")));
            }
            random.setMaxNum(maxInput);
        });
        configMax.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0));
        config.add(configMax);

        //playerName
        JMenuItem configPlayer = new JMenuItem("Setează nume");
        configPlayer.addActionListener(e -> {
            setPlayer(JOptionPane.showInputDialog("Setează numele jucatorului astfel incat feedback-ul \n" +
                    "sa fie mai personal.\n\n" + "Introdu numele:"));


        });
        config.add(configPlayer);

        //joke
        JMenuItem config3 = new JMenuItem("Afișează rezultatul");
        config3.addActionListener(e -> JOptionPane.showMessageDialog(null, "", "Glumă", JOptionPane.PLAIN_MESSAGE));
        config.add(config3);

        //Despre aplicatie menu
        JMenuItem despre = new JMenuItem("Despre ...");
        despre.addActionListener(e -> {
            JTextArea textArea = new JTextArea(about.aboutApp());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(600, 500));
            JOptionPane.showMessageDialog(null, scrollPane, "Despre ...", JOptionPane.INFORMATION_MESSAGE);
        });
        aboutApp.add(despre);

        this.setJMenuBar(menuBar);

    //set feedback box
        feedbackLabel = new JLabel();
        feedbackLabel.setForeground(orange);
        feedbackLabel.setFont(font.deriveFont(14F));
        feedbackLabel.setVerticalAlignment(JLabel.CENTER);
        feedbackLabel.setHorizontalAlignment(JLabel.CENTER);
        feedbackLabel.setHorizontalTextPosition(JLabel.CENTER);
        feedbackLabel.setVerticalTextPosition(JLabel.CENTER);
        feedbackLabel.setBounds(50, 280, 400, 45);
        this.add(feedbackLabel, BorderLayout.AFTER_LINE_ENDS);

    //set buttons
        submitButton = new JButton("VERIFICA");
        configButton(submitButton);
        submitButton.addActionListener(this::actionPerformed);
        submitButton.setBounds(86,380, 120,35);
        submitButton.setBorderPainted(false);
        this.add(submitButton);

        resetButton = new JButton("RESET");
        configButton(resetButton);
        resetButton.addActionListener(this::actionPerformed);
        resetButton.setBounds(292, 380, 120,35);
        resetButton.setBorderPainted(false);
        this.add(resetButton, BorderLayout.SOUTH);

        this.setVisible(true); //set visible
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == getSubmitButton()) {
            try {
                int answerUI = Integer.parseInt(getAnswerLabel().getText());
                System.out.println(answerUI + " = " + random.getResult() + " num1 = " + random.getNum1() +
                        " num 2 = " + random.getNum2());
                if (answerUI == random.getResult()) {
                    cleanWindow();
                    feedbackLabel.setText("Corect. " + (getPlayer().isEmpty() ? "Bravo" : "Bravo ") +
                            getPlayer() + "!");
                    while(Integer.parseInt(num1Label.getText()) == random.getNum1() &&
                            Integer.parseInt(num2Label.getText()) == random.getNum2()) {
                        random.generate1();
                        random.generate2();
                    }
                    num1Label.setText("" + random.getNum1());
                    num2Label.setText("" + random.getNum2());
                    getAnswerLabel().setText("");
                    correctScore++;
                    tableScore.setText("Corect: " + correctScore +  "                  Gresit: " + incorrectScore);
                } else {
                    feedbackLabel.setText("Mai încearcă, răspunsul nu este corect.");
                    answerLabel.setText("");
                    incorrectScore++;
                    tableScore.setText("Corect: " + correctScore +  "                  Gresit: " + incorrectScore);
                }
            } catch (NumberFormatException ex) {
                feedbackLabel.setText("<html>Răspunsul nu este valid. <br>   Încearcă să introduci un număr</html>");
            }
        } else if (e.getSource() == resetButton) {
            random.generate1();
            random.generate2();
            cleanWindow();
        }
    }

    public void cleanWindow() {

        num1Label.setText("" + random.getNum1());
        num2Label.setText("" + random.getNum2());
        answerLabel.setText("");
        feedbackLabel.setText("");
    }
    public JButton configButton(JButton button) {

        button.setForeground(orange);
        button.setBackground(grey);
        button.setFont(font.deriveFont(12F));

        return button;
    }

    public JLabel getNum1Label() {
        return num1Label;
    }

    public JLabel getNum2Label() {
        return num2Label;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public JButton getResetButton() {
        return resetButton;
    }

    public JLabel getFeedbackLabel() {
        return feedbackLabel;
    }

    public JTextField getAnswerLabel() {
        return answerLabel;
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

    public int getMinNum() {
        return random.getMinNum();
    }

    public int getMaxNum() {
        return random.getMaxNum();
    }
}
