
package model;

public class Login {
    private static Usuario usuarioLogueado;
    
    public static void setUsuario(Usuario usuario) {
        usuarioLogueado = usuario;
    }

    public static Usuario getUsuario() {
        return usuarioLogueado;
    }
}
