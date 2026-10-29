package br.com.estudo.livrariaapi;

import java.math.BigDecimal;
import jakarta.validation.constraints.*;

// DTO de entrada; ID é gerado no servidor.
public record LivroRequest(
    @NotBlank(message="titulo é obrigatório") @Size(max=120) String titulo,
    @NotBlank(message="autor é obrigatório") @Size(max=120) String autor,
    @NotNull @DecimalMin("0.00") @Digits(integer=9, fraction=2) BigDecimal preco
) {}
