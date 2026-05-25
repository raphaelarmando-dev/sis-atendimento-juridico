package br.com.sisjur.dao;

import br.com.sisjur.model.Cliente;
import br.com.sisjur.util.JPAUtil;
import javax.persistence.EntityManager;
import java.util.List;
import java.io.Serializable;
                                                      
                                                       
public class ClienteDAO implements Serializable{
	/* Como a lógica é idêntica a do AdvogadoDAO, copiei e só 
     * ajustei as entidades para Cliente porque funciona da mesma maneira */
	
	private static final long serialVersionUID = 1L;
	
	public void salvar(Cliente cliente) {
	    EntityManager em = JPAUtil.getEntityManager();     
	    try {
	        em.getTransaction().begin();                   
	        em.persist(cliente);                           
	        em.getTransaction().commit();                  
	    } catch (Exception e) {
	        em.getTransaction().rollback();                
	        throw e;                                       
	    } finally {
	        em.close();                                    
	    }
	}

	public void atualizar(Cliente cliente) {
	    EntityManager em = JPAUtil.getEntityManager();     
	    try {
	        em.getTransaction().begin();                   
	        em.merge(cliente);                             
	        em.getTransaction().commit();               
	    } catch (Exception e) {
	        em.getTransaction().rollback();    
	        throw e;                                    
	    } finally {
	        em.close();                         
	    }
	}

    public Cliente buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public List<Cliente> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void remover(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Cliente cliente = em.find(Cliente.class, id);
            if (cliente != null) em.remove(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
