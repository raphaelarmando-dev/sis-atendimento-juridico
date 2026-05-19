package br.com.sisjur.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

//Mudei os planos para usar herança e evitar repetição de código.
//Por isso, tive que alterar a classe Advogado para tirar os campos que vieram para cá.
//Assim, quando eu precisar cadastrar um Advogado ou Cliente, ele já herda tudo daqui pronto.
//O @MappedSuperclass avisa ao Hibernate que não quero uma tabela pessoa no banco.
@MappedSuperclass
public abstract class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(length = 20)
    private String telefone;

    @Column(nullable = false, unique = true, length = 14)
    private String cpf;

    // Construtor
    public Pessoa() {
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
