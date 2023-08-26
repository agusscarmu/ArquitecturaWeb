package org.example.objs;

public class Cliente {
    private int idCliente;
    private String nombre;
    private String mail;

    public Cliente(int idCliente, String nombre, String mail){
        this.idCliente=idCliente;
        this.nombre=nombre;
        this.mail=mail;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente){
        this.idCliente=idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

}
