import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MathGame extends JFrame implements ActionListener {
    private JTextField answerField;
    private JLabel num1Label, num2Label, resultLabel, feedbackLabel;
    private JButton submitButton, resetButton;
    private int maxNum, num1, num2, result;

    public MathGame(int maxNum) {
        this.maxNum = maxNum;
        num1 = (int) (Math.random() * (maxNum + 1));
        num2 = (int) (Math.random() * (maxNum + 1));
        result = num1 + num2;

        setTitle("MaHAHAte");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(25, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel gamePanel = new JPanel(new GridLayout(4, 3, 15, 15));
        gamePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

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

        answerField = new JTextField();
        answerField.setFont(answerField.getFont().deriveFont(24f));
// testing       answerField.addActionListener(KeyEvent.VK_ENTER == 0 ? submitButton : null);
        gamePanel.add(answerField);

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

                    num1 = (int) (Math.random() * (maxNum + 1));
                    num2 = (int) (Math.random() * (maxNum + 1));
                    result = num1 + num2;
                    num1Label.setText("" + num1);
                    num2Label.setText("" + num2);
                    answerField.setText("");
//                    resultLabel.setText("");
                } else {
                    feedbackLabel.setText("Mai încearcă, răspunsul nu este corect.");
                    feedbackLabel.setForeground(Color.BLUE);
                }
            } catch (NumberFormatException ex) {
                feedbackLabel.setText("Răspunsul nu este valid. Încearcă să introduci un număr");
                feedbackLabel.setForeground(Color.RED);
            }
        } else if (e.getSource() == resetButton) {
            num1 = (int) (Math.random() * (maxNum + 1));
            num2 = (int) (Math.random() * (maxNum + 1));
            result = num1 + num2;
            num1Label.setText("" + num1);
            num2Label.setText("" + num2);
            answerField.setText("");
//            resultLabel.setText("");
            feedbackLabel.setText("");
            feedbackLabel.setForeground(Color.BLACK);
        }
    }

    public static void main(String[] args) {
        int maxNum = Integer.parseInt(JOptionPane.showInputDialog("Numărul maxim generat:"));
        MathGame game = new MathGame(maxNum);
    }

}

