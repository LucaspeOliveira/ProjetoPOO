package projeto.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import projeto.model.EntityUtensilios;
import projeto.persistence.GenericDAO;
import projeto.persistence.utensilios.UtensiliosDAO;
import projeto.persistence.utensilios.UtensiliosDAOImplementation;

public class ControllUtensilios {
    GenericDAO gDao = new GenericDAO();
    UtensiliosDAO estDao = new UtensiliosDAOImplementation(gDao);

    public List<EntityUtensilios> listar(){
        List<EntityUtensilios> lista = new ArrayList<>();
        try { 
            lista = estDao.listarUtensilios();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public EntityUtensilios pesquisar(String termoPesquisa){
        EntityUtensilios Utensilios = new EntityUtensilios();
        int id = 0;
        if(termoPesquisa == "")
            return Utensilios;
        try {
            id = Integer.parseInt(termoPesquisa);
            Utensilios = estDao.procurarUtensilios(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Utensilios;
    }

    public void addUtensilios(EntityUtensilios Utensilios) throws SQLException, ClassNotFoundException{
        try {
            estDao.adicionarUtensilios(Utensilios);
        } catch (ClassNotFoundException e) {
            System.err.println(e);
        }
    }

    public void excluiUtensilios(int id) {
        try {
            estDao.excluirUtensilios(id);
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    public void alterar (EntityUtensilios ropa) throws ClassNotFoundException, SQLException{
        estDao.atualizarUtensilios(ropa);
    }

}
