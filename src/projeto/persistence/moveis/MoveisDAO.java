package projeto.persistence.moveis;

import java.sql.SQLException;
import java.util.List;

import projeto.model.EntityMoveis;

public interface MoveisDAO {
    public List<EntityMoveis> listarMoveis() throws SQLException, ClassNotFoundException;
    public EntityMoveis procurarMoveis(int idMoveis) throws SQLException, ClassNotFoundException, IllegalArgumentException;
    public void atualizarMoveis(EntityMoveis Moveis) throws SQLException, ClassNotFoundException;
    public void excluirMoveis(int idMoveis) throws SQLException, ClassNotFoundException;
    public void adicionarMoveis(EntityMoveis Moveis) throws SQLException, ClassNotFoundException;
}
