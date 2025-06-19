package atm;

import javax.swing.*;

public class AdministradorLoginScreen extends JFrame {

    public AdministradorLoginScreen() {
        setTitle("Acceso Administrador");
        setSize(300, 200);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setBounds(30, 30, 80, 25);
        add(userLabel);

        JTextField userField = new JTextField();
        userField.setBounds(100, 30, 150, 25);
        add(userField);

        JLabel passLabel = new JLabel("Clave:");
        passLabel.setBounds(30, 70, 80, 25);
        add(passLabel);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(100, 70, 150, 25);
        add(passField);

        JButton loginBtn = new JButton("Ingresar");
        loginBtn.setBounds(90, 110, 100, 30);
        add(loginBtn);

        loginBtn.addActionListener(e -> {
            String user = userField.getText();
            String pass = new String(passField.getPassword());

            if (user.equals("admin") && pass.equals("admin123")) {
                new AdministradorPanel();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Acceso denegado.");
            }
        });

        setVisible(true);
    }
}

