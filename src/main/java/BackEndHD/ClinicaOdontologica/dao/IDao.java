package BackEndHD.ClinicaOdontologica.dao;

import java.util.List;

public interface IDao<T> {
    T guardar(T t);
    List<T> listarTodos();
    T buscarPorId(Integer id);
    void eliminar (Integer id);
    void actualizar(T t);

    T buscarPorString(String valor);
}
