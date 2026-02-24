
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
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Cliente;
import model.Producto;

public final class VentaView extends JFrame {
    private JPanel panel, panelSecundario;
    private JLabel titulo, tituloSecundario, lbltipoComprobante, lblnumComprobante, lblfechaVenta, lblcliente, lbltotal, lblmetodoPago, lblmontoRecibido, lblvuelto, lblcantidad, lblsubtotal, lblprecioVenta, lblproducto;
    private JTextField txtmontoRecibido, txtnumComprobante, txtvuelto, txtotal, txtfecha, txtcantidad, txtprecioVenta, txtsubtotal;
    private JComboBox cbxtipoComprobante, cbxmetodoPago, cbxcliente, cbxproducto;
    private JButton btnEliminar, btnFormulario, btnAgregarCompra, btnRegistrar, btnDetalleVenta;
    private DefaultTableModel modelo;
    private JTable tabla;
   
    
    private JScrollPane scroll;
    private JSeparator separador;
    
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
    private JPanel panelFormulario(){
        panelSecundario = new JPanel();
        panelSecundario.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints(); 
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(30, 10, 30, 10);
        
        tituloSecundario = new JLabel("Registrar nueva venta");
        tituloSecundario.setFont(new Font("Inter", Font.BOLD, 20));
        panelSecundario.add(tituloSecundario, gbc);
        
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        gbc.gridx=0;
        gbc.gridy=1;
        lbltipoComprobante = new JLabel("Tipo comprobante: ");
        panelSecundario.add(lbltipoComprobante, gbc);
        
        gbc.gridx=1;
        gbc.gridy=1;
        lblnumComprobante = new JLabel("N° comprobante: ");
        panelSecundario.add(lblnumComprobante, gbc);
        
        gbc.gridx=2;
        gbc.gridy=1;
        lblfechaVenta = new JLabel("Fecha: ");
        panelSecundario.add(lblfechaVenta, gbc);
        
        gbc.gridx=3;
        gbc.gridy=1;
        lblcliente = new JLabel("Cliente: ");
        panelSecundario.add(lblcliente, gbc);
        
        gbc.gridx=0;
        gbc.gridy=2;
        String[] tiposComprobantes = {"Boleta", "Factura", "Tocket"};
        JComboBox<String> cbxtipoComprobante = new JComboBox(tiposComprobantes);
        cbxtipoComprobante.setPreferredSize(new Dimension(150,30));
        panelSecundario.add(cbxtipoComprobante, gbc);
        
        gbc.gridx=1;
        gbc.gridy=2;
        txtnumComprobante = new JTextField(15);
        txtnumComprobante.setPreferredSize(new Dimension(0,30));
        txtnumComprobante.setEditable(false);
        txtnumComprobante.setFocusable(false);
        txtnumComprobante.setBackground(new Color(240, 240, 240));
        panelSecundario.add(txtnumComprobante, gbc);
        
        gbc.gridx=2;
        gbc.gridy=2;
        txtfecha = new JTextField(15);
        txtfecha.setPreferredSize(new Dimension(0,30));
        txtfecha.setEditable(false);
        txtfecha.setFocusable(false);
        panelSecundario.add(txtfecha, gbc);
        
        gbc.gridx=3;
        gbc.gridy=2;
        cbxcliente = new JComboBox<Cliente>();
        cbxcliente.setPreferredSize(new Dimension(150,30));
        AutoCompleteDecorator.decorate(cbxcliente);
        panelSecundario.add(cbxcliente, gbc);
        
        separador = new JSeparator();
        gbc.gridwidth = 4; 
        
        gbc.gridx = 0;          
        gbc.gridy = 3;            
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        panelSecundario.add(separador, gbc);
        
        gbc.gridwidth = 2; 
        
        gbc.gridx = 0;          
        gbc.gridy = 4;
        lblproducto = new JLabel("Producto: ");
        panelSecundario.add(lblproducto, gbc);
        
        gbc.gridwidth = 1; 
        
        gbc.gridx = 2;          
        gbc.gridy = 4;
        lblprecioVenta = new JLabel("Precio: ");
        panelSecundario.add(lblprecioVenta, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 4;
        lblcantidad = new JLabel("Cantidad: ");
        panelSecundario.add(lblcantidad, gbc);
        
        gbc.gridwidth = 2;    
           
        gbc.gridx = 0;          
        gbc.gridy = 5;
        cbxproducto = new JComboBox<Producto>();
        cbxproducto.setPreferredSize(new Dimension(0,30));
        AutoCompleteDecorator.decorate(cbxproducto);
        panelSecundario.add(cbxproducto, gbc);
        
        gbc.gridwidth = 1; 
        gbc.fill = GridBagConstraints.CENTER;
        
        gbc.gridx = 2;
        gbc.gridy = 5;
        txtprecioVenta = new JTextField(15);
        txtprecioVenta.setPreferredSize(new Dimension(0,30));
        txtprecioVenta.setEditable(false);
        txtprecioVenta.setFocusable(false);
        panelSecundario.add(txtprecioVenta, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 5;
        txtcantidad = new JTextField(15);
        txtcantidad.setPreferredSize(new Dimension(0,30));
        panelSecundario.add(txtcantidad, gbc);
        
        gbc.gridwidth = 4; 
        
        gbc.gridx = 3;
        gbc.gridy = 6;
        btnAgregarCompra = new JButton();
        btnAgregarCompra.setText("Agregar");
        btnAgregarCompra.setPreferredSize(new Dimension(150,30));
        btnAgregarCompra.setBackground(new Color(255,239,153));
        panelSecundario.add(btnAgregarCompra, gbc);
        
        gbc.gridx = 0;          
        gbc.gridy = 7;   
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        String[] columnas = {"N°", "Cantidad", "Producto", "Precio", "Subtotal"};
        modelo = new DefaultTableModel(columnas,0);
        tabla = new JTable(modelo);
        scroll = new JScrollPane(tabla);
        scroll.setPreferredSize(new Dimension(tabla.getPreferredSize().width, 200));
        panelSecundario.add(scroll, gbc);
        
        separador = new JSeparator();
        gbc.gridwidth = 4; 
        
        gbc.gridx = 0;          
        gbc.gridy = 8;            
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        panelSecundario.add(separador, gbc);
        
        gbc.fill = GridBagConstraints.CENTER;
        
        gbc.gridx=0;
        gbc.gridy=9;
        lbltotal = new JLabel("Total: ");
        panelSecundario.add(lbltotal, gbc);
        
        gbc.gridx=1;
        gbc.gridy=9;
        lblmetodoPago = new JLabel("Método pago: ");
        panelSecundario.add(lblmetodoPago, gbc);
        
        gbc.gridx=2;
        gbc.gridy=9;
        lblmontoRecibido = new JLabel("Monto rebicido: ");
        panelSecundario.add(lblmontoRecibido, gbc);
        
        gbc.gridx=3;
        gbc.gridy=9;
        lblvuelto = new JLabel("Vuelto: ");
        panelSecundario.add(lblvuelto, gbc);
        
        gbc.gridx=0;
        gbc.gridy=10;
        txtotal = new JTextField(15);
        txtotal.setPreferredSize(new Dimension(0,30));
        txtotal.setEditable(false);
        txtotal.setFocusable(false);
        panelSecundario.add(txtotal, gbc);
        
        gbc.gridx=1;
        gbc.gridy=10;
        String[] metodosPago = {"Efectivo", "Yape", "Plin", "Crédito"};
        cbxmetodoPago = new JComboBox(metodosPago);
        cbxmetodoPago.setPreferredSize(new Dimension(150,30));
        panelSecundario.add(cbxmetodoPago, gbc);
          
        gbc.gridx=2;
        gbc.gridy=10;
        txtmontoRecibido = new JTextField(15);
        txtmontoRecibido.setPreferredSize(new Dimension(0,30));
        panelSecundario.add(txtmontoRecibido, gbc);
        
        gbc.gridx=3;
        gbc.gridy=10;
        txtvuelto = new JTextField(15);
        txtvuelto.setPreferredSize(new Dimension(0,30));
        txtvuelto.setEditable(false);
        txtvuelto.setFocusable(false);
        panelSecundario.add(txtvuelto, gbc);
        
        gbc.gridx=1;
        gbc.gridy=11;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnRegistrar = new JButton();
        btnRegistrar.setText("Registrar venta");
        btnRegistrar.setPreferredSize(new Dimension(300,30));
        btnRegistrar.setBackground(new Color(171,239,255));
        panelSecundario.add(btnRegistrar, gbc);
        
        return panelSecundario;
    }
    private void eventos(){
        btnFormulario.addActionListener(e -> {
            JDialog dialogo = new JDialog(this,"Sistema de ventas | Registrar venta", true);
            dialogo.add(panelFormulario());
            dialogo.pack(); 
            dialogo.setLocationRelativeTo(this); 
            dialogo.setResizable(false);
            dialogo.setVisible(true);
        });
    }
    private void ejecutarBtnRegistrar(){
        
    }
    private void ejecutarBtnAnular(){
        
    }
}
