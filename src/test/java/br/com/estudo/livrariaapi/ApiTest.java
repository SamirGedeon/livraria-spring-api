package br.com.estudo.livrariaapi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
class ApiTest {
    @Autowired MockMvc mvc;
    @Autowired com.fasterxml.jackson.databind.ObjectMapper json;
    @Test void crudFiltroERegras() throws Exception {
        String body="{\"titulo\": \"Dom Casmurro\", \"autor\": \"Machado de Assis\", \"preco\": 39.9}";
        String response=mvc.perform(post("/livros").contentType(MediaType.APPLICATION_JSON).content(body)).andExpect(status().isCreated()).andExpect(header().exists("Location")).andReturn().getResponse().getContentAsString();
        long id=json.readTree(response).get("id").asLong();
        mvc.perform(get("/livros/"+id)).andExpect(status().isOk()).andExpect(jsonPath("$.titulo").value("Dom Casmurro"));
        mvc.perform(get("/livros").param("titulo","CASM")).andExpect(status().isOk()).andExpect(jsonPath("$[0].id").value(id));
        
        mvc.perform(put("/livros/"+id).contentType(MediaType.APPLICATION_JSON).content("{\"titulo\": \"Dom Casmurro Atualizado\", \"autor\": \"Machado de Assis\", \"preco\": 39.9}")).andExpect(status().isOk()).andExpect(jsonPath("$.titulo").value("Dom Casmurro Atualizado"));
        mvc.perform(delete("/livros/"+id)).andExpect(status().isNoContent()).andExpect(content().string(""));
        mvc.perform(get("/livros/"+id)).andExpect(status().isNotFound());
        mvc.perform(delete("/livros/"+id)).andExpect(status().isNotFound());
    }
    @Test void validacaoEContratoHttp() throws Exception {
        mvc.perform(post("/livros").contentType(MediaType.APPLICATION_JSON).content("{}")).andExpect(status().isBadRequest()).andExpect(jsonPath("$.campos").isArray());
        mvc.perform(post("/livros").contentType(MediaType.APPLICATION_JSON).content("{")).andExpect(status().isBadRequest());
        mvc.perform(get("/livros/abc")).andExpect(status().isBadRequest());
        mvc.perform(post("/livros").contentType(MediaType.TEXT_PLAIN).content("teste")).andExpect(status().isUnsupportedMediaType());
        mvc.perform(get("/livros").accept(MediaType.TEXT_HTML)).andExpect(status().isNotAcceptable());
    }
}
