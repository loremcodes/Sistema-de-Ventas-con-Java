
package model;

import java.time.LocalDateTime;

public class Venta {
    private int id;
    private String tipoComprobante;
    private String num_comprobante;
    private LocalDateTime fecha_venta;
    private double total; 
    private String metodoPago;
    private double montoRecibido;
    private double vuelto;
    private Cliente cliente;
    private Usuario usuario;
    private int estadoVenta;

    public Venta(){}
    public Venta(int id, String tipoComprobante, String num_comprobante, LocalDateTime fecha_venta, double total, String metodoPago, double montoRecibido, double vuelto, Cliente cliente, Usuario usuario, int estadoVenta) {
        this.id = id;
        this.tipoComprobante = tipoComprobante;
        this.num_comprobante = num_comprobante;
        this.fecha_venta = fecha_venta;
        this.total = total;
        this.metodoPago = metodoPago;
        this.montoRecibido = montoRecibido;
        this.vuelto = vuelto;
        this.cliente = cliente;
        this.usuario = usuario;
        this.estadoVenta = estadoVenta;
    }

    public int getId() {
        return id;
    }
    public String getTipoComprobante() {
        return tipoComprobante;
    }
    public String getNumComprobante() {
        return num_comprobante;
    }
    public LocalDateTime getFechaVenta() {
        return fecha_venta;
    }
    public double getTotal() {
        return total;
    }
    public String getMetodoPago() {
        return metodoPago;
    }
    public double getMontoRecibido() {
        return montoRecibido;
    }
    public double getVuelto() {
        return vuelto;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public int getEstadoVenta() {
        return estadoVenta;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }
    public void setNumComprobante(String num_comprobante) {
        this.num_comprobante = num_comprobante;
    }
    public void setFechaVenta(LocalDateTime fecha_venta) {
        this.fecha_venta = fecha_venta;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
    public void setMontoRecibido(double montoRecibido) {
        this.montoRecibido = montoRecibido;
    }
    public void setVuelto(double vuelto) {
        this.vuelto = vuelto;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public void setEstadoVenta(int estadoVenta) {
        this.estadoVenta = estadoVenta;
    }
    
    
    
    
    
    
}
