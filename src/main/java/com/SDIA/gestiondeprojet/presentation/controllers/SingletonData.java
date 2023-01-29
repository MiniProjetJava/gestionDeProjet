package com.SDIA.gestiondeprojet.presentation.controllers;

public class SingletonData {

    private SingletonData() {
    }


    private static final SingletonData instance = new SingletonData();

    String mail;

    public static SingletonData getInstance(){
        return instance;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
