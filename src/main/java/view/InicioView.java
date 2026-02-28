
package view;

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
import javax.swing.SwingConstants;

public class InicioView extends JFrame{
    private JLabel titulo, subtitulo;
    private JPanel panel;
    private JButton btncliente, btnproductos, btncategoria, btnusuario, btnventa;
    
    public InicioView(){
        configurarFrame();
        initComponentes();
        eventos();
    }
    public void configurarFrame(){
        setTitle("Sistemas de Ventas | Inicio");
        setSize(600,700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        this.setLayout(new GridBagLayout());
    }
    public void initComponentes(){
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints(); 
        gbc.gridx = 0; 
        gbc.gridy = 0; 
        gbc.insets = new Insets(10, 10, 2, 2); // margen 
        gbc.anchor = GridBagConstraints.CENTER;
        
        gbc.gridwidth = 2;
        
        titulo = new JLabel("SISTEMA DE VENTAS");
        titulo.setPreferredSize(new Dimension(200, 30));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titulo, gbc);
        
        gbc.gridx = 0; 
        gbc.gridy = 1; 
        subtitulo = new JLabel("Tienda Dorita");
        subtitulo.setPreferredSize(new Dimension(200, 30));
        subtitulo.setHorizontalAlignment(SwingConstants.CENTER);
        subtitulo.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(subtitulo, gbc);
        
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 10, 10); 
        
        gbc.gridx = 0; 
        gbc.gridy = 2; 
        btnventa = new JButton();
        btnventa.setText("Ventas");
        btnventa.setPreferredSize(new Dimension(200, 50));
        btnventa.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(btnventa, gbc);
        
        gbc.gridx = 1; 
        gbc.gridy = 2; 
        btncliente = new JButton();
        btncliente.setText("Clientes");
        btncliente.setPreferredSize(new Dimension(200, 50));
        btncliente.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(btncliente, gbc);
        
        gbc.gridx = 0; 
        gbc.gridy = 3; 
        btnproductos = new JButton();
        btnproductos.setText("Productos");
        btnproductos.setPreferredSize(new Dimension(200, 50));
        btnproductos.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(btnproductos, gbc);
        
        gbc.gridx = 1; 
        gbc.gridy = 3; 
        btncategoria = new JButton();
        btncategoria.setText("Categorias");
        btncategoria.setPreferredSize(new Dimension(200, 50));
        btncategoria.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(btncategoria, gbc);
        
        gbc.gridx = 0; 
        gbc.gridy = 4; 
        btnusuario = new JButton();
        btnusuario.setText("Usuarios");
        btnusuario.setPreferredSize(new Dimension(200, 50));
        btnusuario.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(btnusuario, gbc);
        
        this.add(panel); 
    }
    public void eventos(){
        btnventa.addActionListener(e -> {
            VentaView ventanaVenta = new VentaView();
            ventanaVenta.setVisible(true);
        });
        btncliente.addActionListener(e -> {
            ClienteView ventanaCliente = new ClienteView();
            ventanaCliente.setVisible(true);
        });
        btnproductos.addActionListener(e -> {
            ProductoView ventanaProducto = new ProductoView();
            ventanaProducto.setVisible(true);
        });
        btncategoria.addActionListener(e -> {
            ProductoView ventanaCategoria = new ProductoView();
            ventanaCategoria.setVisible(true);
        });
        btnusuario.addActionListener(e -> {
            UsuarioView ventanaUsuario = new UsuarioView();
            ventanaUsuario.setVisible(true);
        });
    }
}
