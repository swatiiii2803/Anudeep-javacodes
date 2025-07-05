import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MyOrder extends JFrame {
    Connection con;

    public MyOrder(String userEmail) {
        setTitle("My Orders");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel heading = new JLabel("My Orders for: " + userEmail, SwingConstants.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 24));
        add(heading, BorderLayout.NORTH);

        String[] columns = {
            "Order ID", "Customer Name", "Email", "Address", "Phone",
            "Product ID", "Product Name", "Price", "Purchase Date"
        };
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mydb", "root", "db_1234567890");

            PreparedStatement ps = con.prepareStatement(
                """
                SELECT o.order_id, c.name, c.email, c.address, c.phone_no,
                       o.product_id, o.product_name, o.price, o.pur_date
                FROM orders o
                JOIN customer c ON o.customer_id = c.customer_id
                WHERE c.email = ?
                """
            );
            ps.setString(1, userEmail);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int orderId = rs.getInt("order_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phone = rs.getString("phone_no");
                int productId = rs.getInt("product_id");
                String productName = rs.getString("product_name");
                String price = rs.getString("price");
                String purDate = rs.getString("pur_date");

                model.addRow(new Object[]{
                    orderId, name, email, address, phone,
                    productId, productName, price, purDate
                });
            }

        } catch(Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Could not fetch orders: " + e.getMessage());
        }

        // back button
        JButton backButton = new JButton("Back to Shop");
        backButton.addActionListener(e -> {
            new SecondPage(userEmail);
            dispose();
        });
        JPanel bottom = new JPanel();
        bottom.add(backButton);
        add(bottom, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
