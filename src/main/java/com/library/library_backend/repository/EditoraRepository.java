package com.library.library_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.library_backend.model.Editora;


@Repository
public interface EditoraRepository extends JpaRepository <Editora, Integer>{
        Optional<Editora> findByNome(String nome);
        Optional<Editora> findByCnpj(String cnpj);
        Optional<Editora> findByGerente(String gerente);
}
