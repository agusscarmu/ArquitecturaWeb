package org.example.DAO.Factory;

import org.example.ConexionMySql;
import org.example.DAO.*;
import org.example.DAO.DAOMySQL.ClienteDAOMySQL;
import org.example.DAO.DAOMySQL.FacturaDAOMySQL;
import org.example.DAO.DAOMySQL.FacturaProductoDAOMySQL;
import org.example.DAO.DAOMySQL.ProductoDAOMySQL;

class DAOFactoryMySQL extends DAOAbstractFactory {
    private static DAOFactoryMySQL instancia;
    private final ConexionMySql c;

    private DAOFactoryMySQL(){
        this.c = new ConexionMySql();
    }
    public static DAOFactoryMySQL getInstancia(){
        if(instancia==null){
            instancia = new DAOFactoryMySQL();
        }
        return instancia;
    }

    public DAO<?> crearDAO(String archivoCSV) throws Exception {
        switch (archivoCSV){
            case "productos.csv":
                return new ProductoDAOMySQL(archivoCSV, c);
            case "clientes.csv":
                return new ClienteDAOMySQL(archivoCSV, c);
            case "facturas.csv":
                return new FacturaDAOMySQL(archivoCSV, c);
            case "facturas-productos.csv":
                return new FacturaProductoDAOMySQL(archivoCSV, c);
        }
        return null;
    }
}
