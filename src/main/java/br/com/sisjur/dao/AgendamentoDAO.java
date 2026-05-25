package br.com.sisjur.dao;

import br.com.sisjur.model.Agendamento;
import br.com.sisjur.util.JPAUtil;
import javax.persistence.EntityManager;
import java.util.List;
import java.io.Serializable;

public class AgendamentoDAO implements Serializable {       // Segui o mesmo padrao de AdvogadoDAO e ClienteDAO, porem com ajustes para lidar com relacionamento das duas
	
	private static final long serialVersionUID = 1L;												        
    
	public void salvar(Agendamento agendamento) {
        EntityManager em = JPAUtil.getEntityManager();      // Pego a conexão da minha fábrica
        try {
            em.getTransaction().begin();                    // Abro a transação para escrever
            Agendamento gerenciado = em.merge(agendamento); // Merge une agendamento aos objetos Advogado/Cliente existentes
            agendamento.setId(gerenciado.getId());          // Atualiza o objeto original com o ID gerado pelo banco
            em.getTransaction().commit();              	    // Confirmo a gravação no banco
        } catch (Exception e) {
            em.getTransaction().rollback();            		// Se falhar, desfaço a transação
            throw e;                                   		// Relanço o erro pra debugar
        } finally {
            em.close();                                		// Garanto o fechamento para liberar recurso
        }
    }
	
	public void atualizar(Agendamento agendamento) {
		EntityManager em = JPAUtil.getEntityManager();
	    try {
	        em.getTransaction().begin();
	        em.merge(agendamento); 							// merge só para entidades existentes com ID
	        em.getTransaction().commit();
	    } catch (Exception e) {
	        em.getTransaction().rollback();
	        throw e;
	    } finally {
	        em.close();
	    }
	}

    public Agendamento buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
        	return em.createQuery(
                    "SELECT a FROM Agendamento a " +
                    "JOIN FETCH a.advogado " +
                    "JOIN FETCH a.cliente " +
                    "WHERE a.id = :id", Agendamento.class)
                    .setParameter("id", id)
                    .getSingleResult();
            } finally {
                em.close();
            }
    }

    public List<Agendamento> buscarPorCliente(Long clienteId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT a FROM Agendamento a WHERE a.cliente.id = :clienteId", Agendamento.class) // JPQL filtrando pela chave estrangeira de cliente  
                     .setParameter("clienteId", clienteId)
                     .getResultList(); 
        } finally {
            em.close();
        }
    }

    public List<Agendamento> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
        	return em.createQuery(
                    "SELECT a FROM Agendamento a " +
                    "JOIN FETCH a.advogado " +
                    "JOIN FETCH a.cliente", Agendamento.class)
                    .getResultList();
            } finally {
                em.close();
            }
    }

    public void remover(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();                              // Precisa de transação pra remover
            Agendamento agendamento = em.find(Agendamento.class, id); // Busco antes pra garantir que existe
            if (agendamento != null) em.remove(agendamento);          // Se achou, mando remover
            em.getTransaction().commit();                             // Confirmo a remoção
        } catch (Exception e) {
            em.getTransaction().rollback();                           // Rollback se algo der erro na exclusão
            throw e;
        } finally {
            em.close();
        }
    }
}
