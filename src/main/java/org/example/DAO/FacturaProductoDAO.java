package org.example.DAO;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.Conexion;
import org.example.objs.FacturaProducto;
import org.example.objs.Producto;

import java.io.FileReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class FacturaProductoDAO implements DAO<FacturaProducto> {

    Conexion c = Conexion.getInstance();

    public FacturaProductoDAO(String archivoCSV) throws Exception {
        String csvFilePath = System.getProperty("user.dir") + "/"+archivoCSV;

        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(csvFilePath));

        for(CSVRecord row: parser) {
            FacturaProducto fp = new FacturaProducto(Integer.parseInt(row.get("idFactura")),Integer.parseInt(row.get("idProducto")),Integer.parseInt(row.get("cantidad")));
            insertar(fp);
        }
    }
    @Override
    public void crear() throws Exception {
        c.conectar();
        PreparedStatement ps = c.conn().prepareStatement("CREATE TABLE Factura_Producto (\n" +
                "    idFactura INT,\n" +
                "    idProducto INT,\n" +
                "    cantidad INT,\n" +
                "PRIMARY KEY (idFactura, idProducto),\n" +
                "FOREIGN KEY (idFactura) REFERENCES Factura(idFactura),\n" +
                "FOREIGN KEY (idProducto) REFERENCES Producto(idProducto)\n" +
                ");");
        ps.executeUpdate();
        c.cerrar();
    }

    @Override
    public void drop() throws Exception {
        c.conectar();
        PreparedStatement ps = c.conn().prepareStatement("DROP TABLE Factura_Producto");
        ps.executeUpdate();
        c.cerrar();
    }

    @Override
    public void insertar(FacturaProducto fp) throws Exception {
        try {
            c.conectar();
            PreparedStatement ps = c.conn().prepareStatement("INSERT INTO Factura_Producto(idFactura, idProducto, cantidad) VALUES (?,?,?)");
            ps.setInt(1, fp.getIdFactura());
            ps.setInt(2, fp.getIdProducto());
            ps.setInt(3, fp.getCantidad());
            ps.executeUpdate();
            c.cerrar();
        }catch (SQLException e){
            crear();
            insertar(fp);
        }
    }

    @Override
    public void actualizar(FacturaProducto fp) throws Exception {
        c.conectar();
        PreparedStatement ps = c.conn().prepareStatement("UPDATE `Factura_Producto` SET `idFactura`= ?,`idProducto`= ?, cantidad = ? WHERE idFactura = ? AND idProducto = ?");
        int idFactura = fp.getIdFactura();
        int idProducto = fp.getIdProducto();
        ps.setInt(1, idFactura);
        ps.setInt(2, idProducto);
        ps.setInt(3, fp.getCantidad());

        ps.setInt(4, idFactura);
        ps.setInt(5, idProducto);

        ps.executeUpdate();

        c.cerrar();
    }

    @Override
    public void eliminar(FacturaProducto fp) throws Exception {
        c.conectar();
        PreparedStatement ps = c.conn().prepareStatement("DELETE FROM Factura_Producto WHERE idFactura = ? AND idProducto = ?");
        ps.setInt(1, fp.getIdFactura());
        ps.setInt(2, fp.getIdProducto());
        ps.executeUpdate();
        c.cerrar();
    }

    @Override
    public LinkedList<FacturaProducto> listar() throws Exception {
        LinkedList<FacturaProducto> s = new LinkedList<>();
        c.conectar();
        PreparedStatement ps = c.conn().prepareStatement("SELECT * FROM Factura_Producto");
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            FacturaProducto fp = new FacturaProducto(rs.getInt(1),rs.getInt(2), rs.getInt(3));
            s.add(fp);
        }
        c.cerrar();

        return s;
    }
}
