package projeto.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import projeto.model.EntityEletronico;
import projeto.persistence.GenericDAO;
import projeto.persistence.eletronico.EletronicoDAO;
import projeto.persistence.eletronico.EletronicoDAOImplementation;

public class ControllEletronico {
    GenericDAO gDao = new GenericDAO();
    EletronicoDAO estDao = new EletronicoDAOImplementation(gDao);

    public List<EntityEletronico> listar(){
        List<EntityEletronico> lista = new ArrayList<>();
        try { 
            lista = estDao.listarEletronico();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public EntityEletronico pesquisar(String termoPesquisa){
        EntityEletronico Eletronico = new EntityEletronico();
        int id = 0;
        if(termoPesquisa == "")
            return Eletronico;
        try {
            id = Integer.parseInt(termoPesquisa);
            Eletronico = estDao.procurarEletronico(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Eletronico;
    }

    public void addEletronico(EntityEletronico Eletronico) throws SQLException, ClassNotFoundException{
        try {
            estDao.adicionarEletronico(Eletronico);
        } catch (ClassNotFoundException e) {
            System.err.println(e);
        }
    }

    public void excluiEletronico(int id) {
        try {
            estDao.excluirEletronico(id);
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    public void alterar (EntityEletronico ropa) throws ClassNotFoundException, SQLException{
        estDao.atualizarEletronico(ropa);
    }

}
