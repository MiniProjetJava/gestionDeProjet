package com.SDIA.gestiondeprojet.metier;

import com.SDIA.gestiondeprojet.dao.entities.Intervenant;

import java.sql.SQLException;
import java.util.List;

public interface IMetier<T> {
    public T add(T o) throws SQLException;
    public List<Intervenant> getAll() throws SQLException;
    public void delete(T o) throws SQLException;
    public List<T> findByMotCle(String mot) throws SQLException;
    public T findByMail(String mail) throws SQLException;
    public T update(T o) throws SQLException;

}
