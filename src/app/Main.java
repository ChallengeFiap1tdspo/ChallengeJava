package app;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

import dao.MedicoDAO;
import dao.PacienteDAO;
import dao.TeleconsultaDAO;
import servicos.AgendaFacil;
import servicos.FalaConsulta;
import servicos.FocoCerto;
import servicos.ModuloTelemedicina;
import servicos.RedeCuidar;
import servicos.AjudaGeral;
import usuarios.Hospital;
import usuarios.Instituto;
import usuarios.Medico;
import usuarios.Notificacao;
import usuarios.Paciente;
import usuarios.Teleconsulta;

public class Main {
    public static void main(String[] args) {

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        Scanner scanner = new Scanner(System.in);

        // Inicializa os DAOs
        PacienteDAO pacienteDAO = new PacienteDAO();
        MedicoDAO medicoDAO = new MedicoDAO();
        TeleconsultaDAO teleconsultaDAO = new TeleconsultaDAO();

        System.out.println("Bem-vindo ao Sistema de Teleatendimento do AgendaFácil!");
        System.out.println("======================================================");

        // --- Cadastro de Paciente ---
        String nomeCompleto;
        do {
            System.out.print("Digite seu nome completo: ");
            nomeCompleto = scanner.nextLine();
            if (nomeCompleto.trim().length() < 3) {
                System.out.println("Nome muito curto. Tente novamente.");
            }
        } while (nomeCompleto.trim().length() < 3);

        System.out.print("Digite sua idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite seu email: ");
        String email = scanner.nextLine();

        System.out.print("Digite seu endereço: ");
        String endereco = scanner.nextLine();

        String telefone;
        do {
            System.out.print("Digite seu número de telefone (11 dígitos, só números): ");
            telefone = scanner.nextLine().replaceAll("\\D", "");
            if (telefone.length() != 11) {
                System.out.println("Telefone inválido. Tente novamente.");
            }
        } while (telefone.length() != 11);

        System.out.print("Você possui alguma deficiência? (Sim/Não): ");
        String possuiDeficiencia = scanner.nextLine();
        String deficiencia = "Nenhuma";

        if (possuiDeficiencia.equalsIgnoreCase("Sim")) {
            System.out.println("Informe sua deficiência:");
            System.out.println("  (1) Cadeirante");
            System.out.println("  (2) Visual");
            System.out.println("  (3) Auditiva");
            System.out.println("  (4) Outras");
            System.out.print("Digite a opção desejada: ");
            int opcaoDef = scanner.nextInt();
            scanner.nextLine();
            switch (opcaoDef) {
                case 1 -> {
                    deficiencia = "Visual";
                    System.out.println("\n🔊 Modo de acessibilidade ativado! Suporte de voz habilitado.");
                }
                case 2 -> {
                    deficiencia = "Auditiva";
                    System.out.println("\n💬 Notificações visuais ajustadas para facilitar seu atendimento.");
                }
                case 3 -> {
                    System.out.print("Digite sua deficiência: ");
                    deficiencia = scanner.nextLine();
                    System.out.println("\n🔎 Recursos personalizados ativados.");
                }
                default -> {
                    deficiencia = "Outras";
                    System.out.println("\n⚙️ Atendimento adaptado conforme necessidades.");
                }
            }
        } else {
            System.out.println("\n✔️ Atendimento padrão ativado.");
        }

        // Cria e salva o paciente
        Paciente paciente = new Paciente(1, nomeCompleto, idade, deficiencia, telefone, email, endereco);
        pacienteDAO.inserir(paciente);
        exibirMensagemPaciente(paciente);

        // Escolha do módulo
        System.out.println("\nOlá " + paciente.getNome() + ", escolha a funcionalidade:");
        System.out.println(" 1 - AgendaFácil");
        System.out.println(" 2 - FalaConsulta");
        System.out.println(" 3 - FocoCerto");
        System.out.println(" 4 - RedeCuidar");
        System.out.println(" 5 - Ajuda e Suporte");
        System.out.print("Digite sua opção (1-5): ");
        int opcaoModulo = scanner.nextInt();
        scanner.nextLine();

        ModuloTelemedicina modulo = switch (opcaoModulo) {
            case 1 -> new AgendaFacil();
            case 2 -> new FalaConsulta();
            case 3 -> new FocoCerto();
            case 4 -> new RedeCuidar();
            case 5 -> new AjudaGeral();
            default -> {
                System.out.println("Opção inválida. Encerrando sistema.");
                System.exit(0);
                yield null;
            }
        };

        System.out.println("\nIniciando módulo: " + modulo.getNome());
        modulo.executar(scanner);

        // Caso seja teleconsulta
        if(opcaoModulo != 1 && opcaoModulo != 5) {
            System.out.println("\nCaio: Gerando os dados da sua teleconsulta...");

            // Cria e salva médico
            Medico medico = new Medico(101, "Dr. Roberto", "12345678", "CRM12345", "Cardiologia", true);
            medicoDAO.inserir(medico);

            // Cria e salva teleconsulta
            Teleconsulta teleconsulta = new Teleconsulta(
                    301,
                    LocalDate.now(),
                    LocalTime.now(),
                    "Agendada",
                    paciente,
                    medico,
                    false,
                    3
            );
            teleconsultaDAO.inserir(teleconsulta);

            System.out.println("\n------ Relatório da Teleconsulta ------");
            System.out.println("ID       : " + teleconsulta.getId());
            System.out.println("Data     : " + teleconsulta.getData().format(dateFormatter));
            System.out.println("Hora     : " + teleconsulta.getHora().format(timeFormatter));
            System.out.println("Status   : " + teleconsulta.getStatus());
            System.out.println("Paciente : " + paciente.getNome());
            System.out.println("Médico   : " + medico.getNome());
            System.out.println("Orientação correta? " + (teleconsulta.isOrientacaoCorreta() ? "Sim" : "Não"));
            System.out.println("Qualidade do vídeo: " + teleconsulta.getQualidadeVideo() + " / 10");
            System.out.println("---------------------------------------");

            Notificacao notificacao = new Notificacao(
                    201,
                    "Confirmamos sua teleconsulta com o Médico responsável.",
                    LocalDateTime.now(),
                    paciente
            );
            notificacao.enviarNotificacao();
        } else if(opcaoModulo == 1) {
            System.out.println("\nAgenda confirmada! O aplicativo lembrará você automaticamente.");
        }

        // Dados do hospital
        Instituto instituto1 = new Instituto("InCor", "Cardiologia");
        Instituto instituto2 = new Instituto("NeuroCenter", "Neurologia");
        Hospital hospital = new Hospital("Hospital das Clínicas - USP", 2400,
                Arrays.asList(instituto1, instituto2));

        System.out.println("\nDados do Hospital:");
        System.out.println(hospital);

        System.out.println("\nObrigado por utilizar nosso sistema! Cuide-se bem.");
        scanner.close();
    }

    public static void exibirMensagemPaciente(Paciente paciente) {
        if (paciente.getIdade() >= 65)
            System.out.println("Olá, " + paciente.getNome() + ". Recursos de acessibilidade ativados.");
        else
            System.out.println("Seja bem-vindo ao nosso sistema!");
    }
}
