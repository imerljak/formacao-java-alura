package br.com.casadocodigo.loja.models;

import java.util.Calendar;
import java.util.List;

public class RelatorioProdutos {

    private final Calendar dataGeracao;
    private final Integer quantidade;
    private final List<Produto> produtos;

    public static RelatorioProdutos from(List<Produto> produtos) {
        return new RelatorioProdutos(
                Calendar.getInstance(),
                produtos.size(),
                produtos
        );
    }

    private RelatorioProdutos(Calendar dataGeracao, Integer quantidade, List<Produto> produtos) {
        this.dataGeracao = dataGeracao;
        this.quantidade = quantidade;
        this.produtos = produtos;
    }

    public Calendar getDataGeracao() {
        return dataGeracao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }
}
