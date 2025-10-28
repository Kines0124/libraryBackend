package com.library.library_backend.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.library.library_backend.model.Cliente;
import com.library.library_backend.model.Compra;

public class CompraGetDTO {

    private Integer id;
    private String notaFiscal;
    private String data;
    private Cliente cliente;
    private Float valor_pago;

    public CompraGetDTO(Compra compra) {
        this.id = compra.getId();
        this.notaFiscal = compra.getNotaFiscal();
        this.data = compra.getData();
        this.cliente = compra.getCliente();
        this.valor_pago = compra.getValorPago();
    }

    public Integer getId() {
        return id;
    }

    public String getNotaFiscal() {
        return notaFiscal;
    }

    public String getData() {
        return data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Float getValor_pago() {
        return valor_pago;
    }
    
    public static List<CompraGetDTO> convert(List<Compra> lista){
        return lista.stream().map(CompraGetDTO::new).collect(Collectors.toList());
    }
}
