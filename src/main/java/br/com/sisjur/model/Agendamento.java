package br.com.sisjur.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "agendamento")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    // @Enumerated salvei como string no banco pra ficar legível, se não ele grava um número que quebra tudo se eu mudar a ordem do Enum
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAgendamento tipo;

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

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
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
}
