package com.example.gestiondeprojet.dao;

import com.example.gestiondeprojet.entities.Intervenant;
import com.example.gestiondeprojet.entities.Responsable;
import com.example.gestiondeprojet.entities.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDao implements DaoImpl<Users>{

    @Override
    public Users add(Users users) throws SQLException {
        Connection con = SingletonConnexion.getConnection();
        PreparedStatement pst = con.prepareStatement("insert into allusers(nom, prenom, adresse, mail, telephone, password, role) values (?,?,?,?,?,?,?)" );
        pst.setString(1, users.getNom());
        pst.setString(2, users.getPrenom());
        pst.setString(3, users.getAdresse());
        pst.setString(4,users.getMail());
        pst.setString(5, users.getTelephone());
        pst.setString(6, users.getPassword());
        pst.setString(7, users.getRole());

        pst.executeUpdate();
        return users;
    }

    @Override
    public Users update(Users users) throws SQLException {
        Connection con = SingletonConnexion.getConnection();

        PreparedStatement pst = con.prepareStatement("update allusers set nom = ?, prenom = ?, adresse = ?, mail = ?, telephone = ? where id = ?");

        pst.setString(1, users.getNom());
        pst.setString(2,users.getPrenom());
        pst.setString(3, users.getAdresse());
        pst.setString(4,users.getMail());
        pst.setString(5, users.getTelephone());
        pst.setInt(6, users.getId());

        pst.executeUpdate();

        return users;
    }

    @Override
    public List<Intervenant> getAllIntervenant() throws SQLException {

        Connection con = SingletonConnexion.getConnection();
        PreparedStatement pst = con.prepareStatement("select * from allusers where role='Intervenant'");
        ResultSet rs = pst.executeQuery();

        List<Intervenant> p = new ArrayList<>();
        while (rs.next()){
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            String adresse = rs.getString("adresse");
            String mail = rs.getString("mail");
            String telephone = rs.getString("telephone");
            String password = rs.getString("password");


            Intervenant intervenant = new Intervenant(id,nom,prenom,adresse,mail,telephone,password);

            p.add(intervenant);

        }
        return p;
    }


    @Override
    public List<Responsable> getAllResponsable() throws SQLException {
        return null;
    }

    @Override
    public List<Users> findByMotCle(String mot) throws SQLException {
        return null;
    }

    @Override
    public Users findByMail(String email) throws SQLException {

        Connection con = SingletonConnexion.getConnection();
        PreparedStatement pst = con.prepareStatement("select * from allusers where mail = ?");
        pst.setString(1, email);
        ResultSet rs = pst.executeQuery();

        Users users = new Users();

        if (rs.next()) {
            users.setNom(rs.getString("nom"));
            users.setPrenom(rs.getString("prenom"));
            users.setAdresse(rs.getString("adresse"));
            users.setMail(rs.getString("mail"));
            users.setTelephone(rs.getString("telephone"));
            users.setPassword(rs.getString("password"));

        }
        return users;

    }


    @Override
    public void delete(int id) throws SQLException {
        Connection con = SingletonConnexion.getConnection();
        PreparedStatement pst = con.prepareStatement("delete from allusers where id=?");
        pst.setInt(1, id);
        pst.executeUpdate();
    }
}
