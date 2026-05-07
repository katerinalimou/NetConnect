import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.*;

public class UserPageFrame extends JFrame{

	// Step 1
    private JPanel mainPanel = new JPanel();
    
    // Step 2
    private JTextArea nameArea = new JTextArea(1, 12);
    private JTextArea emailArea = new JTextArea(1, 20);
    private JButton backButton = new JButton("Back to Login Screen");

    private JTextArea postField = new JTextArea(3, 45);
    private JButton postButton = new JButton("Post");

    private JTextArea postsArea = new JTextArea(10, 35);
    private JTextArea suggestedArea = new JTextArea(6, 9);

    private User user;
    private JFrame loginFrame;

    // Formatter για εμφάνιση ημερομηνίας και ώρας post
    private SimpleDateFormat formatter =
            new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public UserPageFrame(User user, JFrame loginFrame) {
        this.user = user;
        this.loginFrame = loginFrame;
        
        postField.setLineWrap(true);
        postField.setWrapStyleWord(true);

        JScrollPane postScroll = new JScrollPane(postField);

 
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Name,Email, BackButton
        nameArea.setEditable(false);
        emailArea.setEditable(false);

        nameArea.setText(user.getName());
        emailArea.setText(user.getEmail());

        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        row1.add(nameArea);
        row1.add(emailArea);
        row1.add(backButton);

        // PostField & Post Button
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        row2.add(postScroll);
        row2.add(postButton);

        // Posts 
        JLabel postsLabel = new JLabel("Recent Posts by Friends");

        postsArea.setEditable(false);
        JScrollPane postsScroll = new JScrollPane(postsArea);
        postsScroll.setPreferredSize(new Dimension(420, 200));

        JPanel postsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        postsPanel.add(postsLabel);
        postsPanel.add(postsScroll);

        //Suggested Friends 
        JLabel suggestedLabel = new JLabel("Suggested Friends");

        suggestedArea.setEditable(false);
        suggestedArea.setEditable(false);

        
        JPanel suggestedPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        suggestedPanel.add(suggestedLabel);
        suggestedPanel.add(suggestedArea);

        // Προσθήκη στο mainPanel 
        mainPanel.add(row1);
        mainPanel.add(row2);
        mainPanel.add(postsPanel);
        mainPanel.add(suggestedPanel);

        // Step 3
        this.setContentPane(mainPanel);

        // Step 4
        postButton.addActionListener(new PostListener());
        backButton.addActionListener(new BackListener());

        refreshPosts();
        refreshSuggested();

        this.setTitle("Σελίδα Χρήστη");
        this.setSize(700, 480);
        this.setResizable(false);
        this.setVisible(true);
    }

    // Listener για Post
    class PostListener implements ActionListener {
    	@Override
    	public void actionPerformed(ActionEvent e) {
    		user.addPost(postField.getText());
    	    postField.setText("");
    	    refreshPosts();
    	}
    }

    // Listener επιστροφής
    class BackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            loginFrame.setVisible(true);
        }
    }

    // Posts με ημερομηνία & ώρα
    private void refreshPosts() {
        postsArea.setText("");
        for (Post p : user.getFriendsAndOwnPosts()) {
            postsArea.append(
                p.getAuthor().getName() + " - " +
                formatter.format(p.getTimestamp()) + "\n"
            );
            postsArea.append(p.getText() + "\n");
        }
    }

    // Suggested friends
    private void refreshSuggested() {
        suggestedArea.setText("");
        for (User u : user.getSuggestedFriends()) {
            suggestedArea.append(u.getName() + "\n");
        }
    }
}
