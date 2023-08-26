package org.example.objs;


public class Producto{
    private int id;
    private String nombre;
    private float valor;

    public Producto(int id, String nombre, float valor) throws Exception {
        this.id=id;
        this.nombre=nombre;
        this.valor=valor;
    }

    public void setId(int id){
        this.id=id;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public void setValor(float valor){
        this.valor=valor;
    }

    public int getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public float getValor(){
        return valor;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", valor=" + valor +
                '}';
    }
}
