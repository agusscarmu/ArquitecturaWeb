package org.example.DAO;

import org.example.Conexion;
import org.example.objs.Factura;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class FacturaDAO implements DAO<Factura> {

    Conexion c = Conexion.getInstance();

    public FacturaDAO(String archivoCSV){

    }
    @Override
    public void crear() throws Exception {
        c.conectar();
        PreparedStatement ps = c.conn().prepareStatement("CREATE TABLE Factura (\n" +
                "    idFactura INT PRIMARY KEY,\n" +
                "    idCliente INT,\n" +
                ");");
        ps.executeUpdate();
        c.cerrar();
    }

    @Override
    public void drop() throws Exception {
        c.conectar();
        PreparedStatement ps = c.conn().prepareStatement("DROP TABLE Factura");
        ps.executeUpdate();
        c.cerrar();
    }

    @Override
    public void insertar(Factura factura) throws Exception {
        try {
            c.conectar();
            PreparedStatement ps = c.conn().prepareStatement("INSERT INTO Factura(idFactura, idCliente) VALUES (?,?)");
            ps.setInt(1, factura.getIdFactura());
            ps.setInt(2, factura.getIdCliente());
            ps.executeUpdate();
            c.cerrar();
        }catch (SQLException e){
            crear();
            insertar(factura);
        }
    }

    @Override
    public void actualizar(Factura factura) throws Exception {
        c.conectar();
        PreparedStatement ps = c.conn().prepareStatement("UPDATE `Factura` SET `idFactura`= ?,`idCliente`= ? WHERE idFactura = ?");
        ps.setInt(1, factura.getIdFactura());
        ps.setInt(2, factura.getIdCliente());


        ps.setInt(3, factura.getIdFactura());

        ps.executeUpdate();

        c.cerrar();
    }

    @Override
    public void eliminar(Factura factura) throws Exception {
        c.conectar();
        PreparedStatement ps = c.conn().prepareStatement("DELETE FROM Factura WHERE idFactura = ?");
        ps.setInt(1, factura.getIdFactura());
        ps.executeUpdate();
        c.cerrar();
    }

    @Override
    public LinkedList<Factura> listar() throws Exception {
        LinkedList<Factura> s = new LinkedList<>();
        c.conectar();
        PreparedStatement ps = c.conn().prepareStatement("SELECT * FROM Factura");
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            Factura f = new Factura(rs.getInt(1), rs.getInt(2));
            s.add(f);
        }
        c.cerrar();

        return s;
    }
}
