package com.library.library_backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ItemCompraPostDTO {

    @NotNull
    private Integer livroId;

    @NotNull
    @Min(value = 1, message = "A quantidade minima é 1...")
    private Integer quantidade;

    public void setLivroId(Integer livroId) {
        this.livroId = livroId;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getLivroId() {
        return livroId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }


}
