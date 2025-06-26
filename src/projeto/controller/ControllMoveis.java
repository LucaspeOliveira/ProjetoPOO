package projeto.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import projeto.model.EntityMoveis;
import projeto.persistence.GenericDAO;
import projeto.persistence.moveis.MoveisDAO;
import projeto.persistence.moveis.MoveisDAOImplementation;

public class ControllMoveis {
    GenericDAO gDao = new GenericDAO();
    MoveisDAO estDao = new MoveisDAOImplementation(gDao);

    public List<EntityMoveis> listar(){
        List<EntityMoveis> lista = new ArrayList<>();
        try { 
            lista = estDao.listarMoveis();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public EntityMoveis pesquisar(String termoPesquisa){
        EntityMoveis Moveis = new EntityMoveis();
        int id = 0;
        if(termoPesquisa == "")
            return Moveis;
        try {
            id = Integer.parseInt(termoPesquisa);
            Moveis = estDao.procurarMoveis(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Moveis;
    }

    public void addMoveis(EntityMoveis Moveis) throws SQLException, ClassNotFoundException{
        try {
            estDao.adicionarMoveis(Moveis);
        } catch (ClassNotFoundException e) {
            System.err.println(e);
        }
    }

    public void excluiMoveis(int id) {
        try {
            estDao.excluirMoveis(id);
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    public void alterar (EntityMoveis ropa) throws ClassNotFoundException, SQLException{
        estDao.atualizarMoveis(ropa);
    }

}
