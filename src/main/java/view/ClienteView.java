
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

public class ClienteView extends JFrame {
    private JPanel panel, panelFormulario;
    private JLabel titulo, lblnombres, lblapellidoPaterno, lblapellidoMaterno, lbldni, lblcelular;
    private JTextField txtnombres, txtapellidoPaterno, txtapellidoMaterno, txtdni, txtcelular;
    private JButton btnFormulario, btnRegistrar, btnEliminar;
    private DefaultTableModel modelo;
    private JTable tabla;
    private JScrollPane scroll;
    
    public ClienteView(){
        configurarFrame();
        initComponentes();
    }
    public void configurarFrame(){
        setTitle("Sistema de Ventas | Gestión de clientes");
       setSize(800,700);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       
       this.setLayout(new BorderLayout());
    }
    public void initComponentes(){
        panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints(); 
        gbc.gridx = 0; 
        gbc.gridy = 0; 
        gbc.gridwidth = 9;
        gbc.insets = new Insets(20, 10, 20, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        
        titulo = new JLabel("Clientes");
        titulo.setFont(new Font("Inter", Font.BOLD, 24));
        panel.add(titulo, gbc);
        
        gbc.gridwidth = 1;
        
        gbc.gridx = 7;
        gbc.gridy = 1;
        gbc.weightx = 1.0;        
        gbc.anchor = GridBagConstraints.EAST;
        btnEliminar = new JButton("Eliminar cliente");
        btnEliminar.setBackground(new Color(255,100,100));
        btnEliminar.setPreferredSize(new Dimension(200, 30));
        panel.add(btnEliminar, gbc);
        
        gbc.gridx = 8;
        gbc.gridy = 1;    
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.EAST;
        btnFormulario = new JButton("Agregar cliente");
        btnFormulario.setBackground(new Color(171,239,255));
        btnFormulario.setPreferredSize(new Dimension(200, 30));
        panel.add(btnFormulario, gbc);
        
        String[] columnas = {"N°", "Nombres", "Apellido paterno", "Apellido materno", "DNI", "Celular"};
        modelo = new DefaultTableModel();
        tabla = new JTable(modelo);
        scroll = new JScrollPane(tabla);
        scroll.setPreferredSize(new Dimension(750, 200));
        panel.add(scroll, gbc);
        
        
       this.add(panel); 
    }
    public void panelFormulario(){
        panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints(); 
        gbc.gridx = 0; 
        gbc.gridy = 0; 
        gbc.insets = new Insets(20, 10, 20, 10); // margen 
        gbc.anchor = GridBagConstraints.CENTER;
    }
    public void ejecutarEventos(){}
    
}
