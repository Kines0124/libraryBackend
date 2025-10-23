package com.library.library_backend.dto;

import java.util.List;

import com.library.library_backend.model.Livro;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LivroPutDTO {

    @NotBlank
    private String titulo;

    @NotBlank
    private String autor;

    @NotNull
    @Size(min = 1)
    private List<Integer> assuntosId;

    @NotNull
    private Integer editoraId;

    @NotNull
    @Min(1)
    private Short edicao;

    @NotNull
    @Min(1000)
    @Max(9999)
    private Short ano;

    @NotBlank
    private String isbn;

    @NotNull
    @Min(1)
    private Short quantidade;

    @NotNull
    @DecimalMin("0.0")
    private Float preco;
	
	public String getTitulo() {
		return titulo;
	}

    public String getAutor(){
        return autor;
    }

    public List<Integer> getAssuntosId(){
        return assuntosId;
    }

    public Integer getEditoraId(){
        return editoraId;
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

    public void update (Livro item){
        item.setEdicao(this.edicao);
        item.setAno(this.ano);
        item.setQuantidade(this.quantidade);
        item.setPreco(this.preco);
    }
}
