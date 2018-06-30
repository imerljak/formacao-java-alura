package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class Pedido {
    private Integer id;
    private BigDecimal valor;
    private Calendar data;
    private List<Produto> produtos;

    public Integer getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Calendar getData() {
        return data;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public String getTitulosString() {
        return produtos
                .parallelStream()
                .map(Produto::getTitulo)
                .collect(Collectors.joining(", "));
    }
}
