package com.library.library_backend.dto;

import java.util.List;

import com.library.library_backend.model.Assunto;
import com.library.library_backend.model.Editora;
import com.library.library_backend.model.Livro;
import com.library.library_backend.repository.AssuntoRepository;
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

    public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

    public void setAutor(String autor){
        this.autor = autor;
    }

    public void setAssuntosId(List<Integer> assuntosId){
        this.assuntosId = assuntosId;
    }

    public void setEditoraId(Integer editoraId){
        this.editoraId = editoraId;
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
		
	public Livro convert( EditoraRepository editoraRepository, AssuntoRepository assuntoRepository ) {
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

        List<Assunto> assuntos = assuntoRepository.findAllById(this.assuntosId);
        if (assuntos.size() != this.assuntosId.size()) {
            throw new IllegalArgumentException("Um ou mais assuntos não encontrados");
        }
        livro.setAssuntos(assuntos);

        return livro;
	}

	
}
