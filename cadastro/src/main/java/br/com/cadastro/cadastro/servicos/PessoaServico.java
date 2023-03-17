package br.com.cadastro.cadastro.servicos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.cadastro.cadastro.modelos.Mensagem;
//import br.com.cadastro.cadastro.modelos.Mensagem;
import br.com.cadastro.cadastro.modelos.Pessoa;
import br.com.cadastro.cadastro.repositorios.PessoaRepositorio;

@Service
public class PessoaServico {

    @Autowired
    private Mensagem m;

    @Autowired
    private PessoaRepositorio pr;

    // metodo para listar as pessoas
    public Iterable<Pessoa> listar() {
        return pr.findAll();
    }

    // metodo para cadastrar ou alterar pessoas
    public ResponseEntity<?> cadastrarAlterar(Pessoa obj, String acao) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse(obj.getDataNascimento(), formatter); // converte a string em um objeto
                                                                              // LocalDate
        LocalDate dataAtual = LocalDate.now(); // obtém a data atual
        if (data.isAfter(dataAtual)) {
            m.setMensagem("a data de nascimento nao pode ser uma data futura!");
            return new ResponseEntity<>(m, HttpStatus.BAD_REQUEST);
        }
        if (obj.getNome() == null || obj.getNome().isEmpty() ||
                obj.getCpf() == null || obj.getCpf().isEmpty() ||
                obj.getDataNascimento() == null || obj.getDataNascimento().isEmpty() ||
                obj.getContatos() == null || obj.getContatos().isEmpty()) {
            return ResponseEntity.badRequest().body("Todos os campos de Pessoa são obrigatórios");
        } else {
            if (acao.equals("cadastrar")) {
                return new ResponseEntity<>(pr.save(obj), HttpStatus.CREATED);
            } else if (acao.equals("alterar")) {
                return new ResponseEntity<>(pr.save(obj), HttpStatus.OK);
            } else {
                m.setMensagem("acao invalida");
                return new ResponseEntity<>(m, HttpStatus.BAD_REQUEST);
            }
        }
    }

    // metodo para selecionar pessoas pelo codigo
    public ResponseEntity<?> selecionarPeloId(Long id) {
        if (pr.countById(id) == 0) {
            m.setMensagem("codigo inexistente!");
            return new ResponseEntity<>(m, HttpStatus.BAD_REQUEST);
        } else
            return new ResponseEntity<>(pr.findById(id), HttpStatus.OK);

    }

    // metodo para remover pessoas
    public ResponseEntity<Mensagem> deletar(Long id) {
        pr.deleteById(id);

        m.setMensagem("pessoa deletada!");
        return new ResponseEntity<>(m, HttpStatus.OK);
    }

}
