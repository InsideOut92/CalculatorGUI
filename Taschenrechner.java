import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class Taschenrechner{

    private LabelTextField zahl1_panel = new LabelTextField("Zahl 1:", "Bitte geben Sie eine Zahl ein");
    private LabelTextField zahl2_panel = new LabelTextField("Zahl 2:", "Bitte geben Sie eine Zahl ein");

    private JButton plus_button = new JButton("+");
    private JButton minus_button = new JButton("-");
    private JButton multiply_button = new JButton("*");
    private JButton divide_button = new JButton("/");
    private JButton rest_button = new JButton("%");
    private JButton ergebnis_button = new JButton("=");

    private JButton Zahl1Einfügen = new JButton("Ergebnis wird zu Zahl 1");

    private JButton Zahl2Einfügen = new JButton("Ergebnis wird zu Zahl 2");

    private LabelTextField ergebnis_panel = new LabelTextField("Ergebnis:", "Das Ergebnis der Rechnung: ");

    private JFrame window = new JFrame("Taschenrechner");
    private JPanel panel = new JPanel();

    // fill: GridBagConstraints.BOTH oder GridBagConstraints.1
    private GBConstraints gbc = new GBConstraints(0, 0, 2, 1);

    private GridBagLayout gbl = new GridBagLayout();

    // JTextArea für die Historie hinzugefügt
    private JTextArea historyTextArea = new JTextArea();
    private StringBuilder history = new StringBuilder();

    public void gui(){
        panel.setLayout(gbl);
        gbl.setConstraints(panel, gbc);

        gbc = new GBConstraints(0, 0, 2, 1);
        panel.add(zahl1_panel, gbc);

        gbc = new GBConstraints(0, 1, 2, 1);
        panel.add(zahl2_panel, gbc);

        gbc = new GBConstraints(0, 2, 1, 1);
        panel.add(plus_button, gbc);

        gbc = new GBConstraints(1, 2, 1, 1);
        panel.add(minus_button, gbc);

        gbc = new GBConstraints(0, 3);
        panel.add(multiply_button, gbc);

        gbc = new GBConstraints(1, 3, 1, 1);
        panel.add(divide_button, gbc);

        gbc = new GBConstraints(0, 4, 1, 1);
        panel.add(rest_button, gbc);

        // Neue Buttons für das Ersetzen der Zahlen durch das vorherige Ergebnis
        gbc = new GBConstraints(6, 0, 1, 1);
        panel.add(Zahl1Einfügen, gbc);

        gbc = new GBConstraints(6, 1, 1, 1);
        panel.add(Zahl2Einfügen, gbc);


        // gbc = new GBConstraints(0, 5, 2, 1);
        // panel.add(ergebnis_button, gbc);

        // JTextArea zur GUI hinzufügen
        gbc = new GBConstraints(0, 7, 2, 1);
        panel.add(historyTextArea, gbc);
        // So wird die historyTextArea nicht editierbar gemacht
        historyTextArea.setEditable(false);

        // breiter gemacht, hat aber nicht funktioniert -> zurückgesetzt
        gbc = new GBConstraints(0, 6, 2, 1);
        panel.add(ergebnis_panel, gbc);

        window.add(panel);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        window.setSize(800, 600);
        window.setVisible(true);

    }

    public void setButtonListeners(){
        ActionListener plusListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                double result = zahl1() + zahl2();
                result_text(result);
                addToHistory(zahl1(), "+", zahl2(), result);
            }
        };
        plus_button.addActionListener(plusListener);


        ActionListener minusListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                double result = zahl1() - zahl2();
                result_text(result);
                addToHistory(zahl1(), "-", zahl2(), result);
            }
        };
        minus_button.addActionListener(minusListener);

        // ActionListener für Multiplikation
        ActionListener multiplyListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                double result = zahl1() * zahl2();
                result_text(result);
                addToHistory(zahl1(), "*", zahl2(), result);
            }
        };
        multiply_button.addActionListener(multiplyListener);

        // ActionListener für Division
        ActionListener divideListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if (zahl2() != 0) {
                    double result = zahl1() / zahl2();
                    result_text(result);
                    addToHistory(zahl1(), "/", zahl2(), result);
                } else {
                    // Division durch 0 vermeiden
                    ergebnis_panel.setTextFieldText("Ungültige Operation");
                }
            }
        };
        divide_button.addActionListener(divideListener);

        // ActionListener für Modulo
        ActionListener restListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if (zahl2() != 0) {
                    double result = zahl1() % zahl2();
                    result_text(result);
                    addToHistory(zahl1(), "%", zahl2(), result);
                } else {
                    // Modulo durch 0 vermeiden
                    ergebnis_panel.setTextFieldText("Ungültige Operation");
                }
            }
        };
        rest_button.addActionListener(restListener);

        // ActionListener für Ergebnis
        ActionListener ergebnisListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
            }
        };
        ergebnis_button.addActionListener(ergebnisListener);

        // ActionListener für Ergebnis = Zahl1
        ActionListener ErgebnisZuZahl1 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                zahl1_panel.setTextFieldText(ergebnis_panel.getTextFieldText());
            }
        };
        Zahl1Einfügen.addActionListener(ErgebnisZuZahl1);

        // ActionListener für Ergebnis = Zahl2
        ActionListener ErgebnisZuZahl2 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                zahl2_panel.setTextFieldText(ergebnis_panel.getTextFieldText());
            }
        };
        Zahl2Einfügen.addActionListener(ErgebnisZuZahl2);
    }

    private void result_text(double zahl){
        ergebnis_panel.setTextFieldText(String.format(Locale.GERMAN, "%.4f", zahl));
    }

    private double zahl1(){
        return allowNumbers(zahl1_panel.getTextFieldText());
    }

    private double zahl2(){
        return allowNumbers(zahl2_panel.getTextFieldText());
    }

    private double allowNumbers(String text){
        // Das musste ergänzt werden, dadurch werden Kommas durch Punkte ersetzt
        text = text.replace(',', '.');

        String clean = "";

        for (int i = 0; i < text.length(); i++){
            // Ziffern und Punkte akzeptieren lassen
            if ((text.charAt(i) >= '0' && text.charAt(i) <= '9') || text.charAt(i) == '.'){
                clean += text.charAt(i);
            }
        }
        if (clean.equals("")){
            clean = "0";
        }
        return Double.parseDouble(clean);
    }

    private void addToHistory(double operand1, String operator, double operand2, double result){
        String historyEntry = String.format(Locale.GERMAN, "%.4f %s %.4f = %.4f%n", operand1, operator, operand2, result);
        history.append(historyEntry);
        // Hier wird die gesamte Historie gesetzt
        historyTextArea.setText(history.toString());
        // Hier wird das Ergebnis angezeigt
        ergebnis_panel.setTextFieldText(String.format(Locale.GERMAN, "%.4f", result));
    }
}