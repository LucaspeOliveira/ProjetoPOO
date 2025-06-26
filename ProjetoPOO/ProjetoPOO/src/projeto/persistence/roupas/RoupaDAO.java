package projeto.persistence.roupas;

import java.sql.SQLException;
import java.util.List;
import projeto.model.EntityRoupa;

public interface RoupaDAO {
    public List<EntityRoupa> listarRoupas() throws SQLException, ClassNotFoundException;
    public EntityRoupa procurarRoupa(int idRoupa) throws SQLException, ClassNotFoundException, IllegalArgumentException;
    public void atualizarRoupa(EntityRoupa roupa) throws SQLException, ClassNotFoundException;
    public void excluirRoupa(int idRoupa) throws SQLException, ClassNotFoundException;
    public void adicionarRoupa(EntityRoupa roupa) throws SQLException, ClassNotFoundException;
}
