package org.rplacios.appmockito.ejemplos.models;

import java.util.ArrayList;
import java.util.List;

public class Examen {

    private Long id;
    private String name;
    private List<String> question;

    public Examen(Long id, String name) {
        this.id = id;
        this.name = name;
        this.question = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getQuestion() {
        return question;
    }

    public void setQuestion(List<String> question) {
        this.question = question;
    }
}
