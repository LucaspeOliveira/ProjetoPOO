package projeto.persistence.roupas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import projeto.model.EntityRoupa;
import projeto.persistence.GenericDAO;

public class RoupaDAOImplementation implements RoupaDAO {

    private GenericDAO gDao;
    public RoupaDAOImplementation(GenericDAO gDao){
        this.gDao = gDao;
    }

    @Override
    public List<EntityRoupa> listarRoupas() throws ClassNotFoundException, SQLException {
        Connection con = gDao.getConnection();
        String sql = "SELECT * FROM roupa";
        PreparedStatement ps;
        List<EntityRoupa> listaRoupas = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                EntityRoupa roupa = new EntityRoupa();
                roupa.setId(rs.getInt("id"));
                roupa.setNome(rs.getString("nome"));
                roupa.setTipo(rs.getString("tipoRoupa"));
                roupa.setNome(rs.getString("nome"));
                roupa.setMarca(rs.getString("marca"));
                roupa.setTamanho(rs.getString("tamanho"));
                roupa.setGenero(rs.getString("genero"));
                roupa.setQuantidade(rs.getInt("quantidade"));
                listaRoupas.add(roupa);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaRoupas;
    }

    @Override
    public EntityRoupa procurarRoupa(int idRoupa) throws SQLException, ClassNotFoundException, IllegalArgumentException {
        Connection con = gDao.getConnection();
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT * FROM roupa WHERE id = ?");
        PreparedStatement ps = con.prepareStatement(sql.toString());
        ps.setInt(1, idRoupa);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            EntityRoupa roupa = new EntityRoupa();
            roupa.setId(rs.getInt("id"));
            roupa.setNome(rs.getString("nome"));
            roupa.setTipo(rs.getString("tipoRoupa"));
            roupa.setNome(rs.getString("nome"));
            roupa.setMarca(rs.getString("marca"));
            roupa.setTamanho(rs.getString("tamanho"));
            roupa.setGenero(rs.getString("genero"));
            roupa.setQuantidade(rs.getInt("quantidade"));
            return roupa;
        }
        rs.close();
        ps.close();
        con.close();
        throw new IllegalArgumentException("Objeto não encontrado");
    }

    @Override
    public void atualizarRoupa(EntityRoupa roupa) throws SQLException, ClassNotFoundException {
        Connection con = gDao.getConnection();
        String sql = "UPDATE roupa SET tipoRoupa = ?, marca = ?, tamanho = ? WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql.toString());
        ps.setString(1, roupa.getTipo());
        ps.setString(2, roupa.getMarca());
        ps.setString(3, roupa.getTamanho());
        ps.setInt(4, roupa.getId());
        ps.execute();
        ps.close();
        con.close();
    }

    @Override
    public void excluirRoupa(int idRoupa) throws SQLException, ClassNotFoundException {
        Connection con = gDao.getConnection();
        String sql = "DELETE roupa WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idRoupa);
        ps.execute();
        ps.close();
        con.close();
    }

    @Override
    public void adicionarRoupa(EntityRoupa roupa) throws SQLException, ClassNotFoundException {
        Connection con = gDao.getConnection();
        String sql=  "INSERT INTO roupa VALUES (?, ?, ?, ?, ?, ?) ";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, roupa.getTipo());
        ps.setString(2, roupa.getNome());
        ps.setString(3, roupa.getMarca());
        ps.setString(4, roupa.getTamanho());
        ps.setString(5, roupa.getGenero());
        ps.setInt(6, roupa.getQuantidade());
        ps.execute();
        ps.close();
        con.close();
    }

    
}
