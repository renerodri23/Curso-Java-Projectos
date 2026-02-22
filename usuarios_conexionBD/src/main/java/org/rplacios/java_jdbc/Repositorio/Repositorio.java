package org.rplacios.java_jdbc.Repositorio;

import java.sql.SQLException;
import java.util.List;

public interface Repositorio<T> {

    List<T> listar();

    T searchId(long id);

    void save(T t);

    void delete(Long id) throws SQLException;

}
