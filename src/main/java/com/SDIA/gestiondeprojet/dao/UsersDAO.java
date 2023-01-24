package com.SDIA.gestiondeprojet.dao;

import com.SDIA.gestiondeprojet.dao.entities.Users;

import java.util.List;


public interface UsersDAO extends DAO <Users>{

    List<Users> findByRole(String role);

}
