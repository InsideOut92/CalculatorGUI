import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class WettrennenGUI {
    JFrame frame = new JFrame("Wettrennen");
    JLabel anzahlTeilnehmer = new JLabel("Anzahl der Teilnehmer");
    JTextField teilnehmerTF = new JTextField();
    JLabel anzahlRunden = new JLabel("Anzahl der Runden");
    JTextField rundenTF = new JTextField();
    JButton startButton = new JButton("Starte Rennen");

    JLabel platzierungsLabel = new JLabel("Platzierung");

    JPanel vehiclePanel = new JPanel();

    CheckBoxPanel suv_panel = new CheckBoxPanel("SUV", "/Users/patrick/IdeaProjects/Dateimanipulation/WettrennenManipulieren/pictures/suv.jpeg");
    CheckBoxPanel traktor_panel = new CheckBoxPanel("Traktor", "/Users/patrick/IdeaProjects/Dateimanipulation/WettrennenManipulieren/pictures/traktor.jpeg");
    CheckBoxPanel motorrad_panel = new CheckBoxPanel("Motorrad", "/Users/patrick/IdeaProjects/Dateimanipulation/WettrennenManipulieren/pictures/motorrad.jpeg");
    CheckBoxPanel rennauto_panel = new CheckBoxPanel("Rennauto", "/Users/patrick/IdeaProjects/Dateimanipulation/WettrennenManipulieren/pictures/rennauto.jpeg");

    public WettrennenGUI() {
    }

    private void createAndShowGUI() {
        JPanel panel = new JPanel();

        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setLayout(gbl);
        gbl.setConstraints(panel, gbc);

        vehiclePanel.setLayout(new BoxLayout(vehiclePanel, BoxLayout.X_AXIS));
        vehiclePanel.add(suv_panel);
        vehiclePanel.add(traktor_panel);
        vehiclePanel.add(motorrad_panel);
        vehiclePanel.add(rennauto_panel);

        gbc.gridx = 0;
        gbc.gridy = 0;
        // ermöglicht die Überdeckung von 3 Spalten
        gbc.gridwidth = 3;
        panel.add(vehiclePanel, gbc);

        // setzt für alle nachfolgenden Elemente die Spaltenbreite wieder auf 1
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(anzahlRunden, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(rundenTF, gbc);
        rundenTF.setPreferredSize(new Dimension(100, 30));

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(anzahlTeilnehmer, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(teilnehmerTF, gbc);
        teilnehmerTF.setPreferredSize(new Dimension(100, 30));

        gbc.gridx = 2;
        gbc.gridy = 4;
        panel.add(startButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(platzierungsLabel, gbc);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    public void main() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
                setButtonListener();
            }
        });
    }

    private void setButtonListener() {
        ActionListener startListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rundenZahl = 0;
                try {
                    rundenZahl = Integer.parseInt(rundenTF.getText());
                } catch (Exception exception) {
                    rundenZahl = 6;
                }

                int teilnehmerZahl = 0;
                try {
                    teilnehmerZahl = Integer.parseInt(teilnehmerTF.getText());
                } catch (Exception exception) {
                    teilnehmerZahl = 10;
                }

                ArrayList<String> vehicleList = new ArrayList<>();

                if (traktor_panel.checkBox.isSelected()) {
                    vehicleList.add("Traktor");
                }

                if (suv_panel.checkBox.isSelected()) {
                    vehicleList.add("SUV");
                }

                if (rennauto_panel.checkBox.isSelected()) {
                    vehicleList.add("Rennauto");
                }

                if (motorrad_panel.checkBox.isSelected()) {
                    vehicleList.add("Motorrad");
                }

                if (vehicleList.size() == 0) {
                    Collections.addAll(vehicleList, "Rennauto", "SUV", "Traktor", "Motorrad");
                }

                String[] vehicleArray = vehicleList.toArray(new String[vehicleList.size()]);


                // ToDone: Wettrennen initialisieren
                Wettrennen wettrennen = new Wettrennen();
                wettrennen.initializeGUI(rundenZahl, teilnehmerZahl, vehicleArray);
                // wettrennen.guiInitializer(rundenZahl, teilnehmerZahl);
                /*
                System.out.println("RundenZahl: " + rundenZahl);
                System.out.println("TeilnehmerZahl: " + teilnehmerZahl);
                */

                // ToDone: Wettrennen starten
                wettrennen.raceStart();

                // ToDone: Platzierung aus dem Rennen lesen
                ArrayList<Fahrzeug> top3Liste = wettrennen.top3();

                // ToDone: Anzeigen der Gewinner mit Bild
                displayWinners(top3Liste);

                platzierungsLabel.setText("<html>Platzierung: <br/> " +
                        "1.Platz: " + top3Liste.get(0).getKennzeichen() + " : " + top3Liste.get(0).getRennstrecke() + " <br/> " +
                        "2.Platz: " + top3Liste.get(1).getKennzeichen() + " : " + top3Liste.get(1).getRennstrecke() + " <br/> " +
                        "3.Platz: " + top3Liste.get(2).getKennzeichen() + " : " + top3Liste.get(2).getRennstrecke() + " </html>");

            }
        };
        startButton.addActionListener(startListener);

    }

    public void displayWinners(ArrayList<Fahrzeug> winners) {
        JFrame winnersFrame = new JFrame("Gewinner");
        winnersFrame.setLayout(new GridLayout(3, 1));

        for (int i = 0; i < Math.min(winners.size(), 3); i++) {
            Fahrzeug winner = winners.get(i);
            String vehicleType = winner.getKennzeichen().toLowerCase();

            // Bild laden und skalieren
            String imagePath = "/Users/patrick/IdeaProjects/Dateimanipulation/WettrennenManipulieren/pictures/" + vehicleType.toLowerCase() + ".jpeg";
            ImageIcon icon = new ImageIcon(imagePath);
            Image image = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            icon = new ImageIcon(image);

            // JLabel für das Bild erstellen
            JLabel imageLabel = new JLabel(vehicleType, icon, JLabel.CENTER);

            // Text für die Platzierung erstellen & zum Label hinzufügen
            String labelText = (i + 1) + ". Platz: " + winner.getKennzeichen() + " : " + winner.getRennstrecke();
            JLabel textLabel = new JLabel(labelText);

            // JPanel für Bild+Text
            JPanel winnerPanel = new JPanel();
            winnerPanel.setLayout(new BorderLayout());
            winnerPanel.add(imageLabel, BorderLayout.NORTH);
            winnerPanel.add(textLabel, BorderLayout.SOUTH);

            winnersFrame.add(winnerPanel);
        }

        winnersFrame.setSize(300, 400);
        winnersFrame.setLocationRelativeTo(null);
        winnersFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        winnersFrame.setVisible(true);
    }
}
