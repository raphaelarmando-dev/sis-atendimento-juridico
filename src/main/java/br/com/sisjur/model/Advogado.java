package br.com.sisjur.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "advogados")
public class Advogado implements Serializable {

	// O JSF exige isso para conseguir salvar e passar os dados do objeto entre as telas
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // O Postgres controla o autoincremento (1, 2, 3...) via SERIAL
    private Long id;
    
    // Sei que não precisava porque o Hibernate cuida disso, mas usei as anotações @Column só para deixar claro o limite de caracteres e validações
    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, unique = true, length = 20)
    private String oab;

    @Column(length = 20)
    private String telefone;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    // Construtor padrao obrigatorio para o Hibernate
    public Advogado() {
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getOab() {
        return oab;
    }

    public void setOab(String oab) {
        this.oab = oab;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Gera um código único pro objeto usando o ID, pro Java não se perder em listas
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // Compara se dois advogados sao iguais olhando apenas para o ID
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true; // Se for o mesmo objeto na memoria, e igual
        if (obj == null)
            return false; // Se o outro objeto for nulo, nao e igual
        if (getClass() != obj.getClass())
            return false; // Se forem de classes diferentes, nao sao iguais
        Advogado other = (Advogado) obj;
        return Objects.equals(id, other.id); // Se os IDs forem iguais, retorna true
    }
}
