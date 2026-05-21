package br.com.sisjur;

import br.com.sisjur.dao.ClienteDAO;
import br.com.sisjur.model.Cliente;

public class ClienteDAOTest {
	/* Como fiz com ClienteDAO, usei como base a estrutura AdvogadoDAOTest, pois a lógica 
     * é a mesma, so que para teste, tendo salvar e buscar para validar o DAO. */
    public static void main(String[] args) {
        ClienteDAO dao = new ClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setNome("ArmandoDAO");
        cliente.setCpf("222.333.444-55");
        cliente.setEmail("armandodao@email.com");
        cliente.setTelefone("21999999999");

        try {
            dao.salvar(cliente);
            System.out.println("Sucesso: Cliente salvo com ID: " + cliente.getId());
            
            Cliente encontrado = dao.buscarPorId(cliente.getId());
            System.out.println("Sucesso: Cliente encontrado: " + encontrado.getNome());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
