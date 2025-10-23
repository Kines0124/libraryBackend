package com.library.library_backend.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.library.library_backend.model.Assunto;

public class AssuntoGetDTO {
    
    private Integer id;
    private String descricao;

    public AssuntoGetDTO(){
    }

        public AssuntoGetDTO(Assunto assunto){
        this.id = assunto.getId();
        this.descricao = assunto.getDescricao();
    }

    public Integer getId(){
        return id;
    }

    public String getDescricao(){
        return descricao;
    }


    public static List<AssuntoGetDTO> convert(List<Assunto> lista){
        return lista.stream().map(AssuntoGetDTO::new).collect(Collectors.toList());
    }

}
