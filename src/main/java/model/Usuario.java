
package model;

public class Usuario {
    private int id;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String rol;
    private String username;
    private String password;
    private String estado;
    
    public Usuario(){}
    public Usuario(int id, String nombres, String apellidoPaterno, String apellidoMaterno, String rol, 
            String username, String password){
        this.id = id;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.rol = rol;
        this.username = username;
        this.password = password;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }
    public String getNombres() {
        return nombres;
    }
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }
    public String getRol() {
        return rol;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    
    public void setId(int id) {
        this.id = id;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
