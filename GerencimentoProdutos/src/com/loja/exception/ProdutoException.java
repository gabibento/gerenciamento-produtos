package com.loja.exception;

/**
 * Exceção personalizada para indicar produto não encontrado.
 * A ProdutoException é utilizada para lançar um erro no caso da ausência de um produto com determinado ID.
 * Esta classe herda de {@link RuntimeException}, o que permite seu lançamento sem necessidade de captura obrigatória.
 */
public class ProdutoException extends RuntimeException {

    /**
     * Construtor que inicializa a exceção com uma mensagem específica.
     *
     * @param mensagem a mensagem de erro que descreve o motivo pelo qual a exceção foi lançada, indicando o problema encontrado.
     */
    public ProdutoException(String mensagem) {
        super(mensagem); // Passa a mensagem para a superclasse RuntimeException.
    }
}
