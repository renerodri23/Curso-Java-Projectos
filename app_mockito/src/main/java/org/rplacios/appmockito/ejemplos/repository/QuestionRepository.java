package org.rplacios.appmockito.ejemplos.repository;

import java.util.List;

public interface QuestionRepository {

    List<String> findQuestionByExamenId(Long id);
}
