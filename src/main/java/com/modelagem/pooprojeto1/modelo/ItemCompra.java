package com.modelagem.pooprojeto1.modelo;

public class ItemCompra {

    private final Produto produto;
    private final int quantidade;
    private final double precoUnitario;

    public ItemCompra(Produto produto, int quantidade, double precoUnitario) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo.");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade do item deve ser maior que zero.");
        }
        Produto.validarPreco(precoUnitario);

        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    /** Subtotal = quantidade × preço unitário (valores congelados no item). */
    public double getSubtotal() {
        return quantidade * precoUnitario;
    }
}
