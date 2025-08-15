package servicos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import usuarios.Consulta;
import usuarios.Paciente;
import usuarios.Medico;

public class AgendaFacil extends ModuloTelemedicina {

    // Lista estática para armazenar todas as consultas
    private static List<Consulta> consultasAgendadas = new ArrayList<>();

    public AgendaFacil() {
        super("AgendaFácil", "Agendamento e lembretes inteligentes");
    }

    @Override
    public void executar(Scanner scanner) {
        System.out.println("----- Executando AgendaFácil -----");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        LocalDate consultaDate = null;
        while (consultaDate == null) {
            System.out.print("Digite a data da consulta (DD/MM/YYYY): ");
            String dataStr = scanner.nextLine().trim();
            try {
                consultaDate = LocalDate.parse(dataStr, dateFormatter);

                if (consultaDate.getYear() < 2025) {
                    System.out.println("Por favor, informe uma data a partir de 2025.");
                    consultaDate = null;
                }
            } catch (Exception e) {
                System.out.println("Data inválida! Certifique-se que o dia (01-31), mês (01-12) e o ano estejam corretos.");
            }
        }

        LocalTime consultaTime = null;
        while (consultaTime == null) {
            System.out.print("Digite o horário da consulta (HH:mm): ");
            String horaStr = scanner.nextLine().trim();
            try {
                consultaTime = LocalTime.parse(horaStr, timeFormatter);

                LocalTime inicio = LocalTime.of(8, 0);
                LocalTime fim = LocalTime.of(17, 0);
                if (consultaTime.isBefore(inicio) || consultaTime.isAfter(fim)) {
                    System.out.println("Horário inválido! Por favor, insira um horário entre 08:00 e 17:00.");
                    consultaTime = null; // reinicia a leitura
                }
            } catch (Exception e) {
                System.out.println("Horário inválido! Use o formato HH:mm (exemplo: 14:30).");
            }
        }

        // Aqui você criaria a consulta com dados do paciente e médico (exemplo fictício)
        Paciente paciente = new Paciente(1, "João Silva", 66, "Nenhuma", "11999999999", "joao@email.com", "Rua A");
        Medico medico = new Medico(1, "Dra. Maria", "11988888888", "CRM12345", "Clínico Geral", true);

        Consulta novaConsulta = new Consulta(consultasAgendadas.size() + 1, consultaDate, consultaTime, "Agendada", paciente, medico);
        consultasAgendadas.add(novaConsulta);

        System.out.println("Consulta agendada para " + consultaDate.format(dateFormatter)
                + " às " + consultaTime.format(timeFormatter) + ".");
        System.out.println("O aplicativo lembrará você automaticamente na data agendada.");
        System.out.println("----------------------------------");
    }

    // Método para listar todas as consultas de um paciente
    public static void listarConsultasPaciente(Paciente paciente) {
        System.out.println("=== Consultas do paciente: " + paciente.getNome() + " ===");
        boolean encontrou = false;
        for (Consulta c : consultasAgendadas) {
            if (c.getPaciente().equals(paciente)) {
                System.out.println(c);
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhuma consulta encontrada para este paciente.");
        }
    }

    // Método para cancelar uma consulta pelo ID
    public static void cancelarConsulta(int idConsulta) {
        Iterator<Consulta> iterator = consultasAgendadas.iterator();
        boolean removida = false;
        while (iterator.hasNext()) {
            Consulta c = iterator.next();
            if (c.getId() == idConsulta) {
                iterator.remove();
                System.out.println("Consulta ID " + idConsulta + " foi cancelada com sucesso.");
                removida = true;
                break;
            }
        }
        if (!removida) {
            System.out.println("Consulta com ID " + idConsulta + " não encontrada.");
        }
    }
}
