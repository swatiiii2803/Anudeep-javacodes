import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class LoginForm extends JFrame implements ActionListener {
    JTextField nameField, emailField, phoneField, addressField;
    JButton submitButton;
    Connection con;

    LoginForm() {
        setTitle("Register");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // background
        ImageIcon bgIcon = new ImageIcon("login.jpg");
        Image bgImage = bgIcon.getImage();

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null);

        // semi-transparent card
        JPanel card = new JPanel();
        card.setLayout(new GridBagLayout());
        card.setBackground(new Color(255, 255, 255, 230));
        card.setBounds(150, 100, 400, 500);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Register");
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 26));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=2;
        card.add(titleLabel, gbc);

        gbc.gridwidth=1;
        gbc.gridy++;
        gbc.gridx=0;
        card.add(new JLabel("Name:"), gbc);
        gbc.gridx=1;
        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(200,40));
        card.add(nameField, gbc);

        gbc.gridy++;
        gbc.gridx=0;
        card.add(new JLabel("Email:"), gbc);
        gbc.gridx=1;
        emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(200,40));
        card.add(emailField, gbc);

        gbc.gridy++;
        gbc.gridx=0;
        card.add(new JLabel("Phone:"), gbc);
        gbc.gridx=1;
        phoneField = new JTextField();
        phoneField.setPreferredSize(new Dimension(200,40));
        card.add(phoneField, gbc);

        gbc.gridy++;
        gbc.gridx=0;
        card.add(new JLabel("Address:"), gbc);
        gbc.gridx=1;
        addressField = new JTextField();
        addressField.setPreferredSize(new Dimension(200,40));
        card.add(addressField, gbc);

        gbc.gridy++;
        gbc.gridx=0;
        gbc.gridwidth=2;
        submitButton = new JButton("Submit");
        submitButton.setBackground(new Color(255,105,180));
        submitButton.setForeground(Color.WHITE);
        submitButton.setPreferredSize(new Dimension(200, 50));
        submitButton.setFont(new Font("Arial", Font.BOLD, 18));
        submitButton.addActionListener(this);
        card.add(submitButton, gbc);

        backgroundPanel.add(card);
        add(backgroundPanel);

        // db connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mydb", "root", "db_1234567890");
        } catch(Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "DB connection error!");
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String address = addressField.getText().trim();

        if(name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(this,"Please fill all fields!");
            return;
        }

        try {
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO customer (name,email,phone_no,address) VALUES (?,?,?,?)");
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, address);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Registered Successfully!");
            try {
                new SecondPage(email);  // go to second page
                dispose();
            } catch(Exception ex2) {
                ex2.printStackTrace();
                JOptionPane.showMessageDialog(this, "SecondPage failed: " + ex2.getMessage());
            }

        } catch(SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,"Registration failed!\nMaybe email already registered?");
        }
    }

    public static void main(String[] args) {
        new LoginForm();
    }
}