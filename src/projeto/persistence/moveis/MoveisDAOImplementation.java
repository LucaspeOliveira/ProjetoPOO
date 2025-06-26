package projeto.persistence.moveis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import projeto.model.EntityMoveis;
import projeto.persistence.GenericDAO;

public class MoveisDAOImplementation implements MoveisDAO{

    private GenericDAO gDao;
    public MoveisDAOImplementation(GenericDAO gDao){
        this.gDao = gDao;
    }

    @Override
    public List<EntityMoveis> listarMoveis() throws SQLException, ClassNotFoundException {
        Connection con = gDao.getConnection();
        String sql = "SELECT * FROM moveis";
        PreparedStatement ps;
        List<EntityMoveis> listaMoveiss = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                EntityMoveis Moveis = new EntityMoveis();
                Moveis.setId(rs.getInt("id"));
                Moveis.setNome(rs.getString("nome"));
                Moveis.setTipo(rs.getString("tipo"));
                Moveis.setMarca(rs.getString("marca"));
                Moveis.setTamanho(rs.getString("tamanho"));
                Moveis.setQuantidade(rs.getInt("quantidade"));
                listaMoveiss.add(Moveis);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaMoveiss;
    }

    @Override
    public EntityMoveis procurarMoveis(int idMoveis) throws SQLException, ClassNotFoundException, IllegalArgumentException {
        Connection con = gDao.getConnection();
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT * FROM moveis WHERE id = ?");
        PreparedStatement ps = con.prepareStatement(sql.toString());
        ps.setInt(1, idMoveis);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            EntityMoveis moveis = new EntityMoveis();
            moveis.setId(rs.getInt("id"));
            moveis.setNome(rs.getString("nome"));
            moveis.setTipo(rs.getString("tipomoveis"));
            moveis.setMarca(rs.getString("marca"));
            moveis.setQuantidade(rs.getInt("quantidade"));
            return moveis;
        }
        rs.close();
        ps.close();
        con.close();
        throw new IllegalArgumentException("Objeto não encontrado");
        }

    @Override
    public void atualizarMoveis(EntityMoveis Moveis) throws SQLException, ClassNotFoundException {
        Connection con = gDao.getConnection();
        String sql = "UPDATE moveis SET tipo = ?, marca = ?, tamanho = ? WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql.toString());
        ps.setString(1, Moveis.getTipo());
        ps.setString(2, Moveis.getMarca());
        ps.setString(3, Moveis.getTamanho());
        ps.setInt(4, Moveis.getId());
        ps.execute();
        ps.close();
        con.close();
    }

    @Override
    public void excluirMoveis(int idMoveis) throws SQLException, ClassNotFoundException {
        Connection con = gDao.getConnection();
        String sql = "DELETE moveis WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idMoveis);
        ps.execute();
        ps.close();
        con.close();
    }

    @Override
    public void adicionarMoveis(EntityMoveis moveis) throws SQLException, ClassNotFoundException {
        Connection con = gDao.getConnection();
        String sql=  "INSERT INTO moveis VALUES (?, ?, ?, ?, ?) ";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(2, moveis.getTipo());
        ps.setString(1, moveis.getNome());
        ps.setString(3, moveis.getMarca());
        ps.setString(4, moveis.getTamanho());
        ps.setInt(5, moveis.getQuantidade());
        ps.execute();
        ps.close();
        con.close();
    }
    
}
