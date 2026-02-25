
package model;

public class DetalleVenta {
    private int idVenta;
    private Producto producto;
    private double cantidad;
    private double precioVenta;
    private double subtotal;
    
    public DetalleVenta(){}
    public DetalleVenta(int idVenta, Producto producto, double cantidad, double precioVenta, double subtotal) {
        this.idVenta = idVenta;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
        this.subtotal = subtotal;
    }

    public int getIdVenta() {
        return idVenta;
    }
    public Producto getProducto() {
        return producto;
    }
    public double getCantidad() {
        return cantidad;
    }
    public double getPrecioVenta() {
        return precioVenta;
    }
    public double getSubtotal() {
        return subtotal;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
