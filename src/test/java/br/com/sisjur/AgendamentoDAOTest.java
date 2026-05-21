package br.com.sisjur;

import br.com.sisjur.dao.AgendamentoDAO;
import br.com.sisjur.dao.AdvogadoDAO;
import br.com.sisjur.dao.ClienteDAO;
import br.com.sisjur.model.Agendamento;
import br.com.sisjur.model.Advogado;
import br.com.sisjur.model.Cliente;
import br.com.sisjur.model.TipoAgendamento;

import java.time.LocalDateTime;

public class AgendamentoDAOTest {

    public static void main(String[] args) {
        AgendamentoDAO agDAO = new AgendamentoDAO();				// Instanciei os DAOs para compor o agendamento
        AdvogadoDAO advDAO = new AdvogadoDAO();
        ClienteDAO cliDAO = new ClienteDAO();

        try {
            Advogado adv = advDAO.buscarPorId(1L); 					// Busquei um advogado e um cliente que já existam
        	if (adv == null) {
                adv = new Advogado();
                adv.setNome("Advogado Padrao");
                adv.setOab("123456/RJ");
                adv.setCpf("111.111.111-11");
                adv.setEmail("adv@teste.com");
                advDAO.salvar(adv);
            }
            Cliente cli = cliDAO.buscarPorId(1L);					// Se o banco estiver vazio, cria instâncias e salva via advDAO/cliDAO primeiro
            if (cli == null) {
                cli = new Cliente();
                cli.setNome("Cliente Padrao");
                cli.setCpf("222.222.222-22");
                cli.setEmail("cli@teste.com");
                cliDAO.salvar(cli);
            }
            if (adv == null || cli == null) {
                System.err.println("Erro: Preciso de um Advogado e um Cliente no banco para testar!");
                return;
            }

            Agendamento ag = new Agendamento(); 					// Criei o objeto Agendamento
            ag.setAdvogado(adv);
            ag.setCliente(cli);
            ag.setDataHora(LocalDateTime.now());
            ag.setTipo(TipoAgendamento.TRIAGEM);

            agDAO.salvar(ag);                                     	// Salvei o agendamento
            System.out.println("Sucesso: Agendamento salvo com ID: " + ag.getId());

            Agendamento encontrado = agDAO.buscarPorId(ag.getId()); // Confirmei se salvou
            if (encontrado != null) {
                System.out.println("Sucesso: Agendamento encontrado no banco!");
                System.out.println("Advogado vinculado: " + encontrado.getAdvogado().getNome());
                System.out.println("Cliente vinculado: " + encontrado.getCliente().getNome());
            } else {
                System.err.println("Erro: Não foi possível encontrar o registro no banco.");
            }
        } catch (Exception e) {
            System.err.println("Erro durante o teste de integração:");
            e.printStackTrace();
        }
    }
}
