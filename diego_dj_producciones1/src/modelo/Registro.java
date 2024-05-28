/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author JOTACE
 */
public class Registro {
    private int codigo;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private String tipo_evento;
    private int cantidad_invitados;

   // constructor vacio
    public Registro() {
    }
    
    //  constructor agregar
    public Registro(String nombre, String apellido, String telefono, String correo, String tipo_evento, int cantidad_invitados) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.tipo_evento = tipo_evento;
        this.cantidad_invitados = cantidad_invitados;
    }
    
    // constructor actualizar 

    public Registro(int codigo, String nombre, String apellido, String telefono, String correo, String tipo_evento, int cantidad_invitados) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.tipo_evento = tipo_evento;
        this.cantidad_invitados = cantidad_invitados;
    }
    
    // getter y setter

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTipo_evento() {
        return tipo_evento;
    }

    public void setTipo_evento(String tipo_evento) {
        this.tipo_evento = tipo_evento;
    }

    public int getCantidad_invitados() {
        return cantidad_invitados;
    }

    public void setCantidad_invitados(int cantidad_invitados) {
        this.cantidad_invitados = cantidad_invitados;
    }
    
} // fin clase producto  
