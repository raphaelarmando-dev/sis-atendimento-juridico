package br.com.sisjur.bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.com.sisjur.model.Cliente;
import br.com.sisjur.dao.ClienteDAO;

@Named
@ViewScoped
public class ClienteBean implements Serializable {		  // Copiei a estrutura do AdvogadoBean, já que a lógica de CRUD é exatamente a mesma

    private static final long serialVersionUID = 1L;

    @Inject
    private ClienteDAO clienteDAO;                                 
    private Cliente cliente;                                       
    private List<Cliente> clientes;                                

    @PostConstruct
    public void init() {
        novo();
        listar();
    }

    public void novo() {
        cliente = new Cliente();
    }

    public void listar() {
        this.clientes = clienteDAO.listarTodos();
    }

    public void salvar() {
        clienteDAO.salvar(cliente);
        adicionarMensagem("Cliente salvo com sucesso!");
        novo();
        listar();
    }
    
    public void remover(Cliente cli) {
        clienteDAO.remover(cli.getId());
        adicionarMensagem("Cliente removido!");
        listar();
    }
    
    public void editar(Cliente cli) {
        this.cliente = cli;
    }

    private void adicionarMensagem(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
    }

    // Getters e Setters
    public Cliente getCliente() { 
    	return cliente; 
    }
    public void setCliente(Cliente cliente) { 
    	this.cliente = cliente; 
    }

    public List<Cliente> getClientes() { 
    	return clientes; 
    }
    public void setClientes(List<Cliente> clientes) { 
    	this.clientes = clientes; 
    }
}
