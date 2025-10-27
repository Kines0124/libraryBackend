package com.library.library_backend.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.library.library_backend.model.Editora;

public class EditoraGetDTO {
    
    private Integer id;
    private String nome;
    private String cnpj;
    private String logradouro;
    private Integer numero;
    private String bairro;
    private String municipio;
    private String uf;
    private String cep;
    private String telefone;
    private String gerente;

    public EditoraGetDTO(){
    }

    public EditoraGetDTO(Editora editora){
        this.id = editora.getId();
        this.nome = editora.getNome();
        this.cnpj = editora.getCnpj();
        this.logradouro = editora.getLogradouro();
        this.numero = editora.getNumero();
        this.bairro = editora.getBairro();
        this.municipio = editora.getMunicipio();
        this.uf = editora.getUf();
        this.cep = editora.getCep();
        this.telefone = editora.getTelefone();
        this.gerente = editora.getGerente();
    }

    public Integer getId() {
        return id;
    }

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

    public static List<EditoraGetDTO> convert(List<Editora> lista){
        return lista.stream().map(EditoraGetDTO::new).collect(Collectors.toList());
    }

}
