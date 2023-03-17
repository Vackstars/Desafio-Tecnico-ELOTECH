package br.com;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.cadastro.cadastro.controles.ContatoControle;
import br.com.cadastro.cadastro.modelos.Contato;
import br.com.cadastro.cadastro.repositorios.ContatoRepositorio;
import br.com.cadastro.cadastro.servicos.ContatoServico;

public class ContatoControleTests {

    @Mock
    private ContatoRepositorio cr;

    @Mock
    private ContatoServico cs;

    @InjectMocks
    private ContatoControle cc;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Deve retornar HTTP 201 CREATED e o contato cadastrado")
    @Test
    public void testCadastrar() {
        // simula a injeção de dados para criar o contato
        Contato contato = new Contato();
        contato.setId(1L);
        contato.setNome("Fabricio");
        contato.setEmail("fabricio@hotmail.com");
        contato.setTelefone("44999980601");

        // Mocka a classe contato servico e define um comportamento para o metodo passado
        // Depois define o valor de retorno com o objeto da contato e o status CREATED
        Mockito.<ResponseEntity<?>>when(cs.cadastrarAlterar(contato, "cadastrar"))
                .thenReturn(new ResponseEntity<>(contato, HttpStatus.CREATED));

        // Cria um objeto ResponseEntity para receber o status apos a chamada do metodo
        // cadastrar de cc para validar ele depois
        ResponseEntity<?> resposta = cc.cadastrar(contato);

        // Verifica se a resposta nao eh nula e possui corpo
        assertNotNull(resposta.getBody());
        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());

        // Validar se os campos de contato não estão vazios
        assertNotNull(contato.getNome());
        assertNotNull(contato.getEmail());
        assertNotNull(contato.getTelefone());
    

    }

    @DisplayName("Deve retornar HTTP 200 OK e o contato alterado")
    @Test
    public void testAlterar() {
        // simula a injeção de dados para criar o contato
        Contato contato = new Contato();
        contato.setId(1L);
        contato.setNome("Fabricio");
        contato.setEmail("fabricio@hotmail.com");
        contato.setTelefone("44999980601");

        // Mocka a classe contato servico e define um comportamento para o metodo passado
        // Depois define o valor de retorno com o objeto do contato e o status OK
        Mockito.<ResponseEntity<?>>when(cs.cadastrarAlterar(contato, "alterar"))
                .thenReturn(new ResponseEntity<>(contato, HttpStatus.OK));

        // Cria um objeto ResponseEntity para receber o status apos a chamada do metodo
        //alterar de cc para validar ele depois
        ResponseEntity<?> resposta = cc.alterar(contato);

        // Verifica se a resposta nao eh nula e possui corpo
        assertNotNull(resposta.getBody());
        assertEquals(HttpStatus.OK, resposta.getStatusCode());

        // Validar se os campos de contato não estão vazios
        assertNotNull(contato.getId());
        assertNotNull(contato.getNome());
        assertNotNull(contato.getEmail());
        assertNotNull(contato.getTelefone());

        
    }

    @DisplayName("Deve retornar uma lista com os contatos")
    @Test
    public void testListar() {
        // Configura o mock da classe cs para o método "listar",
        // retornando uma lista vazia de objetos do tipo contato.
        Mockito.when(cs.listar())
                .thenReturn(new ArrayList<Contato>());

        // Chama o método "listar" de cc, que por sua vez chama o método
        // "listar" de cs, retornando uma lista vazia de objetos do tipo contato.
        Iterable<Contato> contato = cc.listar();

        // Verifica se a lista de contatos retornada não é nula.
        assertNotNull(contato);
    }

    @DisplayName("Dev")
    @Test
    public void testListarPorNome() {
        // Define o valor para o parâmetro nome
        String nome = "Enzo";
        Contato contato = new Contato();
        contato.setNome(nome);

        // Define o comportamento de cr quando o método "findByNome" é chamado com o
        // parâmetro "nome".
        // Neste caso, é definido que será retornado uma lista vazia.
        Mockito.when(cr.findByNome(nome)).thenReturn(new ArrayList<Contato>());

        // Chama o método "listarPorNome" de cc passando o parâmetro "nome".
        // O resultado é armazenado em uma variável "contatos".
        List<Contato> contatos = cc.listarPorNome(nome);

        // Verifica se a variável "contatos" não é nula.
        assertNotNull(contatos);
    }

    @Test
    public void testSelecionarId() {
        Long id = 1L;
        Contato contato = new Contato();
        contato.setId(id);

        // Mocka a classe contato servico e define um comportamento para o metodo passado
        // Depois define o valor de retorno com o objeto do contato e o status OK
        Mockito.<ResponseEntity<?>>when(cs.selecionarPeloId(id))
                .thenReturn(new ResponseEntity<>(contato, HttpStatus.OK));

        // Chama o metodo do cc que utiliza o servico para buscar o contato pelo ID
        ResponseEntity<?> resposta = cc.selecionarId(id);

        // Verifica se a resposta nao eh nula e possui corpo
        assertNotNull(resposta.getBody());
        assertEquals(HttpStatus.OK, resposta.getStatusCode());

        // Verifica se o ID da contato nao eh nulo
        assertNotNull(contato.getId());
    }

    @Test
    public void testRemover() {
        Long id = 1L;
        Contato contato = new Contato();
        contato.setId(id);

        // Mocka a classe contato servico e define um comportamento para o metodo passado
        // Depois define o valor de retorno com null e status NO_CONTENT
        Mockito.<ResponseEntity<?>>when(cs.deletar(id))
                .thenReturn(new ResponseEntity<>(null, HttpStatus.NO_CONTENT));

        // Chama o metodo do cc que utiliza o servico para remover o contato pelo ID
        ResponseEntity<?> resposta = cc.remover(id);

        // Verifica se a resposta possui corpo nulo e o status NO_CONTENT
        assertNull(resposta.getBody());
        assertEquals(HttpStatus.NO_CONTENT, resposta.getStatusCode());

        // Verifica se o ID do contato nao eh nulo
        assertNotNull(contato.getId());
    }

}
