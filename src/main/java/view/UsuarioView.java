
package view;

import controller.UsuarioController;
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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import model.Usuario;

public class UsuarioView extends JFrame {
    private JPanel panel, panelFormulario;
    private JLabel titulo, tituloFormulario, lblnombres, lblapellidoPaterno, lblapellidoMaterno, lblrol, lblusuario, lblcontrasenia;
    private JTextField txtnombres, txtapellidoPaterno, txtapellidoMaterno, txtrol, txtusuario;
    private JPasswordField txtcontrasenia;
    private JButton btnFormulario, btnRegistrar, btnEliminar;
    private DefaultTableModel modelo;
    private JTable tablaUsuarios;
    private JScrollPane scrollPane;
    private final UsuarioController control;

    public UsuarioView(){
        control = new UsuarioController();
        
        configurarFrame();
        initComponentes();
        eventos();
        ejecutarLista();
    }
    private void configurarFrame(){
        setTitle("Sistemas de Ventas | Usuarios");
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
        gbc.gridwidth = 7;
        gbc.insets = new Insets(20, 10, 20, 10); // margen 
        gbc.anchor = GridBagConstraints.CENTER;
        
        titulo = new JLabel("Gestión de usuarios", SwingConstants.CENTER);
        titulo.setFont(new Font("Inter", Font.BOLD, 24));
        panel.add(titulo, gbc);
        
        gbc.gridwidth = 1;
        
        gbc.gridx = 6;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        btnFormulario = new JButton("Agregar usuario");
        btnFormulario.setBackground(new Color(171,239,255));
        btnFormulario.setPreferredSize(new Dimension(200, 30));
        panel.add(btnFormulario, gbc);
        
        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        btnEliminar = new JButton("Eliminar usuario");
        btnEliminar.setBackground(new Color(255,100,100));
        btnEliminar.setPreferredSize(new Dimension(0, 40));
        panel.add(btnEliminar, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 7; // Ocupa el ancho total del formulario
        gbc.fill = GridBagConstraints.BOTH; // La tabla debe expandirse
        gbc.weighty = 1.0; // Le da prioridad para estirarse verticalmente
        gbc.insets = new Insets(20, 10, 10, 10);

        // 1. Definir columnas
        String[] columnas = {"N°", "Nombres", "Apellido paterno", "Apellido materno", "Rol", "Usuario"};
         // 2. Modelo de datos (por ahora vacío)
        modelo = new DefaultTableModel(columnas, 0);
        // 3. Crear la tabla con el modelo
        tablaUsuarios = new JTable(modelo);
        // 4. Meter la tabla en un scroll pane
        scrollPane = new JScrollPane(tablaUsuarios);
        scrollPane.setPreferredSize(new Dimension(500, 200));

        panel.add(scrollPane, gbc);
        
        this.add(panel);   
    }
    private JPanel panelFormulario(){
        panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints(); 
        gbc.gridx = 0; 
        gbc.gridy = 0; 
        gbc.insets = new Insets(20, 10, 20, 10); // margen 
        gbc.anchor = GridBagConstraints.CENTER;
        
        gbc.gridwidth = 3;
        tituloFormulario = new JLabel("Registrar nuevo usuario");
        tituloFormulario.setFont(new Font("Inter", Font.BOLD, 20));
        panelFormulario.add(tituloFormulario, gbc);
        
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        lblnombres = new JLabel("Nombres: ");
        panelFormulario.add(lblnombres, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        lblapellidoPaterno = new JLabel("Apellido paterno: ");
        panelFormulario.add(lblapellidoPaterno, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 1;
        lblapellidoMaterno = new JLabel("Apellido materno: ");
        panelFormulario.add(lblapellidoMaterno, gbc);
        
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        txtnombres = new JTextField(15);
        txtnombres.setPreferredSize(new Dimension(0,30));
        panelFormulario.add(txtnombres, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        txtapellidoPaterno = new JTextField(15);
        txtapellidoPaterno.setPreferredSize(new Dimension(0,30));
        panelFormulario.add(txtapellidoPaterno, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 2;
        txtapellidoMaterno = new JTextField(15);
        txtapellidoMaterno.setPreferredSize(new Dimension(0,30));
        panelFormulario.add(txtapellidoMaterno, gbc);
         
        gbc.gridx = 0;
        gbc.gridy = 3;
        lblrol = new JLabel("Rol: ");
        panelFormulario.add(lblrol, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        lblusuario = new JLabel("Usuario: ");
        panelFormulario.add(lblusuario, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 3;
        lblcontrasenia = new JLabel("Contraseña: ");
        panelFormulario.add(lblcontrasenia, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        txtrol = new JTextField(15);
        txtrol.setPreferredSize(new Dimension(0,30));
        panelFormulario.add(txtrol, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 4;
        txtusuario = new JTextField(15);
        txtusuario.setPreferredSize(new Dimension(0,30));
        panelFormulario.add(txtusuario, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 4;
        txtcontrasenia = new JPasswordField(15);
        txtcontrasenia.setPreferredSize(new Dimension(0,30));
        panelFormulario.add(txtcontrasenia, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 5;
        btnRegistrar = new JButton("Registrar usuario");
        btnRegistrar.setBackground(new Color(171,239,255));
        btnRegistrar.setPreferredSize(new Dimension(150, 30));
        gbc.insets = new Insets(30, 10, 30, 10);
        panelFormulario.add(btnRegistrar, gbc);
       
       btnRegistrar.addActionListener(e -> ejecutarBtnRegistrar());
        this.add(panelFormulario, BorderLayout.NORTH);
        
        return panelFormulario;
    }
    private void eventos(){
        btnFormulario.addActionListener(e -> {
            JDialog dialog = new JDialog(this, "Sistema de Ventas | Registrar usuario", true);
            dialog.add(panelFormulario());
            dialog.pack(); // Ajusta la ventana al contenido automáticamente
            dialog.setLocationRelativeTo(this); // La centra respecto a la tabla
            dialog.setResizable(false); // Evita que el usuario la deforme
            dialog.setVisible(true);
            
            ejecutarLista();
        });
        btnEliminar.addActionListener(e -> ejecutarBtnEliminar());
    }
    private void limpiarCampos() {
        txtnombres.setText("");
        txtapellidoPaterno.setText("");
        txtapellidoMaterno.setText("");
        txtrol.setText("");
        txtusuario.setText("");
        txtcontrasenia.setText("");
    }
    private void ejecutarBtnRegistrar(){
        Usuario nuevoUsuario = new Usuario();
        if (txtnombres.getText().trim().isEmpty() || txtapellidoPaterno.getText().trim().isEmpty() || 
            txtapellidoMaterno.getText().trim().isEmpty() || txtrol.getText().trim().isEmpty() || 
            txtusuario.getText().trim().isEmpty() || new String(txtcontrasenia.getPassword()).trim().isEmpty()) {
    
                JOptionPane.showMessageDialog(null, "Complete todos los campos del formulario");
        }else{
            nuevoUsuario.setNombres(txtnombres.getText());
            nuevoUsuario.setApellidoPaterno(txtapellidoPaterno.getText());
            nuevoUsuario.setApellidoMaterno(txtapellidoMaterno.getText());
            nuevoUsuario.setRol(txtrol.getText());
            nuevoUsuario.setUsername(txtusuario.getText());
            nuevoUsuario.setPassword(new String(txtcontrasenia.getPassword())); 
        
            if(control.registrarUsuario(nuevoUsuario)){
                JOptionPane.showMessageDialog(null, "Usuario registrado");
                limpiarCampos();
            }else{
            JOptionPane.showMessageDialog(null, "Error al registrar usuario");
            }
        }
       
    }
    private void ejecutarBtnEliminar(){
        int filaSeleccionada = tablaUsuarios.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un usuario de la tabla para eliminar.");
            return;
        }
        
        int id = Integer.parseInt(tablaUsuarios.getValueAt(filaSeleccionada, 0).toString());
        String nombre = tablaUsuarios.getValueAt(filaSeleccionada, 1).toString();
        
        int respuesta = JOptionPane.showConfirmDialog(this, 
            "¿Seguro que quieres eliminar a " + nombre + "?", 
            "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        
        Usuario usuario = new Usuario();
        usuario.setId(id);
        
        if (respuesta == JOptionPane.YES_OPTION) {
            if (control.eliminarUsuario(usuario)){
                JOptionPane.showMessageDialog(this, "Usuario eliminado con éxito");
                ejecutarLista();
            }else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar el usuario.");
            }
        }
    }
    private void ejecutarLista(){
        modelo.setRowCount(0);
        
        List<Usuario> lista = control.actualizarLista();

        for (Usuario usuario: lista) {
            Object[] fila = {
                usuario.getId(),
                usuario.getNombres(),
                usuario.getApellidoPaterno(),
                usuario.getApellidoMaterno(),
                usuario.getRol(),
                usuario.getUsername(),
            };
            modelo.addRow(fila);
        } 
        
    }
}
