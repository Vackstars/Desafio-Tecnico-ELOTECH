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

import br.com.cadastro.cadastro.modelos.Contato;
import br.com.cadastro.cadastro.repositorios.ContatoRepositorio;
import br.com.cadastro.cadastro.servicos.ContatoServico;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/contatos")
public class ContatoControle {
    @Autowired
    private ContatoRepositorio cr;

    @Autowired
    private ContatoServico cs;

    
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@Valid @RequestBody Contato obj) {
        return cs.cadastrarAlterar(obj, "cadastrar");
    }

    @PutMapping("/alterar")
    public ResponseEntity<?> alterar(@Valid @RequestBody Contato obj) {
        return cs.cadastrarAlterar(obj, "alterar");
    }

    @GetMapping("/listar")
    public Iterable<Contato> listar() {
        return cs.listar();
    }

    @GetMapping("/listar/{nome}")
    public List<Contato> listarPorNome(@PathVariable String nome) {
        return cr.findByNome(nome);

    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> selecionarId(@PathVariable Long id) {
        return cs.selecionarPeloId(id);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        return cs.deletar(id);

    }

}
