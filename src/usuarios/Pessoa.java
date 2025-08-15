package usuarios;

public abstract class Pessoa {
    protected int id;
    protected String nome;
    protected String contato;

    public Pessoa(int id, String nome, String contato) {
        if (nome == null || nome.trim().isEmpty()) {
            System.err.println("Erro: Nome não pode ser vazio. Encerrando o programa.");
            System.exit(1);
        }
        this.id = id;
        this.nome = nome;
        this.contato = contato;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            System.err.println("Erro: Nome não pode ser vazio. Encerrando o programa.");
            System.exit(1);
        }
        this.nome = nome;
    }
    public String getContato() {
        return contato;
    }
    public void setContato(String contato) {
        this.contato = contato;
    }

    @Override
    public String toString() {
        return "Pessoa [id=" + id + ", nome=" + nome + ", contato=" + contato + "]";
    }
}
