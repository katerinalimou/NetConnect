import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;

public class VirusFrame extends JFrame{
	 public VirusFrame(User user, JFrame loginFrame) {

	        setTitle("Πιθανή Μετάδοση Ιού");
	        setSize(500, 450);
	        setLayout(new BorderLayout());

	      
	        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

	        // Πληροφορίες που θέλω να εμφανίζονται
	        JPanel boxPanel = new JPanel();
	        boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
	        boxPanel.setPreferredSize(new Dimension(350, 300));

	        // Περιοχή κειμένου για τα ονόματα χρηστών
	        JTextArea area = new JTextArea();
	        area.setEditable(false);

	        
	        area.append("**********************************************\n");
	        area.append(user.getName() + " has been infected.\n");
	        area.append("The following users have to be tested:\n");
	        area.append("**********************************************\n");

	        // Εμφάνιση χρηστών που πρέπει να εξεταστούν
	        for (User u : user.getVirusTransmissionList()) {
	            area.append(u.getName() + "\n");
	        }
	        
	        
	        // Scroll ώστε να χωράνε όλα τα ονόματα
	        JScrollPane scrollPane = new JScrollPane(area);
	        scrollPane.setPreferredSize(new Dimension(350, 220));

	        
	        // Κουμπί επιστροφής στο login screen
	        JButton backBtn = new JButton("Back to Login Screen");
	        backBtn.setAlignmentX(JComponent.CENTER_ALIGNMENT);
	        backBtn.addActionListener(e -> {
	            dispose();
	            loginFrame.setVisible(true);
	        });

	        // Προσθήκη στοιχείων 
	        boxPanel.add(scrollPane);
	        boxPanel.add(backBtn);

	        //Κεντρικη στοιχιση
	        centerPanel.add(boxPanel);
	        add(centerPanel, BorderLayout.CENTER);

	        setLocationRelativeTo(null);
	        setVisible(true);
	    }

}
