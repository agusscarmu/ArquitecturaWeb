package org.example;


import org.example.DAO.*;
import org.example.DAO.Factory.DAOAbstractFactory;
import org.example.DAO.DAOMySQL.ClienteDAOMySQL;
import org.example.DAO.DAOMySQL.ProductoDAOMySQL;
import org.example.objs.Cliente;
import org.example.objs.Producto;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws Exception {

        DAO d1 = DAOAbstractFactory.crearDAO("clientes.csv", "MySQL");
        DAO d2 = DAOAbstractFactory.crearDAO("facturas.csv", "MySQL");
        DAO d3 = DAOAbstractFactory.crearDAO("productos.csv", "MySQL");
        DAO d4 = DAOAbstractFactory.crearDAO("facturas-productos.csv", "MySQL");

        ProductoDAOMySQL pdao = (ProductoDAOMySQL) d3;
        ClienteDAOMySQL cdao = (ClienteDAOMySQL) d1;
        Producto p = pdao.masRecaudo();
        System.out.println("Producto que mas recaudo: "+p.getNombre());
        LinkedList<Cliente> clientes = cdao.listarOrdenado();
        for(Cliente c : clientes){
            System.out.println(c);
        }
    }
}