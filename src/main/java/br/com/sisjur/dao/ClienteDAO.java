package br.com.sisjur.dao;

import br.com.sisjur.model.Cliente;
import br.com.sisjur.util.JPAUtil;
import javax.persistence.EntityManager;
import java.util.List;

                                                      
                                                       
public class ClienteDAO {
	/* Como a lógica é idêntica a do AdvogadoDAO, copiei e só 
     * ajustei as entidades para Cliente porque funciona da mesma maneira */
    public void salvar(Cliente cliente) {                    
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Cliente gerenciado = em.merge(cliente);
            cliente.setId(gerenciado.getId()); 
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
