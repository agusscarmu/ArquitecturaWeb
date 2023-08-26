package org.example.DAO;

public interface CRUD<T> {

    void crear() throws Exception;
    void drop() throws Exception;
    void insertar(T t) throws Exception;
    void seleccionar(T t) throws Exception;
    void actualizar(T t) throws Exception;
    void eliminar(T t) throws Exception;
    void listar(T t) throws Exception;
}
