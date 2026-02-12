
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class ConexionBD {
    Connection conexion = null;
    
    String host = "localhost";
    String port = "3306";
    String BD = "java";
    
    String user = "root";
    String password = "admin";
    
    String datos = "jdbc:mysql://"+host+":"+port+"/"+BD;
    
    public Connection establecer(){
        try {
            // 1. Cargar el Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2. Establecer la conexión (Sin el cast problemático)
            conexion = DriverManager.getConnection(datos, user, password); 
            //JOptionPane.showMessageDialog(null, "Conexión a base de datos exitosa");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido acceder a la base de datos: " + e.toString());
        }
        
        // 3. RETORNAR la conexión real, no null
        return conexion; 
    }
}
