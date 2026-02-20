
package model;

public class Cliente {
    private int id;
    private String nombres;
    private String apellido_paterno;
    private String apellido_materno;
    private String dni;
    private String celular;
    
    public Cliente(){}
    public Cliente(int id, String nombres, String apellido_paterno, String apellido_materno, String dni, String celular){
        this.id = id;
        this.nombres = nombres;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.dni = dni;
        this.celular = celular;
    }

    public int getId() {
        return id;
    }
    public String getNombres() {
        return nombres;
    }
    public String getApellidoPaterno() {
        return apellido_paterno;
    }
    public String getApellidoMaterno() {
        return apellido_materno;
    }
    public String getDNI() {
        return dni;
    }
    public String getCelular() {
        return celular;
    }
    
    public void setId(int id){
        this.id = id;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public void setApellidoPaterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }
    public void setApellidoMaterno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }
    public void setDNI(String dni) {
        this.dni = dni;
    }
    public void setCelular(String celular) {
        this.celular = celular;
    }
}
