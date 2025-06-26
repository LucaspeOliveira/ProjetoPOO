package projeto.persistence.utensilios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import projeto.model.EntityUtensilios;
import projeto.persistence.GenericDAO;

public class UtensiliosDAOImplementation implements UtensiliosDAO{

    private GenericDAO gDao;
    public UtensiliosDAOImplementation(GenericDAO gDao){
        this.gDao = gDao;
    }

    @Override
    public List<EntityUtensilios> listarUtensilios() throws ClassNotFoundException, SQLException {
        Connection con = gDao.getConnection();
        String sql = "SELECT * FROM utensilios";
        PreparedStatement ps;
        List<EntityUtensilios> listaUtensilios = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                EntityUtensilios Utensilios = new EntityUtensilios();
                Utensilios.setId(rs.getInt("id"));
                Utensilios.setNome(rs.getString("nome"));
                Utensilios.setTipo(rs.getString("tipo"));
                Utensilios.setQuantidade(rs.getInt("quantidade"));
                listaUtensilios.add(Utensilios);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaUtensilios;
    }

    @Override
    public EntityUtensilios procurarUtensilios(int idUtensilios) throws SQLException, ClassNotFoundException, IllegalArgumentException {
        Connection con = gDao.getConnection();
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT * FROM utensilios WHERE id = ?");
        PreparedStatement ps = con.prepareStatement(sql.toString());
        ps.setInt(1, idUtensilios);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            EntityUtensilios Utensilios = new EntityUtensilios();
            Utensilios.setId(rs.getInt("id"));
            Utensilios.setNome(rs.getString("nome"));
            Utensilios.setTipo(rs.getString("tipo"));
            Utensilios.setQuantidade(rs.getInt("quantidade"));
            return Utensilios;
        }
        rs.close();
        ps.close();
        con.close();
        throw new IllegalArgumentException("Objeto não encontrado");
    }

    @Override
    public void atualizarUtensilios(EntityUtensilios Utensilios) throws SQLException, ClassNotFoundException {
        Connection con = gDao.getConnection();
        String sql = "UPDATE Utensilios SET tipo = ?, nome = ?, quantidade = ? WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql.toString());
        ps.setString(1, Utensilios.getTipo());
        ps.setInt(3, Utensilios.getQuantidade());
        ps.setString(2, Utensilios.getNome());
        ps.setInt(4, Utensilios.getId());
        ps.execute();
        ps.close();
        con.close();
    }

    @Override
    public void excluirUtensilios(int idUtensilios) throws SQLException, ClassNotFoundException {
        Connection con = gDao.getConnection();
        String sql = "DELETE Utensilios WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idUtensilios);
        ps.execute();
        ps.close();
        con.close();
    }

    @Override
    public void adicionarUtensilios(EntityUtensilios Utensilios) throws SQLException, ClassNotFoundException {
        Connection con = gDao.getConnection();
        String sql=  "INSERT INTO Utensilios VALUES (?, ?, ?) ";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(2, Utensilios.getTipo());
        ps.setString(1, Utensilios.getNome());
        ps.setInt(3, Utensilios.getQuantidade());
        ps.execute();
        ps.close();
        con.close();
    } 
}
