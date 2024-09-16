# App StockAgil
### Entrega CP3 | Desenvolvimento Queries "Pesquisas com JPQL e Spring Data JPA"

## Descrição
StockAgil é uma aplicação para previsão de demandas de estoque.
### - Continuação do Desenvolvimento

## Equipe
- Jose Carnauba RM552367 | Eduardo Junio RM552374 | Danielly Pfander RM552391

## Distribução de Atividades

- Jose Carnaúba: Implementação das entidades JPA
- Eduardo Junio: Configuração do banco de dados e repositórios
- Jose Carnauba: Desenvolvimento dos endpoints REST
- Danielly Pfander: Documentação e testes

## Instruções para Rodar a Aplicação
1. Clone o repositório.
2. Configure o banco de dados no arquivo `application.properties`.
3. Rode o comando `StockAgilApplication`.

## Endpoints Entrega Sprint2
- `POST /api/produto`: Cria um novo produto.
- `GET /api/produto/{id}`: Retorna um produto pelo ID.
- `GET /api/produto`: Retorna todos os produtos.
- `PUT /api/produto/{id}`: Atualiza um produto pelo ID.
- `DELETE /api/produto/{id}`: Deleta um produto pelo ID.

## Endpoints CP3
- `GET /api/usuarios/pesquisar-nome?parteNome={Jose}`: Retorna o nome do usuario.
- `GET /api/produtos/pesquisar-nome?parteNome=Pneu`: Retorna um produto por nome e parte do nome.
- `GET /api/produtos/nome-preco?nome=bateria&preco=14.100&page=0&size=10`: Retorna um produto por dois parâmetros.
- `GET /api/produtos/por-data?dataInicio=20/05/2024&dataFim=24/05/2024`: Retorna por intervalo de datas.
