package com.modelagem.pooprojeto1.modelo;

public class Pagamento {

    private final double valor;
    private final MeioPagamento meio;

    public Pagamento(double valor, MeioPagamento meio) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor do pagamento deve ser maior que zero.");
        }
        if (meio == null) {
            throw new IllegalArgumentException("Meio de pagamento não pode ser nulo.");
        }
        this.valor = valor;
        this.meio = meio;
    }

    public double getValor() {
        return valor;
    }

    public MeioPagamento getMeio() {
        return meio;
    }
}
