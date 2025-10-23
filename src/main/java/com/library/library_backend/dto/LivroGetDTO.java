package com.library.library_backend.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.library.library_backend.model.Assunto;
import com.library.library_backend.model.Editora;
import com.library.library_backend.model.Livro;

public class LivroGetDTO {

    private Integer id;
    private String  titulo;
    private String  autor;
    private List<Assunto> assuntos;
    private Editora editora;
    private Short   edicao;
    private Short   ano;
    private String  isbn;
    private Short   quantidade;
    private Float   preco; 

    public LivroGetDTO(){
    }
        public LivroGetDTO(Livro livro){
        this.id         = livro.getId();
        this.titulo     = livro.getTitulo();
        this.autor      = livro.getAutor();
        this.assuntos   = livro.getAssuntos();
        this.editora    = livro.getEditora();
        this.edicao     = livro.getEdicao();
        this.ano        = livro.getAno();
        this.isbn       = livro.getIsbn();
        this.quantidade = livro.getQuantidade();
        this.preco      = livro.getPreco();
    }

    public Integer getId(){
        return id;
    }

    public String getTitulo(){
        return titulo;
    }

    public String getAutor(){
        return autor;
    }

    public List<Assunto> getAssuntos(){
        return assuntos;
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

    public static List<LivroGetDTO> convert(List<Livro> lista){
        return lista.stream().map(LivroGetDTO::new).collect(Collectors.toList());
    }

}