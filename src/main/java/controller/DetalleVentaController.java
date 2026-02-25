
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Categoria;
import model.DetalleVenta;
import model.Producto;
import util.ConexionBD;

public class DetalleVentaController {
    ConexionBD conexion = new ConexionBD();
    
    public boolean registrarDetalle(DetalleVenta dventa){
        String sql = "INSERT INTO detalle_venta(cantidad, precio_venta, subtotal, idventa, idproducto) VALUES(?, ?, ?, ?, ?)";
        
        try(Connection connection = conexion.establecer();
            PreparedStatement prepareStatement = connection.prepareStatement(sql)){
            
            prepareStatement.setDouble(1, dventa.getCantidad());
            prepareStatement.setDouble(2, dventa.getPrecioVenta());
            prepareStatement.setDouble(3, dventa.getSubtotal());
            prepareStatement.setInt(4, dventa.getIdVenta());
            prepareStatement.setInt(5, dventa.getProducto().getId());
            
            return prepareStatement.executeUpdate() > 1;
            
        }catch(SQLException e){
            System.out.println("Error al registrar Detalla venta");
            e.printStackTrace();
            return false;
        }
    }
    public List<DetalleVenta> lista(){
         List<DetalleVenta> listaDetalles = new ArrayList();
         
         String sql = "SELECT d.cantidad p.nombre_producto d.precio_venta FROM detalle_venta d INNER JOIN producto p ON d.id_producto = p.id_producto";
         try(Connection connection = conexion.establecer();
             PreparedStatement prepareStatement = connection.prepareStatement(sql)){
             
             ResultSet resultSet = prepareStatement.executeQuery();
                while(resultSet.next()){
                    DetalleVenta detalleVenta = new DetalleVenta();
                    detalleVenta.setCantidad(resultSet.getDouble("cantidad"));
                    Producto producto = new Producto();
                    producto.setDescripcion(resultSet.getString("descripcion"));
                    detalleVenta.setProducto(producto);
                    detalleVenta.setPrecioVenta(resultSet.getDouble("precio_venta"));
                    detalleVenta.setSubtotal(resultSet.getDouble("subtotal"));

                    listaDetalles.add(detalleVenta);
                }
             
         }catch(SQLException e){
             System.out.println("Error al obtener lista de Detalle Ventas: "+e.getMessage());
         }
         return listaDetalles;
    }
    
}
