package org.example.DAO;

public class DAOFactory {

    public static DAO<?> crearDAO(String archivoCSV) throws Exception {
        switch (archivoCSV){
            case "productos.csv":
                return new ProductoDAO(archivoCSV);
            case "clientes.csv":
                return new ClienteDAO(archivoCSV);
            case "facturas.csv":
                return new FacturaDAO(archivoCSV);
            case "facturas-productos.csv":
                return new FacturaProductoDAO(archivoCSV);
        }
        return null;
    }
}
