package com.library.library_backend.dto;

import java.util.List;

import com.library.library_backend.model.Genero;
import com.library.library_backend.model.Editora;
import com.library.library_backend.model.Livro;
import com.library.library_backend.repository.GeneroRepository;
import com.library.library_backend.repository.EditoraRepository;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LivroPostDTO {
    
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
		
	public Livro convert( EditoraRepository editoraRepository, GeneroRepository assuntoRepository ) {
        Livro livro = new Livro();
        livro.setTitulo(this.titulo);
        livro.setAutor(this.autor);
        livro.setEdicao(this.edicao);
        livro.setAno(this.ano);
        livro.setIsbn(this.isbn);
        livro.setQuantidade(this.quantidade);
        livro.setPreco(this.preco);

        Editora editora = editoraRepository.findById(this.editoraId)
            .orElseThrow(() -> new IllegalArgumentException("Editora não encontrada"));
        livro.setEditora(editora);

        List<Genero> genero = assuntoRepository.findAllById(this.generoId);
        if (genero.size() != this.generoId.size()) {
            throw new IllegalArgumentException("Um ou mais generos não encontrados");
        }
        livro.setGenero(genero);

        return livro;
	}

	
}
