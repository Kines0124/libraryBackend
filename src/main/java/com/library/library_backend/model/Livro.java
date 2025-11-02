package com.library.library_backend.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Livro  {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;
    private String  titulo;
    private String  autor;

    @ManyToMany
    @JoinTable(
        name = "livro_genero",
        joinColumns = @JoinColumn(name = "livro_id"),
        inverseJoinColumns = @JoinColumn(name = "genero_id")
    )
    @JsonManagedReference
    private List<Genero> genero;

    @ManyToOne
    @JoinColumn(name = "editora_id")
    private Editora editora;

    private Short   edicao;
    private Short   ano;
    private String  isbn;
    private Short   quantidade;
    private Float   preco; 


    public Integer getId(){
        return id;
    }

    public String getTitulo(){
        return titulo;
    }

    public String getAutor(){
        return autor;
    }

    public List<Genero> getGenero(){
        return genero;
    }

    public Editora getEditora(){
        return editora;
    }

    public Short getEdicao(){
        return edicao;
    }

    public Short getAno(){
        return ano;
    }

    public String getIsbn(){
        return isbn;
    }

    public Short getQuantidade(){
        return quantidade;
    }

    public Float getPreco(){
        return preco;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public void setAutor(String autor){
        this.autor = autor;
    }

    public void setGenero(List<Genero> genero){
        this.genero = genero;
    }

    public void setEditora(Editora editora){
        this.editora = editora;
    }

    public void setEdicao(Short edicao){
        this.edicao = edicao;
    }

    public void setAno(Short ano){
        this.ano = ano;
    }

    public void setIsbn(String isbn){
        this.isbn = isbn;
    }

    public void setQuantidade(Short quantidade){
        this.quantidade = quantidade;
    }

    public void setPreco(Float preco){
        this.preco = preco;
    }
}
