package com.library.library_backend.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.library.library_backend.model.Cliente;


public class ClienteGetDTO {
    
    private Integer id;
    private String  nome;
    private String  logradouro;
    private Integer numero;
    private String  bairro;
    private String  municipio;
    private String  uf;
    private String  cep;
    private String  telefone;
    private String  cpf;

    public ClienteGetDTO()  {
    }

    public ClienteGetDTO( Cliente cliente )  {
        this.id         = cliente.getId();
        this.nome       = cliente.getNome();
        this.logradouro = cliente.getLogradouro();
        this.numero     = cliente.getNumero();
        this.bairro     = cliente.getBairro();
        this.municipio  = cliente.getMunicipio();
        this.uf         = cliente.getUf();
        this.cep        = cliente.getCep();
        this.telefone   = cliente.getTelefone();
        this.cpf        = cliente.getCpf();
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getBairro() {
        return bairro;
    }

    public String getMunicipio() {
        return municipio;
    }

    public String getUf() {
        return uf;
    }

    public String getCep() {
        return cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public static List<ClienteGetDTO> convert(List<Cliente> lista){
        return lista.stream().map(ClienteGetDTO::new).collect(Collectors.toList());
    }
}
