package org.example;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionMySql {

    private Connection conn;

    private String driver = "com.mysql.cj.jdbc.Driver";
    private String dbUrl = "jdbc:mysql://localhost:3306/PrimerEntrega";

    private String user = "root";
    private String pass = "";

    public void conectar() throws Exception{
        Connection connection = DriverManager.getConnection(dbUrl, user, pass);
        Class.forName(driver).getDeclaredConstructor().newInstance();
        this.conn = connection;
    }

    public void cerrar() throws Exception{
        if(conn != null){
            if(!conn.isClosed()){
                conn.close();
            }
        }
    }

    public Connection conn(){
        return conn;
    }

}
