package com.library.library_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.library_backend.model.Compra;


@Repository
public interface CompraRepository extends JpaRepository <Compra, Integer>{
        Optional<Compra> findByNotaFiscal(String notaFiscal);
}
