package com.SDIA.gestiondeprojet.metier;

import com.SDIA.gestiondeprojet.dao.MaterielleDAO;
import com.SDIA.gestiondeprojet.dao.MaterielleDAOImpl;
import com.SDIA.gestiondeprojet.dao.entities.Materielle;
import com.SDIA.gestiondeprojet.dao.entities.Tache;

import java.sql.SQLException;
import java.util.List;

public class MaterielleMetierImpl implements MaterielleMetier{
    private MaterielleDAO materielleDAO = new MaterielleDAOImpl();

    public MaterielleMetierImpl() throws SQLException {
    }

    @Override
    public List<Materielle> selectAll() {
        return materielleDAO.findAll();
    }

    @Override
    public Materielle selectMaterielleByID(long X) {
        return materielleDAO.findById(X);
    }

    @Override
    public boolean update(Materielle Element) throws SQLException {
        return materielleDAO.update(Element);
    }

    @Override
    public void insert(Materielle Element) {
        materielleDAO.save(Element);
    }

    @Override
    public void delete(Materielle materielle) throws SQLException {
        materielleDAO.delete(materielle);
    }

    @Override
    public List<Materielle> selectAllMaterielleByType(String type) {
        return materielleDAO.findByType(type);
    }

    @Override
    public List<Materielle> selectAllMateriellEtat(String Etat) {
        return materielleDAO.findByEtat(Etat);
    }

    @Override
    public List<Tache> selectAllTasksByMaterielle(Materielle materielle) {
        return materielleDAO.findAllTasks(materielle);
    }
}
