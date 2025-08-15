package usuarios;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Notificacao {
    private int id;
    private String mensagem;
    private LocalDateTime dataEnvio;
    private Paciente destinatario;

    // Lista estática para armazenar todas as notificações enviadas
    private static List<Notificacao> notificacoesEnviadas = new ArrayList<>();

    public Notificacao(int id, String mensagem, LocalDateTime dataEnvio, Paciente destinatario) {
        this.id = id;
        this.mensagem = mensagem;
        this.dataEnvio = dataEnvio;
        this.destinatario = destinatario;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getMensagem() {
        return mensagem;
    }
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }
    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }
    public Paciente getDestinatario() {
        return destinatario;
    }
    public void setDestinatario(Paciente destinatario) {
        this.destinatario = destinatario;
    }

    public void enviarNotificacao() {
        System.out.println("Enviando notificação para " + destinatario.getNome() + ": " + mensagem);
        enviarSMS();
        // Armazena na lista de notificações enviadas
        notificacoesEnviadas.add(this);
    }

    public void enviarSMS() {
        String tel = destinatario.getTelefone();
        if (tel == null || tel.isEmpty()) {
            System.out.println("Número de telefone não informado. SMS não enviado.");
        } else {
            System.out.println("SMS enviado para " + tel + " com a mensagem: " + mensagem);
        }
    }

    // Método para listar todas as notificações enviadas
    public static void listarNotificacoesEnviadas() {
        if (notificacoesEnviadas.isEmpty()) {
            System.out.println("Nenhuma notificação enviada.");
        } else {
            System.out.println("=== Lista de Notificações Enviadas ===");
            for (Notificacao n : notificacoesEnviadas) {
                System.out.println(n);
            }
        }
    }

    @Override
    public String toString() {
        return "Notificacao [id=" + id + ", mensagem=" + mensagem
                + ", dataEnvio=" + dataEnvio + ", destinatario=" + destinatario.getNome() + "]";
    }
}
