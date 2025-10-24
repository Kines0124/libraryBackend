package com.library.library_backend.dto;

import org.hibernate.validator.constraints.Length;

import com.library.library_backend.model.Cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class ClientePostDTO {
    
    @NotBlank
    @Length(max = 63)
    private String  nome;

    @NotBlank
    @Length(max = 63)
    private String  logradouro;

    @NotNull
    private Integer numero;

    @NotBlank
    @Length(max = 63)
    private String  bairro;

    @NotBlank
    @Length(max = 31)
    private String  municipio;

    @NotBlank
    @Length(max = 2)
    private String  uf;

    @NotBlank
    @Length(max = 15)
    private String  cep;

    @NotBlank
    @Length(max = 31)
    private String  telefone;

    @NotBlank
    @Length(max = 31)
    private String  cpf;

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

    public Cliente convert(){
        Cliente cliente = new Cliente();
        
        cliente.setNome(this.nome);
        cliente.setLogradouro(this.logradouro);
        cliente.setNumero(this.numero);
        cliente.setBairro(this.bairro);
        cliente.setMunicipio(this.municipio);
        cliente.setUf(this.uf);
        cliente.setCep(this.cep);
        cliente.setTelefone(this.telefone);
        cliente.setCpf(this.cpf);

        return cliente;
    }
}
