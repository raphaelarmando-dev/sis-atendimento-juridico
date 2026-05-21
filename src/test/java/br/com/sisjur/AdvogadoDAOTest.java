package br.com.sisjur;

import br.com.sisjur.dao.AdvogadoDAO;
import br.com.sisjur.model.Advogado;

public class AdvogadoDAOTest {

    public static void main(String[] args) {
        AdvogadoDAO dao = new AdvogadoDAO(); // Instancio o DAO para acesso aos dados

        Advogado adv = new Advogado(); // Crio o objeto que será persistido
        adv.setNome("RaphaelDAO");
        adv.setOab("123456/RJ");
        adv.setCpf("109.876.543-21");
        adv.setEmail("raphaeldao@email.com");

        try {
            dao.salvar(adv); // Persisto o objeto no banco
            System.out.println("Sucesso: Advogado salvo com ID: " + adv.getId());
            
            Advogado encontrado = dao.buscarPorId(adv.getId()); // Valido se a persistência ocorreu
            
            if (encontrado != null) { // Verifico se o registro foi recuperado com sucesso
                System.out.println("Sucesso: Registro encontrado no banco: " + encontrado.getNome());
                System.out.println("OAB confirmada: " + encontrado.getOab());
            } else {
                System.err.println("Erro: Não foi possível encontrar o registro no banco."); // Registro não localizado
            }
            
        } catch (Exception e) {
            System.err.println("Erro durante o teste de integração:"); e.printStackTrace(); // Capturo e reporto erros inesperados
        }
    }
}
