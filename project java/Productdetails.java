import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.LinkedHashMap;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Productdetails extends JFrame implements ActionListener {
    JButton buyButton, backButton;
    String userEmail;
    int productId;
    String productName;
    String price;
    String warranty;
    String brand;
    String imagePath;
    String category;
    Image backgroundImage;

    Connection con;

    public Productdetails(String userEmail, int productId, String productName, int priceInt, String warranty, String brand, String imagePath, String category) {
        this.userEmail = userEmail;
        this.productId = productId;
        this.productName = productName;
        this.price = String.valueOf(priceInt);
        this.warranty = warranty;
        this.brand = brand;
        this.imagePath = imagePath;
        this.category = category;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mydb", "root", "db_1234567890");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "DB error!");
        }

        ImageIcon bgIcon = new ImageIcon("background.jpg");
        backgroundImage = bgIcon.getImage();

        setTitle(productName + " Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        LinkedHashMap<String, String> attributes = new LinkedHashMap<>();
        attributes.put("Product ID", String.valueOf(productId));
        attributes.put("Product", productName);
        attributes.put("Category", category);
        attributes.put("Price", "Rs. " + price);
        attributes.put("Warranty", warranty);
        attributes.put("Brand", brand);

        String[] columns = {"Attribute", "Value"};
        Object[][] data = new Object[attributes.size()][2];
        int i = 0;
        for (var entry : attributes.entrySet()) {
            data[i][0] = entry.getKey();
            data[i][1] = entry.getValue();
            i++;
        }

        JTable table = new JTable(new DefaultTableModel(data, columns) {
            public boolean isCellEditable(int r, int c) { return false; }
        });

        JScrollPane tableScroll = new JScrollPane(table);

        JLabel imageLabel = new JLabel(new ImageIcon(imagePath), SwingConstants.CENTER);

        JPanel backgroundPanel = new JPanel(new BorderLayout()) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        backgroundPanel.add(imageLabel, BorderLayout.NORTH);
        backgroundPanel.add(tableScroll, BorderLayout.CENTER);

        buyButton = new JButton("Buy");
        buyButton.addActionListener(this);
        backButton = new JButton("Back");
        backButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(buyButton);
        buttonPanel.add(backButton);

        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(backgroundPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buyButton) {
            try {
                // get customer id from customer table
                PreparedStatement userPs = con.prepareStatement(
                    "SELECT customer_id FROM customer WHERE email = ? LIMIT 1"
                );
                userPs.setString(1, userEmail);
                ResultSet rs = userPs.executeQuery();

                if (rs.next()) {
                    int customerId = rs.getInt("customer_id");
                    java.sql.Date purDate = new java.sql.Date(System.currentTimeMillis());

                    // insert new order
                    PreparedStatement insertPs = con.prepareStatement(
                        "INSERT INTO orders (customer_id, product_id, product_name, price, pur_date) VALUES (?, ?, ?, ?, ?)"
                    );
                    insertPs.setInt(1, customerId);
                    insertPs.setInt(2, productId);
                    insertPs.setString(3, productName);
                    insertPs.setString(4, price);
                    insertPs.setDate(5, purDate);

                    insertPs.executeUpdate();

                    JOptionPane.showMessageDialog(this,
                        "Order confirmed!\nProduct: " + productName + "\nPrice: Rs." + price);

                    new LastPage(userEmail);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this,
                        "No registered user found with email: " + userEmail);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "DB insert failed: " + ex.getMessage());
            }
        } else if (e.getSource() == backButton) {
            new SecondPage(userEmail);
            dispose();
        }
    }
}
