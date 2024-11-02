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
        if (produto.getNome() == null || produto.getNome().isEmpty()) {
            throw new ValidacaoException("O nome do produto não pode ser vazio.");
        }
        if (produto.getNome().length() < 2) {
            throw new ValidacaoException("O nome do produto deve ter pelo menos 2 caracteres.");
        }
        if(produto.getPreco() <= 0){
            throw new ValidacaoException("O preço do produto deve ser maior que zero");
        }
        if (produto.getQuantidadeEstoque() < 0){
            throw new ValidacaoException("A quantidade em estoque do produto não pode ser menor que zero");
        }
        if(produto.getCategoria() == null || produto.getCategoria().isEmpty()){
            throw new ValidacaoException("A categoria do produto não pode ser vazia");
        }
    }
}
