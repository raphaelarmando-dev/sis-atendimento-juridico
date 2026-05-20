package br.com.sisjur;

import javax.persistence.EntityManager;
import br.com.sisjur.model.Advogado;
import br.com.sisjur.model.Cliente;
import br.com.sisjur.util.JPAUtil;

public class Main {

    public static void main(String[] args) {
        System.out.println("Tentando conectar com o banco...");
        
        // Chamei o meu gerenciador usando a JPAUtil que criei lá na pasta util.
        EntityManager em = JPAUtil.getEntityManager();
        
        System.out.println("Conectado! Iniciando teste das entidades...");
        
        // Abri a transação aqui porque o Hibernate me obriga a fazer isso para salvar os dados.
        em.getTransaction().begin();
        
        // Criei esse advogado de teste com dados fictícios só para ver se o banco aceita as regras.
        Advogado adv = new Advogado();
        adv.setNome("Dr. Raphael");
        adv.setOab("123456/RJ");
        adv.setEmail("raphael@email.com");
        adv.setTelefone("21999999999");
        adv.setCpf("000.000.000-00");
        
        // Passei o objeto pro Hibernate preparar a gravação.
        em.persist(adv);
        
        // Criei esse cliente de teste seguindo o mesmo padrão do advogado.
        Cliente cli = new Cliente();
        cli.setNome("Armando");
        cli.setStatusProcessual("Ativo");
        cli.setNumeroProcesso("99999-99.2026.8.00.0000");
        cli.setEmail("armando@email.com");
        cli.setTelefone("21988888888");
        cli.setCpf("123.456.789-10");
        
        em.persist(cli);
        
        // Commitei a transação para mandar os dados lá pro Postgres.
        em.getTransaction().commit();
        
        System.out.println("Se chegou ate aqui, as tabelas foram criadas e ambos foram salvos!");
        
        // Fechei o em aqui para eu não deixar conexão aberta desnecessariamente no banco.
        em.close();
    }
}
