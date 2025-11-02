package com.library.library_backend.dto;

import java.util.List;

import com.library.library_backend.model.Editora;
import com.library.library_backend.model.Genero;
import com.library.library_backend.model.Livro;
import com.library.library_backend.repository.EditoraRepository;
import com.library.library_backend.repository.GeneroRepository;

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
    private List<Integer> generoId;

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

    public List<Integer> getGeneroId(){
        return generoId;
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

    public Livro update (Livro item, EditoraRepository editoraRepository, GeneroRepository generoRepository){
        item.setTitulo(this.titulo); 
        item.setAutor(this.autor);   
        item.setIsbn(this.isbn);     
        item.setEdicao(this.edicao);
        item.setAno(this.ano);
        item.setQuantidade(this.quantidade);
        item.setPreco(this.preco);

        Editora editora = editoraRepository.findById(this.editoraId)
            .orElseThrow(() -> new IllegalArgumentException("Editora não encontrada!"));
        item.setEditora(editora);

        List<Genero> novosGeneros = generoId.stream()
            .map(id -> generoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Gênero com ID " + id + " não encontrado!")))
            .toList();

            item.setGenero(novosGeneros);

            return item;
    }
}
