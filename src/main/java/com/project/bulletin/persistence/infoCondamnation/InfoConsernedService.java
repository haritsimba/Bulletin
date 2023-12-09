package com.project.bulletin.persistence.infoCondamnation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class InfoConsernedService {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Bulletin");
    private final EntityManager entityManager = emf.createEntityManager();
    private static InfoConsernedService instance = null;

    public static  InfoConsernedService getInstance() {
        return instance == null ? instance = new InfoConsernedService() : null;
    }
    public List<InfoConserned> getConsernedList(){
        Query query = entityManager.createQuery("SELECT ic FROM InfoConserned ic", InfoConserned.class);
        return query.getResultList();
    }

    public void addConserned(String nom, String prenoms, String pere, String mere, Date dateNaissance, String lieuNaissance,
                             String domicile, String profession, String nationalite){
        entityManager.getTransaction().begin();
        InfoConserned Conserned = new InfoConserned();
        Conserned.setNom(nom);
        Conserned.setPrenoms(prenoms);
        Conserned.setPere(pere);
        Conserned.setMere(mere);
        Conserned.setDateNaissance(dateNaissance);
        Conserned.setLieuNaissance(lieuNaissance);
        Conserned.setDomicile(domicile);
        Conserned.setProfession(profession);
        if(!Objects.equals(nationalite, "")){
            Conserned.setNationalite(nationalite.toUpperCase());
        }
        entityManager.persist(Conserned);
        entityManager.getTransaction().commit();
    }
    public InfoConserned getConsernedById(int id){
        return entityManager.find(InfoConserned.class,id);
    }
    public HashSet<InfoConserned> getConsernedByName(String searchQuery) {
        HashSet<InfoConserned> queryResults = new HashSet<InfoConserned>();
        HashSet<InfoConserned> finalResult = new HashSet<InfoConserned>();
        boolean specFound = false;

        Query query = entityManager.createQuery("SELECT ic FROM InfoConserned ic WHERE ic.nom LIKE :search " +
                "OR ic.prenoms LIKE :search", InfoConserned.class);
        for (String search : searchQuery.split(" ")
        ) {
            query.setParameter("search", "%" + search + "%");
            queryResults.addAll(query.getResultList());
        }
        for (InfoConserned result:queryResults
             ) {
            if ((result.getNom() + " " + result.getPrenoms()).contains(searchQuery) ||
                    (result.getPrenoms() + " " + result.getNom()).contains(searchQuery)){
               specFound = true;
               finalResult.add(result);
            }
        }
        if(specFound){
            return finalResult;
        }
        finalResult = queryResults;
        return finalResult;
    }

}
