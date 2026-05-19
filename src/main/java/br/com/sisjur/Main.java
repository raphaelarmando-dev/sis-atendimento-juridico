package br.com.sisjur;

import javax.persistence.EntityManager;
import br.com.sisjur.util.JPAUtil;

public class Main {

    public static void main(String[] args) {
        System.out.println("Tentando conectar com o banco...");
        
        // Só chamei o gerenciador para o Hibernate ler o meu XML e criar as tabelas.
        EntityManager em = JPAUtil.getEntityManager();
        
        System.out.println("Se chegou ate aqui, as tabelas foram criadas no Postgres!");
        
        // Fechei aqui para eu nao deixar conexoes abertas sem necessidade.
        em.close();
    }
}
