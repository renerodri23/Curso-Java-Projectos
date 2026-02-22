package org.rplacios.appmockito.ejemplos.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.rplacios.appmockito.ejemplos.models.Examen;
import org.rplacios.appmockito.ejemplos.repository.ExamenRepository;
import org.rplacios.appmockito.ejemplos.repository.QuestionRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ExamenServiceImpTest {
    ExamenRepository repo;
    ExamenService service ;
    QuestionRepository qRepo;


    @BeforeEach
    void setUp() {
         repo = mock(ExamenRepository.class);
         qRepo = mock(QuestionRepository.class);
         service = new ExamenServiceImp(repo,qRepo);
    }

    @Test
    void findExamenByName() {
        when(repo.findAll()).thenReturn(Datos.EXAMENES);
        Optional<Examen> examen = service.findExamenByName("Matematicas");

    }



    @Test
    void findExamenByNameEmptyList() {
        List<Examen> datos = Collections.emptyList();
        when(repo.findAll()).thenReturn(datos);
        Optional<Examen> examen = service.findExamenByName("Matematicas");
        assertFalse(examen.isPresent());

    }

    @Test
    void testExamQuestion() {
        when(repo.findAll()).thenReturn(Datos.EXAMENES);
        when(qRepo.findQuestionByExamenId(5L)).thenReturn(Datos.PREGUNTAS);
        Examen examen = service.findExamenByNameWithQuestion("Matematicas");
        assertEquals(4,examen.getQuestion().size());
        verify(repo).findAll();
        verify(qRepo).findQuestionByExamenId(5L);

    }
}