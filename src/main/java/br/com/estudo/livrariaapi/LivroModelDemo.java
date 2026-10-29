package br.com.estudo.livrariaapi;

import java.math.BigDecimal;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
@Service
@Profile("demo")
public class LivroModelDemo extends ArmazenamentoEmMemoria { public LivroModelDemo() { criar(new Livro(null, "Dom Casmurro", "Machado de Assis", new BigDecimal("39.9"))); } }
