package br.com.cadastro.cadastro.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.cadastro.cadastro.modelos.Contato;

@Repository
public interface ContatoRepositorio extends CrudRepository<Contato, Long> {

    List<Contato> findAll();

    List<Contato> findByNome(String nome);

    Long countById (Long id);
    
}
