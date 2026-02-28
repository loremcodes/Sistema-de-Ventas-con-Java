
package view;

import controller.ClienteController;
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
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import model.Cliente;
import model.Usuario;

public class ClienteView extends JFrame {
    private JPanel panel, panelSecundario;
    private JLabel titulo, tituloFormulario, lblnombres, lblapellidoPaterno, lblapellidoMaterno, lbldni, lblcelular;
    private JTextField txtnombres, txtapellidoPaterno, txtapellidoMaterno, txtdni, txtcelular;
    private JButton btnFormulario, btnRegistrar, btnEliminar;
    private DefaultTableModel modelo;
    private JTable tabla;
    private JScrollPane scroll;
    private final ClienteController control;
    
    public ClienteView(){
        control = new ClienteController();
        
        configurarFrame();
        initComponentes();
        eventos();
        ejecutarLista();
    }
    public void configurarFrame(){
        setTitle("Sistema de Ventas | Gestión de clientes");
       setSize(600,700);
       setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       
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
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;  
        gbc.anchor = GridBagConstraints.WEST;
        btnEliminar = new JButton("Eliminar cliente");
        btnEliminar.setBackground(new Color(255,100,100));
        btnEliminar.setPreferredSize(new Dimension(200, 30));
        panel.add(btnEliminar, gbc);
        
        gbc.gridx = 5;
        gbc.gridy = 1;    
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.EAST;
        btnFormulario = new JButton("Agregar cliente");
        btnFormulario.setBackground(new Color(171,239,255));
        btnFormulario.setPreferredSize(new Dimension(200, 30));
        panel.add(btnFormulario, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 6;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0; 
        gbc.weighty = 1.0; 
        String[] columnas = {"N°", "Nombres", "Apellido paterno", "Apellido materno", "DNI", "Celular"};
        modelo = new DefaultTableModel(columnas,0);
        tabla = new JTable(modelo);
        scroll = new JScrollPane(tabla);
        scroll.setPreferredSize(new Dimension(500, 200));
        panel.add(scroll, gbc);
        
       this.add(panel); 
    }
    public JPanel panelFormulario(){
        panelSecundario = new JPanel();
        panelSecundario.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints(); 
        gbc.gridx = 0; 
        gbc.gridy = 0; 
        gbc.insets = new Insets(20, 10, 20, 10); // margen 
        gbc.anchor = GridBagConstraints.CENTER;
        
        gbc.gridwidth = 3;
        tituloFormulario = new JLabel("Registrar nuevo cliente");
        tituloFormulario.setFont(new Font("Inter", Font.BOLD, 20));
        panelSecundario.add(tituloFormulario, gbc);
        
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        lblnombres = new JLabel("Nombres: ");
        panelSecundario.add(lblnombres, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        lblapellidoPaterno = new JLabel("Apellido paterno: ");
        panelSecundario.add(lblapellidoPaterno, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 1;
        lblapellidoMaterno = new JLabel("Apellido materno: ");
        panelSecundario.add(lblapellidoMaterno, gbc);
            
        gbc.gridx = 0;
        gbc.gridy = 2;
        txtnombres = new JTextField(15);
        txtnombres.setPreferredSize(new Dimension(0,30));
        panelSecundario.add(txtnombres, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        txtapellidoPaterno = new JTextField(15);
        txtapellidoPaterno.setPreferredSize(new Dimension(0,30));
        panelSecundario.add(txtapellidoPaterno, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 2;
        txtapellidoMaterno = new JTextField(15);
        txtapellidoMaterno.setPreferredSize(new Dimension(0,30));
        panelSecundario.add(txtapellidoMaterno, gbc);
         
        gbc.gridx = 0;
        gbc.gridy = 3;
        lbldni = new JLabel("N° DNI: ");
        panelSecundario.add(lbldni, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        lblcelular = new JLabel("Celular: ");
        panelSecundario.add(lblcelular, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        txtdni = new JTextField(15);
        txtdni.setPreferredSize(new Dimension(0,30));
        panelSecundario.add(txtdni, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 4;
        txtcelular = new JTextField(15);
        txtcelular.setPreferredSize(new Dimension(0,30));
        panelSecundario.add(txtcelular, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 5;
        btnRegistrar = new JButton("Registrar cliente");
        btnRegistrar.setBackground(new Color(171,239,255));
        btnRegistrar.setPreferredSize(new Dimension(150, 30));
        gbc.insets = new Insets(30, 10, 30, 10);
        panelSecundario.add(btnRegistrar, gbc);
       
        btnRegistrar.addActionListener(e -> ejecutarBtnRegistrar());
        this.add(panelSecundario, BorderLayout.NORTH);
        
        return panelSecundario;
    }
    public void limpiarCampos(){
        txtnombres.setText(""); txtapellidoPaterno.setText(""); txtapellidoMaterno.setText(""); txtdni.setText(""); txtcelular.setText("");
    }
    public void eventos(){
        btnFormulario.addActionListener(e -> {
            JDialog dialog = new JDialog(this, "Sistema de Ventas | Registrar cliente", true);
            dialog.add(panelFormulario());
            dialog.pack();
            dialog.setLocationRelativeTo(this);
            dialog.setResizable(false);
            dialog.setVisible(true);
            ejecutarLista();
        });
        btnEliminar.addActionListener(e -> ejecutarBtnEliminar());
    }
    public void ejecutarBtnRegistrar(){
        Cliente nuevoCliente = new Cliente();
        
        if(txtnombres.getText().trim().isEmpty() || txtapellidoPaterno.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Complete todos los datos del formulario");
        }else{
            nuevoCliente.setNombres(txtnombres.getText());
            nuevoCliente.setApellidoPaterno(txtapellidoPaterno.getText());
            nuevoCliente.setApellidoMaterno(txtapellidoMaterno.getText());
            nuevoCliente.setDNI(txtdni.getText());
            nuevoCliente.setCelular(txtcelular.getText());
            
            if(control.registrarCliente(nuevoCliente)){
                JOptionPane.showMessageDialog(null, "Cliente registrado");
                limpiarCampos();
            }else{
                JOptionPane.showMessageDialog(null, "Error al registrar cliente");
            }
        }
    }
    public void ejecutarBtnEliminar(){
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un cliente de la tabla para eliminar.");
            return;
        }
        
        int id = Integer.parseInt(tabla.getValueAt(filaSeleccionada, 0).toString());
        
        int respuesta = JOptionPane.showConfirmDialog(this, 
            "¿Seguro que quieres eliminar al cliente", 
            "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        
        Cliente cliente = new Cliente();
        cliente.setId(id);
        
        if (respuesta == JOptionPane.YES_OPTION) {
            if (control.eliminarCliente(cliente)){
                JOptionPane.showMessageDialog(this, "Cliente eliminado con éxito");
                ejecutarLista();
            }else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar al cliente");
            }
        }
    }
    public void ejecutarLista(){
        modelo.setRowCount(0);
        int item=1;
        List<Cliente> lista = control.visualizarLista();

        for (Cliente cliente: lista) {
            Object[] fila = {
                item,
                cliente.getNombres(),
                cliente.getApellidoPaterno(),
                cliente.getApellidoMaterno(),
                cliente.getDNI(),
                cliente.getCelular(),
            };
            modelo.addRow(fila);
            item++;
        } 
    }
    
}
