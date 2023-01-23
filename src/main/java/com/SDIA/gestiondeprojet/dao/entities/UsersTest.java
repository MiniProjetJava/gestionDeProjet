package com.SDIA.gestiondeprojet.dao.entities;

import java.io.Serializable;

public class UsersTest implements Serializable {
    private String mail;
    private String password;

    private String role;

    public UsersTest() {
    }

    public UsersTest(String mail, String password, String role) {
        this.mail = mail;
        this.password = password;
        this.role = role;
    }

    public UsersTest(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
