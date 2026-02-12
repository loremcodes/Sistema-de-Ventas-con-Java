
package view;

import controller.LoginController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public final class LoginView extends JFrame {
    private JPanel panel;
    private JButton botonInicio;
    private JTextField usuariotxt;
    private JPasswordField contraseniatxt;
    private JLabel titulo, usuarioLabel, contraseniaLabel;
    
    private LoginController control;
    
    public LoginView(){
        control = new LoginController();
        
        configurarFrame();
        initComponentes();
        eventos();
    }
    private void configurarFrame(){
        setTitle("Sistemas de Ventas | Login");
        this.setSize(600,700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.setLayout(new BorderLayout());
    }
    private void initComponentes(){
        panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(255, 255, 255));
        
        GridBagConstraints gbc = new GridBagConstraints(); 
        gbc.gridx = 0; 
        
        gbc.gridy = 0; 
        gbc.insets = new Insets(10, 10, 10, 10); // margen 
        gbc.anchor = GridBagConstraints.CENTER;
        
        titulo = new JLabel();
        titulo.setText("Iniciar Sesión");
        titulo.setPreferredSize(new Dimension(200, 50));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(new Font("Inter", Font.BOLD, 18));
        panel.add(titulo, gbc);
        
        gbc.gridy =1;
        usuarioLabel = new JLabel();
        usuarioLabel.setText("Usuario: ");
        usuarioLabel.setPreferredSize(new Dimension(200, 10));
        usuarioLabel.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(usuarioLabel, gbc);
        
        gbc.gridy = 2;
        usuariotxt = new JTextField(20);
        usuariotxt .setPreferredSize(new Dimension(300, 40));
        panel.add(usuariotxt, gbc);
        
        gbc.gridy =3;
        contraseniaLabel = new JLabel();
        contraseniaLabel.setText("Contraseña: ");
        contraseniaLabel.setPreferredSize(new Dimension(200, 10));
        contraseniaLabel.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(contraseniaLabel, gbc);
        
        gbc.gridy = 4;
        contraseniatxt = new JPasswordField(20);
        contraseniatxt.setPreferredSize(new Dimension(300, 40));
        panel.add(contraseniatxt, gbc);
        
        gbc.gridy = 5;
        botonInicio = new JButton();
        botonInicio.setText("Ingresar");
        botonInicio.setBackground(new Color(135,210,255));
        botonInicio.setPreferredSize(new Dimension(200, 40));
        panel.add(botonInicio, gbc);
        
        this.add(panel);
    }
    private void eventos(){
        // Al presionar el botón o dar Enter en la contraseña
        botonInicio.addActionListener(e -> ejecutarLogin());
        contraseniatxt.addActionListener(e -> ejecutarLogin());
    }
    private void ejecutarLogin() {
        String user = usuariotxt.getText();
        String pass = new String(contraseniatxt.getPassword());
        
        if(control.validacion(user, pass)){
            InicioView inicio = new InicioView();
            inicio.setVisible(true);
            this.dispose();
        }
    }
}
