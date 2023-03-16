package br.com.cadastro.cadastro.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name= "Contatos")
@Getter
@Setter
public class Contato {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    @Column(nullable = false, length = 150)
    private String nome;

    @NotEmpty
    @Column(nullable = false, length = 11)
    private String telefone;

    @NotEmpty
    @Email (message = "Email inválido")
    @Column(nullable = false, length = 50)
    private String email;


    
}
