package com.loja;

import com.loja.ui.MenuProdutos;

/**
 * Classe principal do sistema de loja, responsável por inicializar e executar o programa.
 *
 * A execução do sistema inicia a partir deste ponto, onde a instância do menu de produtos
 * é criada e o menu interativo é exibido para que o usuário possa gerenciar os produtos
 * (cadastrar, buscar, listar, atualizar e deletar).
 * </p>
 */
public class Main {

    /**
     * Método principal que inicializa o sistema de loja.
     *
     * Cria uma instância da classe {@link MenuProdutos} e chama o método {@code exibirMenu()}
     * para iniciar a interface interativa com o usuário, permitindo a seleção de operações
     * para gerenciamento dos produtos na loja.
     *
     * @param args Argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        MenuProdutos menu = new MenuProdutos();
        menu.exibirMenu();
    }
}
