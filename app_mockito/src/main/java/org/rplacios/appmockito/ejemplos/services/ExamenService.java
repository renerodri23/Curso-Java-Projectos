package org.rplacios.appmockito.ejemplos.services;

import org.rplacios.appmockito.ejemplos.models.Examen;

import java.util.Optional;

public interface ExamenService {
    Optional<Examen> findExamenByName(String name);
    Examen findExamenByNameWithQuestion(String name);
}
