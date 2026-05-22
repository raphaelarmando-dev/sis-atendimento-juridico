package br.com.sisjur.bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.com.sisjur.model.Agendamento;
import br.com.sisjur.model.Advogado;
import br.com.sisjur.model.Cliente;
import br.com.sisjur.dao.AgendamentoDAO;
import br.com.sisjur.dao.AdvogadoDAO;
import br.com.sisjur.dao.ClienteDAO;

@Named
@ViewScoped
public class AgendamentoBean implements Serializable { 			// Estrutura base de AdvogadoBean, adaptada para a lógica de Agendamento

    private static final long serialVersionUID = 1L;

    @Inject
    private AgendamentoDAO agendamentoDAO;
    @Inject
    private AdvogadoDAO advogadoDAO;
    @Inject
    private ClienteDAO clienteDAO;

    private Agendamento agendamento;
    private List<Agendamento> agendamentos;
    private List<Advogado> advogados; 							// Lista para o select
    private List<Cliente> clientes;   							// Lista para o select

    @PostConstruct
    public void init() {
        novo();
        listar();
        carregarCombos(); 										// Novo método para popular selects
    }

    public void novo() {
        agendamento = new Agendamento();
    }

    public void listar() {
        this.agendamentos = agendamentoDAO.listarTodos();
    }

    private void carregarCombos() {
        this.advogados = advogadoDAO.listarTodos();
        this.clientes = clienteDAO.listarTodos();
    }

    public void salvar() {
        agendamentoDAO.salvar(agendamento);
        adicionarMensagem("Agendamento salvo!");
        novo();
        listar();
    }
    
    public void remover(Agendamento ag) {
        agendamentoDAO.remover(ag.getId());
        adicionarMensagem("Agendamento removido!");
        listar();
    }
    
    public void editar(Agendamento ag) {
        this.agendamento = ag;
    }

    private void adicionarMensagem(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
    }

    // Getters e Setters
    public Agendamento getAgendamento() {
    	return agendamento; 
    }
    
    public void setAgendamento(Agendamento agendamento) {
    	this.agendamento = agendamento; 
    }

    public List<Agendamento> getAgendamentos() {
    	return agendamentos; 
    }
    
    public void setAgendamentos(List<Agendamento> agendamentos) {
    	this.agendamentos = agendamentos; 
    }

    public List<Advogado> getAdvogados() { 
    	return advogados; 
    }
    
    public List<Cliente> getClientes() { 
    	return clientes; 
    }
}
