package com.example.gestiondeprojet.dao;

import com.example.gestiondeprojet.dao.entities.Intervenant;
import com.example.gestiondeprojet.dao.entities.Responsable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IntervenantDao implements DaoImpl<Intervenant>{
    @Override
    public Intervenant add(Intervenant intervenant) throws SQLException {
        Connection con = SingletonConnexion.getConnection();
        PreparedStatement pst = con.prepareStatement("insert into intervenant(nom, prenom, adresse, mail, telephone, password) values (?,?,?,?,?,?)" );
        pst.setString(1, intervenant.getNom());
        pst.setString(2, intervenant.getPrenom());
        pst.setString(3, intervenant.getAdresse());
        pst.setString(4,intervenant.getMail());
        pst.setString(5, intervenant.getTelephone());
        pst.setString(6, intervenant.getPassword());

        pst.executeUpdate();
        return intervenant;
    }

    @Override
    public Intervenant update(Intervenant intervenant) throws SQLException {
        Connection con = SingletonConnexion.getConnection();

        PreparedStatement pst = con.prepareStatement("update intervenant set nom = ?, prenom = ?, adresse = ?, mail = ?, telephone = ? where id = ?");

        pst.setString(1, intervenant.getNom());
        pst.setString(2,intervenant.getPrenom());
        pst.setString(3, intervenant.getAdresse());
        pst.setString(4,intervenant.getMail());
        pst.setString(5, intervenant.getTelephone());
        pst.setInt(6, intervenant.getId());

        pst.executeUpdate();

        return intervenant;
    }

    @Override
    public List<Intervenant> getAllIntervenant() throws SQLException {
        return null;
    }

    @Override
    public List<Responsable> getAllResponsable() throws SQLException {
        return null;
    }


    @Override
    public void delete(int id) throws SQLException {
        Connection con = SingletonConnexion.getConnection();
        PreparedStatement pst = con.prepareStatement("delete from intervenant where id=?");
        pst.setInt(1, id);
        pst.executeUpdate();

    }

    public List<Intervenant> findByMotCle(String mot) throws SQLException {
        Connection con = SingletonConnexion.getConnection();
        PreparedStatement pst = con.prepareStatement("select * from allusers  where mail like ? ");
        pst.setString(1, "%"+mot+"%");

        ResultSet rs = pst.executeQuery();

        List<Intervenant> intervenantList = new ArrayList<>();

        while (rs.next()){
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            String adresse = rs.getString("adresse");
            String mail = rs.getString("mail");
            String telephone = rs.getString("telephone");
            String password = rs.getString("password");

            Intervenant intervenant = new Intervenant(nom,prenom,adresse,mail,telephone, password);
            intervenantList.add(intervenant);

        }
        return intervenantList;
    }

    @Override
    public Intervenant findByMail(String mail) throws SQLException {
        return null;
    }
}
