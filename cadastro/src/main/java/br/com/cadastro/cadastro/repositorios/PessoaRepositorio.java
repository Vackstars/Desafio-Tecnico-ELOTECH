package br.com.cadastro.cadastro.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.cadastro.cadastro.modelos.Pessoa;

@Repository
public interface PessoaRepositorio extends CrudRepository<Pessoa,Long>  {
    
    List<Pessoa> findAll();

    List<Pessoa> findByNome(String nome);

    Long countById (Long id);
}
