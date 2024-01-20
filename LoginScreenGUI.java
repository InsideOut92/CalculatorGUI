import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginScreenGUI implements ActionListener{

    private static JLabel userLabel;
    private static JTextField userText;
    private static JLabel passwordLabel;
    private static JPasswordField passwordText;
    private static JButton button;
    private static JLabel success;

    public static void main(String[] args){

        // Hauptpanel mit GridLayout erstellen (3 Zeilen, 2 Spalten)
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JFrame frame = new JFrame();
        frame.setSize(500, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        // Benutzer-Komponenten
        userLabel = new JLabel("Benutzer:");
        userText = new JTextField(20);

        // Passwort-Komponenten
        passwordLabel = new JLabel("Passwort:");
        passwordText = new JPasswordField(20);

        // Hinzufügen von Benutzer- und Passwort-Komponenten zum Panel
        panel.add(userLabel);
        panel.add(userText);
        panel.add(passwordLabel);
        panel.add(passwordText);

        button = new JButton("Login");
        button.addActionListener(new LoginScreenGUI());
        panel.add(button);

        success = new JLabel("");
        panel.add(success);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){

        // Event-Handler für den Login-Button
        System.out.println("Button gedrückt");

        // Benutzername und Passwort aus den Textfeldern abrufen
        String user = userText.getText();
        String password = passwordText.getText();
        System.out.println(user + ", " + password);

        // Einfache Überprüfung für Benutzername und Passwort (Demo-Zwecke)
        if (user.equals("Patrick") && password.equals("SicheresPasswort123")){
            success.setText("Login erfolgreich!");
        }
    }
}
