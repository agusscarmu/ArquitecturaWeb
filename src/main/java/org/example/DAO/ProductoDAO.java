package org.example.DAO;

import org.example.Conexion;
import org.example.objs.Producto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class ProductoDAO implements DAO<Producto> {

    Conexion c = Conexion.getInstance();


    @Override
    public void crear() throws Exception {
        c.conectar();
        PreparedStatement ps = c.conn().prepareStatement("CREATE TABLE Producto (\n" +
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
        PreparedStatement ps = c.conn().prepareStatement("DROP TABLE Producto");
        ps.executeUpdate();
        c.cerrar();
    }

    @Override
    public void insertar(Producto producto) throws Exception {
        try {
            c.conectar();
            PreparedStatement ps = c.conn().prepareStatement("INSERT INTO Producto(idProducto, nombre, valor) VALUES (?,?,?)");
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
    public void actualizar(Producto producto) throws Exception {
        c.conectar();
        PreparedStatement ps = c.conn().prepareStatement("UPDATE `Producto` SET `idProducto`= ?,`nombre`= ?,`valor`= ? WHERE idProducto = ?");
        ps.setInt(1, producto.getId());
        ps.setString(2, producto.getNombre());
        ps.setFloat(3, producto.getValor());

        ps.setInt(4, producto.getId());

        ps.executeUpdate();

        c.cerrar();
    }

    @Override
    public void eliminar(Producto producto) throws Exception {
        c.conectar();
        PreparedStatement ps = c.conn().prepareStatement("DELETE FROM Producto WHERE idProducto = ?");
        ps.setInt(1, producto.getId());
        ps.executeUpdate();
        c.cerrar();
    }

    @Override
    public LinkedList<Producto> listar() throws Exception {
        LinkedList<Producto> s = new LinkedList<>();
        c.conectar();
        PreparedStatement ps = c.conn().prepareStatement("SELECT * FROM Producto");
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            Producto p = new Producto(rs.getInt(1),rs.getString(2), rs.getFloat(3));
            s.add(p);
        }
        c.cerrar();

        return s;
    }
}
