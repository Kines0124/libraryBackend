package com.library.library_backend.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"nomeCliente", "valorTotalGasto"})
public interface CompraTotalPessoaDTO {
    String getNomeCliente();
    Float getValorTotalGasto();
}