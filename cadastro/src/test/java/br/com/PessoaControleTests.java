package br.com;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.cadastro.cadastro.controles.PessoaControle;
import br.com.cadastro.cadastro.modelos.Contato;
import br.com.cadastro.cadastro.modelos.Pessoa;
import br.com.cadastro.cadastro.repositorios.PessoaRepositorio;
import br.com.cadastro.cadastro.servicos.PessoaServico;

public class PessoaControleTests {

    @Mock
    private PessoaRepositorio pr;

    @Mock
    private PessoaServico ps;

    @InjectMocks
    private PessoaControle pc;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCadastrar() {
        // simula a injeção de dados para criar a pessoa e o contato
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Enzo");
        pessoa.setCpf("13322548961");
        pessoa.setDataNascimento("21/12/2000");
        List<Contato> contatos = new ArrayList<>();
        Contato contato = new Contato();
        contato.setId(1L);
        contato.setNome("Fabricio");
        contato.setEmail("fabricio@hotmail.com");
        contato.setTelefone("44999980601");
        contatos.add(contato);
        pessoa.setContatos(contatos);

        // Mocka a classe pessoa servico e define um comportamento para o metodo passado
        // Depois define o valor de retorno com o objeto da pessoa e o status CREATED
        Mockito.<ResponseEntity<?>>when(ps.cadastrarAlterar(pessoa, "cadastrar"))
                .thenReturn(new ResponseEntity<>(pessoa, HttpStatus.CREATED));

        // Cria um objeto ResponseEntity para receber o status apos a chamada do metodo
        // cadastrar de pc para validar ele depois
        ResponseEntity<?> resposta = pc.cadastrar(pessoa);

        // Verifica se a resposta nao eh nula e possui corpo
        assertNotNull(resposta.getBody());
        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());

        // Validar se os campos de pessoa não estão vazios
        assertNotNull(pessoa.getNome());
        assertNotNull(pessoa.getCpf());
        assertNotNull(pessoa.getDataNascimento());

        // Verifica se a lista de contatos da pessoa não está vazia
        assertFalse(pessoa.getContatos().isEmpty());

    }

    @Test
    public void testAlterar() {
        // simula a injeção de dados para criar a pessoa e o contato
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Enzo");
        pessoa.setCpf("13322548961");
        pessoa.setDataNascimento("21/12/2000");
        List<Contato> contatos = new ArrayList<>();
        Contato contato = new Contato();
        contato.setId(1L);
        contato.setNome("Fabricio");
        contato.setEmail("fabricio@hotmail.com");
        contato.setTelefone("44999980601");
        contatos.add(contato);
        pessoa.setContatos(contatos);

        // Mocka a classe pessoa servico e define um comportamento para o metodo passado
        // Depois define o valor de retorno com o objeto da pessoa e o status OK
        Mockito.<ResponseEntity<?>>when(ps.cadastrarAlterar(pessoa, "alterar"))
                .thenReturn(new ResponseEntity<>(pessoa, HttpStatus.OK));

        // Cria um objeto ResponseEntity para receber o status apos a chamada do metodo
        // alterar de pc para validar ele depois
        ResponseEntity<?> resposta = pc.alterar(pessoa);

        // Verifica se a resposta nao eh nula e possui corpo
        assertNotNull(resposta.getBody());
        assertEquals(HttpStatus.OK, resposta.getStatusCode());

        // Validar se os campos de pessoa não estão vazios
        assertNotNull(pessoa.getId());
        assertNotNull(pessoa.getNome());
        assertNotNull(pessoa.getCpf());
        assertNotNull(pessoa.getDataNascimento());

        // Verifica se a lista de contatos da pessoa não está vazia
        assertFalse(pessoa.getContatos().isEmpty());
    }

    @Test
    public void testListar() {
        // Configura o mock da classe ps para o método "listar",
        // retornando uma lista vazia de objetos do tipo Pessoa.
        Mockito.when(ps.listar())
                .thenReturn(new ArrayList<Pessoa>());

        // Chama o método "listar" de pc, que por sua vez chama o método
        // "listar" de ps, retornando uma lista vazia de objetos do tipo Pessoa.
        Iterable<Pessoa> pessoas = pc.listar();

        // Verifica se a lista de pessoas retornada não é nula.
        assertNotNull(pessoas);
    }

    @Test
    public void testListarPorNome() {
        // Define o valor para o parâmetro nome
        String nome = "Enzo";

        // Define o comportamento de pr quando o método "findByNome" é chamado com o
        // parâmetro "nome".
        // Neste caso, é definido que será retornado uma lista vazia.
        Mockito.when(pr.findByNome(nome)).thenReturn(new ArrayList<Pessoa>());

        // Chama o método "listarPorNome" de pc passando o parâmetro "nome".
        // O resultado é armazenado em uma variável "pessoas".
        List<Pessoa> pessoas = pc.listarPorNome(nome);

        // Verifica se a variável "pessoas" não é nula.
        assertNotNull(pessoas);
    }

    @Test
    public void testSelecionarId() {
        Long id = 1L;
        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);

        // Mocka a classe pessoa servico e define um comportamento para o metodo passado
        // Depois define o valor de retorno com o objeto da pessoa e o status OK
        Mockito.<ResponseEntity<?>>when(ps.selecionarPeloId(id))
                .thenReturn(new ResponseEntity<>(pessoa, HttpStatus.OK));

        // Chama o metodo do pc que utiliza o servico para buscar a pessoa pelo ID
        ResponseEntity<?> resposta = pc.selecionarId(id);

        // Verifica se a resposta nao eh nula e possui corpo
        assertNotNull(resposta.getBody());
        assertEquals(HttpStatus.OK, resposta.getStatusCode());

        // Verifica se o ID da pessoa nao eh nulo
        assertNotNull(pessoa.getId());
    }

    @Test
    public void testRemover() {
        Long id = 1L;
        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);

        // Mocka a classe pessoa servico e define um comportamento para o metodo passado
        // Depois define o valor de retorno com null e status NO_CONTENT
        Mockito.<ResponseEntity<?>>when(ps.deletar(id))
                .thenReturn(new ResponseEntity<>(null, HttpStatus.NO_CONTENT));

        // Chama o metodo do pc que utiliza o servico para remover a pessoa pelo ID
        ResponseEntity<?> resposta = pc.remover(id);

        // Verifica se a resposta possui corpo nulo e o status NO_CONTENT
        assertNull(resposta.getBody());
        assertEquals(HttpStatus.NO_CONTENT, resposta.getStatusCode());

        // Verifica se o ID da pessoa nao eh nulo
        assertNotNull(pessoa.getId());
    }

}
