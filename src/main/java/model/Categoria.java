
package model;

public class Categoria {
    private int id;
    private String nombreCategoria;
    private String descripcion;
    
    public Categoria(){}
    public Categoria(int id, String nombreCategoria, String descripcion){
        this.id = id;
        this.nombreCategoria = nombreCategoria;
        this.descripcion = descripcion;
    }
    
    public int getId(){
        return id;
    }
    public String getNombreCategoria(){
        return nombreCategoria;
    }
    public String getDescripcion(){
        return descripcion;
    }
    
    public void setId(int id){
        this.id = id;
    }
    public void setNombreCategoria(String nombreCategoria){
        this.nombreCategoria = nombreCategoria;
    }
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    
    @Override
    public String toString() {
        return this.nombreCategoria; // Esto es lo que el usuario verá en la lista desplegable
    }
}
