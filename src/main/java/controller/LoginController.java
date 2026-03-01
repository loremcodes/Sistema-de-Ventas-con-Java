
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Usuario;
import org.mindrot.jbcrypt.BCrypt;
import util.ConexionBD;

public class LoginController {
    
    ConexionBD conexion = new ConexionBD();
    
    public Usuario validacion(String user, String passPlano){
        String sql = "SELECT * FROM usuario WHERE username=?";
        try(Connection connection = conexion.establecer();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            
            preparedStatement.setString(1,user);
            ResultSet resultset = preparedStatement.executeQuery();
            
            if(resultset.next()){
                String hashBD = resultset.getString("password");
                if (BCrypt.checkpw(passPlano, hashBD)) {
                    Usuario usuario = new Usuario();
                    usuario.setId(resultset.getInt("id_usuario"));
                    usuario.setNombres(resultset.getString("nombres"));
                    usuario.setUsername(resultset.getString("username")); 
                    
                    return usuario;
                }
            }
        }catch(SQLException e){
            System.out.println("Error al quere obtener los datos del login");
        }
        return null;
    }
}
