package com.loja.ui;

import com.loja.exception.ValidacaoException;
import com.loja.gerenciador.GerenciadorProdutos;
import com.loja.modelo.Produto;

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
                gerenciador.validarProduto(produto);
                gerenciador.criar(produto);
                System.out.println("Produto cadastrado com sucesso!");
                produtoCadastrado = true;
            }catch (ValidacaoException e){
                System.out.println("Erro ao cadastrar produto: " + e.getMessage());
            }
        }

    }
    public Produto requisitarDados(){
        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();

        System.out.print("Preço: ");
        double preco = scanner.nextDouble();

        System.out.print("Quantidade: ");
        int quantidadeEstoque = scanner.nextInt();

        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();

        return new Produto(nome, preco, quantidadeEstoque, categoria);
    }
}
