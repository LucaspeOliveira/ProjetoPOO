package projeto.persistence.utensilios;

import java.sql.SQLException;
import java.util.List;

import projeto.model.EntityUtensilios;

public interface UtensiliosDAO {
    public List<EntityUtensilios> listarUtensilios() throws SQLException, ClassNotFoundException;
    public EntityUtensilios procurarUtensilios(int idUtensilios) throws SQLException, ClassNotFoundException, IllegalArgumentException;
    public void atualizarUtensilios(EntityUtensilios Utensilios) throws SQLException, ClassNotFoundException;
    public void excluirUtensilios(int idUtensilios) throws SQLException, ClassNotFoundException;
    public void adicionarUtensilios(EntityUtensilios Utensilios) throws SQLException, ClassNotFoundException;
}
