package com.library.library_backend.dto;

import java.sql.Date;

import org.hibernate.validator.constraints.Length;

import com.library.library_backend.model.Cliente;
import com.library.library_backend.model.Compra;
import com.library.library_backend.repository.ClienteRepository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

public class CompraPostDTO {
    
    @NotBlank
    @Length(max = 15)
    private String nota_fiscal;

    @NotBlank
    @PastOrPresent
    @Length(max = 10)
    private String data;

    @NotBlank 
    private Integer clienteId;

    public String getNota_fiscal() {
        return nota_fiscal;
    }

    public String getData() {
        return data;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public Compra convert(ClienteRepository clienteRepository){
        Compra compra = new Compra();
        
        compra.setNotaFiscal(this.nota_fiscal);
        compra.setData(this.data);

        Cliente cliente = clienteRepository.findById(this.clienteId).orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        compra.setCliente(cliente);

        return compra;
    }

}
