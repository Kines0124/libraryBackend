package com.library.library_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.library.library_backend.dto.CompraTotalPessoaDTO;
import com.library.library_backend.model.Compra;


@Repository
public interface CompraRepository extends JpaRepository <Compra, Integer>{
        Optional<Compra> findByNotaFiscal(String notaFiscal);
        List<Compra> findAllByNotaFiscal(String notaFiscal);

        @Query(value = "SELECT c.nome AS nome_cliente, SUM(cl.quantidade * cl.preco_unitario) AS valor_total_gasto "
        + "FROM compra_livro cl "
        + "INNER JOIN compra co ON co.id = cl.compra_id "
        + "INNER JOIN cliente c ON c.id = co.cliente_id "
        + "GROUP BY c.nome "
        + "ORDER BY valor_total_gasto DESC;", nativeQuery = true)
        List<CompraTotalPessoaDTO> totalGastoPorPessoa(); 


}
