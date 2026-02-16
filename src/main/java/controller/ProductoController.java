
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Categoria;
import model.Producto;
import util.ConexionBD;

public class ProductoController {
    ConexionBD conexion = new ConexionBD();
    
    public boolean registrarProducto(Producto producto){
        String sql = "INSERT INTO producto(nombre_producto,marca,descripcion,unidad_medida,precio_compra,precio_venta,stock,id_categoria) VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
        
        try(Connection connection = conexion.establecer();
            PreparedStatement prepareStatement = connection.prepareStatement(sql)){
            
            prepareStatement.setString(1, producto.getNombre());
            prepareStatement.setString(2, producto.getMarca());
            prepareStatement.setString(3, producto.getDescripcion());
            prepareStatement.setString(4, producto.getUnidadMedida());
            prepareStatement.setDouble(5, producto.getPrecioCompra());
            prepareStatement.setDouble(6, producto.getPrecioVenta());
            prepareStatement.setDouble(7, producto.getStock());
            prepareStatement.setInt(8, producto.getCategoria().getId());
            
            return prepareStatement.executeUpdate()>0;
            
        }
        catch(SQLException e){
            System.out.println("Error en: "+e.getMessage());
            e.printStackTrace();
            return false;
        }
       
    }
    public boolean eliminarProducto(Producto producto){
        String sql = "DELETE FROM producto WHERE id_producto=?";
        
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
        String sql = "SELECT p.id_producto, p.nombre_producto, p.marca, p.descripcion, p.unidad_medida, p.precio_compra, p.precio_venta, p.stock, c.nombre_categoria FROM producto p INNER JOIN categoria c on p.id_categoria = c.id_categoria;";
        
        try(Connection connection = conexion.establecer();
            PreparedStatement prepareStatement = connection.prepareStatement(sql)){
            
            ResultSet resultSet = prepareStatement.executeQuery();
                while(resultSet.next()){
                    Producto producto = new Producto();
                    producto.setId(resultSet.getInt("id_producto"));
                    producto.setNombre(resultSet.getString("nombre_producto"));
                    producto.setMarca(resultSet.getString("marca"));
                    producto.setDescripcion(resultSet.getString("descripcion"));
                    producto.setUnidadMedida(resultSet.getString("unidad_medida"));
                    producto.setPrecioCompra(resultSet.getDouble("precio_compra"));
                    producto.setPrecioVenta(resultSet.getDouble("precio_venta"));
                    producto.setStock(resultSet.getDouble("stock"));
                    
                    Categoria categoria = new Categoria();
                    categoria.setNombreCategoria(resultSet.getString("nombre_categoria"));
                    
                    producto.setCategoria(categoria);
                    listaProductos.add(producto);
                }
                
        }catch(SQLException e){
            System.out.println("Error al obtener lista de productos: "+e.getMessage());
        }
        return listaProductos;
    }
        
}
