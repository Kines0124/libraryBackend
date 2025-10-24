package com.library.library_backend.dto;

import org.hibernate.validator.constraints.Length;

import com.library.library_backend.model.Editora;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class EditoraPostDTO {

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

    public String getCnpj() {
        return cnpj;
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

    public String getGerente() {
        return gerente;
    }

    public Editora convert(){
        Editora editora = new Editora();
        
        editora.setNome(this.nome);
        editora.setCnpj(this.cnpj);
        editora.setLogradouro(this.logradouro);
        editora.setNumero(this.numero);
        editora.setBairro(this.bairro);
        editora.setMunicipio(this.municipio);
        editora.setUf(this.uf);
        editora.setCep(this.cep);
        editora.setTelefone(this.telefone);
        editora.setGerente(this.gerente);

        return editora;
    }
}
