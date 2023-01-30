package com.SDIA.gestiondeprojet.dao;

import com.SDIA.gestiondeprojet.dao.entities.AssocTacheMaterielle;
import com.SDIA.gestiondeprojet.dao.entities.Materielle;
import com.SDIA.gestiondeprojet.dao.entities.Tache;

import java.util.List;

public interface AssocTacheMaterielleDAO extends DAO<AssocTacheMaterielle> {

    List<AssocTacheMaterielle> findByMaterielle(Materielle materielle);

    Long findByTacheMaterielle(Tache tache, Materielle materielle);

    List<AssocTacheMaterielle> findByTache(Tache tache);
    public AssocTacheMaterielle save(Tache tache, Materielle materielle);
}
