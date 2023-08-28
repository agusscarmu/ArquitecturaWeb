package org.example;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.DAO.ClienteDAO;
import org.example.DAO.DAO;
import org.example.DAO.DAOFactory;
import org.example.DAO.ProductoDAO;
import org.example.objs.Cliente;
import org.example.objs.Producto;

import java.io.FileReader;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws Exception {
        DAO d1 = DAOFactory.crearDAO("clientes.csv");
        DAO d2 = DAOFactory.crearDAO("facturas.csv");
        DAO d3 = DAOFactory.crearDAO("productos.csv");
        DAO d4 = DAOFactory.crearDAO("facturas-productos.csv");

        ProductoDAO pdao = (ProductoDAO) d3;
        ClienteDAO cdao = (ClienteDAO) d1;
        Producto p = pdao.masRecaudo();
        System.out.println("Producto que mas recaudo: "+p.getNombre());
        LinkedList<Cliente> clientes = cdao.listarOrdenado();
        for(Cliente c : clientes){
            System.out.println(c);
        }
    }
}