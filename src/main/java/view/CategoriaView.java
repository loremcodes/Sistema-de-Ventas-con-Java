
package view;

import controller.CategoriaController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Categoria;
import model.Producto;

public class CategoriaView extends JFrame{
    private JPanel panel, panelFormulario;
    private JButton btnFormulario, btnEliminar, btnRegistrar;
    private JLabel lblnombre, lbldescripcion, titulo, tituloFormulario;
    private JTextField txtnombre, txtdescripcion;
    private DefaultTableModel modelo;
    private JTable tabla;
    private JScrollPane scroll;
    private final CategoriaController control;
    private List<Categoria> listaActual;
    
    public CategoriaView(){
        control = new CategoriaController();
        configurarFrame();
        initComponentes();
        eventos();
        ejecutarLista();
    }
    public void configurarFrame(){
        setTitle("Sistema de Ventas | Categorías");
        setSize(600,500);
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
        gbc.gridwidth = 3;
        gbc.insets = new Insets(20, 10, 20, 10); // margen 
        gbc.anchor = GridBagConstraints.CENTER;
       
        titulo = new JLabel("Categorías");
        titulo.setFont(new Font("Inter", Font.BOLD, 24));
        panel.add(titulo, gbc);
        
        gbc.gridwidth = 1;
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        btnEliminar = new JButton();
        btnEliminar.setText("Eliminar categoria");
        btnEliminar.setBackground(new Color(255,100,100));
        btnEliminar.setPreferredSize(new Dimension(200, 30));
        panel.add(btnEliminar, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        btnFormulario = new JButton();
        btnFormulario.setText("Registrar categoria");
        btnFormulario.setBackground(new Color(171,239,255));
        btnFormulario.setPreferredSize(new Dimension(200, 30));
        panel.add(btnFormulario, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        String[] columnas = {"N°", "Nombre","Descripción"};
        modelo = new DefaultTableModel(columnas,0);
        tabla = new JTable(modelo);
        scroll = new JScrollPane(tabla);
        scroll.setPreferredSize(new Dimension(750, 200));
        panel.add(scroll, gbc);
        
        this.add(panel); 
    }
    public JPanel formularioAgregar(){
        panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints(); 
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(30, 10, 30, 10); // margen 
        
        tituloFormulario = new JLabel("Registrar nueva caategoria");
        tituloFormulario .setFont(new Font("Inter", Font.BOLD, 20));
        panelFormulario.add(tituloFormulario, gbc);
        
        gbc.gridy = 1;
        lblnombre = new JLabel("Nombre: ");
        panelFormulario.add(lblnombre,gbc);
        
        gbc.gridy = 2;
        txtnombre = new JTextField(15);
        txtnombre.setPreferredSize(new Dimension(0,30));
        panelFormulario.add(txtnombre,gbc);
        
        gbc.gridy = 3;
        lbldescripcion = new JLabel("Descripcion: ");
        panelFormulario.add(lbldescripcion,gbc);
        
        gbc.gridy = 4;
        txtdescripcion = new JTextField(15);
        txtdescripcion.setPreferredSize(new Dimension(0,30));
        panelFormulario.add(txtdescripcion,gbc);
        
        gbc.gridy = 5;
        btnRegistrar = new JButton("Registrar categoria");
        btnRegistrar.setBackground(new Color(171,239,255));
        btnRegistrar.setPreferredSize(new Dimension(150, 30));
        panelFormulario.add(btnRegistrar, gbc);
        
        btnRegistrar.addActionListener(e -> ejecutarBtnRegistrar());
        
        this.add(panelFormulario);
        return panelFormulario;
    }
    public void limpiarCampos(){
        txtnombre.setText(""); txtdescripcion.setText("");
    }
    public void eventos(){
        btnFormulario.addActionListener(e ->{
            JDialog dialogo = new JDialog(this, "Sistema de Ventas | Registrar categoria", true);
            dialogo.add(formularioAgregar());
            dialogo.pack(); // Ajusta la ventana al contenido automáticamente
            dialogo.setLocationRelativeTo(this); // La centra respecto a la tabla
            dialogo.setResizable(false); // Evita que el usuario la deforme
            dialogo.setVisible(true);
            ejecutarLista();
        });
        btnEliminar.addActionListener(e -> ejecutarBtnEliminar());
    }
    public void ejecutarBtnRegistrar(){
        Categoria categoria = new Categoria();
        if(txtnombre.getText().trim().isEmpty() || txtdescripcion.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Por favor completar todos los campos");
        }else{
            categoria.setNombreCategoria(txtnombre.getText());
            categoria.setDescripcion(txtdescripcion.getText());
            
            if(control.registrarCategoria(categoria)){
                JOptionPane.showMessageDialog(null, "Categoria registrado");
                limpiarCampos();
            }else{
                JOptionPane.showMessageDialog(null, "Error al registrar categoria");
            }
        }
        ejecutarLista();
    }
    public void ejecutarBtnEliminar(){
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
            Categoria categoria = new Categoria();
            categoria.setId(id);

            if (control.eliminarCategoria(categoria)) {
                JOptionPane.showMessageDialog(this, "Producto eliminado correctamente");
                ejecutarLista();
            } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar: El producto podría estar vinculado a una venta.");
            }
        } 
    }
    public void ejecutarLista(){
        modelo.setRowCount(0);
        int item = 1;
        this.listaActual = control.visualizarLista();
        for(Categoria categoria : listaActual){
            Object[] fila = {
                item,
                categoria.getNombreCategoria(),
                categoria.getDescripcion(),
            };
            modelo.addRow(fila);
            item++;
        }
    }
}
