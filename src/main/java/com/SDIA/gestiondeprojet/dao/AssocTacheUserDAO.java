package com.SDIA.gestiondeprojet.dao;

import com.SDIA.gestiondeprojet.dao.DAO;
import com.SDIA.gestiondeprojet.dao.entities.AssocTacheUser;
import com.SDIA.gestiondeprojet.dao.entities.Materielle;
import com.SDIA.gestiondeprojet.dao.entities.Tache;
import com.SDIA.gestiondeprojet.dao.entities.Users;

import java.util.List;

public interface AssocTacheUserDAO extends DAO<AssocTacheUser> {

    List<AssocTacheUser> findByUser(Users user);

    List<AssocTacheUser> findByTache(Tache tache);

    public void save(Tache tache, Users user);
    public void delete(Tache tache, Users user);
}
