package app;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import dao.PacienteDAO;
import dao.MedicoDAO;
import dao.TeleconsultaDAO;
import servicos.FalaConsulta;
import usuarios.Paciente;
import usuarios.Medico;
import usuarios.Teleconsulta;

public class TesteSistema {
    public static void main(String[] args) {
        // Inicializa DAOs
        PacienteDAO pacienteDAO = new PacienteDAO();
        MedicoDAO medicoDAO = new MedicoDAO();
        TeleconsultaDAO teleconsultaDAO = new TeleconsultaDAO();

        System.out.println("=== TESTE DE CRUD E LÓGICA DE NEGÓCIO ===\n");

        // --- Teste Paciente ---
        Paciente paciente1 = new Paciente(1, "João Silva", 70, "Nenhuma", "11999999999", "joao@email.com", "Rua A, 123");
        Paciente paciente2 = new Paciente(2, "Maria Souza", 30, "Auditiva", "11988888888", "maria@email.com", "Rua B, 456");

        pacienteDAO.inserir(paciente1);
        pacienteDAO.inserir(paciente2);

        System.out.println("Pacientes cadastrados:");
        for(Paciente p : pacienteDAO.listar()) {
            System.out.println(p.resumoPaciente() + " | Idoso? " + p.isIdoso());
        }

        // --- Teste Médico ---
        Medico medico1 = new Medico(101, "Dr. Roberto", "12345678", "CRM12345", "Cardiologia", true);
        Medico medico2 = new Medico(102, "Dra. Ana", "87654321", "CRM54321", "Neurologia", false);

        medicoDAO.inserir(medico1);
        medicoDAO.inserir(medico2);

        System.out.println("\nMédicos cadastrados:");
        for(Medico m : medicoDAO.listar()) {
            System.out.println(m.getNome() + " | Disponível? " + m.estaDisponivel());
        }

        // Atualizando disponibilidade
        medico2.atualizarDisponibilidade(true);
        System.out.println("\nApós atualização de disponibilidade:");
        System.out.println(medico2.getNome() + " | Disponível? " + medico2.estaDisponivel());

        // --- Teste Teleconsulta ---
        Teleconsulta consulta1 = new Teleconsulta(201, LocalDate.now(), LocalTime.of(14, 30), "Agendada", paciente1, medico1, false, 3);
        teleconsultaDAO.inserir(consulta1);

        System.out.println("\nTeleconsultas cadastradas:");
        for(Teleconsulta t : teleconsultaDAO.listar()) {
            System.out.println(t.gerarRelatorio());
        }

        // Finalizar consulta e gerar relatório atualizado
        consulta1.finalizarConsulta(LocalTime.of(15, 0));
        System.out.println("\nRelatório após finalizar consulta:");
        System.out.println(consulta1.gerarRelatorio());

        // --- Teste Simulação de comando de voz ---
        FalaConsulta fala = new FalaConsulta();
        System.out.println("\nTeste de comando de voz:");
        fala.simularComandoVoz("iniciar consulta");
        fala.simularComandoVoz("encerrar consulta");

        // --- Teste Buscar e Deletar ---
        System.out.println("\nDeletando paciente Maria Souza...");
        pacienteDAO.deletar(2);

        System.out.println("Pacientes restantes:");
        for(Paciente p : pacienteDAO.listar()) {
            System.out.println(p.resumoPaciente());
        }

        System.out.println("\n=== FIM DOS TESTES ===");
    }
}
