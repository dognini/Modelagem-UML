package com.modelagem.pooprojeto1.modelo;

public class Comprador {

    private final String nome;
    private final String email;

    public Comprador(String nome, String email) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do comprador não pode ser vazio.");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("E-mail do comprador não pode ser vazio.");
        }
        this.nome = nome.trim();
        this.email = email.trim();
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}
