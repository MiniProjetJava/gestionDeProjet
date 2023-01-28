package com.SDIA.gestiondeprojet.metier;

import com.SDIA.gestiondeprojet.dao.TacheDAO;
import com.SDIA.gestiondeprojet.dao.TacheDAOImpl;
import com.SDIA.gestiondeprojet.dao.entities.Materielle;
import com.SDIA.gestiondeprojet.dao.entities.Projet;
import com.SDIA.gestiondeprojet.dao.entities.Tache;
import com.SDIA.gestiondeprojet.dao.entities.Users;

import java.sql.SQLException;
import java.util.List;

public class TacheMetierImpl implements TacheMetier {
    private TacheDAO tacheDAO = new TacheDAOImpl();

    public TacheMetierImpl() throws SQLException {
    }

    public List<Tache> selectAll(){
        return tacheDAO.findAll();
    }
    public Tache selectTacheByID(long ID) {
        return tacheDAO.findById (ID);
    }

    public boolean updateTache(Tache Element) throws SQLException {
        return tacheDAO.update(Element);
    }

    public void insertTache(Tache Element){
        tacheDAO.save(Element);
    }

    public void delete(Tache tache) throws SQLException {
        tacheDAO.delete(tache);
    }

    public List<Tache> selectTacheByCreateur(Users Createur){
        return tacheDAO.findByCreateur(Createur);
    }

    public List<Tache> selectTacheByEtat(String Etat){
        return tacheDAO.findByEtat(Etat);
    }

    public List<Materielle> selectMateriellesDeTache(Tache tache){
        return tacheDAO.findAllMaterielles(tache);
    }

    public List<Tache> selectTacheByProjet(Projet projet){
        return tacheDAO.findByProjet(projet);

    }

    @Override
    public List<Tache> selectTacheByDesc(String desc) {
        return tacheDAO.findByDescription(desc);
    }


}
