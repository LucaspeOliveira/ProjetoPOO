package projeto.persistence.Eletronico;

import java.sql.SQLException;
import java.util.List;
import projeto.model.EntityEletronico;

public interface EletronicoDAO {
    public List<EntityEletronico> listarEletronicos() throws SQLException, ClassNotFoundException;
    public EntityEletronico procurarEletronicos(int idEletronico) throws SQLException, ClassNotFoundException, IllegalArgumentException;
    public void atualizarEletronicos(EntityEletronico eletronico) throws SQLException, ClassNotFoundException;
    public void excluirEletronicos(int idEletronico) throws SQLException, ClassNotFoundException;
    public void adicionarEletronicos(EntityEletronico eletronico) throws SQLException, ClassNotFoundException;
}
