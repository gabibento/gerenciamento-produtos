package com.loja.gerenciador;

import com.loja.exception.ValidacaoException;
import com.loja.modelo.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class GerenciadorProdutos {
    private List<Produto> produtos = new ArrayList<>();
    private int proximoId = 1;

    public void criar(Produto produto){
        validarProduto(produto);
        produto.setId(proximoId);
        produtos.add(produto);
        proximoId++;
    }
    public Optional<Produto> buscarPorId(int id) {
        return produtos.stream()
                .filter(produto -> produto.getId() == id)
                .findFirst();
    }
    public List<Produto> listarTodos(){
        return new ArrayList<>(produtos);
    }
    public boolean atualizar(Produto produto){
        Optional<Produto> produtoEncontrado = buscarPorId(produto.getId());

        if(produtoEncontrado.isPresent()){
            validarProduto(produto);

            Produto produtoAtualizado = produtoEncontrado.get();

            produtoAtualizado.setNome(produto.getNome());
            produtoAtualizado.setPreco(produto.getPreco());
            produtoAtualizado.setCategoria(produto.getCategoria());
            produtoAtualizado.setQuantidadeEstoque(produto.getQuantidadeEstoque());

            return true;
        }
        return false;
    }
    public boolean deletar(int id){
        return produtos.removeIf(produto -> produto.getId() == id);
    }
    public List<Produto> buscarPorNome(String nome){
        return produtos.stream()
                .filter(produto -> produto.getNome().toLowerCase().contains(nome.toLowerCase()))
                .toList();
    }
    public List<Produto> buscarPorCategoria(String categoria){
        return produtos.stream()
                .filter(produto -> produto.getCategoria().equalsIgnoreCase(categoria))
                .toList();
    }
    public void validarProduto(Produto produto){
        validarNome(produto.getNome());
        validarPreco(produto.getPreco());
        validarPreco(produto.getPreco());
        validarCategoria(produto.getCategoria());
    }
    public void validarNome(String nome){
        if (nome == null || nome.isEmpty()) {
            throw new ValidacaoException("O nome do produto não pode ser vazio.");
        }
        if (nome.length() < 2) {
            throw new ValidacaoException("O nome do produto deve ter pelo menos 2 caracteres.");
        }
    }
    public void validarPreco(double preco){
        if(preco <= 0){
            throw new ValidacaoException("O preço do produto deve ser maior que zero");
        }
    }
    public void validarQuantidade(int quantidade){
        if (quantidade < 0){
            throw new ValidacaoException("A quantidade em estoque do produto não pode ser menor que zero");
        }
    }
    public void validarCategoria(String categoria){
        if(categoria == null || categoria.isEmpty()){
            throw new ValidacaoException("A categoria do produto não pode ser vazia");
        }
    }
}
