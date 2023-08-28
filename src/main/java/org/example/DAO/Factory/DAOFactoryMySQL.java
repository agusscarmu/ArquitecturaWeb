package org.example.DAO.Factory;

import org.example.DAO.*;
import org.example.DAO.DAOMySQL.ClienteDAOMySQL;
import org.example.DAO.DAOMySQL.FacturaDAOMySQL;
import org.example.DAO.DAOMySQL.FacturaProductoDAOMySQL;
import org.example.DAO.DAOMySQL.ProductoDAOMySQL;

class DAOFactoryMySQL extends DAOAbstractFactory {

    public DAO<?> crearDAO(String archivoCSV) throws Exception {
        switch (archivoCSV){
            case "productos.csv":
                return new ProductoDAOMySQL(archivoCSV);
            case "clientes.csv":
                return new ClienteDAOMySQL(archivoCSV);
            case "facturas.csv":
                return new FacturaDAOMySQL(archivoCSV);
            case "facturas-productos.csv":
                return new FacturaProductoDAOMySQL(archivoCSV);
        }
        return null;
    }
}
