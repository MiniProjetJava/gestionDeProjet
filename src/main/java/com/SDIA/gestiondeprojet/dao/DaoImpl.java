package com.example.gestiondeprojet.dao;

import com.example.gestiondeprojet.dao.entities.Intervenant;
import com.example.gestiondeprojet.dao.entities.Responsable;

import java.sql.SQLException;
import java.util.List;

public interface DaoImpl<T> {
    public T add(T o) throws SQLException;
    public T update(T o) throws SQLException;
    public List<Intervenant> getAllIntervenant() throws SQLException;
    public List<Responsable> getAllResponsable() throws SQLException;
    public List<T> findByMotCle(String mot) throws SQLException;
    public T findByMail(String mail) throws SQLException;
    public void delete(int id) throws SQLException;
}
