package org.example.DAO.DAOMySQL;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.ConexionMySql;
import org.example.DAO.DAO;
import org.example.objs.Producto;

import java.io.FileReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class ProductoDAOMySQL implements DAO<Producto> {

    private ConexionMySql c;

    public ProductoDAOMySQL(String archivoCSV, ConexionMySql c) throws Exception {
        String csvFilePath = System.getProperty("user.dir") + "/"+archivoCSV;
        this.c=c;
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(csvFilePath));

        for(CSVRecord row: parser) {
            Producto p = new Producto(Integer.parseInt(row.get("idProducto")),row.get("nombre"),Integer.parseInt(row.get("valor")));
            insertar(p);
        }
    }

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

    public Producto masRecaudo() throws Exception {
        c.conectar();
        PreparedStatement ps = c.conn().prepareStatement("SELECT p.* FROM Factura_Producto fp" +
                                                            " INNER JOIN Producto p ON fp.idProducto=p.idProducto" +
                                                            " GROUP BY fp.idProducto" +
                                                            " ORDER BY sum(fp.cantidad * p.valor) DESC LIMIT 1");
        ResultSet rs = ps.executeQuery();
        rs.next();
        Producto p = new Producto(rs.getInt(1),rs.getString(2), rs.getFloat(3));
        c.cerrar();
        return p;
    }
}
