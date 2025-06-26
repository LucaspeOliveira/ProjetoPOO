package projeto.persistence.eletronico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import projeto.model.EntityEletronico;
import projeto.persistence.GenericDAO;

public class EletronicoDAOImplementation implements EletronicoDAO {

    private GenericDAO gDao;
    public EletronicoDAOImplementation(GenericDAO gDao){
        this.gDao = gDao;
    }

    @Override
    public List<EntityEletronico> listarEletronico() throws SQLException, ClassNotFoundException {
        Connection con = gDao.getConnection();
        String sql = "SELECT * FROM eletronico";
        PreparedStatement ps;
        List<EntityEletronico> listaEletronico = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                EntityEletronico eletronico = new EntityEletronico();
                eletronico.setId(rs.getInt("id"));
                eletronico.setNome(rs.getString("nome"));
                eletronico.setTipo(rs.getString("tipo"));
                eletronico.setMarca(rs.getString("marca"));
                eletronico.setQuantidade(rs.getInt("quantidade"));
                listaEletronico.add(eletronico);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaEletronico;
    }

    @Override
    public EntityEletronico procurarEletronico(int idEletronico) throws SQLException, ClassNotFoundException, IllegalArgumentException {
        Connection con = gDao.getConnection();
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT * FROM eletronico WHERE id = ?");
        PreparedStatement ps = con.prepareStatement(sql.toString());
        ps.setInt(1, idEletronico);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            EntityEletronico eletronico = new EntityEletronico();
            eletronico.setId(rs.getInt("id"));
            eletronico.setNome(rs.getString("nome"));
            eletronico.setTipo(rs.getString("tipo"));
            eletronico.setMarca(rs.getString("marca"));
            eletronico.setQuantidade(rs.getInt("quantidade"));
            return eletronico;
        }
        rs.close();
        ps.close();
        con.close();
        throw new IllegalArgumentException("Objeto não encontrado");
    }

    @Override
    public void atualizarEletronico(EntityEletronico eletronico) throws SQLException, ClassNotFoundException {
        Connection con = gDao.getConnection();
        String sql = "UPDATE eletronico SET tipo = ?, marca = ?, tamanho = ? WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql.toString());
        ps.setString(1, eletronico.getTipo());
        ps.setString(2, eletronico.getMarca());
        //ps.setString(3, eletronico.getTamanho());
        ps.setInt(4, eletronico.getId());
        ps.execute();
        ps.close();
        con.close();
    }

    @Override
    public void excluirEletronico(int idEletronico) throws SQLException, ClassNotFoundException {
        Connection con = gDao.getConnection();
        String sql = "DELETE eletronico WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idEletronico);
        ps.execute();
        ps.close();
        con.close();;
    }

    @Override
    public void adicionarEletronico(EntityEletronico eletronico) throws SQLException, ClassNotFoundException {
        Connection con = gDao.getConnection();
        String sql=  "INSERT INTO eletronico VALUES (?, ?, ?, ?) ";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(2, eletronico.getTipo());
        ps.setString(1, eletronico.getNome());
        ps.setString(3, eletronico.getMarca());
        ps.setInt(4, eletronico.getQuantidade());
        ps.execute();
        ps.close();
        con.close();
    }
    
}
