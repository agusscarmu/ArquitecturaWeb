package org.example.DAO;

import org.example.Conexion;
import org.example.objs.Cliente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class ClienteDAO implements DAO<Cliente> {

    Conexion c = Conexion.getInstance();

    public ClienteDAO(String archivoCSV){

    }
    @Override
    public void crear() throws Exception {
        c.conectar();
        PreparedStatement ps = c.conn().prepareStatement("CREATE TABLE Cliente (\n" +
                "    idCliente INT PRIMARY KEY,\n" +
                "    nombre VARCHAR(500),\n" +
                "    email VARCHAR(150)\n" +
                ");");
        ps.executeUpdate();
        c.cerrar();
    }

    @Override
    public void drop() throws Exception {
        c.conectar();
        PreparedStatement ps = c.conn().prepareStatement("DROP TABLE Cliente");
        ps.executeUpdate();
        c.cerrar();
    }

    @Override
    public void insertar(Cliente cliente) throws Exception {
        try {
            c.conectar();
            PreparedStatement ps = c.conn().prepareStatement("INSERT INTO Cliente(idCliente, nombre, email) VALUES (?,?,?)");
            ps.setInt(1, cliente.getIdCliente());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getMail());
            ps.executeUpdate();
            c.cerrar();
        }catch (SQLException e){
            crear();
            insertar(cliente);
        }
    }

    @Override
    public void actualizar(Cliente cliente) throws Exception {
        c.conectar();
        PreparedStatement ps = c.conn().prepareStatement("UPDATE `Cliente` SET `idCliente`= ?,`nombre`= ?,`email`= ? WHERE idCliente = ?");
        ps.setInt(1, cliente.getIdCliente());
        ps.setString(2, cliente.getNombre());
        ps.setString(3, cliente.getMail());

        ps.setInt(4, cliente.getIdCliente());

        ps.executeUpdate();

        c.cerrar();
    }

    @Override
    public void eliminar(Cliente cliente) throws Exception {
        c.conectar();
        PreparedStatement ps = c.conn().prepareStatement("DELETE FROM Cliente WHERE idCliente = ?");
        ps.setInt(1, cliente.getIdCliente());
        ps.executeUpdate();
        c.cerrar();
    }

    @Override
    public LinkedList<Cliente> listar() throws Exception {
        LinkedList<Cliente> s = new LinkedList<>();
        c.conectar();
        PreparedStatement ps = c.conn().prepareStatement("SELECT * FROM Cliente");
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            Cliente cliente = new Cliente(rs.getInt(1),rs.getString(2), rs.getString(3));
            s.add(cliente);
        }
        c.cerrar();

        return s;
    }
}