package com.modelagem.pooprojeto1.modelo;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Compra {

    private final Comprador comprador;
    private EstadoCompra estado;
    private final List<ItemCompra> itens;
    private final List<Pagamento> pagamentos;

    public Compra(Comprador comprador) {
        if (comprador == null) {
            throw new IllegalArgumentException("Comprador não pode ser nulo.");
        }
        this.comprador = comprador;
        this.estado = EstadoCompra.PENDENTE;
        this.itens = new ArrayList<>();
        this.pagamentos = new ArrayList<>();
    }

    public Comprador getComprador() {
        return comprador;
    }

    public EstadoCompra getEstado() {
        return estado;
    }

    public List<ItemCompra> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public List<Pagamento> getPagamentos() {
        return Collections.unmodifiableList(pagamentos);
    }

    public double getTotal() {
        double total = 0;
        for (ItemCompra item : itens) {
            total += item.getSubtotal();
        }
        return total;
    }

    public void adicionarItem(Produto produto, int quantidade) {
        if (estado != EstadoCompra.PENDENTE) {
            throw new IllegalStateException("Só é possível adicionar itens com compra PENDENTE.");
        }
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo.");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }
        if (quantidade > produto.getQuantidadeEstoque()) {
            throw new IllegalStateException(
                    "Estoque insuficiente para " + produto.getNome()
                            + ". Disponível: " + produto.getQuantidadeEstoque());
        }

        ItemCompra item = new ItemCompra(produto, quantidade, produto.getPreco());
        itens.add(item);
    }

    public void finalizarCompra(List<Pagamento> novosPagamentos) {
        if (estado != EstadoCompra.PENDENTE) {
            throw new IllegalStateException("Só é possível finalizar compra PENDENTE.");
        }
        if (itens.isEmpty()) {
            throw new IllegalStateException("Compra precisa ter pelo menos um item.");
        }
        if (novosPagamentos == null || novosPagamentos.isEmpty()) {
            throw new IllegalArgumentException("Informe ao menos um pagamento.");
        }

        for (ItemCompra item : itens) {
            Produto p = item.getProduto();
            if (item.getQuantidade() > p.getQuantidadeEstoque()) {
                throw new IllegalStateException(
                        "Estoque insuficiente ao finalizar para: " + p.getNome());
            }
        }

        double somaPagamentos = 0;
        for (Pagamento pg : novosPagamentos) {
            somaPagamentos += pg.getValor();
        }
        double total = getTotal();
        if (Math.abs(somaPagamentos - total) > 0.01) {
            throw new IllegalArgumentException("Soma dos pagamentos (" + somaPagamentos + ") deve ser igual ao total (" + total + ")");
        }

        for (ItemCompra item : itens) {
            item.getProduto().reduzirEstoque(item.getQuantidade());
        }

        pagamentos.clear();
        pagamentos.addAll(novosPagamentos);
        estado = EstadoCompra.CONFIRMADA;
    }

    public void cancelar() {
        if (estado != EstadoCompra.PENDENTE) {
            throw new IllegalStateException("Só é possível cancelar compra PENDENTE.");
        }
        estado = EstadoCompra.CANCELADA;
        itens.clear();
    }
}
