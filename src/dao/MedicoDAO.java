package dao;

import java.util.ArrayList;
import java.util.List;
import usuarios.Medico;

public class MedicoDAO implements GenericDAO<Medico> {

    private final List<Medico> medicos = new ArrayList<>();

    @Override
    public void inserir(Medico medico) {
        medicos.add(medico);
        System.out.println("Médico inserido: " + medico.getNome());
    }

    @Override
    public List<Medico> listar() {
        return new ArrayList<>(medicos);
    }

    @Override
    public Medico buscarPorId(int id) {
        return medicos.stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void atualizar(Medico medico) {
        for (int i = 0; i < medicos.size(); i++) {
            if (medicos.get(i).getId() == medico.getId()) {
                medicos.set(i, medico);
                System.out.println("Médico atualizado: " + medico.getNome());
                return;
            }
        }
        System.out.println("Médico não encontrado.");
    }

    @Override
    public void deletar(int id) {
        medicos.removeIf(m -> m.getId() == id);
        System.out.println("Médico deletado, ID: " + id);
    }
}
