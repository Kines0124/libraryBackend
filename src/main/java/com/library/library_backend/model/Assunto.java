package com.library.library_backend.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Assunto {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descricao;

    @ManyToMany(mappedBy = "assuntos")
    @JsonBackReference
    private Set<Livro> livros = new HashSet<>();


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

    public void setLivros(Set<Livro> livros){
        this.livros = livros;
    }

    public Set<Livro> getLivros(){
        return livros;
    }

}