package com.modelagem.pooprojeto1;

import com.modelagem.pooprojeto1.modelo.Compra;
import com.modelagem.pooprojeto1.modelo.Comprador;
import com.modelagem.pooprojeto1.modelo.EstadoCompra;
import com.modelagem.pooprojeto1.modelo.MeioPagamento;
import com.modelagem.pooprojeto1.modelo.Pagamento;
import com.modelagem.pooprojeto1.modelo.Produto;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Produto notebook = new Produto(
                "Notebook",
                "Notebook 8GB RAM",
                3500.00,
                10
        );
        Produto mouse = new Produto(
                "Mouse",
                "Mouse sem fio",
                89.90,
                50
        );

        Comprador ana = new Comprador("Ana", "ana@email.com");
        Compra compra = new Compra(ana);

        compra.adicionarItem(notebook, 1);
        compra.adicionarItem(mouse, 2);

        System.out.println("Comprador: " + compra.getComprador().getNome());
        System.out.println("Itens: " + compra.getItens().size());
        System.out.println("Total: R$ " + String.format("%.2f", compra.getTotal()));
        System.out.println("Estado: " + compra.getEstado());

        double total = compra.getTotal();
        List<Pagamento> pagamentos = Arrays.asList(
                new Pagamento(total * 0.5, MeioPagamento.CREDITO),
                new Pagamento(total * 0.5, MeioPagamento.DEBITO)
        );
        compra.finalizarCompra(pagamentos);

        System.out.println("Estado após finalizar: " + compra.getEstado());
        System.out.println("Estoque notebook: " + notebook.getQuantidadeEstoque());
        System.out.println("Estoque mouse: " + mouse.getQuantidadeEstoque());
        System.out.println("Pagamentos: " + compra.getPagamentos().size());

        demonstrarCancelamento();
    }

    private static void demonstrarCancelamento() {
        System.out.println();
        System.out.println("--- Compra cancelada (exemplo) ---");
        Compra c = new Compra(new Comprador("Bruno", "bruno@email.com"));
        c.adicionarItem(new Produto("Caneta", "Azul", 2.50, 100), 3);
        c.cancelar();
        System.out.println("Estado: " + c.getEstado() + " (esperado: " + EstadoCompra.CANCELADA + ")");
        System.out.println("Itens após cancelar: " + c.getItens().size());
    }
}
