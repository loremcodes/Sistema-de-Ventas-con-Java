
package view;

import controller.CategoriaController;
import controller.ClienteController;
import controller.DetalleVentaController;
import controller.ProductoController;
import controller.VentaController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Categoria;
import model.Cliente;
import model.DetalleVenta;
import model.Login;
import model.Producto;
import model.Venta;

public final class VentaView extends JFrame {
    private JPanel panel, panelSecundario, panelDetalle;
    private JLabel titulo, tituloSecundario, tituloDetalles, lbltipoComprobante, lblnumComprobante, lblfechaVenta, lblcliente, lbltotal, lblmetodoPago, lblmontoRecibido, lblvuelto, lblcantidad, lblsubtotal, lblprecioVenta, lblproducto;
    private JTextField txtmontoRecibido, txtnumComprobante, txtvuelto, txtotal, txtfecha, txtcantidad, txtprecioVenta, txtsubtotal;
    private JComboBox<String> cbxtipoComprobante;
    private JComboBox<String> cbxmetodoPago;
    private JComboBox<Cliente> cbxcliente; 
    private JComboBox<Producto> cbxproducto;
    private JButton btnAnular, btnFormulario, btnAgregarCompra, btnRegistrarVenta, btnVerDetalleVenta, btnEliminarCompra, btnModificarCompra;
    private DefaultTableModel modelo, modeloDetalle, modeloVerDetalle;
    private JTable tabla, tablaDetalle, tablaVerDetalle;
    private JScrollPane scroll, scrollDetalle, scrollVerDetalle;
    private JSeparator separador;
    private DetalleVentaController controlDetalleVenta;
    private List<DetalleVenta> listaDetalle = new ArrayList();
    private VentaController controlVenta;
    private double totalPagar = 0.0;
    private List<Venta> listaVentas;
    
    public VentaView(){
        controlVenta = new VentaController();
        controlDetalleVenta = new DetalleVentaController();
        
        configurarFrame();
        initComponentes();
        eventos();
        ejecutarLista();
    }
    public void configurarFrame(){
        setTitle("Sistema de ventas | Gestión de ventas");
        setSize(800,700);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
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
        btnAnular = new JButton("Anular venta");
        btnAnular.setBackground(new Color(255,100,100));
        btnAnular.setPreferredSize(new Dimension(200, 30));
        panel.add(btnAnular, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        btnVerDetalleVenta = new JButton("Ver detalle venta");
        btnVerDetalleVenta.setBackground(new Color(255,229,138));
        btnVerDetalleVenta.setPreferredSize(new Dimension(200, 30));
        panel.add(btnVerDetalleVenta, gbc);
        
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
        String[] columnas = {"N°", "Tipo comprobante", "N° comprobante", "Fecha venta", "Cliente", "Total", "Método pago", "Monto recibido", "Vuelto", "Usuario", "Estado"};
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
        String[] tiposComprobantes = {"Boleta", "Factura", "Ticket"};
        cbxtipoComprobante = new JComboBox(tiposComprobantes);
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
        cbxcliente = new JComboBox<>();
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
        cbxproducto = new JComboBox<>();
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
        
        gbc.gridx = 1;
        gbc.gridy = 6;
        btnModificarCompra = new JButton();
        btnModificarCompra.setText("Modificar");
        btnModificarCompra.setPreferredSize(new Dimension(150,30));
        btnModificarCompra.setBackground(new Color(255,191,102));
        panelSecundario.add(btnModificarCompra, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 6;
        btnEliminarCompra = new JButton();
        btnEliminarCompra.setText("Eliminar");
        btnEliminarCompra.setPreferredSize(new Dimension(150,30));
        btnEliminarCompra.setBackground(new Color(255,100,100));
        panelSecundario.add(btnEliminarCompra, gbc);
        
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
        modeloDetalle = new DefaultTableModel(columnas,0);
        tablaDetalle = new JTable(modeloDetalle);
        scrollDetalle = new JScrollPane(tablaDetalle);
        scrollDetalle.setPreferredSize(new Dimension(tablaDetalle.getPreferredSize().width, 200));
        panelSecundario.add(scrollDetalle, gbc);
        
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
        btnRegistrarVenta = new JButton();
        btnRegistrarVenta.setText("Registrar venta");
        btnRegistrarVenta.setPreferredSize(new Dimension(300,30));
        btnRegistrarVenta.setBackground(new Color(171,239,255));
        panelSecundario.add(btnRegistrarVenta, gbc);
        
        llenarComboCliente();
        llenarComboProducto();
        
        /*Eventos para las variables JComboBox----------------------------------*/
        cbxproducto.addActionListener(e -> {
            Producto p = (Producto) cbxproducto.getSelectedItem(); 
            if (p != null) txtprecioVenta.setText(String.valueOf(p.getPrecioVenta()));
        });
        /*----------------------------------------------------------------------*/
        
        btnAgregarCompra.addActionListener(e-> ejecutarBtnAgregarCompra());
        btnEliminarCompra.addActionListener(e -> ejecutarBtnEliminarCompra());
        btnModificarCompra.addActionListener(e -> ejecutarBtnModificarCompra());
        txtmontoRecibido.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
            calcularVuelto();
            }
        });
        btnRegistrarVenta.addActionListener(e -> ejecutarBtnRegistrarVenta());
        
        return panelSecundario;
    }
    private JPanel panelVerDetalles(){
        panelDetalle = new JPanel();
        panelDetalle.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints(); 
        gbc.gridx = 0; 
        gbc.gridy = 0; 
        gbc.gridwidth = 10;
        gbc.insets = new Insets(20, 10, 20, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        
        tituloDetalles = new JLabel("Detalle Venta");
        tituloDetalles.setFont(new Font("Inter", Font.BOLD, 14));
        panelDetalle.add( tituloDetalles, gbc);
        
        gbc.gridx = 0; 
        gbc.gridy = 1; 
        String[] columnas = {"N°", "Cantidad", "Producto", "Precio", "Subtotal"};
        modeloVerDetalle = new DefaultTableModel(columnas,0);
        tablaVerDetalle = new JTable(modeloVerDetalle);
        scrollVerDetalle = new JScrollPane(tablaVerDetalle );
        scrollVerDetalle.setPreferredSize(new Dimension(tablaVerDetalle .getPreferredSize().width, 200));
        panelDetalle.add(scrollVerDetalle, gbc);
        
        ejecutarBtnVerDetalle(); 
        
        return panelDetalle;
    }
    private void llenarComboCliente(){
        cbxcliente.removeAllItems();

        ClienteController cliente= new ClienteController();
        List<Cliente> lista = cliente.visualizarLista();
      
        for(Cliente cli: lista){
           cbxcliente.addItem(cli);
        }
        cbxcliente.setSelectedIndex(-1);
    }
    private void llenarComboProducto(){
        cbxproducto.removeAllItems();
        
        ProductoController producto = new ProductoController();
        List<Producto> lista = producto.visualizarLista();
        
        for(Producto pro : lista){
            cbxproducto.addItem(pro);
        }
        cbxproducto.setSelectedIndex(-1);
    }
    private void eventos(){
        btnFormulario.addActionListener(e -> {
            JDialog dialogo = new JDialog(this,"Sistema de ventas | Registrar venta", true);
            dialogo.add(panelFormulario());
            
            String numComprobante = obtenerNumeroComprobante();
            txtnumComprobante.setText(numComprobante);
            
            LocalDateTime ahora = LocalDateTime.now();
            DateTimeFormatter formatoCortes = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            txtfecha.setText(ahora.format(formatoCortes));
            
            dialogo.pack(); 
            dialogo.setLocationRelativeTo(this); 
            dialogo.setResizable(false);
            dialogo.setVisible(true);
        });
        btnVerDetalleVenta.addActionListener(e -> {
            JDialog dialogo = new JDialog(this,"Sistema de ventas | Detalle Venta", true);
            dialogo.add(panelVerDetalles());
            
            dialogo.pack(); 
            dialogo.setLocationRelativeTo(this); 
            dialogo.setResizable(false);
            dialogo.setVisible(true);
        });
        btnAnular.addActionListener(e -> ejecutarBtnAnular());
    }
    private void ejecutarLista(){
        modelo.setRowCount(0);
        int item=1;
        this.listaVentas = controlVenta.visualizarLista();
        
        for (Venta venta: listaVentas) {
            String estadoTexto = (venta.getEstadoVenta() == 1) ? "Activa" : "Anulada";
            Object[] fila = {
                item,
                venta.getTipoComprobante(),
                venta.getNumComprobante(),
                venta.getFechaVenta(),
                venta.getCliente(),
                venta.getTotal(),
                venta.getMetodoPago(),
                venta.getMontoRecibido(),
                venta.getVuelto(),
                venta.getUsuario().getNombres(),
                estadoTexto,
            };
            modelo.addRow(fila);
            item++;
        } 
    }
    private void ejecutarBtnAgregarCompra(){
        String texto = ((JTextField) cbxproducto.getEditor().getEditorComponent()).getText();
        
        if(texto.trim().trim().isEmpty() || txtcantidad.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Complete todos los campos del formulario");
        }else{
            Producto prod = (Producto) cbxproducto.getSelectedItem();
            double cant = Double.parseDouble(txtcantidad.getText());
            double precioVenta = Double.parseDouble(txtprecioVenta.getText());
       
            double subtotal = calcularSubtotal(cant, precioVenta);
            /*Vamos guardando temporalmente los datos que tenemos en nuestro objeto*/
            DetalleVenta detalle = new DetalleVenta();
            
            detalle.setCantidad(Double.parseDouble(txtcantidad.getText()));
            detalle.setProducto(prod);
            detalle.setPrecioVenta(Double.parseDouble(txtprecioVenta.getText()));
            detalle.setSubtotal(subtotal);
            
            listaDetalle.add(detalle);
            /*--------------------------------------------------------*/
            
            int item = 1;
            
            Object[] fila = new Object[5];
            fila[0] = item;
            fila[1] = cant;
            fila[2] = prod;
            fila[3] = precioVenta;
            fila[4] = subtotal;
            
            modeloDetalle.addRow(fila);
            item++;
            
            calcularTotalPagar();
            limpiarCamposDetalle();
        }
    }
    private void ejecutarBtnEliminarCompra(){
        int filaSeleccionada = tablaDetalle.getSelectedRow();
        
        if (filaSeleccionada != -1) {
            modeloDetalle.removeRow(filaSeleccionada);
            listaDetalle.remove(filaSeleccionada);    // Borrar el objeto de la lista (estan sincronizadas)
            calcularTotalPagar();
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar");
        } 
    }
    private void ejecutarBtnModificarCompra(){
        int filaSeleccionada = tablaDetalle.getSelectedRow();

        if(filaSeleccionada!=-1){
            String nuevaCant = JOptionPane.showInputDialog(this, "Ingrese cantidad", "Modificar cantidad", JOptionPane.QUESTION_MESSAGE);
            if(nuevaCant!=null){
                try{
                    double converCant = Double.parseDouble(nuevaCant);
                    modeloDetalle.setValueAt(converCant, filaSeleccionada, 1);
                
                    double precioV = (double) modeloDetalle.getValueAt(filaSeleccionada, 3);
                    Double subtotalActualizado = calcularSubtotal(converCant, precioV);
                    modeloDetalle.setValueAt(subtotalActualizado, filaSeleccionada, 4);
                    
                    DetalleVenta actualizarDetalle = listaDetalle.get(filaSeleccionada);
                    actualizarDetalle.setCantidad(converCant);
                    actualizarDetalle.setSubtotal(subtotalActualizado);
                
                    calcularTotalPagar();
                }catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Por favor ingresar un número válido");
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione la fila a modificar");
        }
    }
    private void ejecutarBtnRegistrarVenta(){
        if (listaDetalle.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay productos agregados a la compra");
        }else{
            String tipoComprobante = cbxtipoComprobante.getSelectedItem().toString();
            Cliente cliente = (Cliente) cbxcliente.getSelectedItem();
            String metodoPago = cbxmetodoPago.getSelectedItem().toString();
            
            
            Venta venta = new Venta();
            venta.setTipoComprobante(tipoComprobante);
            venta.setNumComprobante(txtnumComprobante.getText());
            venta.setFechaVenta(LocalDateTime.now());
            venta.setCliente(cliente);
            venta.setTotal(this.totalPagar);
            venta.setMetodoPago(metodoPago);
            venta.setUsuario(Login.getUsuario());
            
            try {
                venta.setMontoRecibido(Double.parseDouble(txtmontoRecibido.getText()));
                venta.setVuelto(Double.parseDouble(txtvuelto.getText()));

                if (controlVenta.registrarVenta(venta, listaDetalle)) {
                    JOptionPane.showMessageDialog(this, "Venta N° " + venta.getNumComprobante() + " registrada.");
                    ejecutarLista();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al guardar en la base de datos.");
                }  
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Verifique los montos de pago y vuelto.");
            }
        }
    }
    private void ejecutarBtnVerDetalle(){
        int filaSeleccionada = tabla.getSelectedRow();
        int item = 1;
        if(filaSeleccionada != -1){
            int idVenta = listaVentas.get(filaSeleccionada).getId();
            
            List<DetalleVenta> detalle = controlDetalleVenta.lista(idVenta);
            modeloVerDetalle.setRowCount(0);
            
            for(DetalleVenta dv : detalle){
                Object[] fila = {
                    item,
                    dv.getCantidad(),
                    dv.getProducto(),
                    dv.getPrecioVenta(),
                    dv.getSubtotal()
                };
                modeloVerDetalle.addRow(fila);
                item++;
            }
        }
        
    }
    private void ejecutarBtnAnular(){
        int filaSeleccionada = tabla.getSelectedRow();
        
        if (filaSeleccionada != -1) {
            int ventaId = listaVentas.get(filaSeleccionada).getId();

            int respuesta = JOptionPane.showConfirmDialog(this, 
                "¿Seguro que quieres anular la venta", 
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        
            Venta venta = new Venta();
            venta.setId(ventaId);
        
            if (respuesta == JOptionPane.YES_OPTION) {
                if (controlVenta.anularVenta(venta)){
                    JOptionPane.showMessageDialog(this, "Venta anulada con éxito");
                    ejecutarLista();
                }else {
                    JOptionPane.showMessageDialog(this, "No se pudo eliminar la venta");
                }
            }
        }else{
            JOptionPane.showMessageDialog(this, "Por favor, selecciona una venta para anular");
        }
    }
    private void limpiarCamposDetalle(){
        cbxproducto.setSelectedIndex(-1);
        txtcantidad.setText("");
    }
    private String obtenerNumeroComprobante(){
        String ultimoNum = controlVenta.obtenerUltimoComprobante();
        
        int numeroActual = Integer.parseInt(ultimoNum);
        int siguienteNumero = numeroActual + 1;
        
        return String.valueOf(siguienteNumero);
    }
    private double calcularSubtotal(double cant, double precioV){
        double subtotal = cant * precioV;
        return subtotal;
    }
    private void calcularTotalPagar() {
        this.totalPagar = 0;
        for (int i = 0; i < tablaDetalle.getRowCount(); i++) {
            totalPagar += (double) tablaDetalle.getValueAt(i, 4);
        }
        txtotal.setText("S/. " + totalPagar);
    }
    private void calcularVuelto(){
        if (txtmontoRecibido.getText().trim().isEmpty()) {
            txtvuelto.setText("0.00");
            return;
        }

        double montoRecibido = Double.parseDouble(txtmontoRecibido.getText().trim());

        double vuelto = montoRecibido - totalPagar;

        if (vuelto < 0) {
            txtvuelto.setText("Monto insuficiente");
        } else {
            txtvuelto.setText(String.format(java.util.Locale.US, "%.2f", vuelto));
        }
    }
}
