package com.library.library_backend.dto;

import org.hibernate.validator.constraints.Length;

import com.library.library_backend.model.Genero;

import jakarta.validation.constraints.NotBlank;

public class GeneroPutDTO {
    
    @NotBlank
    @Length(max = 20)
    private String descricao;

    public String getDescricao(){
        return descricao;
    }

    public void update (Genero item){
        item.setDescricao(this.descricao);
    }

}
