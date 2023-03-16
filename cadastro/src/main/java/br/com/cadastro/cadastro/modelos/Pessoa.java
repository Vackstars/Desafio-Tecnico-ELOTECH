package br.com.cadastro.cadastro.modelos;

import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name="Pessoas")
@Getter
@Setter
public class Pessoa {
    //Atributos
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty (message = "insira um nome")
    @Column(nullable = false, length = 150)
    private String nome;

    @NotEmpty (message = "insira um cpf")
    @Column(nullable = false, length = 11)
    @CPF (message = "CPF inválido")
    private String cpf;

    @NotEmpty (message = "insira uma data de nascimento")
    @Column(nullable = false, length = 10)
    private String dataNascimento;

    @OneToMany
    @JoinColumn(name = "codigo_pessoa")
    @Column(nullable = false)
    @NotEmpty(message = "a pessoa precisa de ao menos 1 contato")
    private List<Contato> contatos;
    
}
