
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Categoria;
import model.Cliente;
import util.ConexionBD;

public class ClienteController {
    ConexionBD conexion = new ConexionBD();
    
    public boolean registrarCliente(Cliente cliente){
        String sql = "INSERT INTO cliente(nombres, apellido_paterno, apellido_materno, dni, celular) VALUES(?, ?, ?, ?, ?)";
        
        try(Connection connection = conexion.establecer();
            PreparedStatement prepareStatement = connection.prepareStatement(sql)){
            
            prepareStatement.setString(1, cliente.getNombres());
            prepareStatement.setString(2, cliente.getApellidoPaterno());
            prepareStatement.setString(3, cliente.getApellidoMaterno());
            prepareStatement.setString(4, cliente.getDNI());
            prepareStatement.setString(5, cliente.getCelular());
            
            return prepareStatement.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.out.println("Error al registrar cliente");
            e.printStackTrace();
            return false;
        }
    }
    public boolean eliminarCliente(Cliente cliente){
        String sql = "DELETE FROM cliente WHERE id_cliente=?";
        try(Connection connection = conexion.establecer();
            PreparedStatement prepareStatement = connection.prepareStatement(sql)){
            
            prepareStatement.setInt(1, cliente.getId());
            
            return prepareStatement.executeUpdate()>0;
            
        }catch(SQLException e){
            System.out.println("Error al eliminar cliente");
            e.printStackTrace();
            return false;
        }
    }
    public List<Cliente> visualizarLista(){
        List<Cliente> listaClientes = new ArrayList();
        String sql = "SELECT * FROM cliente ";
        
        try(Connection connection = conexion.establecer();
            PreparedStatement prepareStatement = connection.prepareStatement(sql);){
            
            ResultSet resultset = prepareStatement.executeQuery();
            while(resultset.next()){
                Cliente cliente = new Cliente();
                cliente.setId(resultset.getInt("id_cliente"));
                cliente.setNombres(resultset.getString("nombres"));
                cliente.setApellidoPaterno(resultset.getString("apellido_paterno"));
                cliente.setApellidoMaterno(resultset.getString("apellido_materno"));
                cliente.setDNI(resultset.getString("dni"));
                cliente.setCelular(resultset.getString("celular"));
                
                listaClientes.add(cliente);
            }
            
        }catch(SQLException e){
            System.out.println("Error al obtener lista de clientes");
            e.printStackTrace();
        }
        return listaClientes;
    }
}
