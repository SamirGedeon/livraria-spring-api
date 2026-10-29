package br.com.estudo.livrariaapi;

import java.util.*;
import java.math.BigDecimal;
public interface LivroModel {
    Livro criar(Livro entrada);
    List<Livro> listar(String titulo, String autor);
    Optional<Livro> buscar(Long id);
    Optional<Livro> atualizar(Long id, Livro entrada);
    boolean excluir(Long id);
}
