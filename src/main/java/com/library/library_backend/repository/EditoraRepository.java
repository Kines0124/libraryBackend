package com.library.library_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.library_backend.model.Editora;


@Repository
public interface EditoraRepository extends JpaRepository <Editora, Integer>{
    
}
