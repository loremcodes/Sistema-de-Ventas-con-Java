
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDateTime;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public final class VentaView extends JFrame {
    private JPanel panel, panelFormulario;
    private JLabel titulo, tituloSecundario, lbltipoComprobante, lblnumComprobante, lblfechaVenta, lblcliente, lbltotal, lblmetodoPago, lblmontoRecibido, lblvuelto, lblusuario, lblcantidad, lblsubtotal, lblprecioVenta;
    private JTextField txtmontoRecibido, txtnumComprobante, txtvuelto, txtotal, txtfecha;
    private JComboBox cbxtipoComprobante, cbxmetodoPago, cbxcliente, cbxusuario;
    private JButton btnEliminar, btnFormulario, btnRegistrar, btnDetalleVenta;
    private DefaultTableModel modelo;
    private JTable tabla;
    private JScrollPane scroll;
    
    public VentaView(){
        configurarFrame();
        initComponentes();
        eventos();
    }
    public void configurarFrame(){
        setTitle("Sistema de ventas | Gestión de ventas");
        setSize(800,700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.setLayout(new BorderLayout());
    }
    private void initComponentes(){
        panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints(); 
        gbc.gridx = 0; 
        gbc.gridy = 0; 
        gbc.gridwidth = 10;
        gbc.insets = new Insets(20, 10, 20, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        
        titulo = new JLabel("Ventas");
        titulo.setFont(new Font("Inter", Font.BOLD, 24));
        panel.add(titulo, gbc);
        
        gbc.gridwidth = 1;
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        btnEliminar = new JButton("Anular venta");
        btnEliminar.setBackground(new Color(255,100,100));
        btnEliminar.setPreferredSize(new Dimension(200, 30));
        panel.add(btnEliminar, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        btnDetalleVenta = new JButton("Ver detalle venta");
        btnDetalleVenta.setBackground(new Color(255,229,138));
        btnDetalleVenta.setPreferredSize(new Dimension(200, 30));
        panel.add(btnDetalleVenta, gbc);
        
        gbc.gridx = 9;
        gbc.gridy = 1;    
        gbc.anchor = GridBagConstraints.EAST;
        btnFormulario = new JButton("Registrar nueva venta");
        btnFormulario.setBackground(new Color(171,239,255));
        btnFormulario.setPreferredSize(new Dimension(200, 30));
        panel.add(btnFormulario, gbc);
           
        gbc.gridwidth = 10;
        gbc.gridx = 0;
        gbc.gridy = 2;  
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        String[] columnas = {"N°", "Tipo comprobante", "N° comprobante", "Fecha venta", "Cliente", "Total", "Método pago", "Monto recibido", "Vuelto", "Usuario"};
        modelo = new DefaultTableModel(columnas,0);
        tabla = new JTable(modelo);
        scroll = new JScrollPane(tabla);
        scroll.setPreferredSize(new Dimension(500, 200));
        panel.add(scroll, gbc);
        
        this.add(panel);
    }
    private void eventos(){
        
    }
    private JPanel panelFormulario(){
        panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints(); 
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(30, 10, 30, 10);
        
        tituloSecundario = new JLabel("Registrar nueva venta");
        tituloSecundario.setFont(new Font("Inter", Font.BOLD, 20));
        panelFormulario.add(tituloSecundario, gbc);
        
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        gbc.gridx=0;
        gbc.gridy=1;
        lbltipoComprobante = new JLabel("Tipo comprobante: ");
        panelFormulario.add(lbltipoComprobante, gbc);
        
        gbc.gridx=1;
        gbc.gridy=1;
        lblnumComprobante = new JLabel("N° comprobante: ");
        panelFormulario.add(lblnumComprobante, gbc);
        
        gbc.gridx=2;
        gbc.gridy=1;
        lblfechaVenta = new JLabel("Fecha: ");
        panelFormulario.add(lblfechaVenta, gbc);
        
        gbc.gridx=0;
        gbc.gridy=2;
        String[] tiposComprobantes = {"Boleta", "Factura", "Tocket"};
        JComboBox<String> cbxtipoComprobante = new JComboBox(tiposComprobantes);
        cbxtipoComprobante.setPreferredSize(new Dimension(150,30));
        panelFormulario.add(cbxtipoComprobante, gbc);
        
        gbc.gridx=1;
        gbc.gridy=2;
        txtnumComprobante = new JTextField(15);
        txtnumComprobante.setPreferredSize(new Dimension(0,30));
        txtnumComprobante.setEditable(false);
        txtnumComprobante.setFocusable(false);
        txtnumComprobante.setBackground(new Color(240, 240, 240));
        panelFormulario.add(txtnumComprobante, gbc);
        
        gbc.gridx=2;
        gbc.gridy=2;
        txtfecha = new JTextField(15);
        txtfecha.setPreferredSize(new Dimension(0,30));
        txtfecha.setEditable(false);
        txtfecha.setFocusable(false);
        panelFormulario.add(txtfecha, gbc);
        
        
        
        return panelFormulario();
    }
    private void ejecutarBtnRegistrar(){
        
    }
    private void ejecutarBtnAnular(){
        
    }
}
