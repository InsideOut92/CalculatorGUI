/*      Ein Programm zum Umrechnen von Dezimalzahlen in:
        Binär-, Oktal- und Hexadezimalzahlen
        Es soll einen Knopf zum Berechnen geben und einen um die Eingabe wieder zurückzusetzen.
        Zudem MUSS das Programm eine grafische Benutzeroberfläche (GUI) besitzen.
        Zudem soll die praktische Erstellung eines "GridBagLayouts" geübt werden.
        EXTRA: Es werden nun "Lambda-Ausdrücke" verwendet :)
 */

import javax.swing.*;
import java.awt.*;

public class binaryconversionLambda extends JFrame{
    private JLabel LD, LB, LO, LH;
    private JTextField TD, TB, TO, TH;
    private JButton calculate, clear;
    private JPanel p;

    public binaryconversionLambda(){
        LD = new JLabel("Dezimalzahl:");
        LB = new JLabel("Binärzahl:");
        LO = new JLabel("Oktalzahl");
        LH = new JLabel("Hexadezimalzahl");

        TD = new JTextField(20);
        TB = new JTextField(20);
        TO = new JTextField(20);
        TH = new JTextField(20);

        calculate = new JButton("Berechnen");
        clear = new JButton("Clear");

        p = new JPanel();
        p.setLayout(new GridLayout(5, 2));
        p.add(LD);
        p.add(TD);
        p.add(LB);
        p.add(TB);
        p.add(LO);
        p.add(TO);
        p.add(LH);
        p.add(TH);
        p.add(calculate);
        p.add(clear);

        setLayout(new FlowLayout());
        add(p);
        setTitle("Dezimalumwandlung");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // ActionListener für die Knöpfe mit Lambda-Ausdrücken hinzufügen
        calculate.addActionListener(e ->{
            try{
                int n = Integer.parseInt(TD.getText());
                TB.setText(Integer.toBinaryString(n));
                TO.setText(Integer.toOctalString(n));
                TH.setText(Integer.toHexString(n));

                // Festlegen der Textfelder als nicht editierbar
                TB.setEditable(false);
                TO.setEditable(false);
                TH.setEditable(false);
            } catch (NumberFormatException ex){
                // Handle ungültige Eingabe, z.B. wenn keine Zahl eingegeben wurde
                JOptionPane.showMessageDialog(this, "Ungültige Eingabe. Bitte geben Sie eine Dezimalzahl ein.");
            }
        });

        clear.addActionListener(e ->{
            // Löscht den Text aus den Eingabefeldern und setzt die Textfelder als editierbar zurück
            TD.setText("");
            TB.setText("");
            TH.setText("");
            TO.setText("");

            // Setzt die Textfelder als editierbar zurück
            TB.setEditable(true);
            TO.setEditable(true);
            TH.setEditable(true);
        });
    }

    public static void main(String[] args){
        // Startet die Anwendung
        SwingUtilities.invokeLater(binaryconversionLambda::new);
    }
}
