package br.com.cadastro.cadastro.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cadastro.cadastro.modelos.Pessoa;
import br.com.cadastro.cadastro.repositorios.PessoaRepositorio;
import br.com.cadastro.cadastro.servicos.PessoaServico;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoas")
public class PessoaControle {

    @Autowired
    private PessoaRepositorio pr;

    @Autowired
    private PessoaServico ps;

    // o formato da data para cadastrar a dataNascimento é (dd/mm/aaaa)
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@Valid @RequestBody Pessoa obj) {
        return ps.cadastrarAlterar(obj, "cadastrar");
    }

    @PutMapping("/alterar")
    public ResponseEntity<?> alterar(@Valid @RequestBody Pessoa obj) {
        return ps.cadastrarAlterar(obj, "alterar");
    }

    @GetMapping("/listar")
    public Iterable<Pessoa> listar() {
        return ps.listar();
    }

    @GetMapping("/listarNome/{nome}")
    public List<Pessoa> listarPorNome(@PathVariable String nome) {
        return pr.findByNome(nome);

    }

    @GetMapping("/listarId/{id}")
    public ResponseEntity<?> selecionarId(@PathVariable Long id) {
        return ps.selecionarPeloId(id);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        return ps.deletar(id);

    }

}
