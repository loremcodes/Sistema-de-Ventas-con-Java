
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.ConexionBD;

public class LoginController {
    
    ConexionBD conexion = new ConexionBD();
    
    public boolean validacion(String user, String pass){
        String sql = "SELECT * FROM usuario WHERE username=? AND password=?";
        try(Connection connection = conexion.establecer();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            
            preparedStatement.setString(1,user);
            preparedStatement.setString(2,pass);
            
            try(ResultSet resultset = preparedStatement.executeQuery()){
                return resultset.next();
            }
        }catch(SQLException e){
            System.out.println("Error al quere obtener los datos");
            return false;
        }
    }
}
