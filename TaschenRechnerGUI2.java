// Anordnung ist nicht richtig

import javax.swing.*;
import java.awt.*;
import java.util.function.BinaryOperator;

public class TaschenRechnerGUI2 {
    private JFrame frame;
    private JTextField textField1, textField2, resultField;
    private String selectedOperator;

    public TaschenRechnerGUI2() {
        frame = new JFrame("Einfacher Taschenrechner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textField1 = new JTextField(10);
        textField2 = new JTextField(10);
        resultField = new JTextField(20);
        resultField.setEditable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Erste Zeile

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(textField1, gbc);

        gbc.gridx = 1;
        panel.add(createOperatorPanel(), gbc);

        gbc.gridx = 2;
        panel.add(textField2, gbc);

        // Zweite Zeile

        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        // Abstand nach oben
        gbc.insets = new Insets(10, 0, 0, 0);
        panel.add(createCalculateButton(), gbc);

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(BorderLayout.NORTH, panel);
        frame.getContentPane().add(BorderLayout.SOUTH, resultField);

        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    private JPanel createOperatorPanel() {
        JPanel operatorPanel = new JPanel(new GridLayout(4, 1));

        JButton addButton = createOperatorButton("+");
        JButton subtractButton = createOperatorButton("-");
        JButton multiplyButton = createOperatorButton("*");
        JButton divideButton = createOperatorButton("/");

        operatorPanel.add(addButton);
        operatorPanel.add(subtractButton);
        operatorPanel.add(multiplyButton);
        operatorPanel.add(divideButton);

        return operatorPanel;
    }

    private JButton createOperatorButton(String operator) {
        JButton button = new JButton(operator);
        button.addActionListener(e -> setOperator(operator));
        return button;
    }

    private void setOperator(String operator) {
        selectedOperator = operator;
    }

    private JButton createCalculateButton() {
        JButton calculateButton = new JButton("Berechnen");
        calculateButton.addActionListener(e -> calculate());
        return calculateButton;
    }

    private void calculate() {
        try {
            double num1 = Double.parseDouble(textField1.getText());
            double num2 = Double.parseDouble(textField2.getText());

            BinaryOperator<Double> operation = switch (selectedOperator) {
                case "+" -> Double::sum;
                case "-" -> (a, b) -> a - b;
                case "*" -> (a, b) -> a * b;
                case "/" -> (a, b) -> a / b;
                default -> throw new IllegalArgumentException("Ungültiger Operator");
            };

            double result = operation.apply(num1, num2);
            resultField.setText("Ergebnis: " + String.valueOf(result));
        } catch (IllegalArgumentException ex) {
            resultField.setText("Ungültige Eingabe");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TaschenRechnerGUI2::new);
    }
}
