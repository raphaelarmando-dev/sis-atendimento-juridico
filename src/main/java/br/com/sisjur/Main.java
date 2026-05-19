package br.com.sisjur;

import javax.persistence.EntityManager;
import br.com.sisjur.model.Advogado;
import br.com.sisjur.util.JPAUtil;

public class Main {

    public static void main(String[] args) {
        System.out.println("Tentando conectar com o banco...");
        
        // Chamei o meu gerenciador usando a JPAUtil que criei lá na pasta util.
        EntityManager em = JPAUtil.getEntityManager();
        
        System.out.println("Conectado! Iniciando teste da entidade Advogado...");
        
        // Abri a transação aqui porque o Hibernate me obriga a fazer isso para salvar os dados.
        em.getTransaction().begin();
        
        // Criei esse advogado de teste com dados fictícios só para ver se o banco aceita as regras.
        Advogado adv = new Advogado();
        adv.setNome("Dr. Raphael");
        adv.setOab("123456/RJ");
        adv.setEmail("raphael@email.com");
        adv.setTelefone("21999999999");
        
        // Passei o objeto pro Hibernate preparar a gravação.
        em.persist(adv);
        
        // Commitei a transação para mandar o Dr.Raphael lá pro Postgres.
        em.getTransaction().commit();
        
        System.out.println("Se chegou ate aqui, a tabela foi criada e o advogado foi salvo!");
        
        // Fechei o em aqui para eu não deixar conexão aberta desnecessariamente no banco.
        em.close();
    }
}
