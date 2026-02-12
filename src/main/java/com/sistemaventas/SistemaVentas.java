
package com.sistemaventas;

import util.ConexionBD;
import view.LoginView;
import view.ProductoView;
import view.UsuarioView;
import view.VentaView;

public class SistemaVentas {

    public static void main(String[] args) {
        /*LoginView login = new LoginView();
        login.setVisible(true);*/
        
        UsuarioView usuario = new UsuarioView();
        usuario.setVisible(true);
        
        /*ProductoView producto = new ProductoView();
        producto.setVisible(true);*/
        
        /*VentaView venta = new VentaView();
        venta.setVisible(true);*/
    }
}
