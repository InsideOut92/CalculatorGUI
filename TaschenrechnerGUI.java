import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaschenrechnerGUI {
    private JFrame frame;
    private JTextField inputField;
    private JTextField secondInputField;
    private JButton addButton, subtractButton, multiplyButton, divideButton, calculateButton;
    private double firstOperand;
    private String operator;

    public TaschenrechnerGUI() {
        frame = new JFrame("Taschenrechner");
        inputField = new JTextField();
        secondInputField = new JTextField();
        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multiplyButton = new JButton("*");
        divideButton = new JButton("/");
        calculateButton = new JButton("Berechnen");

        createAndShowGUI();
        setButtonListeners();
    }

    private void createAndShowGUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        inputField.setHorizontalAlignment(JTextField.RIGHT);
        panel.add(inputField);

        secondInputField.setHorizontalAlignment(JTextField.RIGHT);
        secondInputField.setEditable(false);
        panel.add(secondInputField);

        panel.add(addButton);
        panel.add(subtractButton);
        panel.add(multiplyButton);
        panel.add(divideButton);
        panel.add(calculateButton);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    private void setButtonListeners() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleOperatorButtonClick("+");
            }
        });

        subtractButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleOperatorButtonClick("-");
            }
        });

        multiplyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleOperatorButtonClick("*");
            }
        });

        divideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleOperatorButtonClick("/");
            }
        });

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateResult();
            }
        });
    }

    private void handleOperatorButtonClick(String selectedOperator) {
        if (!inputField.getText().isEmpty()) {
            firstOperand = Double.parseDouble(inputField.getText());
            operator = selectedOperator;
            secondInputField.setText(firstOperand + " " + operator + " ");
            inputField.setText("");
        }
    }

    private void calculateResult() {
        if (!inputField.getText().isEmpty()) {
            double secondOperand = Double.parseDouble(inputField.getText());
            secondInputField.setText(secondInputField.getText() + secondOperand);
            double result = performCalculation();
            inputField.setText(String.valueOf(result));
            secondInputField.setText(secondInputField.getText() + " = " + result);
        }
    }

    private double performCalculation() {
        switch (operator) {
            case "+":
                return firstOperand + Double.parseDouble(inputField.getText());
            case "-":
                return firstOperand - Double.parseDouble(inputField.getText());
            case "*":
                return firstOperand * Double.parseDouble(inputField.getText());
            case "/":
                double divisor = Double.parseDouble(inputField.getText());
                if (divisor != 0) {
                    return firstOperand / divisor;
                } else {
                    JOptionPane.showMessageDialog(frame, "Division durch Null nicht erlaubt!");
                }
                break;
        }
        return 0; // Fallback-Wert, falls etwas schief geht
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TaschenrechnerGUI();
            }
        });
    }
}
