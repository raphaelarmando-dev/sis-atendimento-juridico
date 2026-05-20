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
@Table(name = "clientes")
public class Cliente extends Pessoa implements Serializable {

    // Segui o padrão da classe Advogado pra não ter erro no JSF
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Atributos
    @Column(length = 50)
    private String statusProcessual;

    @Column(length = 50)
    private String numeroProcesso;

    public Cliente() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatusProcessual() {
        return statusProcessual;
    }

    public void setStatusProcessual(String statusProcessual) {
        this.statusProcessual = statusProcessual;
    }

    public String getNumeroProcesso() {
        return numeroProcesso;
    }

    public void setNumeroProcesso(String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }

    // Garanto que o objeto seja único igual em Advogado
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    // equals de ID
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cliente other = (Cliente) obj;
        return Objects.equals(id, other.id);
    }
}