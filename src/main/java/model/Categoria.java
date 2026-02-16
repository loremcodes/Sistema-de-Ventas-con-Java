
package model;

public class Categoria {
    private int id;
    private String nombreCategoria;
    
    public Categoria(){}
    public Categoria(int id, String nombreCategoria){
        this.id = id;
        this.nombreCategoria = nombreCategoria;
    }
    
    public int getId(){
        return id;
    }
    public String getNombreCategoria(){
        return nombreCategoria;
    }
    
    public void setId(int id){
        this.id = id;
    }
    public void setNombreCategoria(String nombreCategoria){
        this.nombreCategoria = nombreCategoria;
    }
    
    @Override
    public String toString() {
        return this.nombreCategoria; // Esto es lo que el usuario verá en la lista desplegable
    }
}
