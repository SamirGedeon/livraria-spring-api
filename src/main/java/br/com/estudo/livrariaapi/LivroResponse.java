package br.com.estudo.livrariaapi;

import java.math.BigDecimal;

public record LivroResponse(Long id, String titulo, String autor, BigDecimal preco) {}
