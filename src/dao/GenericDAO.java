package dao;

import java.util.List;

public interface GenericDAO<T> {
    void inserir(T obj);
    List<T> listar();
    T buscarPorId(int id);
    void atualizar(T obj);
    void deletar(int id);
}
