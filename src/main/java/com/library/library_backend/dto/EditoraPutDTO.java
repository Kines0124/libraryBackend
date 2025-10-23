package com.library.library_backend.dto;

import org.hibernate.validator.constraints.Length;

import com.library.library_backend.model.Editora;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EditoraPutDTO {

    @NotBlank
    @Length(max = 100)
    private String nome;

    @NotBlank
    @Length(max = 18)
    private String cnpj;

    @NotBlank
    @Length(max = 63)
    private String logradouro;

    @NotNull
    private Integer numero;

    @NotBlank
    @Length(max = 63)
    private String bairro;

    @NotBlank
    @Length(max = 31)
    private String municipio;

    @NotBlank
    @Length(max = 2)
    private String uf;

    @NotBlank
    @Length(max = 15)
    private String cep;

    @NotBlank
    @Length(max = 31)
    private String telefone;

    @NotBlank
    @Length(max = 63)
    private String gerente;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getGerente() {
        return gerente;
    }

    public void setGerente(String gerente) {
        this.gerente = gerente;
    }

public void update (Editora item){
    item.setNome(this.nome);
    item.setCnpj(this.cnpj);
    item.setLogradouro(this.logradouro);
    item.setNumero(this.numero);
    item.setBairro(this.bairro);    
    item.setMunicipio(this.municipio);
    item.setUf(this.uf);
    item.setCep(this.cep);
    item.setTelefone(this.telefone);
    item.setGerente(this.gerente);
}



}
