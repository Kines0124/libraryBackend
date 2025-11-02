package com.library.library_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.library_backend.model.Genero;


@Repository
public interface GeneroRepository extends JpaRepository <Genero, Integer>{
    Optional<Genero> findByDescricao(String descricao);
} 