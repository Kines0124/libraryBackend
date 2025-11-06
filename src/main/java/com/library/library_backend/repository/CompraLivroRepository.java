package com.library.library_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.library.library_backend.model.CompraLivro;

public interface CompraLivroRepository extends JpaRepository<CompraLivro, Integer>{
    @Query(value = "SELECT SUM(cl.quantidade * cl.preco_unitario) FROM compra_livro cl WHERE cl.compra_id = :compraId", nativeQuery = true)

    Optional <Float> calcularValorTotal(@Param("compraId") Integer compraId); 

    @Modifying
    @Query(value = "DELETE FROM compra_livro WHERE compra_id = :compraId", nativeQuery = true)
    
    void deleteAllByCompraId(@Param("compraId") Integer compraId);
}