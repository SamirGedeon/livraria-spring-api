package br.com.estudo.livrariaapi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("demo")
class DemoTest {
    @Autowired LivroModel model;
    @Test void selecionaImplementacaoDemo() { assertInstanceOf(LivroModelDemo.class,model); assertEquals(1, model.listar(null, null).size()); }
}
