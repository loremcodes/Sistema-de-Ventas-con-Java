
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Producto;
import util.ConexionBD;

public class ProductoController {
    ConexionBD conexion = new ConexionBD();
    
    public boolean registrarProducto(Producto producto){
        String sql = "INSERT INTO producto(nombre,marca,descripcion,unidad_medida,precio_compra,precio_venta,stock,categoria) VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
        
        try(Connection connection = conexion.establecer();
            PreparedStatement prepareStatement = connection.prepareStatement(sql)){
            
            prepareStatement.setString(1, producto.getNombre());
            prepareStatement.setString(2, producto.getMarca());
            prepareStatement.setString(3, producto.getDescripcion());
            prepareStatement.setString(4, producto.getUnidadMedida());
            prepareStatement.setDouble(5, producto.getPrecioCompra());
            prepareStatement.setDouble(6, producto.getPrecioVenta());
            prepareStatement.setDouble(7, producto.getStock());
            prepareStatement.setString(8, producto.getCategoria());
            
            return prepareStatement.executeUpdate()>0;
            
        }
        catch(SQLException e){
            System.out.println("Error en: "+e.getMessage());
            return false;
        }
       
    }
    public boolean eliminarProducto(Producto producto){
        String sql = "DELETE FROM producto WHERE idproducto=?";
        
        try(Connection connection = conexion.establecer();
            PreparedStatement prepareStatement = connection.prepareStatement(sql)){
            
            prepareStatement.setInt(1, producto.getId());

            return prepareStatement.executeUpdate()>0;
            
        }catch(SQLException e){
            System.out.println("Error en: "+e.getMessage());
            return false;
        }
    }
    public List<Producto> visualizarLista(){
        List<Producto> listaProductos = new ArrayList();
        String sql = "SELECT *FROM producto";
        
        try(Connection connection = conexion.establecer();
            PreparedStatement prepareStatement = connection.prepareStatement(sql)){
            
            ResultSet resultSet = prepareStatement.executeQuery();
                while(resultSet.next()){
                    Producto producto = new Producto();
                    producto.setId(resultSet.getInt("idProducto"));
                    producto.setNombre(resultSet.getString("nombre"));
                    producto.setMarca(resultSet.getString("marca"));
                    producto.setDescripcion(resultSet.getString("descripcion"));
                    producto.setUnidadMedida(resultSet.getString("unidad_medida"));
                    producto.setPrecioCompra(resultSet.getDouble("precio_compra"));
                    producto.setPrecioVenta(resultSet.getDouble("precio_venta"));
                    producto.setStock(resultSet.getDouble("stock"));
                    producto.setCategoria(resultSet.getString("categoria"));
                    
                    listaProductos.add(producto);
                }
                
        }catch(SQLException e){
            System.out.println("Error al obtener lista de productos: "+e.getMessage());
        }
        return listaProductos;
    }
        
}
