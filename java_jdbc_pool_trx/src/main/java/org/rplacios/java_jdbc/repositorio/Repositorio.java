package org.rplacios.java_jdbc.repositorio;

import java.sql.SQLException;
import java.util.List;

public interface Repositorio<T> {
    List<T> listar() throws SQLException;

    T porId(long id) throws SQLException;

    T save(T t) throws SQLException;

    void eliminar(Long id) throws SQLException;

}
