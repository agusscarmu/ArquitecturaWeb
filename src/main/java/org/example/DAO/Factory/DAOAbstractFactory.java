package org.example.DAO.Factory;

import org.example.DAO.DAO;

public abstract class DAOAbstractFactory{
    private static final DAOAbstractFactory DAOFactoryMySQL = new DAOFactoryMySQL();

    public static DAO<?> crearDAO(String archivoCSV, String database) throws Exception {
        switch (database) {
            case "MySQL":
                return DAOFactoryMySQL.crearDAO(archivoCSV);
//          Con este switch se podria usar la funcionalidad de incorporar al sistema
//          distintas bases de datos en un futuro. Por ejemplo, Derby:
//            case "Derby":
//                return DAOFactoryDerby.crearDAO(archivoCSV);
        }
        return null;
    }

    public abstract DAO<?> crearDAO(String archivoCSV) throws Exception;
}
