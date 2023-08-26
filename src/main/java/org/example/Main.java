package org.example;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.DAO.CRUD;
import org.example.DAO.ProductoDAO;
import org.example.objs.Producto;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        String csvFilePath = System.getProperty("user.dir") + "/productos.csv";

        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(csvFilePath));

        CRUD<Producto> DAO1 = new ProductoDAO();
//
//        for(CSVRecord row: parser) {
//            Producto p = new Producto(Integer.parseInt(row.get("idProducto")),row.get("nombre"),Integer.parseInt(row.get("valor")));
//            DAO1.insertar(p);
//        }



        List<Producto> p = DAO1.listar();

        System.out.println(p);
//        Conexion c = Conexion.getInstance();
//
//        try{
//            ProductoDAO p = new ProductoDAO();
//            p.drop();
//        }catch (Exception e){
//            System.out.println("conexion fallida");
//        }
    }
}