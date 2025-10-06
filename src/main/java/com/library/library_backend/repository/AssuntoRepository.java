package com.library.library_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.library_backend.model.Assunto;
import java.util.Optional;

@Repository
public interface AssuntoRepository extends JpaRepository <Assunto, Integer>{
    Optional<Assunto> findByDescricao(String descricao);
} 