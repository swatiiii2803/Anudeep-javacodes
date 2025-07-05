import java.awt.*;
import javax.swing.*;

public class SecondPage extends JFrame {
    String userEmail;

    public SecondPage(String userEmail) {
        this.userEmail = userEmail;

        setTitle("Electronic Items");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Gradient background panel
        JPanel gradientPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int w = getWidth();
                int h = getHeight();

                Color start = new Color(255, 228, 196);
                Color mid = new Color(255, 182, 193);
                Color end = new Color(173, 216, 230);

                g2d.setPaint(new GradientPaint(0, 0, start, w / 2, 0, mid));
                g2d.fillRect(0, 0, w / 2, h);

                g2d.setPaint(new GradientPaint(w / 2, 0, mid, w, 0, end));
                g2d.fillRect(w / 2, 0, w / 2, h);
            }
        };
        gradientPanel.setLayout(new GridLayout(2, 3, 20, 20));
        gradientPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        addProduct(gradientPanel, 101, "tv.jpg", "TV", 40000, "2 Years", "LG");
        addProduct(gradientPanel, 102, "laptop.jpg", "Laptop", 65000, "1 Year", "Dell");
        addProduct(gradientPanel, 103, "fridge.jpg", "Fridge", 30000, "3 Years", "Samsung");
        addProduct(gradientPanel, 104, "phone.png", "Phone", 25000, "1 Year", "Realme");
        addProduct(gradientPanel, 105, "washing.jpg", "Washing Machine", 35000, "2 Years", "Haier");
        addProduct(gradientPanel, 106, "ac.png", "AC", 45000, "3 Years", "Voltas");

        add(gradientPanel, BorderLayout.CENTER);

        // View orders button
        JButton ordersButton = new JButton("View My Orders");
        ordersButton.setFont(new Font("Arial", Font.BOLD, 18));
        ordersButton.setBackground(new Color(255, 105, 180));
        ordersButton.setForeground(Color.WHITE);
        ordersButton.setFocusPainted(false);
        ordersButton.setPreferredSize(new Dimension(200, 50));
        ordersButton.addActionListener(e -> {
            new MyOrder(userEmail);
            dispose();
        });

        // Back to Home button
        JButton homeButton = new JButton("Back to Home");
        homeButton.setFont(new Font("Arial", Font.BOLD, 18));
        homeButton.setBackground(new Color(60, 179, 113));
        homeButton.setForeground(Color.WHITE);
        homeButton.setFocusPainted(false);
        homeButton.setPreferredSize(new Dimension(200, 50));
        homeButton.addActionListener(e -> {
            new first(); // assuming you have a FirstPage class
            dispose();
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.add(ordersButton);
        bottomPanel.add(homeButton); // new button added next to View My Orders

        add(bottomPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addProduct(JPanel parent, int id, String imageFile, String name, int price, String warranty, String brand) {
        JPanel productPanel = new JPanel(new BorderLayout());
        productPanel.setOpaque(false);
        productPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        JLabel imageLabel;
        try {
            ImageIcon icon = new ImageIcon(imageFile);
            Image scaled = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
            imageLabel = new JLabel(new ImageIcon(scaled), SwingConstants.CENTER);
        } catch (Exception ex) {
            imageLabel = new JLabel("Image Missing", SwingConstants.CENTER);
        }

        JButton detailsButton = new JButton("See Details");
        detailsButton.setFont(new Font("Arial", Font.BOLD, 14));
        detailsButton.setBackground(new Color(173, 216, 230));
        detailsButton.addActionListener(e -> {
            new Productdetails(userEmail, id, name, price, warranty, brand, imageFile, name.toLowerCase());
            dispose();
        });

        productPanel.add(imageLabel, BorderLayout.CENTER);
        productPanel.add(detailsButton, BorderLayout.SOUTH);

        parent.add(productPanel);
    }
}
