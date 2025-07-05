import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LastPage extends JFrame implements ActionListener {
    JButton continueButton, exitButton;
    String userEmail;

    public LastPage(String userEmail) {
        this.userEmail = userEmail;

        setTitle("Thank You");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // gradient background
        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                Color c1 = new Color(255, 204, 204);
                Color c2 = new Color(204, 229, 255);
                g2.setPaint(new GradientPaint(0, 0, c1, 0, getHeight(), c2));
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(new BorderLayout());

        JLabel thankLabel = new JLabel("Thanks for visiting!", SwingConstants.CENTER);
        thankLabel.setFont(new Font("Arial", Font.BOLD, 24));
        thankLabel.setForeground(Color.DARK_GRAY);

        // buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // transparent panel to see the gradient

        continueButton = new JButton("Continue Shopping");
        continueButton.setFont(new Font("Arial", Font.BOLD, 18));
        continueButton.addActionListener(this);

        exitButton = new JButton("Exit to Home");
        exitButton.setFont(new Font("Arial", Font.BOLD, 18));
        exitButton.addActionListener(this);

        buttonPanel.add(continueButton);
        buttonPanel.add(exitButton);

        panel.add(thankLabel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == continueButton) {
            new SecondPage(userEmail); // go back to SecondPage
            dispose();
        } else if (e.getSource() == exitButton) {
            new first(); // go to FirstPage
            dispose();
        }
    }
}
