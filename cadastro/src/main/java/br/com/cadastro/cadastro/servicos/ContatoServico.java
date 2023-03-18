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
        if (obj.getNome() == null || obj.getNome().isEmpty() ||
                obj.getEmail() == null || obj.getEmail().isEmpty() ||
                obj.getTelefone() == null || obj.getTelefone().isEmpty() ) {
            return ResponseEntity.badRequest().body("Todos os campos de Contato são obrigatórios");
        }else {
            if (acao.equals("cadastrar")) {
                return new ResponseEntity<>(cr.save(obj), HttpStatus.CREATED);
            } else if (acao.equals("alterar")) {
                return new ResponseEntity<>(cr.save(obj), HttpStatus.OK);
            } else {
                m.setMensagem("acao invalida");
                return new ResponseEntity<>(m, HttpStatus.BAD_REQUEST);
            }
        }
    }

    

    // metodo para selecionar contatos pelo codigo
    public ResponseEntity<?> selecionarPeloId(Long id) {
        if (cr.countById(id) == 0) {
            m.setMensagem("codigo inexistente!");
            return new ResponseEntity<>(m, HttpStatus.BAD_REQUEST);
        } else
            return new ResponseEntity<>(cr.findById(id), HttpStatus.OK);

    }

    // metodo para remover contatos
    public ResponseEntity<Mensagem> deletar(Long id) {
        cr.deleteById(id);

        m.setMensagem("contato deletado!");
        return new ResponseEntity<>(m, HttpStatus.OK);
    }

}