
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Categoria;
import util.ConexionBD;

public class CategoriaController {
    ConexionBD conexion = new ConexionBD();
    public boolean registrarCategoria(Categoria categoria){
        String sql = "INSERT INTO categoriA(nombre_categoria) VALUES(?)";
        
        try(Connection connection =  conexion.establecer();
            PreparedStatement prepareStatement = connection.prepareStatement(sql)){
            
            prepareStatement.setString(1, categoria.getNombreCategoria());
            
            int filasInsertadas = prepareStatement.executeUpdate();
            return filasInsertadas > 0;
            
        }catch(SQLException e){
            System.out.println("Error al registrar categoria: "+e.getMessage());
            return false;
        }
    }
    public boolean eliminarCategoria(Categoria categoria){
        String sql = "DELETE FROM categoria WHERE id_categoria=?";
        try(Connection connection =  conexion.establecer();
            PreparedStatement prepareStatement = connection.prepareStatement(sql)){
            
            prepareStatement.setInt(1, categoria.getId());
            
            int filasInsertadas = prepareStatement.executeUpdate();
            return filasInsertadas > 0;
            
        }catch(SQLException e){
            System.out.println("Error al registrar categoria: "+e.getMessage());
            return false;
        }
    }
    public List<Categoria> visualizarLista(){
        List<Categoria> listaCategorias = new ArrayList<>();
        String sql = "SELECT *FROM categoria";
        
        try(Connection connection = conexion.establecer();
            PreparedStatement prepareStatement = connection.prepareStatement(sql);){
            
            ResultSet resultset = prepareStatement.executeQuery();
            while(resultset.next()){
                Categoria categoria = new Categoria();
                categoria.setId(resultset.getInt("id_categoria"));
                categoria.setNombreCategoria(resultset.getString("nombre_categoria"));
                
                listaCategorias.add(categoria);
            }
            
        }catch(SQLException e){
            System.out.println("Error al obtener lista de categoria");
        }
        return listaCategorias;
        
    }
}
