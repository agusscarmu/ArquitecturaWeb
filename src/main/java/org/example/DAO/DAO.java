package org.example.DAO;

import java.util.LinkedList;

public interface DAO<T> {

    void crear() throws Exception;
    void drop() throws Exception;
    void insertar(T t) throws Exception;
    void actualizar(T t) throws Exception;
    void eliminar(T t) throws Exception;
    LinkedList<T> listar() throws Exception;
}
