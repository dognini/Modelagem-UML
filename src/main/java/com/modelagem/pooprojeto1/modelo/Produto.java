package com.modelagem.pooprojeto1.modelo;

public class Produto {

    private final String nome;
    private final String descricao;
    private double preco;
    private int quantidadeEstoque;

    public Produto(String nome, String descricao, double preco, int quantidadeEstoque) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do produto não pode ser vazio.");
        }
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("Descrição do produto não pode ser vazia.");
        }
        validarPreco(preco);
        validarQuantidadeEstoque(quantidadeEstoque);

        this.nome = nome.trim();
        this.descricao = descricao.trim();
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    /** Preço não pode ser zero nem negativo (regra do enunciado). */
    public static void validarPreco(double preco) {
        if (preco <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que zero.");
        }
    }

    /** Estoque não pode ser zero nem negativo (regra do enunciado). */
    public static void validarQuantidadeEstoque(int qtd) {
        if (qtd <= 0) {
            throw new IllegalArgumentException("Quantidade em estoque deve ser maior que zero.");
        }
    }

    public void setPreco(double novoPreco) {
        validarPreco(novoPreco);
        this.preco = novoPreco;
    }

    /** Ajuste de estoque (ex.: reposição pelo administrador). */
    public void setQuantidadeEstoque(int novaQtd) {
        validarQuantidadeEstoque(novaQtd);
        this.quantidadeEstoque = novaQtd;
    }

    /**
     * Baixa no estoque quando a compra é confirmada.
     * Não deve ser chamado antes da confirmação (ver {@link Compra#finalizarCompra}).
     */
    public void reduzirEstoque(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade a reduzir deve ser maior que zero.");
        }
        if (quantidade > quantidadeEstoque) {
            throw new IllegalStateException("Estoque insuficiente para o produto: " + nome);
        }
        this.quantidadeEstoque -= quantidade;
    }

    /** Devolve estoque (útil se uma compra confirmada for cancelada/estornada). */
    public void restaurarEstoque(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade a restaurar deve ser maior que zero.");
        }
        this.quantidadeEstoque += quantidade;
    }
}
