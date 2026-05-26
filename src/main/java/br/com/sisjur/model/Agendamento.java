package br.com.sisjur.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "agendamento")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dataHora;

    // @Enumerated salvei como string no banco pra ficar legível, se não, ele grava um número que quebra tudo se eu mudar a ordem do Enum
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAgendamento tipo;
    
    // Campo adicionado após revisar o escopo do desafio para garantir que atendo a todos os requisitos
    @Column(name = "observacoes", columnDefinition = "TEXT")
    private String observacoes;

    // Usei ManyToOne pra avisar que vários agendamentos podem ter um só advogado
    // e JoinColum para garantir o nome da coluna no banco e travar o nulo
    @ManyToOne
    @JoinColumn(name = "advogado_id", nullable = false)
    private Advogado advogado;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    public Agendamento() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public TipoAgendamento getTipo() {
        return tipo;
    }

    public void setTipo(TipoAgendamento tipo) {
        this.tipo = tipo;
    }

    public Advogado getAdvogado() {
        return advogado;
    }

    public void setAdvogado(Advogado advogado) {
        this.advogado = advogado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agendamento that = (Agendamento) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
