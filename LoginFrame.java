import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginFrame extends JFrame {
	// Step 1 
    private JPanel panel = new JPanel();

    // Step 2 
    private JTextField nameField = new JTextField("Please enter your name");
    private JButton enterUserPageButton = new JButton("Enter User Page");
    private JButton showVirusButton = new JButton("Show Potential Infections");

    private ArrayList<User> users;

    public LoginFrame(ArrayList<User> users) {
        this.users = users;

        // Step 3 
        
        panel.add(nameField);
        panel.add(enterUserPageButton);
        panel.add(showVirusButton);

        // Step 4
        this.setContentPane(panel);

        enterUserPageButton.addActionListener(new EnterUserListener());
        showVirusButton.addActionListener(new VirusListener());

        this.setSize(350, 200);
        this.setTitle("Είσοδος χρήστη");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    // Inner Listener για είσοδο στη σελίδα χρήστη
    class EnterUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            User u = findUser(nameField.getText());
            if (u == null) {
                JOptionPane.showMessageDialog(null, "User not found");
            } else {
                new UserPageFrame(u, LoginFrame.this);
                setVisible(false);
            }
        }
    }

    // Inner Listener για πιθανή μετάδοση ιού 
    class VirusListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            User u = findUser(nameField.getText());
            if (u == null) {
                JOptionPane.showMessageDialog(null, "User not found");
            } else {
                new VirusFrame(u, LoginFrame.this);
                setVisible(false);
            }
        }
    }

    // Βοηθητική μέθοδος εύρεσης χρήστη
    private User findUser(String name) {
        for (User u : users)
            if (u.getName().equalsIgnoreCase(name))
                return u;
        return null;
    }
}
