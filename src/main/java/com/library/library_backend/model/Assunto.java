package com.library.library_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Assunto {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descricao;


    public Integer getId(){
        return id;
    }

    public String getDescricao(){
        return descricao;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

}