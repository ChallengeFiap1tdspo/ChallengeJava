package usuarios;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Teleconsulta extends Consulta {
    private boolean orientacaoCorreta;
    private int qualidadeVideo;
    private int duracaoMinutos;

    public Teleconsulta(int id, LocalDate data, LocalTime hora, String status,
                        Paciente paciente, Medico medico, boolean orientacaoCorreta, int qualidadeVideo) {
        super(id, data, hora, status, paciente, medico);
        this.orientacaoCorreta = orientacaoCorreta;
        setQualidadeVideo(qualidadeVideo);
        this.duracaoMinutos = 0; // Inicialmente a duração é 0
    }

    public boolean isOrientacaoCorreta() {
        return orientacaoCorreta;
    }

    public void setOrientacaoCorreta(boolean orientacaoCorreta) {
        this.orientacaoCorreta = orientacaoCorreta;
    }

    public int getQualidadeVideo() {
        return qualidadeVideo;
    }

    public void setQualidadeVideo(int qualidadeVideo) {
        if (qualidadeVideo < 0 || qualidadeVideo > 10) {
            System.out.println("Qualidade do vídeo inválida. Ajustando para o valor padrão 5.");
            this.qualidadeVideo = 5;
        } else {
            this.qualidadeVideo = qualidadeVideo;
        }
    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    // Método para finalizar a consulta e calcular a duração
    public void finalizarConsulta(LocalTime horaFim) {
        if (horaFim.isBefore(getHora())) {
            System.err.println("Erro: Hora de término antes da hora de início!");
            return;
        }
        this.duracaoMinutos = (int) ChronoUnit.MINUTES.between(getHora(), horaFim);
    }

    // Método para gerar relatório com todos os dados
    public String gerarRelatorio() {
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("=== Relatório de Teleconsulta ===\n");
        relatorio.append("ID Consulta: ").append(getId()).append("\n");
        relatorio.append("Data: ").append(getData()).append("\n");
        relatorio.append("Hora de Início: ").append(getHora()).append("\n");
        relatorio.append("Status: ").append(getStatus()).append("\n");
        relatorio.append("Paciente: ").append(getPaciente().getNome()).append(" (Idade: ")
                .append(getPaciente().getIdade()).append(")\n");
        relatorio.append("Médico: ").append(getMedico().getNome()).append(" - Especialidade: ")
                .append(getMedico().getEspecialidade()).append("\n");
        relatorio.append("Orientação Correta: ").append(orientacaoCorreta ? "Sim" : "Não").append("\n");
        relatorio.append("Qualidade do Vídeo: ").append(qualidadeVideo).append("/10\n");
        relatorio.append("Duração: ").append(duracaoMinutos).append(" minutos\n");
        relatorio.append("===============================");
        return relatorio.toString();
    }

    @Override
    public String toString() {
        return "Teleconsulta [" + super.toString() +
                ", orientacaoCorreta=" + orientacaoCorreta +
                ", qualidadeVideo=" + qualidadeVideo +
                ", duracaoMinutos=" + duracaoMinutos + "]";
    }
}
