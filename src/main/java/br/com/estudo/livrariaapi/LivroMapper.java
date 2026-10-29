package br.com.estudo.livrariaapi;

public final class LivroMapper {
    private LivroMapper() {}
    public static Livro toEntity(LivroRequest r, Long id) { return new Livro(id, r.titulo().strip(), r.autor().strip(), r.preco()); }
    public static LivroResponse toResponse(Livro item) { return new LivroResponse(item.getId(), item.getTitulo(), item.getAutor(), item.getPreco()); }
}
