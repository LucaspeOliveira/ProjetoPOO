package projeto.persistence.eletronico;

import java.sql.SQLException;
import java.util.List;
import projeto.model.EntityEletronico;

public interface EletronicoDAO {
    public List<EntityEletronico> listarEletronico() throws SQLException, ClassNotFoundException;
    public EntityEletronico procurarEletronico(int idEletronico) throws SQLException, ClassNotFoundException, IllegalArgumentException;
    public void atualizarEletronico(EntityEletronico eletronico) throws SQLException, ClassNotFoundException;
    public void excluirEletronico(int idEletronico) throws SQLException, ClassNotFoundException;
    public void adicionarEletronico(EntityEletronico eletronico) throws SQLException, ClassNotFoundException;
}
