package com.SDIA.gestiondeprojet.dao;

import com.SDIA.gestiondeprojet.dao.entities.Users;


public interface UsersDAO extends DAO <Users>{

    Users findByRole(String role);

}
