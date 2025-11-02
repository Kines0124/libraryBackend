package com.library.library_backend.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.library.library_backend.model.Genero;

public class GeneroGetDTO {
    
    private Integer id;
    private String descricao;

    public GeneroGetDTO(){
    }

    public GeneroGetDTO(Genero assunto){
        this.id = assunto.getId();
        this.descricao = assunto.getDescricao();
    }

    public Integer getId(){
        return id;
    }

    public String getDescricao(){
        return descricao;
    }

    public static List<GeneroGetDTO> convert(List<Genero> lista){
        return lista.stream().map(GeneroGetDTO::new).collect(Collectors.toList());
    }

}
