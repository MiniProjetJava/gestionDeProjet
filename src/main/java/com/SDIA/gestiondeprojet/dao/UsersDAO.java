package com.SDIA.gestiondeprojet.dao;

import com.SDIA.gestiondeprojet.dao.entities.Users;

import java.sql.SQLException;
import java.util.List;


public interface UsersDAO extends DAO <Users>{

    List<Users> findByRole(String role);
    int findByUsers(Users users) throws SQLException;

}
