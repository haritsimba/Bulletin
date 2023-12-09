package com.project.bulletin.persistence.condamnation;

import com.project.bulletin.persistence.infoCondamnation.InfoConserned;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.Date;
import java.util.List;

public class CondamnationService {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Bulletin");
    private final EntityManager entityManager = emf.createEntityManager();
    private static CondamnationService instance = null;

    public static  CondamnationService getInstance() {
        return instance == null ? instance = new CondamnationService() : null;
    }

    public List<Condamnation> getCondamnationList(){
        Query query = entityManager.createQuery("SELECT c FROM Condamnation c", Condamnation.class);
        return query.getResultList();
    }

    public void createCondamnation(Date dateCondamnation, String coursOuTrubunaux,String natureCrime,
                                   String naturePeine,String observation, InfoConserned infoConserned){
        Condamnation condamnation = new Condamnation();
        condamnation.setDateCondamnation(dateCondamnation);
        condamnation.setCoursOutrubinaux(coursOuTrubunaux);
        condamnation.setNatureCrime(natureCrime);
        condamnation.setObservation(observation);
        condamnation.setNaturePeine(naturePeine);
        condamnation.setInfoConserned(infoConserned);
        entityManager.getTransaction().begin();
        entityManager.persist(condamnation);
        entityManager.getTransaction().commit();
    }
}
