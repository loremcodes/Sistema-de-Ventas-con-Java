
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.DetalleVenta;
import model.Usuario;
import model.Venta;
import util.ConexionBD;

public class VentaController {
    ConexionBD conexion = new ConexionBD();
    
    public boolean registrarVenta(Venta venta, List<DetalleVenta> lista){
        String sql = "INSERT INTO venta(tipo_comprobante, num_comprobante, fecha_venta, total, metodo_pago, monto_recibido, vuelto, id_cliente, id_usuario) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String sqldetalle = "INSERT INTO detalle_venta (cantidad, precio_venta, subtotal, id_venta, id_producto) VALUES (?,?,?,?,?)";
        int idGenerado = 0;
        
        try(Connection connection = conexion.establecer();){
            
            try(PreparedStatement prepareStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                connection.setAutoCommit(false);
            
                prepareStatement.setString(1, venta.getTipoComprobante());
                prepareStatement.setString(2, venta.getNumComprobante());
                prepareStatement.setObject(3, venta.getFechaVenta());
                prepareStatement.setDouble(4, venta.getTotal());
                prepareStatement.setString(5, venta.getMetodoPago());
                prepareStatement.setDouble(6, venta.getMontoRecibido());
                prepareStatement.setDouble(7, venta.getVuelto());
                prepareStatement.setInt(8, venta.getCliente().getId());
                prepareStatement.setInt(9, venta.getUsuario().getId());
            
                prepareStatement.executeUpdate();
            
                ResultSet resultSet = prepareStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    idGenerado = resultSet.getInt(1); 
                }
            }
            
            try( PreparedStatement prepareDetalle = connection.prepareStatement(sqldetalle);){
                for(DetalleVenta dventa : lista){
                    prepareDetalle.setDouble(1, dventa.getCantidad());
                    prepareDetalle.setDouble(2, dventa.getPrecioVenta());
                    prepareDetalle.setDouble(3, dventa.getSubtotal());
                    prepareDetalle.setInt(4, idGenerado);
                    prepareDetalle.setInt(5, dventa.getProducto().getId());
                    prepareDetalle.addBatch();
                }
                
                prepareDetalle.executeBatch();
                connection.commit();
                return true;
            }catch(SQLException e){
                connection.rollback(); // Si fallan los detalles, borramos la cabecera
                throw e;
            }

        }catch(SQLException e){
            System.out.println("Error al registrar venta y detalles");
            e.printStackTrace();
            return false;
        } 
    }
    public boolean anularVenta(Venta venta){
        String sql = "UPDATE venta SET estado_venta = 0 WHERE id_venta = ?";
        
        try(Connection connection = conexion.establecer();
            PreparedStatement prepareStatement = connection.prepareStatement(sql)){
            
            prepareStatement.setInt(1, venta.getId());
            
            return prepareStatement.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.out.println("Error al anular venta");
            e.printStackTrace();
            return false;
        }
    }
    public List<Venta> visualizarLista(){
        List<Venta> listaVentas = new ArrayList();
        String sql = "SELECT v.id_venta, v.tipo_comprobante, v.num_comprobante, v.fecha_venta, concat(c.nombres,' ', c.apellido_paterno) AS cliente, v.total, v.metodo_pago, v.monto_recibido, v.vuelto, u.nombres AS usuario, v.estado_venta FROM venta v LEFT JOIN cliente c ON v.id_cliente = c.id_cliente LEFT JOIN  usuario u ON v.id_usuario = u.id_usuario";
        
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
                    venta.setTotal(resultSet.getDouble("total"));
                    Cliente cliente = new Cliente();
                    cliente.setNombres(resultSet.getString("cliente"));
                    venta.setCliente(cliente);
                    
                    Usuario usuario = new Usuario();
                    usuario.setNombres(resultSet.getString("usuario"));
                    venta.setUsuario(usuario);
                    
                    venta.setEstadoVenta(resultSet.getInt("estado_venta"));
                    listaVentas.add(venta);
                }
                
        }catch(SQLException e){
            System.out.println("Error al obtener lista de productos: "+e.getMessage());
        }
        return listaVentas;
    }
    public String obtenerUltimoComprobante(){
        String sql = "SELECT MAX(num_comprobante) FROM venta";
        
        try (Connection connection = conexion.establecer(); 
            PreparedStatement prepareStatement = connection.prepareStatement(sql);
            
            ResultSet resultSet = prepareStatement.executeQuery()) {
        
            if (resultSet.next()) {
                String ultimo = resultSet.getString(1);
                if (ultimo != null) {
                    return ultimo;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener último comprobante: " + e.getMessage());
        }
        return "000000";
    }
}
