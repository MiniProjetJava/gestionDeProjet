package com.SDIA.gestiondeprojet.metier;

import com.SDIA.gestiondeprojet.dao.ProjetDAO;
import com.SDIA.gestiondeprojet.dao.ProjetDAOImpl;
import com.SDIA.gestiondeprojet.dao.entities.Projet;
import com.SDIA.gestiondeprojet.dao.entities.Responsable;
import com.SDIA.gestiondeprojet.dao.entities.Tache;
import com.SDIA.gestiondeprojet.dao.entities.Users;

import java.sql.SQLException;
import java.util.List;

public class ProjetMetierImpl implements ProjetMetier{
    private ProjetDAO projetDAO = new ProjetDAOImpl();

    public ProjetMetierImpl() throws SQLException {
    }

    @Override
    public List<Projet> selectAll() {
        return projetDAO.findAll();
    }

    @Override
    public Projet selectByID(long X) {
        return projetDAO.findById(X);
    }

    @Override
    public boolean updateProjet(Projet Element) throws SQLException{
        return projetDAO.update(Element);
    }

    @Override
    public void insertProjet(Projet Element) {
        projetDAO.save(Element);
    }

    @Override
    public void deleteProjet(Projet p) throws SQLException {
         projetDAO.delete(p);
    }

    @Override
    public List<Tache> selectAllTaskDeProjet(Projet projet) {
        return projetDAO.findAllTasks(projet);
    }

    @Override
    public List<Projet> selectByReponsable(Users responsable) {
        return projetDAO.findByResponsable(responsable);
    }

}
