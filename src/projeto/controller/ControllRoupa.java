package projeto.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import projeto.model.EntityRoupa;
import projeto.persistence.GenericDAO;
import projeto.persistence.roupas.RoupaDAO;
import projeto.persistence.roupas.RoupaDAOImplementation;

public class ControllRoupa {
    GenericDAO gDao = new GenericDAO();
    RoupaDAO estDao = new RoupaDAOImplementation(gDao);

    public List<EntityRoupa> listar(){
        List<EntityRoupa> lista = new ArrayList<>();
        try { 
            lista = estDao.listarRoupas();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public EntityRoupa pesquisar(String termoPesquisa){
        EntityRoupa roupa = new EntityRoupa();
        int id = 0;
        if(termoPesquisa == "")
            return roupa;
        try {
            id = Integer.parseInt(termoPesquisa);
            roupa = estDao.procurarRoupa(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roupa;
    }

    public void addRoupa(EntityRoupa roupa) throws SQLException, ClassNotFoundException{
        try {
            estDao.adicionarRoupa(roupa);
        } catch (ClassNotFoundException e) {
            System.err.println(e);
        }
    }

    public void excluiRoupa(int id) {
        try {
            estDao.excluirRoupa(id);
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    public void alterar (EntityRoupa ropa) throws ClassNotFoundException, SQLException{
        estDao.atualizarRoupa(ropa);
    }

}
