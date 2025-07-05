import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class first extends JFrame implements ActionListener {
    JButton shopButton;

    first() {
        setTitle("Electronics Shop");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel gradientPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int w = getWidth(), h = getHeight();
                Color c1 = new Color(0, 102, 153);
                Color c2 = new Color(0, 153, 204);
                g2d.setPaint(new GradientPaint(0, 0, c1, 0, h, c2));
                g2d.fillRect(0, 0, w, h);
            }
        };
        gradientPanel.setLayout(new BorderLayout());

        JLabel welcome = new JLabel("Welcome to our Shop of Electronics", SwingConstants.CENTER);
        welcome.setFont(new Font("Verdana", Font.BOLD, 28));
        welcome.setForeground(Color.WHITE);
        gradientPanel.add(welcome, BorderLayout.NORTH);

        ImageIcon icon = new ImageIcon("big.jpg");
        Image img = icon.getImage().getScaledInstance(1000, 500, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(img), SwingConstants.CENTER);
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setOpaque(false);
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        gradientPanel.add(imagePanel, BorderLayout.CENTER);

        shopButton = new JButton("Register");
        shopButton.setFont(new Font("Arial", Font.BOLD, 20));
        shopButton.setPreferredSize(new Dimension(200, 50));
        shopButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(shopButton);
        gradientPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(gradientPanel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        new LoginForm();
        dispose();
    }

    public static void main(String[] args) {
        new first();
    }
}
