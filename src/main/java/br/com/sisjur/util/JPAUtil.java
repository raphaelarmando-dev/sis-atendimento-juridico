package br.com.sisjur.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

    // Criei estático para eu não ficar relendo o XML a cada nova conexão.
    private static final EntityManagerFactory FACTORY = 
            Persistence.createEntityManagerFactory("sisjurPU");

    // Fiz esse método para eu puxar o EntityManager direto nos DAOs de forma simples.
    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }
}
