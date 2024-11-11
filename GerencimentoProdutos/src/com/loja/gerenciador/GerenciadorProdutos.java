package com.loja.gerenciador;

import com.loja.exception.ProdutoException;
import com.loja.exception.ValidacaoException;
import com.loja.modelo.Produto;
import com.loja.ui.CoresConsole;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pelo gerenciamento dos produtos na loja.
 * Esta classe fornece métodos para criar, buscar, atualizar, deletar e validar produtos.
 */
public class GerenciadorProdutos {

    // Lista para armazenar os produtos da loja
    private List<Produto> produtos = new ArrayList<>();

    // Variável que controla o próximo ID a ser atribuído aos produtos
    private int proximoId = 1;

    /**
     * Cria um novo produto e o adiciona à lista de produtos.
     * Antes de adicionar o produto, ele é validado.
     *
     * @param produto O produto a ser criado e adicionado
     */
    public void criar(Produto produto) {
        // Valida o produto antes de adicioná-lo
        validarProduto(produto);
        produto.setId(proximoId);  // Atribui o ID ao produto
        produtos.add(produto);     // Adiciona o produto à lista
        proximoId++;               // Incrementa o próximo ID
    }

    /**
     * Busca um produto pelo seu ID.
     * Retorna um Produto, que pode conter o produto caso encontrado ou lançar uma ProdutoException.
     *
     * @param id O ID do produto a ser buscado
     * @return Produto se encontrado ou ProdutoException
     */
    public Produto buscarPorId(int id) {
        return produtos.stream()
                .filter(produto -> produto.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ProdutoException(CoresConsole.RED + "Produto com id " + id + " não encontrado." + CoresConsole.RESET));
    }

    /**
     * Retorna uma lista com todos os produtos cadastrados na loja.
     *
     * @return Lista de todos os produtos
     */
    public List<Produto> listarTodos() {
        return new ArrayList<>(produtos);
    }

    /**
     * Atualiza as informações de um produto existente.
     * O produto deve existir na lista para ser atualizado.
     *
     * @param produto O produto com os dados atualizados
     * @return true se o produto foi atualizado com sucesso, false caso contrário
     */
    public boolean atualizar(Produto produto) {
        // Busca o produto pelo ID
        Produto produtoEncontrado = buscarPorId(produto.getId());

       try {
            // Valida o produto antes de atualizar
            validarProduto(produto);

            Produto produtoAtualizado = produtoEncontrado;

            // Atualiza os dados do produto encontrado
            produtoAtualizado.setNome(produto.getNome());
            produtoAtualizado.setPreco(produto.getPreco());
            produtoAtualizado.setCategoria(produto.getCategoria());
            produtoAtualizado.setQuantidadeEstoque(produto.getQuantidadeEstoque());
            return true;

        } catch (ProdutoException e){
           return false;
       }
    }

    /**
     * Deleta um produto da lista baseado no seu ID.
     *
     * @param id O ID do produto a ser deletado
     * @return true se o produto foi removido com sucesso, false caso contrário
     */
    public boolean deletar(int id) {
        return produtos.removeIf(produto -> produto.getId() == id);
    }

    /**
     * Busca produtos que contêm o nome informado de forma case insensitive.
     *
     * @param nome O nome (parcial ou completo) a ser buscado
     * @return Lista de produtos que contêm o nome informado
     */
    public List<Produto> buscarPorNome(String nome) {
        return produtos.stream()
                .filter(produto -> produto.getNome().toLowerCase().contains(nome.toLowerCase()))
                .toList();
    }

    /**
     * Busca produtos que pertencem à categoria informada de forma case insensitive.
     *
     * @param categoria A categoria a ser buscada
     * @return Lista de produtos pertencentes à categoria
     */
    public List<Produto> buscarPorCategoria(String categoria) {
        // Filtra os produtos pela categoria
        return produtos.stream()
                .filter(produto -> produto.getCategoria().equalsIgnoreCase(categoria))
                .toList();
    }

    /**
     * Busca produtos que estão dentro da faixa de preço especificada.
     *
     * @param precoMin O preço mínimo da faixa
     * @param precoMax O preço máximo da faixa
     * @return Lista de produtos dentro da faixa de preço
     */
    public List<Produto> buscarPorFaixaPreco(double precoMin, double precoMax) {
        return produtos.stream()
                .filter(produto -> produto.getPreco() >= precoMin && produto.getPreco() <= precoMax)
                .toList();
    }

    /**
     * Valida um produto verificando seus atributos (nome, preço, categoria).
     *
     * @param produto O produto a ser validado
     */
    public void validarProduto(Produto produto) {
        validarNome(produto.getNome());
        validarPreco(produto.getPreco());
        validarQuantidade(produto.getQuantidadeEstoque());
        validarCategoria(produto.getCategoria());
    }

    /**
     * Valida o nome de um produto.
     * O nome deve ter pelo menos 2 caracteres.
     *
     * @param nome O nome a ser validado
     * @throws ValidacaoException Se o nome não for válido
     */
    public void validarNome(String nome) {
        if (nome.length() < 2) {
            throw new ValidacaoException("O nome do produto deve ter pelo menos 2 caracteres.");
        }
    }
    /**
     * Valida o preço de um produto.
     * O preço deve ser maior que zero.
     *
     * @param preco O preço a ser validado
     * @throws ValidacaoException Se o preço não for válido
     */
    public void validarPreco(double preco) {
        if (preco <= 0) {
            throw new ValidacaoException("O preço do produto deve ser maior que zero.");
        }
    }

    /**
     * Valida a quantidade de um produto em estoque.
     * A quantidade não pode ser negativa.
     *
     * @param quantidade A quantidade a ser validada
     * @throws ValidacaoException Se a quantidade não for válida
     */
    public void validarQuantidade(int quantidade) {
        if (quantidade < 0) {
            throw new ValidacaoException("A quantidade em estoque do produto não pode ser menor que zero.");
        }
    }

    /**
     * Valida a categoria de um produto.
     * A categoria não pode ser nula ou vazia.
     *
     * @param categoria A categoria a ser validada
     * @throws ValidacaoException Se a categoria não for válida
     */
    public void validarCategoria(String categoria) {
        if (categoria == null || categoria.isEmpty()) {
            throw new ValidacaoException("A categoria do produto não pode ser vazia.");
        }
    }
}
