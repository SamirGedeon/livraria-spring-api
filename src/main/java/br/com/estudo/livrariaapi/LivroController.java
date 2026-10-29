package br.com.estudo.livrariaapi;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.math.BigDecimal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/livros", produces="application/json")
public class LivroController {
    private final LivroModel model;
    public LivroController(LivroModel model) { this.model = model; }
    @PostMapping(consumes="application/json")
    public ResponseEntity<LivroResponse> criar(@Valid @RequestBody LivroRequest request) {
        Livro criado = model.criar(LivroMapper.toEntity(request, null));
        return ResponseEntity.created(URI.create("/livros/"+criado.getId())).body(LivroMapper.toResponse(criado));
    }
    @GetMapping
    public List<LivroResponse> listar(@RequestParam(name="titulo", required=false) String titulo,
            @RequestParam(name="autor", required=false) String autor) { return model.listar(titulo, autor).stream().map(LivroMapper::toResponse).toList(); }
    @GetMapping("/{id}")
    public LivroResponse buscar(@PathVariable("id") Long id) { return LivroMapper.toResponse(model.buscar(id).orElseThrow(() -> new RecursoNaoEncontradoException(id))); }
    @PutMapping(value="/{id}", consumes="application/json")
    public LivroResponse atualizar(@PathVariable("id") Long id, @Valid @RequestBody LivroRequest request) {
        return LivroMapper.toResponse(model.atualizar(id, LivroMapper.toEntity(request,id)).orElseThrow(() -> new RecursoNaoEncontradoException(id)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        if (!model.excluir(id)) throw new RecursoNaoEncontradoException(id);
        return ResponseEntity.noContent().build();
    }
    
}
