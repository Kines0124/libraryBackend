package com.library.library_backend.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Compra {
    

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;

    @Column(name = "nota_fiscal")
    private String  notaFiscal;

    private Date    data;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private Float   valor_pago = 0.0f;

    @OneToMany(mappedBy = "compra")
    private List<CompraLivro> itens;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(String notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public String getData() {
        return (this.data != null) ? DATE_FORMAT.format(this.data) : null;
    }

    public void setData(String data) {
    if (data == null) {
        this.data = null;
        return;
    }
    try {
        this.data = DATE_FORMAT.parse(data);
    } catch (ParseException e) {
        throw new IllegalArgumentException("Formato de data inválido. Use o padrão yyyy-MM-dd.", e);
    }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Float getValorPago() {
        return valor_pago;
    }

    public void setValorPago(Float valor) {
        this.valor_pago = valor;
    }
}
