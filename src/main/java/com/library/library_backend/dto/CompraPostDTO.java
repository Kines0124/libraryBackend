package com.library.library_backend.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.library.library_backend.model.Cliente;
import com.library.library_backend.model.Compra;
import com.library.library_backend.repository.ClienteRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CompraPostDTO {
    
    private Float valor_pago = 0.0f;

    @NotBlank
    @Length(max = 15)
    private String notaFiscal;

    @NotBlank
    @Length(max = 10)
    private String data;

    @NotBlank
    @Length(max = 31) 
    private String clienteCpf;

    @Valid
    @Size(min = 1, message = "Minimo um item")
    @NotNull
    private List<ItemCompraPostDTO> itens;
    

    public String getNotaFiscal() {
        return notaFiscal;
    }

    public String getData() {
        return data;
    }

    public String getClienteCpf() {
        return clienteCpf;
    }

    public List<ItemCompraPostDTO> getItens(){
        return itens;
    }

    public void setItens(List<ItemCompraPostDTO> itens){
        this.itens = itens;
    }

        public CompraPostDTO() {
    }

    public Compra convert(ClienteRepository clienteRepository){
        Compra compra = new Compra();
        
        compra.setNotaFiscal(this.notaFiscal);
        compra.setData(this.data);

        Cliente cliente = clienteRepository.findByCpf(this.clienteCpf).orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        compra.setCliente(cliente);
        compra.setValorPago(valor_pago);

        return compra;
    }

}
