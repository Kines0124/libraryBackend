package com.library.library_backend.dto;

import org.hibernate.validator.constraints.Length;

import com.library.library_backend.model.Cliente;
import com.library.library_backend.model.Compra;
import com.library.library_backend.repository.ClienteRepository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CompraPostDTO {
    
    private Float valor_pago = 0.0f;

    @NotBlank
    @Length(max = 15)
    private String notaFiscal;

    @NotBlank
    @Length(max = 10)
    private String data;

    @NotNull 
    private Integer clienteId;

    public String getNotaFiscal() {
        return notaFiscal;
    }

    public String getData() {
        return data;
    }

    public Integer getClienteId() {
        return clienteId;
    }

        public CompraPostDTO() {
    }

    public Compra convert(ClienteRepository clienteRepository){
        Compra compra = new Compra();
        
        compra.setNotaFiscal(this.notaFiscal);
        compra.setData(this.data);

        Cliente cliente = clienteRepository.findById(this.clienteId).orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        compra.setCliente(cliente);
        compra.setValorPago(valor_pago);

        return compra;
    }

}
