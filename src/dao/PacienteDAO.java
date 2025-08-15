package dao;

import java.util.ArrayList;
import java.util.List;
import usuarios.Paciente;

public class PacienteDAO {
    private final List<Paciente> pacientes = new ArrayList<>();

    public void inserir(Paciente paciente) {
        pacientes.add(paciente);
        System.out.println("Paciente inserido com sucesso!");
    }

    public List<Paciente> listar() {
        return new ArrayList<>(pacientes);
    }

    public Paciente buscarPorId(int id) {
        return pacientes.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void atualizar(Paciente paciente) {
        for (int i = 0; i < pacientes.size(); i++) {
            if (pacientes.get(i).getId() == paciente.getId()) {
                pacientes.set(i, paciente);
                System.out.println("Paciente atualizado com sucesso!");
                return;
            }
        }
        System.out.println("Paciente não encontrado!");
    }

    public void deletar(int id) {
        pacientes.removeIf(p -> p.getId() == id);
        System.out.println("Paciente deletado com sucesso!");
    }
}
