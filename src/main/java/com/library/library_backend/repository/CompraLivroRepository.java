package com.library.library_backend.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.library_backend.model.CompraLivro;

public interface CompraLivroRepository extends JpaRepository<CompraLivro, Integer>{
    Optional<CompraLivro> findById(Integer id);
}
