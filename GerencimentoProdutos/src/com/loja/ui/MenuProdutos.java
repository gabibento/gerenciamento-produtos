package com.loja.ui;

import com.loja.exception.ProdutoException;
import com.loja.exception.ValidacaoException;
import com.loja.gerenciador.GerenciadorProdutos;
import com.loja.modelo.Produto;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class MenuProdutos {
    private final Scanner scanner = new Scanner(System.in);
    private final GerenciadorProdutos gerenciador = new GerenciadorProdutos();

    public void exibirMenu() throws InterruptedException {
        while(true){
            Thread.sleep(2000);
            System.out.println("\n=== Menu ===");
            System.out.println(
                    "1. Cadastrar Produto\n" +
                    "2. Buscar Produto por ID\n" +
                    "3. Listar Todos os Produtos\n" +
                    "4. Atualizar Produto\n" +
                    "5. Deletar Produto\n" +
                    "6. Buscar por Nome\n" +
                    "7. Buscar por Categoria\n" +
                    "8. Buscar por Faixa de Preço\n" +
                    "9. Sair\n");
            int opcao = lerEntradaInteira("Escolha uma opção: ");
            switch (opcao){
                case 1 -> cadastrarProduto();
                case 2 -> buscarProduto();
                case 3 -> listarProdutos();
                case 4 -> atualizarProduto();
                case 5 -> deletarProduto();
                case 6 -> buscarPorNome();
                case 7 -> buscarPorCategoria();
                case 8 -> buscarPorFaixaPreco();
                case 9 -> {
                    System.out.println("Saindo do sistema!");
                    return;
                }
                default -> System.out.println("Opção inválida");
            }
        }
    }
    private void cadastrarProduto() throws InterruptedException {
        boolean produtoCadastrado = false;

        while(!produtoCadastrado){
            System.out.println("=== Cadastro de Produto ===");
            try{
                Produto produto = requisitarDados();
                gerenciador.criar(produto);
                System.out.println("Produto cadastrado com sucesso!");
                System.out.println("Id gerado: " + produto.getId());
                produtoCadastrado = true;
            }catch (ValidacaoException e){
                System.out.println("Erro ao cadastrar produto: " + e.getMessage());
                Thread.sleep(1000);
            }
        }

    }
    public Produto requisitarDados(){
        String nome, categoria;
        double preco;
        int quantidadeEstoque;

        while(true) {
            nome = lerEntradaString("Digite o nome: ");
            try{
                gerenciador.validarNome(nome);
                break;
            }catch (ValidacaoException e){
                System.out.println(e.getMessage());
            }
        }
        while(true) {
            preco = lerEntradaDouble("Digite o preço: ");
            try{
                gerenciador.validarPreco(preco);
                break;
            }catch (ValidacaoException e){
                System.out.println(e.getMessage());
            }
        }
        while(true) {
            quantidadeEstoque = lerEntradaInteira("Digite a quantidade: ");
            try{
                gerenciador.validarQuantidade(quantidadeEstoque);
                break;
            }catch (ValidacaoException e){
                System.out.println(e.getMessage());
            }
        }
        while(true) {
            categoria = lerEntradaString("Digite a categoria: ");

            try{
                gerenciador.validarCategoria(categoria);
                break;
            }catch (ValidacaoException e){
                System.out.println(e.getMessage());
            }
        }

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
        System.out.println("=== Busca de produto ===");
        Optional<Produto> produto = buscarProdutoPorId();
        System.out.println(produto.isEmpty()? "Busca cancelada!" : produto.get());
    }
    public Optional<Produto> buscarProdutoPorId(){
        while(true){
            try{
                int id = lerEntradaInteira("Id do produto: ");
                if(id == 0) return Optional.empty();
                Optional<Produto> produto = gerenciador.buscarPorId(id);
                if(produto.isEmpty()){
                    throw new ProdutoException("Produto com id " + id + " não encontrado.");
                }
               return produto;
            }catch(ProdutoException e){
                System.out.println(e.getMessage() + " Insira um id válido ou digite 0 para cancelar");
            }
        }
    }
    private void listarProdutos(){
        System.out.println("=== Lista de produtos ===");
        if(gerenciador.listarTodos().isEmpty()){
            System.out.println("Lista de produtos vazia");
        }else{
            gerenciador.listarTodos().forEach(System.out::println);
        }
    }
    private void atualizarProduto(){
        System.out.println("=== Atualizar produto ===");
        Optional<Produto> produtoExistente = buscarProdutoPorId();
        Produto novoProduto = requisitarDados();
        if(produtoExistente.isPresent()){
            novoProduto.setId(produtoExistente.get().getId());

            if(gerenciador.atualizar(novoProduto)){
                System.out.println("Produto atualizado com sucesso!");
            }else{
                System.out.println("Erro ao atualizar produto");
            }
        }

    }
    private void deletarProduto(){
        System.out.println("=== Deletar produto ===");
        Optional<Produto> produto = buscarProdutoPorId();
        if(produto.isPresent()){
            int entrada = lerEntradaInteira("Tem certeza que deseja deletar o produto com id " + produto.get().getId() + "? Digite 1 para 'sim' ou 2 para 'não'.");

            while(true){
                if(entrada == 1){
                    if(gerenciador.deletar(produto.get().getId())){
                        System.out.println("Produto deletado com sucesso! ");
                    }else{
                        System.out.println("Erro ao deletar produto. Por favor tente novamente");
                    }
                    break;
                }else if(entrada == 2){
                    System.out.println("Remoção cancelada!");
                    break;
                }else{
                    entrada = lerEntradaInteira("Entrada inválida! Digite 1 para remover o produto e 2 para cancelar remoção");
                }
            }
        }else{
            System.out.println("Remoção cancelada!");
        }
    }
    private void buscarPorNome(){
        System.out.println("=== Buscar produto por nome ===");
        String nome = lerEntradaString("Nome do produto: ");
        if(gerenciador.buscarPorNome(nome).isEmpty()){
            System.out.println("Não há produtos com o nome informado");
        }else{
            gerenciador.buscarPorNome(nome).forEach(System.out::println);
        }
    }
    private void buscarPorCategoria(){
        System.out.println("=== Buscar por categoria ===");
        String categoria = lerEntradaString("Categoria: ");
        if(gerenciador.buscarPorCategoria(categoria).isEmpty()){
            System.out.println("Não há produtos com a categoria informada");
        }else{
            gerenciador.buscarPorCategoria(categoria).forEach(System.out::println);
        }
    }
    private void buscarPorFaixaPreco(){
        System.out.println("=== Buscar por faixa de preço ===");
        double precoMin = lerEntradaDouble("Preço mínimo: ");
        double precoMax = lerEntradaDouble("Preço maximo: ");
        if(gerenciador.buscarPorFaixaPreco(precoMin, precoMax).isEmpty()){
            System.out.println("Não há produtos com o preço entre R$" + precoMin + " e R$" + precoMax);
        }else{
            gerenciador.buscarPorFaixaPreco(precoMin, precoMax).forEach(System.out::println);
        }

    }


}
