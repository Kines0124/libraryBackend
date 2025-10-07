package com.library.library_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.library_backend.model.Assunto;
import com.library.library_backend.model.Editora;
import com.library.library_backend.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository <Livro, Integer>{
    Optional<Livro> findByTitulo(String titulo);
    List<Livro>     findByAutor(String autor);
    List<Livro>     findByAssuntos(Assunto assunto);
    List<Livro>     findByEditora(Editora editora);
    Optional<Livro> findByIsbn(String isbn);
}