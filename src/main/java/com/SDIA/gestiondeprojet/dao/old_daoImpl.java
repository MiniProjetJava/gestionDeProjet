package com.SDIA.gestiondeprojet.dao;

import com.SDIA.gestiondeprojet.dao.entities.Intervenant;
import com.SDIA.gestiondeprojet.dao.entities.Responsable;

import java.sql.SQLException;
import java.util.List;

public interface old_daoImpl<T> {
    public T add(T o) throws SQLException;
    public T update(T o) throws SQLException;
    public List<Intervenant> getAllIntervenant() throws SQLException;
    public List<Responsable> getAllResponsable() throws SQLException;
    public List<T> findByMotCle(String mot) throws SQLException;
    public T findByMail(String mail) throws SQLException;
    public void delete(int id) throws SQLException;
}
