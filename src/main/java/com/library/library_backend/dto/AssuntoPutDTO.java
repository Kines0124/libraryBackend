package com.library.library_backend.dto;

import org.hibernate.validator.constraints.Length;

import com.library.library_backend.model.Assunto;

import jakarta.validation.constraints.NotBlank;

public class AssuntoPutDTO {
    
    @NotBlank
    @Length(max = 20)
    private String descricao;

    public String getDescricao(){
        return descricao;
    }

    public void update (Assunto item){
        item.setDescricao(this.descricao);
    }

}
