package br.com.cadastro.cadastro.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.cadastro.cadastro.modelos.Contato;
import br.com.cadastro.cadastro.modelos.Mensagem;
import br.com.cadastro.cadastro.repositorios.ContatoRepositorio;

@Service
public class ContatoServico {

    @Autowired
    ContatoRepositorio cr;

    @Autowired
    private Mensagem m;

    // metodo para listar contatos
    public Iterable<Contato> listar() {
        return cr.findAll();
    }

    // metodo para cadastrar ou alterar contatos
    public ResponseEntity<?> cadastrarAlterar(Contato obj, String acao) {
        if (acao.equals("cadastrar")) {
            return new ResponseEntity<Contato>(cr.save(obj), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<Contato>(cr.save(obj), HttpStatus.OK);
        }
    }

    

    // metodo para selecionar pessoas pelo codigo
    public ResponseEntity<?> selecionarPeloId(Long id) {
        if (cr.countById(id) == 0) {
            m.setMensagem("codigo inexistente!");
            return new ResponseEntity<>(m, HttpStatus.BAD_REQUEST);
        } else
            return new ResponseEntity<>(cr.findById(id), HttpStatus.OK);

    }

    // metodo para remover pessoas
    public ResponseEntity<Mensagem> deletar(Long id) {
        cr.deleteById(id);

        m.setMensagem("contato deletado!");
        return new ResponseEntity<>(m, HttpStatus.OK);
    }

}