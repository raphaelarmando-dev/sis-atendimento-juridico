package br.com.sisjur.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "advogados")
public class Advogado extends Pessoa implements Serializable {

    // O JSF exige isso para conseguir salvar e passar os dados do objeto entre as telas
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // O Postgres controla o autoincremento (1, 2, 3...) via SERIAL
    private Long id;
    
    // Sei que não precisava porque o Hibernate cuida disso, mas usei as anotações @Column só para deixar claro o limite de caracteres e validações
    @Column(nullable = false, unique = true, length = 20)
    private String oab;
    
    // Garante exclusão em cascata para evitar erros de integridade (FK)
    @OneToMany(mappedBy = "advogado", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Agendamento> agendamentos; // commitar alteracao depois

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

    public String getOab() {
        return oab;
    }

    public void setOab(String oab) {
        this.oab = oab;
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