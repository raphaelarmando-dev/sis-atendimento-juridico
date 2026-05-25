package br.com.sisjur.bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.com.sisjur.dao.AdvogadoDAO;
import br.com.sisjur.model.Advogado;

@Named															   // Deixa o bean visível pro xhtml
@ViewScoped 													   // Mantém o bean vivo só nessa tela
public class AdvogadoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject														   // Faço a injeção de dependência do DAO
    private AdvogadoDAO advogadoDAO;                               
    private Advogado advogado;                                     // Objeto que vai ficar no form da tela
    private List<Advogado> advogados;                              // Lista que vai popular nossa tabela

    @PostConstruct												   // Roda assim que o bean nasce
    public void init() {                                           
        novo();                                                    // Zera o form
        listar();                                                  // Refresh na lista
    }

    public void novo() {                                           // Reseta o objeto pra um novo cadastro
        advogado = new Advogado();
    }

    public void listar() {                                         // Busca todos no banco
        this.advogados = advogadoDAO.listarTodos();
    }

    public void salvar() {                                         // Salva o registro (consertei a logica)
    	if (advogado.getId() == null) {
            advogadoDAO.salvar(advogado);						   // ID nulo = novo, uso persist
            adicionarMensagem("Advogado cadastrado com sucesso!");
        } else {
            advogadoDAO.atualizar(advogado);					   // ID preenchido = atualizo via merge
            adicionarMensagem("Advogado atualizado com sucesso!");
        }
        novo();													   // limpa o form
        listar();												   // refresh
    }
    
    public void atualizar(Advogado advogado) {                     // atualiza o registro (consertei a logica)
    	this.advogado = advogadoDAO.buscarPorId(advogado.getId()); // busco do banco pra garantir objeto completo
    }
    
    public void remover(Advogado advogado) {
        advogadoDAO.remover(advogado.getId());                     // Removo advogado da lista e do database
        adicionarMensagem("Advogado removido!");				   // Avisa para o usuario
        listar();												   // Refresh na lista
    }
    
    public void editar(Advogado advogado) {                        // Prepara o objeto pra edição
        this.advogado = advogado;
    }

    private void adicionarMensagem(String msg) {                   // Helper pra não repetir código
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
    }

    // Getters e Setters
    public Advogado getAdvogado() { 
    	return advogado; 
    }
    
    public void setAdvogado(Advogado advogado) { 
    	this.advogado = advogado; 
    }

    public List<Advogado> getAdvogados() {
    	return advogados; 
    }
    
    public void setAdvogados(List<Advogado> advogados) {
    	this.advogados = advogados; 
    }
}