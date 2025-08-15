package dao;

import java.util.ArrayList;
import java.util.List;
import usuarios.Teleconsulta;

public class TeleconsultaDAO {
    private final List<Teleconsulta> teleconsultas = new ArrayList<>();

    public void inserir(Teleconsulta teleconsulta) {
        teleconsultas.add(teleconsulta);
        System.out.println("Teleconsulta inserida com sucesso!");
    }

    public List<Teleconsulta> listar() {
        return new ArrayList<>(teleconsultas);
    }

    public Teleconsulta buscarPorId(int id) {
        return teleconsultas.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void atualizar(Teleconsulta teleconsulta) {
        for (int i = 0; i < teleconsultas.size(); i++) {
            if (teleconsultas.get(i).getId() == teleconsulta.getId()) {
                teleconsultas.set(i, teleconsulta);
                System.out.println("Teleconsulta atualizada com sucesso!");
                return;
            }
        }
        System.out.println("Teleconsulta não encontrada!");
    }

    public void deletar(int id) {
        teleconsultas.removeIf(t -> t.getId() == id);
        System.out.println("Teleconsulta deletada com sucesso!");
    }
}
