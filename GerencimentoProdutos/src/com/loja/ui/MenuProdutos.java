package com.loja.ui;
import com.loja.exception.ProdutoException;
import com.loja.exception.ValidacaoException;
import com.loja.gerenciador.GerenciadorProdutos;
import com.loja.modelo.Produto;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Classe responsável pela interface do usuário do menu de produtos.
 * Exibe um menu com várias opções para gerenciar produtos (cadastrar, atualizar, excluir, buscar, etc).
 */
public class MenuProdutos {

    private final Scanner scanner = new Scanner(System.in);
    private final GerenciadorProdutos gerenciador = new GerenciadorProdutos();

    // Cores para facilitar a personalização da saída no terminal
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";

    /**
     * Exibe o menu de opções e gerencia as interações do usuário.
     * O menu continua sendo exibido até que o usuário escolha a opção de sair.
     */
    public void exibirMenu() {
        while (true) {
            // Exibe o cabeçalho do menu com cores
            System.out.println(BLUE + "\n=============================");
            System.out.println("        MENU DE PRODUTOS        ");
            System.out.println("=============================" + RESET);
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Buscar Produto por ID");
            System.out.println("3. Listar Todos os Produtos");
            System.out.println("4. Atualizar Produto");
            System.out.println("5. Deletar Produto");
            System.out.println("6. Buscar por Nome");
            System.out.println("7. Buscar por Categoria");
            System.out.println("8. Buscar por Faixa de Preço");
            System.out.println("9. Sair\n");

            // Lê a opção escolhida pelo usuário
            int opcao = lerEntradaInteira("Escolha uma opção: ");

            // Executa a ação correspondente à opção escolhida
            switch (opcao) {
                case 1 -> cadastrarProduto();
                case 2 -> buscarProduto();
                case 3 -> listarProdutos();
                case 4 -> atualizarProduto();
                case 5 -> deletarProduto();
                case 6 -> buscarPorNome();
                case 7 -> buscarPorCategoria();
                case 8 -> buscarPorFaixaPreco();
                case 9 -> {
                    System.out.println(YELLOW + "Saindo do sistema!" + RESET);
                    return; // Encerra o menu
                }
                default -> System.out.println(RED + "Opção inválida" + RESET); // Caso de opção inválida
            }
            // Pausa para o usuário continuar após cada operação
            pausaParaContinuar();
        }
    }

    /**
     * Método responsável por cadastrar um novo produto, realizando a validação dos dados.
     */
    private void cadastrarProduto() {
        boolean produtoCadastrado = false;
        System.out.println(YELLOW + "=== Cadastro de Produto ===" + RESET);

        // Loop até que o produto seja cadastrado corretamente
        while (!produtoCadastrado) {
            try {
                Produto produto = requisitarDados(); // Solicita os dados para o novo produto
                gerenciador.criar(produto); // Chama o gerenciador para cadastrar o produto
                System.out.println(GREEN + "Produto cadastrado com sucesso!" + RESET);
                System.out.println("ID gerado: " + produto.getId());
                produtoCadastrado = true; // Produto cadastrado com sucesso
            } catch (ValidacaoException e) {
                // Exibe mensagem de erro caso a validação falhe
                System.out.println(RED + "Erro ao cadastrar produto: " + e.getMessage() + RESET);
            }
        }
    }

    /**
     * Método genérico para ler entradas com validação personalizada.
     *
     * @param <T> Tipo da entrada
     * @param mensagem A mensagem a ser exibida ao usuário
     * @param leitura Função para realizar a leitura dos dados
     * @param validacao Função para validar a entrada
     * @return A entrada validada
     */
    private <T> T requisitarEntradaComValidacao(String mensagem, Supplier<T> leitura, Consumer<T> validacao) {
        while (true) {
            try {
                T valor = leitura.get();
                validacao.accept(valor);
                return valor;
            } catch (ValidacaoException e) {
                System.out.println(RED + e.getMessage() + RESET);
            }
        }
    }

    /**
     * Solicita todos os dados necessários para criar um produto e retorna o produto criado.
     *
     * @return Produto criado a partir das entradas do usuário
     */
    public Produto requisitarDados() {
        String nome = requisitarEntradaComValidacao(
                "Digite o nome: ",
                () -> lerEntradaString("Digite o nome: "),
                gerenciador::validarNome
        );

        double preco = requisitarEntradaComValidacao(
                "Digite o preço: ",
                () -> lerEntradaDouble("Digite o preço: "),
                gerenciador::validarPreco
        );

        int quantidadeEstoque = requisitarEntradaComValidacao(
                "Digite a quantidade: ",
                () -> lerEntradaInteira("Digite a quantidade: "),
                gerenciador::validarQuantidade
        );

        String categoria = requisitarEntradaComValidacao(
                "Digite a categoria: ",
                () -> lerEntradaString("Digite a categoria: "),
                gerenciador::validarCategoria
        );

        return new Produto(nome, preco, quantidadeEstoque, categoria);
    }

    /**
     * Busca um produto pelo seu ID, utilizando um método auxiliar.
     */
    private void buscarProduto() {
        System.out.println(YELLOW + "=== Busca de produto ===" + RESET);
        Optional<Produto> produto = buscarProdutoPorId();
        System.out.println(produto.map(Produto::toString).orElse(RED + "Produto não encontrado!" + RESET));
    }

    /**
     * Solicita o ID do produto e retorna o produto correspondente, se encontrado.
     *
     * @return Produto encontrado ou Optional vazio caso não encontrado
     */
    private Optional<Produto> buscarProdutoPorId() {
        while (true) {
            int id = lerEntradaInteira("Id do produto (ou 0 para cancelar): ");
            if (id == 0) return Optional.empty(); // Se ID for 0, cancela a operação

            Optional<Produto> produto = gerenciador.buscarPorId(id); // Busca produto pelo ID
            if (produto.isPresent()) {
                return produto; // Retorna o produto encontrado
            } else {
                // Mensagem de erro caso o produto não seja encontrado
                System.out.println(RED + "Produto com id " + id + " não encontrado." + RESET);
            }
        }
    }

    /**
     * Lista todos os produtos cadastrados.
     */
    private void listarProdutos() {
        System.out.println(YELLOW + "=== Lista de produtos ===" + RESET);
        // Exibe todos os produtos ou mensagem caso a lista esteja vazia
        gerenciador.listarTodos().forEach(System.out::println);
        if (gerenciador.listarTodos().isEmpty()) {
            System.out.println(YELLOW + "Lista de produtos vazia" + RESET);
        }
    }

    /**
     * Atualiza um produto existente, solicitando os novos dados e validando a operação.
     */
    private void atualizarProduto() {
        System.out.println(YELLOW + "=== Atualizar produto ===" + RESET);
        Optional<Produto> produtoExistente = buscarProdutoPorId(); // Busca o produto existente
        if (produtoExistente.isPresent()) {
            // Solicita os novos dados do produto
            Produto novoProduto = requisitarDados();
            novoProduto.setId(produtoExistente.get().getId()); // Mantém o ID do produto original
            if (gerenciador.atualizar(novoProduto)) {
                System.out.println(GREEN + "Produto atualizado com sucesso!" + RESET);
            } else {
                System.out.println(RED + "Erro ao atualizar produto" + RESET);
            }
        } else {
            System.out.println(RED + "Atualização cancelada" + RESET);
        }
    }

    /**
     * Deleta um produto, após confirmação do usuário.
     */
    private void deletarProduto() {
        System.out.println(YELLOW + "=== Deletar produto ===" + RESET);
        Optional<Produto> produto = buscarProdutoPorId(); // Busca o produto por ID
        if (produto.isPresent()) {
            int entrada = lerEntradaInteira("Tem certeza que deseja deletar o produto? (1=Sim, 2=Não): ");
            if (entrada == 1) {
                gerenciador.deletar(produto.get().getId());
                System.out.println(GREEN + "Produto deletado com sucesso!" + RESET);
            } else {
                System.out.println(YELLOW + "Remoção cancelada!" + RESET);
            }
        } else {
            System.out.println(RED + "Produto não encontrado para exclusão." + RESET);
        }
    }

    /**
     * Busca produtos por nome.
     */
    private void buscarPorNome() {
        System.out.println(YELLOW + "=== Buscar produto por nome ===" + RESET);
        String nome = lerEntradaString("Nome do produto: ");
        gerenciador.buscarPorNome(nome).forEach(System.out::println);
        if (gerenciador.buscarPorNome(nome).isEmpty()) {
            System.out.println(YELLOW + "Não há produtos que contenha o nome " + nome + RESET);
        }
    }

    /**
     * Busca produtos por categoria.
     */
    private void buscarPorCategoria() {
        System.out.println(YELLOW + "=== Buscar por categoria ===" + RESET);
        String categoria = lerEntradaString("Categoria: ");
        gerenciador.buscarPorCategoria(categoria).forEach(System.out::println);
        if (gerenciador.buscarPorCategoria(categoria).isEmpty()) {
            System.out.println(YELLOW + "Não há produtos com a categoria " + categoria + RESET);
        }
    }

    /**
     * Busca produtos dentro de uma faixa de preço.
     */
    private void buscarPorFaixaPreco() {
        System.out.println(YELLOW + "=== Buscar por faixa de preço ===" + RESET);
        double precoMin = lerEntradaDouble("Preço mínimo: ");
        double precoMax = lerEntradaDouble("Preço máximo: ");
        gerenciador.buscarPorFaixaPreco(precoMin, precoMax).forEach(System.out::println);
        if (gerenciador.buscarPorFaixaPreco(precoMin, precoMax).isEmpty()) {
            System.out.println(YELLOW + "Não há produtos com essa faixa de preço " + RESET);
        }
    }

    // Métodos auxiliares para ler entradas do usuário
    private String lerEntradaString(String mensagem){
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    private int lerEntradaInteira(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(RED + "Entrada inválida. Por favor, digite um número inteiro." + RESET);
            }
        }
    }

    private double lerEntradaDouble(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(RED + "Entrada inválida. Por favor, digite um número decimal." + RESET);
            }
        }
    }

    /**
     * Pausa a execução e aguarda o usuário pressionar Enter para continuar.
     */
    private void pausaParaContinuar() {
        System.out.println(YELLOW + "\nPressione Enter para continuar..." + RESET);
        scanner.nextLine();
    }

}
