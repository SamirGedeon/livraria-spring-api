package br.com.estudo.livrariaapi;

import java.util.*;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public abstract class ArmazenamentoEmMemoria implements LivroModel {
    private final Map<Long, Livro> itens = new LinkedHashMap<>();
    private final AtomicLong sequencia = new AtomicLong();
    public synchronized Livro criar(Livro entrada) {
        Long id = sequencia.incrementAndGet();
        Livro criado = new Livro(id, entrada.getTitulo(), entrada.getAutor(), entrada.getPreco());
        itens.put(id, criado);
        return criado;
    }
    public synchronized List<Livro> listar(String titulo, String autor) {
        
        return itens.values().stream()
                        .filter(item -> titulo == null || titulo.isBlank() || item.getTitulo().toLowerCase(Locale.ROOT).contains(titulo.strip().toLowerCase(Locale.ROOT)))
            .filter(item -> autor == null || autor.isBlank() || item.getAutor().toLowerCase(Locale.ROOT).contains(autor.strip().toLowerCase(Locale.ROOT)))
            .toList();
    }
    public synchronized Optional<Livro> buscar(Long id) { return Optional.ofNullable(itens.get(id)); }
    public synchronized Optional<Livro> atualizar(Long id, Livro entrada) {
        if (!itens.containsKey(id)) return Optional.empty();
        Livro atualizado = new Livro(id, entrada.getTitulo(), entrada.getAutor(), entrada.getPreco());
        itens.put(id, atualizado);
        return Optional.of(atualizado);
    }
    public synchronized boolean excluir(Long id) { return itens.remove(id) != null; }
}
