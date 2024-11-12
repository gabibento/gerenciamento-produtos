package com.loja.exception;

/**
 * Exceção personalizada para tratar erros de validação em dados de produtos.
 * A ValidacaoException é utilizada para sinalizar que um valor de entrada fornecido para o cadastro ou atualização de um produto é inválido.
 * Essa classe herda de {@link RuntimeException}, permitindo que a exceção seja lançada sem necessidade de captura obrigatória.
 */
public class ValidacaoException extends RuntimeException {

    /**
     * Construtor que inicializa a exceção com uma mensagem específica.
     *
     * @param mensagem a mensagem de erro que descreve a causa da validação falha.
     */
    public ValidacaoException(String mensagem) {
        super(mensagem); // Inicializa a superclasse com a mensagem fornecida.
    }
}
