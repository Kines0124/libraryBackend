package com.library.library_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.library_backend.model.Cliente;


@Repository
public interface ClienteRepository extends JpaRepository <Cliente, Integer>{
        Optional<Cliente> findByNome(String nome);
        Optional<Cliente> findByCpf(String cpf);
}