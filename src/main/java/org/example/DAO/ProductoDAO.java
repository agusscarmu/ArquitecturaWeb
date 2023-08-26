package org.example.DAO;

import org.example.Conexion;
import org.example.objs.Producto;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductoDAO implements CRUD<Producto> {

    Conexion c = Conexion.getInstance();


    @Override
    public void crear() throws Exception {
        c.conectar();
        PreparedStatement ps = c.conn().prepareStatement("CREATE TABLE producto (\n" +
                "    idProducto INT PRIMARY KEY,\n" +
                "    nombre VARCHAR(45),\n" +
                "    valor FLOAT\n" +
                ");");
        ps.executeUpdate();
        c.cerrar();
    }

    @Override
    public void drop() throws Exception {
        c.conectar();
        PreparedStatement ps = c.conn().prepareStatement("DROP TABLE producto");
        ps.executeUpdate();
        c.cerrar();
    }

    @Override
    public void insertar(Producto producto) throws Exception {
        try {
            c.conectar();
            PreparedStatement ps = c.conn().prepareStatement("INSERT INTO producto(idProducto, nombre, valor) VALUES (?,?,?)");
            ps.setInt(1, producto.getId());
            ps.setString(2, producto.getNombre());
            ps.setFloat(3, producto.getValor());
            ps.executeUpdate();
            c.cerrar();
        }catch (SQLException e){
            crear();
            insertar(producto);
        }
    }

    @Override
    public void seleccionar(Producto producto) throws Exception {

    }

    @Override
    public void actualizar(Producto producto) throws Exception {

    }

    @Override
    public void eliminar(Producto producto) throws Exception {

    }

    @Override
    public void listar(Producto producto) throws Exception {

    }
}
