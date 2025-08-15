package usuarios;

public class Paciente extends Pessoa {
    private int idade;
    private String tipoDeficiencia;
    private String telefone;
    private String email;
    private String endereco;

    // Construtor completo
    public Paciente(int id, String nome, int idade, String tipoDeficiencia, String telefone, String email, String endereco) {
        super(id, nome, telefone);
        this.tipoDeficiencia = tipoDeficiencia;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;

        if (idade <= 0 || idade > 110) {
            System.err.println("Erro: Idade inválida!");
            System.exit(1);
        }

        this.idade = idade;

        if (idade >= 65) {
            System.out.println("Paciente com mais de 65 anos. Recursos de acessibilidade especiais serão ativados.");
        }
    }

    // Construtor sobrecarregado para compatibilidade com código antigo
    public Paciente(int id, String nome, int idade, String tipoDeficiencia, String telefone) {
        this(id, nome, idade, tipoDeficiencia, telefone, "", "");
    }

    // Getters e Setters
    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        if (idade <= 0 || idade > 110) {
            System.err.println("Erro: Idade inválida! A idade deve ser maior que 0 e menor ou igual a 110.");
            System.exit(1);
        }
        this.idade = idade;
        if (idade >= 65) {
            System.out.println("Paciente com mais de 65 anos. Recursos de acessibilidade especiais serão ativados.");
        }
    }

    public String getTipoDeficiencia() {
        return tipoDeficiencia;
    }

    public void setTipoDeficiencia(String tipoDeficiencia) {
        this.tipoDeficiencia = tipoDeficiencia;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    // Método para verificar se o paciente é idoso
    public boolean isIdoso() {
        return idade >= 65;
    }

    // Método para retornar resumo do paciente
    public String resumoPaciente() {
        return "Nome: " + getNome() + ", Idade: " + idade + ", Contato: " + telefone;
    }

    @Override
    public String toString() {
        return "Paciente [nome=" + getNome() + ", idade=" + idade +
                ", tipoDeficiencia=" + tipoDeficiencia +
                ", telefone=" + telefone +
                ", email=" + email +
                ", endereco=" + endereco + "]";
    }
}
