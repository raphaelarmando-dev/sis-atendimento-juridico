package br.com.sisjur;

import javax.persistence.EntityManager;
import br.com.sisjur.model.Advogado;
import br.com.sisjur.model.Cliente;
import br.com.sisjur.model.Agendamento;
import br.com.sisjur.model.TipoAgendamento;
import br.com.sisjur.util.JPAUtil;
import java.time.LocalDateTime;

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
        adv.setNome("Dr. Armando");
        adv.setOab("654321/RJ");
        adv.setEmail("armando@email.com");
        adv.setTelefone("21988888888");
        adv.setCpf("123.456.789.10");
        
        // Passei o objeto pro Hibernate preparar a gravação.
        em.persist(adv);
        
        // Criei esse cliente de teste seguindo o mesmo padrão do advogado.
        Cliente cli = new Cliente();
        cli.setNome("Raphael");
        cli.setStatusProcessual("Ativo");
        cli.setNumeroProcesso("88888-88.2026.9.00.0000");
        cli.setEmail("raphael@email.com");
        cli.setTelefone("21999999999");
        cli.setCpf("000.000.000-00");
        
        em.persist(cli);
        
        // Criei o agendamento e liguei ao advogado e cliente que fiz logo acima
        Agendamento ag = new Agendamento();
        ag.setDataHora(LocalDateTime.now());
        ag.setTipo(TipoAgendamento.AUDIENCIA);
        
        // Aqui o Hibernate pega o ID do Advogado e o do Cliente pra preencher as colunas estrangeiras
        ag.setAdvogado(adv);
        ag.setCliente(cli);
        
        em.persist(ag);
        
        // Commitei a transação para mandar os dados lá pro Postgres.
        em.getTransaction().commit();
        
        System.out.println("Se chegou ate aqui, as tabelas foram criadas e tudo foi salvo com sucesso!");
        
        // Fechei o em aqui para eu não deixar conexão aberta desnecessariamente no banco.
        em.close();
    }
}
