package br.com.fiap.stockagil.Controller;

import br.com.fiap.stockagil.dto.CadastrarProduto;
import br.com.fiap.stockagil.entidade.Produto;
import br.com.fiap.stockagil.entidade.ProdutoQuantidade;
import br.com.fiap.stockagil.exception.ProdutoNotFoundException;
import br.com.fiap.stockagil.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    //Cadastrar no banco de dados
    @PostMapping
    @Transactional
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid CadastrarProduto dados,
                                          UriComponentsBuilder uriBuilder) {
        Produto produto = new Produto(dados);
        produtoRepository.save(produto);
        var uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder errors = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("\n")
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.toString());
    }

    //Retornar uma lista de produtos do estoque por ID
    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable Long id) {
        try {
            Produto produto = produtoRepository.getProdutoById(id);
            return ResponseEntity.ok(produto);
        } catch (ProdutoNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Atualizar um produto por id
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> atualizarProduto(@RequestBody @Valid Produto dados) {
        Produto produto = produtoRepository.findById(dados.id())
                .orElseThrow(() -> new RuntimeException("Produto não localizado"));
        produto.atualizarProduto(dados);
        return ResponseEntity.ok().build();
    }

    //Detetar um Produto do banco
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluirProduto(@PathVariable Long id) {
        if (!produtoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        produtoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ===================================================================================================================

    // Implementação CP3

    // Pesquisar por parte de uma String "Nome"
    @GetMapping("/pesquisar-nome")
    public List<Produto> pesquisarPorParteNome(@RequestParam String parteNome) {
        return produtoRepository.findByParteNome(parteNome);
    }

    // Pesquisar por intervalo de datas
    @GetMapping("/por-data")
    public ResponseEntity<?> pesquisarPorIntervaloDeDatas(
            @RequestParam("dataInicio") String dataInicioStr,
            @RequestParam("dataFim") String dataFimStr) {
        try {
            LocalDate dataInicio = LocalDate.parse(dataInicioStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalDate dataFim = LocalDate.parse(dataFimStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            List<Produto> produtos = produtoRepository.findByIntervaloDeDatas(dataInicio, dataFim);
            return ResponseEntity.ok(produtos);
        } catch (DateTimeParseException e) {
            // Lidar com a exceção de formato de data inválido
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Formato de data inválido. Use o formato dd/MM/yyyy.");
        }
    }

    // Pesquisar utilizando dois parâmetros "nome e preço"
    @GetMapping("/nome-preco")
    public ResponseEntity<Page<Produto>> pesquisarPorNomeEPreco(
            @RequestParam String nome,
            @RequestParam double preco,
            @RequestParam int page,
            @RequestParam int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Produto> produtos = produtoRepository.findByNomeAndPreco(nome, preco, pageable);

        if (produtos.hasContent()) {
            return ResponseEntity.ok(produtos);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    // Pesquisar por qualquer parâmetro
    @GetMapping("/quantidade-por-nome")
    public ProdutoQuantidade obterQuantidadeProdutosPorNome(@RequestParam String nome) {
        int quantidade = produtoRepository.somarQuantidadePorNome(nome);
        return new ProdutoQuantidade(nome, quantidade);
    }

}


