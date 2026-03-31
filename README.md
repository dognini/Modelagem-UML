# Modelagem UML e POO — Trabalho acadêmico

## Identificação

| Campo | Informação |
|--------|------------|
| **Disciplina** | Programação Orientada a Objetos |
| **Repositório / pasta** | Modelagem-UML |
| **Aluno** | Diogo Dognini Corrêa |

---

## Enunciado dos projetos

### Projeto 1 — Diagrama UML a partir da descrição

Sistema de **compras online** com:

- **Produto:** nome e descrição imutáveis; preço e quantidade em estoque não podem ser zero nem negativos; um produto pode estar associado a zero ou várias compras (via itens).
- **Compra:** um ou mais itens; um comprador; estado **PENDENTE**, **CANCELADA** ou **CONFIRMADA**; zero ou vários pagamentos; operações de adicionar item e finalizar compra (definindo pagamentos).
- **ItemCompra:** produto, quantidade, preço; pertence a uma única compra.
- **Pagamento:** valor; meio **DINHEIRO**, **DÉBITO** ou **CRÉDITO**.

**Regras:** validar preço e quantidade (não podem ser ≤ 0); podem existir requisitos extras coerentes com o domínio.

### Projeto 2 — Implementação em Java a partir do diagrama UML

Implementar as classes em Java conforme o **diagrama de classes** fornecido pela disciplina (ex.: sistema de **hotel**: `Pessoa`, `Funcionario`, `Hospede`, `Hotel`, `Quarto`, `Reserva`, `Veiculo`, `Cargo`, com herança, composição, agregação e multiplicidades do diagrama).

Neste repositório, a **implementação Java** entregue corresponde ao **Projeto 1** (compras online), alinhada ao diagrama em PlantUML.

---

## Diagramas UML

### Projeto 1 (compras online)

- **Arquivo PlantUML:** [`diagramas/sistema-compras-online.puml`](diagramas/sistema-compras-online.puml)
- **Conteúdo:** classes (`Comprador`, `Produto`, `ItemCompra`, `Pagamento`, `Compra`), enums (`EstadoCompra`, `MeioPagamento`), atributos e métodos principais, relacionamentos e multiplicidades (comprador–compra; composição compra–itens; associação item–produto; agregação compra–pagamentos).

**Como gerar a imagem (PNG/SVG) para o PDF:**

1. Instale a extensão **PlantUML** no VS Code/Cursor **ou** use o servidor online [PlantUML](https://www.plantuml.com/plantuml).
2. Abra `diagramas/sistema-compras-online.puml` e exporte o diagrama.

### Projeto 2 (hotel)

Inclua no relatório a **figura do diagrama** fornecida pela disciplina (ou exporte seu próprio `.puml`, se tiver recriado o modelo).

---

## Códigos-fonte

### Organização (Projeto 1)

```
src/main/java/com/modelagem/pooprojeto1/
├── Main.java                 # Exemplo: produtos, compra, itens, finalizar, cancelar
└── modelo/
    ├── EstadoCompra.java     # enum: PENDENTE, CANCELADA, CONFIRMADA
    ├── MeioPagamento.java    # enum: DINHEIRO, DEBITO, CREDITO
    ├── Comprador.java
    ├── Produto.java          # validações de preço e estoque; nome/descrição imutáveis
    ├── ItemCompra.java
    ├── Pagamento.java
    └── Compra.java           # adicionarItem(), finalizarCompra(), cancelar()
```

### Compilar e executar

Na raiz do projeto:

```bash
mkdir -p target/classes
javac -d target/classes -encoding UTF-8 $(find src/main/java -name "*.java")
java -cp target/classes com.modelagem.pooprojeto1.Main
```

Se o `javac` não for encontrado, instale um JDK e/ou defina `JAVA_HOME` apontando para a pasta do JDK.

### Ideias centrais do código

- **Encapsulamento:** atributos `private`; acesso via getters (e setters só onde faz sentido).
- **Validações:** construtores e métodos lançam `IllegalArgumentException` ou `IllegalStateException` quando as regras são violadas.
- **Estoque:** conferido ao adicionar item e novamente ao finalizar; a baixa ocorre na **confirmação** da compra.

---

## Relatório em PDF (sugestão de conteúdo)

- Identificação do aluno e da disciplina
- Enunciado resumido de cada projeto
- Imagem exportada do diagrama (Projeto 1 e, se aplicável, Projeto 2)
- Explicação das classes principais e das regras de negócio
- Print da execução da classe `Main` ou descrição dos cenários testados

---

_Se o professor pedir outro pacote Java (ex.: `br.edu.faculdade.poo`), ajuste `package` e imports em todo o código._
