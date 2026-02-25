
package model;

public class Producto {
    private int id;
    private String nombre, marca, descripcion, unidadMedida;
    private Categoria categoria;
    private double precioCompra, precioVenta, stock;
    
    public Producto(){  }
    public Producto(int id, String nombre, String marca, String descripcion, String unidadMedida, 
            double precioCompra, double precioVenta, double stock, String categoria){
        this.id = id;
        this.marca = marca;
        this.descripcion = descripcion;
        this.unidadMedida = unidadMedida;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.stock = stock;
        //this.categoria = categoria;
    }
    
    public int getId(){ return id; }
    public String getNombre(){ return nombre; }
    public String getMarca(){ return marca; }
    public String getDescripcion(){ return descripcion; }
    public String getUnidadMedida(){ return unidadMedida; }
    public double getPrecioCompra(){ return precioCompra; }
    public double getPrecioVenta(){ return precioVenta; }
    public double getStock(){ return stock; }
    public Categoria getCategoria(){ return categoria; }
    
    public void setId(int id){ this.id = id; }
    public void setNombre(String nombre){ this.nombre = nombre; }
    public void setMarca(String marca){ this.marca = marca; }
    public void setDescripcion(String descripcion){ this.descripcion = descripcion; }
    public void setUnidadMedida(String unidadMedida){ this.unidadMedida = unidadMedida; }
    public void setPrecioCompra(double precioCompra){ this.precioCompra = precioCompra; }
    public void setPrecioVenta(double precioVenta){ 
        if(precioVenta>0){
            this.precioVenta = precioVenta; 
        }
    }
    public void setStock(double stock){ this.stock = stock; }
    public void setCategoria(Categoria categoria){ this.categoria = categoria; }

    @Override
    public String toString() {
        return descripcion;
    }
    
    
}
