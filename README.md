
# Sistema de Gerenciamento de Produtos

Este é um sistema simples de gerenciamento de produtos, desenvolvido em Java, que permite aos usuários realizar diversas operações em uma loja. Através de um menu interativo, os usuários podem cadastrar, buscar, atualizar, listar e excluir produtos do estoque, além de filtrar produtos por nome, categoria e faixa de preço.

## Funcionalidades

O sistema oferece as seguintes funcionalidades:

1. **Cadastrar Produto**: Cadastra novos produtos no sistema.
2. **Buscar Produto por ID**: Busca um produto pelo seu ID.
3. **Listar Todos os Produtos**: Exibe uma lista com todos os produtos cadastrados no sistema.
4. **Atualizar Produto**: Atualiza as informações de um produto existente.
5. **Deletar Produto**: Permite a exclusão de um produto cadastrado.
6. **Buscar por Nome**: Busca produtos com base no nome informado.
7. **Buscar por Categoria**: Permite buscar produtos de uma categoria específica.
8. **Buscar por Faixa de Preço**: Exibe produtos dentro de uma faixa de preço específica.
9. **Sair**: Finaliza a execução do programa.

## Tecnologias Utilizadas

- **Java 8+**: Linguagem de programação principal.
- **Scanner**: Para capturar entradas do usuário através do terminal.
- **Optional**: Utilizado para evitar erros de nulidade ao buscar produtos.
- **Exceptions**: Tratamento de exceções para validação de entradas e operações.

## Estrutura do Projeto

O projeto está organizado da seguinte maneira:

```
├── com/
│   └── loja/
│       ├── exception/               # Classes de exceção para validação de dados
│       ├── gerenciador/             # Classe para gerenciar os produtos
│       ├── modelo/                  # Modelo da classe Produto
│       └── ui/                      # Interface de usuário (Menu)
        └── Main                     # Classe principal
```

### Principais Classes

- **MenuProdutos**: Classe responsável pela interface de usuário, que exibe o menu e permite a interação com o usuário.
- **GerenciadorProdutos**: Gerencia as operações de produtos, como cadastro, busca, atualização e exclusão.
- **Produto**: Modelo de dados do produto, contendo informações como nome, preço, quantidade e categoria.
- **ProdutoException e ValidacaoException**: Classes de exceção personalizadas para tratar erros específicos no sistema.

## Licença

Este projeto é de código aberto e está sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.
