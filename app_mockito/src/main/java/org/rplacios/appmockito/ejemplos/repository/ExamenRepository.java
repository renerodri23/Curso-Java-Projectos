package org.rplacios.appmockito.ejemplos.repository;

import org.rplacios.appmockito.ejemplos.models.Examen;

import java.util.List;

public interface ExamenRepository {
    List<Examen> findAll();
}
