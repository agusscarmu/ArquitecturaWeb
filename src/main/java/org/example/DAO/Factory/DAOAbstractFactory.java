package org.example.DAO.Factory;

import org.example.DAO.DAO;

public abstract class DAOAbstractFactory{

    public static DAO<?> crearDAO(String archivoCSV, String database) throws Exception {
        DAOAbstractFactory dao=null;
        switch (database) {
            case "MySQL":
                dao = DAOFactoryMySQL.getInstancia();

//          Con este switch se podria usar la funcionalidad de incorporar al sistema
//          distintas bases de datos en un futuro. Por ejemplo, Derby:
//            case "Derby":
//                dao = DAOFactoryDerby.getInstancia();
        }
        return (dao != null) ? dao.crearDAO(archivoCSV) : null;
    }

    public abstract DAO<?> crearDAO(String archivoCSV) throws Exception;
}
