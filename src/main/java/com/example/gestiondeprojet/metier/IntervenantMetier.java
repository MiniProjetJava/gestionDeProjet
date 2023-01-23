package com.example.gestiondeprojet.metier;

import com.example.gestiondeprojet.dao.IntervenantDao;
import com.example.gestiondeprojet.entities.Intervenant;

import java.sql.SQLException;
import java.util.List;

public class IntervenantMetier implements IMetier<Intervenant>{

    public IntervenantMetier() {
    }


    @Override
    public Intervenant add(Intervenant intervenant) throws SQLException {
        return new IntervenantDao().add(intervenant);
    }

    @Override
    public List<Intervenant> getAll() throws SQLException {
        return null;
    }

    @Override
    public void delete(Intervenant intervenant) throws SQLException {
        new IntervenantDao().delete(intervenant.getId());
    }

    @Override
    public List<Intervenant> findByMotClé(String mot) throws SQLException {
        return new IntervenantDao().findByMotCle(mot);
    }

    @Override
    public Intervenant findByMail(String mail) throws SQLException {
        return null;
    }

    @Override
    public Intervenant update(Intervenant intervenant) throws SQLException {
        return new IntervenantDao().update(intervenant);
    }
}
