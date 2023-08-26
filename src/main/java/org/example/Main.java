package org.example;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.DAO.DAO;
import org.example.DAO.ProductoDAO;
import org.example.objs.Producto;

import java.io.FileReader;

public class Main {
    public static void main(String[] args) throws Exception {
        String csvFilePath = System.getProperty("user.dir") + "/productos.csv";

        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(csvFilePath));

        DAO<Producto> DAO1 = new ProductoDAO();

        for(CSVRecord row: parser) {
            Producto p = new Producto(Integer.parseInt(row.get("idProducto")),row.get("nombre"),Integer.parseInt(row.get("valor")));
            DAO1.insertar(p);
        }

//
//
//        List<Producto> p = DAO1.listar();
//        System.out.println("Lista normal: "+p);
//        Producto p1 = new Producto(1,"chau",2);
//        DAO1.actualizar(p1);
//        p = DAO1.listar();
//
//        System.out.println("Lista con 1 actualizado: "+p);
//        p1 = new Producto(1,"hola",2);
//        DAO1.eliminar(p1);
//        p = DAO1.listar();
//
//        System.out.println("primero eliminado: "+p);

        Conexion c = Conexion.getInstance();

//        try{
//            ProductoDAO p4 = new ProductoDAO();
//            p4.drop();
//        }catch (Exception e){
//            System.out.println("conexion fallida");
//        }
    }
}