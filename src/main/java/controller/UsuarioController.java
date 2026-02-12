
package controller;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Usuario;
import util.ConexionBD;
import org.mindrot.jbcrypt.BCrypt;

public class UsuarioController {
    ConexionBD conexion = new ConexionBD();
  
    public boolean registrarUsuario(Usuario usuario){
        String sql = "INSERT INTO usuario(nombres, apellido_paterno, apellido_materno, rol, username, password) VALUES (?, ?, ?, ?, ?, ?)";
        
        try(Connection connection = conexion.establecer();
            PreparedStatement prepareStatement = connection.prepareStatement(sql);){
            
            prepareStatement.setString(1, usuario.getNombres());
            prepareStatement.setString(2, usuario.getApellidoPaterno());
            prepareStatement.setString(3, usuario.getApellidoMaterno());
            prepareStatement.setString(4, usuario.getRol());
            prepareStatement.setString(5, usuario.getUsername());
            //encriptación de la contraseña que registra el usuario con BCrypt
            String passwordHashed = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());
            prepareStatement.setString(6, passwordHashed);
            
            int filasInsertadas = prepareStatement.executeUpdate();
            return filasInsertadas > 0;
            
        }catch(SQLException e){
            System.out.println("Error al querer realizar la operación");
            return false;
        }
    }
    public boolean eliminarUsuario(Usuario usuario){
        String sql = "DELETE FROM usuario WHERE id_usuario=? ";
        
        try(Connection connection = conexion.establecer();
            PreparedStatement prepareStatement = connection.prepareStatement(sql)){
            
            prepareStatement.setInt(1, usuario.getId());
            return prepareStatement.executeUpdate()>0;
            
        }catch(SQLException e){
            System.out.println("Error al querer realizar la operación");
            return false;
        }
    }
    public List<Usuario> actualizarLista(){
        List<Usuario> listaUsuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        
        try(Connection connection = conexion.establecer();
            PreparedStatement prepareStatement = connection.prepareStatement(sql);){
            
            ResultSet resultset = prepareStatement.executeQuery();
                while(resultset.next()){
                    Usuario usuario = new Usuario();
                    usuario.setId(resultset.getInt("id_usuario"));
                    usuario.setNombres(resultset.getString("nombres"));
                    usuario.setApellidoPaterno(resultset.getString("apellido_paterno"));
                    usuario.setApellidoMaterno(resultset.getString("apellido_materno"));
                    usuario.setRol(resultset.getString("rol"));
                    usuario.setUsername(resultset.getString("username"));
                    
                    listaUsuarios.add(usuario);
                }
                
        }catch(SQLException e){
            System.out.println("Error al obtener lista de usuarios");
        }
        return listaUsuarios;
    }
}
    