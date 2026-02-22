package org.rplacios.appmockito.ejemplos.services;

import org.rplacios.appmockito.ejemplos.models.Examen;
import org.rplacios.appmockito.ejemplos.repository.ExamenRepository;
import org.rplacios.appmockito.ejemplos.repository.QuestionRepository;

import java.util.List;
import java.util.Optional;

public class ExamenServiceImp implements ExamenService{
    private ExamenRepository exRepo;
    private QuestionRepository qRepo;

    public ExamenServiceImp(ExamenRepository exRepo, QuestionRepository qRepo) {
        this.exRepo = exRepo;
        this.qRepo = qRepo;
    }

    @Override
    public Optional<Examen> findExamenByName(String name) {
        return exRepo.findAll().stream()
                .filter(e -> e.getName().contains(name))
                .findFirst();
    }

    @Override
    public Examen findExamenByNameWithQuestion(String name) {
        Optional<Examen> exOp = findExamenByName(name);
        Examen examen = null;
        if (exOp.isPresent()){
            examen = exOp.orElseThrow();
            List<String> question = qRepo.findQuestionByExamenId(examen.getId());
            examen.setQuestion(question);
        }
         return examen;
    }
}
