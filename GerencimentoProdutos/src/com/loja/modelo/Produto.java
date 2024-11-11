package com.loja.modelo;

import java.util.Objects;

/**
 * Classe que representa um Produto de uma loja.
 */
public class Produto {

    // Atributos da classe Produto
    private Integer id;                  // Identificador único do produto
    private String nome;                 // Nome do produto
    private double preco;                // Preço do produto
    private int quantidadeEstoque;       // Quantidade disponível em estoque
    private String categoria;            // Categoria do produto

    /**
     * Construtor da classe Produto. Inicializa o nome, preço, quantidade em estoque e categoria.
     *
     * @param nome Nome do produto
     * @param preco Preço do produto
     * @param quantidadeEstoque Quantidade disponível no estoque
     * @param categoria Categoria do produto
     */
    public Produto(String nome, double preco, int quantidadeEstoque, String categoria) {
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.categoria = categoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Método que retorna uma representação em string do produto.
     * Exibe informações como ID, nome, preço, quantidade em estoque e categoria.
     *
     * @return String com as informações do produto
     */
    @Override
    public String toString() {
        return String.format(
                "ID: %d | Nome: %s | Preço: R$ %.2f | Estoque: %d | Categoria: %s",
                id, nome, preco, quantidadeEstoque, categoria
        );
    }

    /**
     * Método que verifica a igualdade entre dois objetos Produto.
     * Dois produtos são considerados iguais se possuírem o mesmo ID.
     *
     * @param o Objeto a ser comparado com o produto atual
     * @return true se os objetos são iguais, false caso contrário
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;  // Se forem o mesmo objeto, são iguais
        if (!(o instanceof Produto produto)) return false;  // Se o objeto não for do tipo Produto, retorna false
        return getId().equals(produto.getId());  // Compara os IDs dos dois produtos
    }

    /**
     * Método que gera o código hash do produto.
     * O código hash é gerado com base no ID do produto.
     *
     * @return Código hash do produto
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
