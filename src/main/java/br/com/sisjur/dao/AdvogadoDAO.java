package br.com.sisjur.dao;

import br.com.sisjur.model.Advogado;
import br.com.sisjur.util.JPAUtil;
import javax.persistence.EntityManager;
import java.util.List;
import java.io.Serializable;

public class AdvogadoDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
    public void salvar(Advogado advogado) {
        EntityManager em = JPAUtil.getEntityManager(); // Pego a conexão da minha fábrica
        try {
            em.getTransaction().begin();               // Abro a transação para escrever
            Advogado gerenciado = em.merge(advogado);  // Merge: se não tem ID, cria; se tem, atualiza
            advogado.setId(gerenciado.getId());        // Atualiza o objeto original com o ID gerado pelo banco                                
            em.getTransaction().commit();              // Confirmo a gravação no banco
        } catch (Exception e) {
            em.getTransaction().rollback();            // Se falhar, desfaço a transação
            throw e;                                   // Relanço o erro pra debugar
        } finally {
            em.close();                                // Garanto o fechamento para liberar recurso
        }
    }

    public Advogado buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Advogado.class, id);        // Busca direta por ID
        } finally {
            em.close();                                // Fecho a conexão de leitura
        }
    }

    public List<Advogado> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT a FROM Advogado a", Advogado.class).getResultList(); // Busca com JPQL
        } finally {
            em.close();
        }
    }

    public void remover(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();                // Precisa de transação pra remover
            Advogado adv = em.find(Advogado.class, id); // Busco antes pra garantir que existe
            if (adv != null) em.remove(adv);            // Se achou, mando remover
            em.getTransaction().commit();               // Confirmo a remoção
        } catch (Exception e) {
            em.getTransaction().rollback();             // Rollback se algo der erro na exclusão
            throw e;
        } finally {
            em.close();                                 // Conexão sempre fechada
        }
    }
}
