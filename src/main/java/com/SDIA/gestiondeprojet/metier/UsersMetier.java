package com.SDIA.gestiondeprojet.metier;

import com.SDIA.gestiondeprojet.dao.UsersDAOImpl;
import com.SDIA.gestiondeprojet.dao.entities.Intervenant;
import com.SDIA.gestiondeprojet.dao.entities.Users;

import java.sql.SQLException;
import java.util.List;

public class UsersMetier implements IMetier<Users>{
    public UsersMetier() {
    }

    @Override
    public void add(Users users) throws SQLException {
        new UsersDAOImpl().save(users);
    }

    @Override
    public List<Users> getAll() throws SQLException {
        return new UsersDAOImpl().findAll();
    }


    public List<Users> getAllIntervenant() throws SQLException {
        return new UsersDAOImpl().findByRole("INTERVENANT");
    }

    @Override
    public void delete(Users o) throws SQLException {
        new UsersDAOImpl().delete(o);
    }

    @Override
    public List<Users> findByMotCle(String mot) throws SQLException {
        return new UsersDAOImpl().findbyMotCle(mot);
    }

    @Override
    public Users findByMail(String mail) throws SQLException {
        return new UsersDAOImpl().findByMail(mail);
    }

    @Override
    public Users update(Users o) throws SQLException {
        return new UsersDAOImpl().updateUser(o);
    }

    public List<Users> findIntervenant() throws SQLException {
        return new UsersDAOImpl().findAllIntervenant();
    }

    // Méthodes pour la vérification de l'utilisateur
    public int checkUsers(Users users) throws SQLException {
        UsersDAOImpl usersDAO = new UsersDAOImpl();
        if (usersDAO.findByUsers(users) == 1){
            System.out.println(">> Vous etes responsable !");
            return 1;
        }
        else if(usersDAO.findByUsers(users) == 2){
            System.out.println(">> Vous etes intervenant !");
            return 2;
        }
        else {
            System.out.println(">> Qui etes vous ??");
            return 0;
        }
    }
}
