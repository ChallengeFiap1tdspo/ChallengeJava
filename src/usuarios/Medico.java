package usuarios;

public class Medico extends Pessoa {
    private String crm;
    private String especialidade;
    private boolean disponivel;

    public Medico(int id, String nome, String contato, String crm, String especialidade, boolean disponivel) {
        super(id, nome, contato);
        this.crm = crm;
        this.especialidade = especialidade;
        this.disponivel = disponivel;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    // Método para verificar se o médico está disponível
    public boolean estaDisponivel() {
        return disponivel;
    }

    // Método para atualizar a disponibilidade do médico
    public void atualizarDisponibilidade(boolean status) {
        this.disponivel = status;
    }

    @Override
    public String toString() {
        return "Medico [" + super.toString() +
                ", crm=" + crm +
                ", especialidade=" + especialidade +
                ", disponivel=" + (disponivel ? "Sim" : "Não") + "]";
    }
}
