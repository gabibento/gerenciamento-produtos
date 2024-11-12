
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

## Como Executar o Projeto

1. **Importar o Projeto para sua IDE**:
   - **IntelliJ IDEA**:
     1. Abra o IntelliJ IDEA.
     2. Clique em `File` -> `Open...` e selecione a pasta `GerenciamentoProdutos`.
   - **Eclipse**:
     1. Abra o Eclipse.
     2. Clique em `File` -> `Import...` -> `Existing Projects into Workspace` e selecione a pasta `GerenciamentoProdutos`.

2. **Configurar o JDK**:
   - Certifique-se de ter o **JDK 11 ou superior** instalado.
   - Configure o JDK na sua IDE:
     - **IntelliJ IDEA**: `File` -> `Project Structure` -> `Project` -> Selecione o JDK.
     - **Eclipse**: `Window` -> `Preferences` -> `Java` -> `Installed JREs` -> Selecione o JDK.

3. **Rodar o Projeto**:
   - No arquivo `Main.java` (`src/com/loja/Main.java`), clique com o botão direito e selecione **Run 'Main'**.

---

## Exemplo de Uso

O usuário escolhe a opção 1 para cadastrar um produto.
O programa solicita os dados do produto (nome, preço, quantidade, categoria) e os valida.
Após o cadastro, o sistema exibe uma confirmação do ID gerado para o produto.
O usuário pode então realizar outras operações, como buscar produtos por ID ou listar todos os produtos.
Exemplo de Menu Interativo:
```markdown
=============================
        MENU DE PRODUTOS        
=============================
1. Cadastrar Produto
2. Buscar Produto por ID
3. Listar Todos os Produtos
4. Atualizar Produto
5. Deletar Produto
6. Buscar por Nome
7. Buscar por Categoria
8. Buscar por Faixa de Preço
9. Sair

Escolha uma opção: 1
Digite o nome: Produto A
Digite o preço: 99.99
Digite a quantidade: 10
Digite a categoria: Eletrônicos

Produto cadastrado com sucesso!
ID gerado: 1
```
## Decisões de Implementação

### 1. **Estrutura de Pacotes**
O projeto foi organizado em pacotes para modularizar o código:

- **`modelo`**: Contém as entidades, como a classe `Produto`.
- **`gerenciador`**: Gerencia a lógica de negócios, como cadastro e validações de produtos.
- **`ui`**: Responsável pela interface com o usuário no console, incluindo o menu interativo.
- **`exception`**: Contém exceções personalizadas para validar entradas de dados e controlar erros.
  
### 2. Uso de `Stream API`
A `Stream API` é utilizada para filtrar listas de produtos de forma concisa, como nas funções `buscarPorNome`, `buscarPorCategoria` e `buscarPorFaixaPreco`. Isso permite filtrar os produtos de acordo com diferentes critérios, utilizando expressões como `filter()` e `toList()`.

### 3. Uso de `Supplier` e `Consumer`
- **`Supplier`**: A classe usa o `Supplier<T>` para fornecer dados ao método `requisitarEntradaComValidacao`. Este tipo de função é utilizado quando a entrada precisa ser fornecida ao invés de ser calculada. Exemplo: `() -> lerEntradaString()` e `() -> lerEntradaDouble()`.
- **`Consumer`**: O `Consumer<T>` é utilizado para validar os dados de entrada após a leitura, como em `gerenciador::validarNome`. Aqui, o `Consumer` é responsável por aplicar a validação após o valor ser fornecido, lançando uma exceção caso a validação falhe.


### 4. **Validação de Dados**
As validações foram implementadas para garantir que os dados inseridos sejam corretos:

- O nome do produto deve ter pelo menos 2 caracteres.
- O preço deve ser maior que zero.
- A quantidade em estoque não pode ser negativa.
- A categoria não pode estar vazia.

Essas validações ajudam a manter a integridade dos dados.

### 5. **Exceções Personalizadas**
- **`ValidacaoException`**: Foi criada para capturar erros específicos de validação de dados. Quando o usuário insere dados inválidos, uma mensagem clara é retornada informando o erro.
- **`ProdutoException`**: Essa exceção foi criada para o caso de um produto não ser encontrado pelo ID especificado. Quando o usuário tenta acessar ou manipular um produto que não existe, a `ProdutoException` é lançada para alertar o usuário de que o produto não foi encontrado.

### 6. Uso de Cores ANSI
O sistema utiliza **códigos de cores ANSI** para dar destaque a mensagens exibidas no terminal, como mensagens de erro, sucesso ou alertas. Os códigos ANSI permitem alterar a cor do texto no terminal, proporcionando uma interface mais amigável e fácil de navegar. 
- **Azul (`\033[34m`)**: Usado para destacar o título do menu e tornar a interface visualmente mais atraente.
- **Verde (`\033[32m`)**: Usado para indicar sucesso em operações, como "Produto cadastrado com sucesso".
- **Vermelho (`\033[31m`)**: Utilizado para exceções.
- **Amarelo (`\033[33m`)**: Usado para informações gerais ou mensagens de alerta.

### 7. **Estrutura do Menu**
O menu interativo permite ao usuário realizar operações como cadastrar, atualizar, excluir ou buscar produtos. O programa continua executando até que o usuário escolha sair.

## Autor
<div align="left">
  <a href="https://github.com/gabibento">
    <img alt="Gabriella Maurea Bento" src="https://avatars.githubusercontent.com/u/143539144?v=4" width="115" style="border-radius:50%">
  </a>
  <br>
  <sub><b>Gabriella Maurea Bento</b></sub><br>
  <sub>RA: 1788213</sub><br>
</div>

## Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo [LICENSE](LICENSE) para mais detalhes.
