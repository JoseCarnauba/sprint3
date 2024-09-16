package br.com.fiap.stockagil.exception;

public class ProdutoNotFoundException extends RuntimeException{
    public ProdutoNotFoundException(String message) {
        super(message);
    }
}
