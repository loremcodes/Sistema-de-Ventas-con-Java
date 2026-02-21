
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Categoria;
import model.Cliente;
import model.Producto;
import model.Usuario;
import model.Venta;
import util.ConexionBD;

public class VentaController {
    ConexionBD conexion = new ConexionBD();
    
    public boolean registrarVenta(Venta venta){
        String sql = "INSERT INTO venta(tipo_comprobante, num_comprobante, fecha_venta, total, metodo_pago, monto_recibido, vuelto, id_cliente, id_usuario) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try(Connection connection = conexion.establecer();
            PreparedStatement prepareStatement = connection.prepareStatement(sql)){
            
            prepareStatement.setString(1, venta.getTipoComprobante());
            prepareStatement.setString(2, venta.getNumComprobante());
            prepareStatement.setObject(3, venta.getFechaVenta());
            prepareStatement.setDouble(4, venta.getTotal());
            prepareStatement.setString(5, venta.getMetodoPago());
            prepareStatement.setDouble(6, venta.getMontoRecibido());
            prepareStatement.setDouble(7, venta.getVuelto());
            prepareStatement.setInt(8, venta.getCliente().getId());
            prepareStatement.setInt(9, venta.getUsuario().getId());
            
            return prepareStatement.executeUpdate()>0;
            
        }catch(SQLException e){
            System.out.println("Error al registrar venta");
            e.printStackTrace();
            return false;
        } 
    }
    public boolean anularVenta(Venta venta){
        String sql = "UPDATE venta SET estado_venta = 0 WHERE id_venta = ?";
        
        try(Connection connection = conexion.establecer();
            PreparedStatement prepareStatement = connection.prepareStatement(sql)){
            
            prepareStatement.setInt(1, venta.getEstadoVenta());
            
            return prepareStatement.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.out.println("Error al anular venta");
            e.printStackTrace();
            return false;
        }
    }
    public List<Venta> visualizarLista(){
        List<Venta> listaVentas = new ArrayList();
        String sql = "SELECT v.id_venta, v.tipo_comprobante, v.num_comprobante, v.fecha_venta, concat(c.nombres,' ', c.apellido_paterno) AS cliente, v.total, v.metodo_pago, v.monto_recibido, v.vuelto, u.nombres AS usuario, v.estado_venta FROM venta v INNER JOIN cliente c ON v.id_cliente = c.id_cliente INNER JOIN  usuario u ON v.id_usuario = u.id_usuario";
        
        try(Connection connection = conexion.establecer();
            PreparedStatement prepareStatement = connection.prepareStatement(sql)){
            
            ResultSet resultSet = prepareStatement.executeQuery();
                while(resultSet.next()){
                    Venta venta = new Venta();
                    venta.setId(resultSet.getInt("id_venta"));
                    venta.setTipoComprobante(resultSet.getString("tipo_comprobante"));
                    venta.setNumComprobante(resultSet.getString("num_comprobante"));
                    venta.setFechaVenta(resultSet.getObject("fecha_venta", java.time.LocalDateTime.class));
                    venta.setMetodoPago(resultSet.getString("metodo_pago"));
                    venta.setMontoRecibido(resultSet.getDouble("monto_recibido")); 
                    venta.setVuelto(resultSet.getDouble("vuelto"));
                    venta.setEstadoVenta(resultSet.getInt("estado_venta"));
                    
                    Cliente cliente = new Cliente();
                    cliente.setNombres(resultSet.getString("cliente"));
                    venta.setCliente(cliente);
                    
                    Usuario usuario = new Usuario();
                    usuario.setNombres(resultSet.getString("usuario"));
                    venta.setUsuario(usuario);
                    
                    listaVentas.add(venta);
                }
                
        }catch(SQLException e){
            System.out.println("Error al obtener lista de productos: "+e.getMessage());
        }
        return listaVentas;
    }
}
