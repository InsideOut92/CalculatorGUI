/*      Ein Programm zum Umrechnen von Dezimalzahlen in:
        Binär-, Oktal- und Hexadezimalzahlen
        Es soll einen Knopf zum Berechnen geben und einen um die Eingabe wieder zurückzusetzen.
        Zudem MUSS das Programm eine grafische Benutzeroberfläche (GUI) besitzen.
        Zudem soll die praktische Erstellung eines "GridBagLayouts" geübt werden.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class binaryconversion extends JFrame implements ActionListener{
    // Labels für die Binär-, Dezimal, Oktal- und Hexadezimalzahl
    JLabel LD, LB, LO, LH;
    // Textfeld in dem Binär-, Dezimal, Oktal- und Hexadezimalzahl ausgegeben werden
    JTextField TD, TB, TO, TH;
    // Knöpfe um die Ausgabe zu erhalten bzw. das Programm zurückzusetzen
    JButton calculate, clear;
    JPanel p;

    public binaryconversion(){

        // Initialisierung der deklarierten Objektvariablen
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
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // ActionListener für die Knöpfe hinzufügen
        calculate.addActionListener(this);
        clear.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae){
        JButton btn = (JButton) ae.getSource();

        // Prüfen welcher Knopf gedrückt wurde
        if(btn == calculate){
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
        }

        // Löscht den Text aus den Eingabefeldern und setzt die Textfelder als editierbar zurück
        if(btn == clear){
            TD.setText("");
            TB.setText("");
            TH.setText("");
            TO.setText("");

            // Setzt die Textfelder als editierbar zurück
            TB.setEditable(true);
            TO.setEditable(true);
            TH.setEditable(true);
        }
    }

    public static void main(String[] args){
        // Startet die Anwendung
        SwingUtilities.invokeLater(binaryconversion::new);
    }
}
