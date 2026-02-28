
package view;

import controller.CategoriaController;
import controller.ProductoController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import model.Categoria;
import model.Producto;

public final class ProductoView extends JFrame{
   private JPanel panel, panelSecundario;
   private JLabel tituloPrincipal, tituloSecundario, lblnombre, lblmarca, lbldescripcion, lblprecioCompra, lblprecioVenta, lblstock, lblunidadMedida, lblcategoria;
   private JTextField txtnombre, txtmarca, txtdescripcion, txtprecioCompra, txtprecioVenta, txtstock, txtunidadMedida;
   private JButton btnFormulario, btnRegistrar, btnEliminar;
   private JComboBox<Categoria> cbxCategoria;
   private DefaultTableModel modelo;
   private JTable tabla;  
   private JScrollPane scroll;
   private final ProductoController control;
   private List<Producto> listaActual;
   
   public ProductoView(){
       control = new ProductoController();
       configurarFrame();
       initComponentes();
       eventos();
       ejecutarLista();
   }
   private void configurarFrame(){
       setTitle("Sistema de Ventas | Gestión de productos");
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
        gbc.gridwidth = 9;
        gbc.insets = new Insets(20, 10, 20, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        
        tituloPrincipal = new JLabel("Gestión de productos");
        tituloPrincipal.setFont(new Font("Inter", Font.BOLD, 24));
        panel.add(tituloPrincipal, gbc);
        
        gbc.gridwidth = 1;
        
        gbc.gridx = 7;
        gbc.gridy = 1;
        gbc.weightx = 1.0;        
        gbc.anchor = GridBagConstraints.EAST;
        btnEliminar = new JButton("Eliminar producto");
        btnEliminar.setBackground(new Color(255,100,100));
        btnEliminar.setPreferredSize(new Dimension(200, 30));
        panel.add(btnEliminar, gbc);
        
        gbc.gridx = 8;
        gbc.gridy = 1;    
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.EAST;
        btnFormulario = new JButton("Agregar producto");
        btnFormulario.setBackground(new Color(171,239,255));
        btnFormulario.setPreferredSize(new Dimension(200, 30));
        panel.add(btnFormulario, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 9;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0; 
        String[] columnas = {"N°", "Nombre", "Marca", "Descripción", "Unidad de medida", "Precio compra", 
            "Precio venta", "Stock", "Categoría"};
        modelo = new DefaultTableModel(columnas,0);
        tabla = new JTable(modelo);
        scroll = new JScrollPane(tabla);
        scroll.setPreferredSize(new Dimension(750, 200));
        panel.add(scroll, gbc);
        
        this.add(panel);
   }
   private JPanel formularioAgregar(){
        panelSecundario = new JPanel();
        panelSecundario.setLayout(new GridBagLayout());
             
        GridBagConstraints gbc = new GridBagConstraints(); 
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(30, 10, 30, 10);
        
        tituloSecundario = new JLabel("Registrar nuevo producto");
        tituloSecundario.setFont(new Font("Inter", Font.BOLD, 20));
        panelSecundario.add(tituloSecundario, gbc);
       
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        gbc.gridx=0;
        gbc.gridy=1;
        lblnombre = new JLabel("Nombre: ");
        panelSecundario.add(lblnombre, gbc);
        
        gbc.gridx=1;
        gbc.gridy=1;
        lblmarca = new JLabel("Marca: ");
        panelSecundario.add(lblmarca, gbc);
        
        gbc.gridx=2;
        gbc.gridy=1;
        lbldescripcion = new JLabel("Descripción: ");
        panelSecundario.add(lbldescripcion, gbc);
        
        gbc.gridx=0;
        gbc.gridy=2;
        txtnombre = new JTextField(15);
        txtnombre.setPreferredSize(new Dimension(0,30));
        panelSecundario.add(txtnombre, gbc);
        
        gbc.gridx=1;
        gbc.gridy=2;
        txtmarca = new JTextField(15);
        txtmarca.setPreferredSize(new Dimension(0,30));
        panelSecundario.add(txtmarca, gbc);
       
        gbc.gridx=2;
        gbc.gridy=2;
        txtdescripcion = new JTextField(15);
        txtdescripcion.setPreferredSize(new Dimension(0,30));
        panelSecundario.add(txtdescripcion, gbc);
        
        gbc.gridx=0;
        gbc.gridy=3;
        lblunidadMedida = new JLabel("Unidad de medida: ");
        panelSecundario.add(lblunidadMedida, gbc);
        
        gbc.gridx=1;
        gbc.gridy=3;
        lblprecioCompra = new JLabel("Precio Compra (S/.): ");
        panelSecundario.add(lblprecioCompra, gbc);
        
        gbc.gridx=2;
        gbc.gridy=3;
        lblprecioVenta = new JLabel("Precio Venta (S/.): ");
        panelSecundario.add(lblprecioVenta, gbc);
        
        gbc.gridx=0;
        gbc.gridy=4;
        txtunidadMedida = new JTextField(15);
        txtunidadMedida.setPreferredSize(new Dimension(0,30));
        panelSecundario.add(txtunidadMedida, gbc);
        
        gbc.gridx=1;
        gbc.gridy=4;
        txtprecioCompra = new JTextField(15);
        txtprecioCompra.setPreferredSize(new Dimension(0,30));
        panelSecundario.add(txtprecioCompra, gbc);
        
        gbc.gridx=2;
        gbc.gridy=4;
        txtprecioVenta = new JTextField(15);
        txtprecioVenta.setPreferredSize(new Dimension(0,30));
        panelSecundario.add(txtprecioVenta, gbc);
        
        gbc.gridx=0;
        gbc.gridy=5;
        lblstock = new JLabel("Stock: ");
        panelSecundario.add(lblstock, gbc);
        
        gbc.gridx=1;
        gbc.gridy=5;
        lblcategoria = new JLabel("Categoría: ");
        panelSecundario.add(lblcategoria, gbc);
        
        gbc.gridx=0;
        gbc.gridy=6;
        txtstock = new JTextField(15);
        txtstock.setPreferredSize(new Dimension(0,30));
        panelSecundario.add(txtstock, gbc);
        
        gbc.gridx=1;
        gbc.gridy=6;
        cbxCategoria = new JComboBox<Categoria>();
        cbxCategoria.setPreferredSize(new Dimension(150,30));
        panelSecundario.add(cbxCategoria, gbc);
        
        gbc.gridy=7;
        gbc.gridwidth=3;
        gbc.insets = new Insets(30, 10, 30, 10);
        btnRegistrar = new JButton("Registrar producto");
        btnRegistrar.setBackground(new Color(171,239,255));
        btnRegistrar.setPreferredSize(new Dimension(150, 30));
        panelSecundario.add(btnRegistrar, gbc);
        
        llenarComboCategoria();
        
        btnRegistrar.addActionListener(e -> ejecutarBtnRegistrar());
        this.add(panelSecundario, BorderLayout.NORTH);
        
        return panelSecundario;
   }
   public void llenarComboCategoria(){
        cbxCategoria.removeAllItems();
        
         if (cbxCategoria == null) {
            System.err.println("Error: El JComboBox aún no ha sido inicializado.");
            return; 
        }
        Categoria seleccion = new Categoria();
        seleccion.setNombreCategoria("Seleccionar..."); 
        seleccion.setId(0); 
        
        this.cbxCategoria.addItem(seleccion);

        CategoriaController categoria = new CategoriaController();
        List<Categoria> lista = categoria.visualizarLista();
      
        for(Categoria cat: lista){
           cbxCategoria.addItem(cat);
        }
   }
   private void limpiarCampos(){
       txtnombre.setText(""); txtmarca.setText(""); txtdescripcion.setText(""); txtprecioCompra.setText(""); txtprecioVenta.setText(""); txtstock.setText(""); txtunidadMedida.setText("");
   }
   private void eventos(){
       btnFormulario.addActionListener(e -> {
        JDialog dialogo = new JDialog(this, "Sistema de Ventas | Registrar producto", true);
        dialogo.add(formularioAgregar());
        dialogo.pack(); // Ajusta la ventana al contenido automáticamente
        dialogo.setLocationRelativeTo(this); // La centra respecto a la tabla
        dialogo.setResizable(false); // Evita que el usuario la deforme
        dialogo.setVisible(true);
        ejecutarLista();
        });
       btnEliminar.addActionListener(e -> ejecutarBtnEliminar());
   }
   private void ejecutarBtnRegistrar(){
       Categoria categSeleccionada = (Categoria) cbxCategoria.getSelectedItem();
       if(categSeleccionada == null || categSeleccionada.getId()==0){
           JOptionPane.showMessageDialog(null, "Por favor, seleccione una categoría válida");
           return;
       }
           
       Producto producto = new Producto();
       
       producto.setNombre(txtnombre.getText());
       producto.setMarca(txtmarca.getText());
       producto.setDescripcion(txtdescripcion.getText());
       producto.setUnidadMedida(txtunidadMedida.getText());
       producto.setPrecioCompra(Double.parseDouble(txtprecioCompra.getText()));
       producto.setPrecioVenta(Double.parseDouble(txtprecioVenta.getText()));
       producto.setStock(Double.parseDouble(txtstock.getText()));
       producto.setCategoria(categSeleccionada);
       
       if(control.registrarProducto(producto)){
           JOptionPane.showMessageDialog(null, "Producto registrado");
           limpiarCampos();
       }else{
           JOptionPane.showMessageDialog(null, "Error al registrar producto");
           
       }
   }
   private void ejecutarBtnEliminar(){
       int filaSeleccionada = tabla.getSelectedRow();
       
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un usuario de la tabla para eliminar.");
            return;
        }
        
        if (this.listaActual == null || filaSeleccionada >= this.listaActual.size()) {
            JOptionPane.showMessageDialog(this, "Error: La lista de datos no está sincronizada.");
            ejecutarLista();
            return;
        }
        
        int id = listaActual.get(filaSeleccionada).getId();
        
        int respuesta = JOptionPane.showConfirmDialog(this, 
            "¿Seguro que quieres eliminar el producto?", 
            "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        
        if (respuesta == JOptionPane.YES_OPTION) {
            Producto producto = new Producto();
            producto.setId(id);

            if (control.eliminarProducto(producto)) {
                JOptionPane.showMessageDialog(this, "Producto eliminado correctamente");
                ejecutarLista();
            } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar: El producto podría estar vinculado a una venta.");
            }
        }
   }
   private void ejecutarLista(){
        modelo.setRowCount(0);
        int item = 1;
        this.listaActual = control.visualizarLista();
        
        for (Producto producto: listaActual) {
            Object[] fila = {
            item,
            producto.getNombre(),
            producto.getMarca(),
            producto.getDescripcion(),
            producto.getUnidadMedida(),
            producto.getPrecioCompra(),
            producto.getPrecioVenta(),
            producto.getStock(),
            (producto.getCategoria() != null) ? producto.getCategoria().getNombreCategoria() : "Sin Categoría" 
            };
            modelo.addRow(fila);
            item++;
        } 
   } 
}
