package com.loja.ui;

import com.loja.exception.ValidacaoException;
import com.loja.gerenciador.GerenciadorProdutos;
import com.loja.modelo.Produto;

import java.util.InputMismatchException;
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
        String nome = scanner.nextLine().trim();

       double preco = solicitarPreco();
       int quantidadeEstoque = solicitarQuantidade();

        System.out.print("Categoria: ");
        String categoria = scanner.nextLine().trim();

        return new Produto(nome, preco, quantidadeEstoque, categoria);
    }
    public double solicitarPreco(){
        boolean precoValido =  false;
        double preco = 0.0;

        while(!precoValido){
            System.out.print("Preço: ");
            try{
                preco = scanner.nextDouble();
                precoValido = true;
            }catch (InputMismatchException e){
                System.out.println("Entrada inválida. Por favor, digite um número válido para o preço.");
                scanner.nextLine();
            }
        }
        return preco;
    }
    public int solicitarQuantidade(){
        boolean quantidadeValida =  false;
        int quantidade = 0;

        while(!quantidadeValida){
            System.out.print("Preço: ");
            try{
                quantidade = scanner.nextInt();
                quantidadeValida = true;
            }catch (InputMismatchException e){
                System.out.println("Entrada inválida. Por favor, digite um número válido para a quantidade.");
                scanner.nextLine();
            }
        }
        return quantidade;
    }

}
