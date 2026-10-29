package br.com.estudo.livrariaapi;

import java.math.BigDecimal;

public class Livro {
    private final Long id;
    private final String titulo;
    private final String autor;
    private final BigDecimal preco;
    public Livro(Long id, String titulo, String autor, BigDecimal preco) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.preco = preco;
    }
    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public BigDecimal getPreco() { return preco; }
}
