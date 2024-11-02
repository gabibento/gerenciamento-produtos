package com.loja.ui;

import com.loja.exception.ProdutoException;
import com.loja.exception.ValidacaoException;
import com.loja.gerenciador.GerenciadorProdutos;
import com.loja.modelo.Produto;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class MenuProdutos {
    private Scanner scanner = new Scanner(System.in);
    private GerenciadorProdutos gerenciador = new GerenciadorProdutos();

    public void exibirMenu(){

    }
    private void cadastrarProduto(){
        boolean produtoCadastrado = false;

        while(!produtoCadastrado){
            try{
                Produto produto = requisitarDados();
                gerenciador.criar(produto);
                System.out.println("Produto cadastrado com sucesso!");
                produtoCadastrado = true;
            }catch (ValidacaoException e){
                System.out.println("Erro ao cadastrar produto: " + e.getMessage());
            }
        }

    }
    public Produto requisitarDados(){
        String nome = lerEntradaString("Nome do produto: ");
        double preco = lerEntradaDouble("Preço: ");
        int quantidadeEstoque = lerEntradaInteira("Quantidade em estoque: ");
        String categoria = lerEntradaString("Categoria: ");

        return new Produto(nome, preco, quantidadeEstoque, categoria);
    }
    private String lerEntradaString(String mensagem){
        System.out.print(mensagem);
        return scanner.nextLine();
    }
    private double lerEntradaDouble(String mensagem){
        boolean valorValido = false;
        double valor = 0.0;
        while(!valorValido){
            System.out.print(mensagem);
            try{
                valor = Double.parseDouble(scanner.nextLine());
                valorValido = true;
            }catch (NumberFormatException e){
                System.out.println("Entrada inválida. Por favor, digite um número decimal");
            }
        }
        return valor;
    }

    private int lerEntradaInteira(String mensagem){
        boolean valorValido =  false;
        int valor = 0;

        while(!valorValido){
            System.out.print(mensagem);
            try{
                valor = Integer.parseInt(scanner.nextLine());
                valorValido = true;
            }catch (NumberFormatException e){
                System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
            }
        }
        return valor;
    }
    private void buscarProduto(){
        System.out.println(buscarProdutoPorId());
    }
    public Produto buscarProdutoPorId(){
        while(true){
            try{
                int id = lerEntradaInteira("Id do produto: ");
                Optional<Produto> produto = gerenciador.buscarPorId(id);
                if(produto.isEmpty()){
                    throw new ProdutoException("Produto com id " + id + " não encontrado.");
                }
               return produto.get();
            }catch(ProdutoException e){
                System.out.println(e.getMessage() + " Insira um id válido.");
            }
        }
    }
    private void listarProdutos(){
        if(gerenciador.listarTodos().isEmpty()){
            System.out.println("Lista de produtos vazia");
        }else{
            System.out.println(gerenciador.listarTodos());
        }
    }
    private void atualizarProduto(){
        Produto produtoExistente = buscarProdutoPorId();
        Produto novoProduto = requisitarDados();
        novoProduto.setId(produtoExistente.getId());

        if(gerenciador.atualizar(novoProduto)){
            System.out.println("Produto atualizado com sucesso!");
        }else{
            System.out.println("Erro ao atualizar produto");
        }
    }


}
