package com.project.bulletin.persistence.condamnation;

import com.project.bulletin.persistence.infoCondamnation.InfoConserned;
import jakarta.persistence.*;

import java.util.Date;

@Entity @Table(name = "condamnation")
public class Condamnation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCondamnation;
    private Date  dateCondamnation;
    private String coursOutrubinaux;
    private String natureCrime;
    private String naturePeine;
    private String observation;

    @ManyToOne
    @JoinColumn(name = "idConserned")
    private InfoConserned infoConserned;

    public InfoConserned getInfoConserned() {
        return infoConserned;
    }

    public void setInfoConserned(InfoConserned infoConserned) {
        this.infoConserned = infoConserned;
    }

    public Condamnation(){
        this(null,"unknown","unknown","unkown","unkown",null);
    }

    public Condamnation(Date dateCondamnation, String coursOutrubinaux, String natureCrime, String naturePeine, String observation,InfoConserned infoConserned) {
        this.dateCondamnation = dateCondamnation;
        this.coursOutrubinaux = coursOutrubinaux;
        this.natureCrime = natureCrime;
        this.naturePeine = naturePeine;
        this.observation = observation;
        this.infoConserned = infoConserned;
    }

    public int getIdCondamnation() {
        return idCondamnation;
    }

    public Date getDateCondamnation() {
        return dateCondamnation;
    }

    public void setDateCondamnation(Date dateCondamnation) {
        this.dateCondamnation = dateCondamnation;
    }

    public String getCoursOutrubinaux() {
        return coursOutrubinaux;
    }

    public void setCoursOutrubinaux(String coursOutrubinaux) {
        this.coursOutrubinaux = coursOutrubinaux;
    }

    public String getNatureCrime() {
        return natureCrime;
    }

    public void setNatureCrime(String natureCrime) {
        this.natureCrime = natureCrime;
    }

    public String getNaturePeine() {
        return naturePeine;
    }

    public void setNaturePeine(String naturePeine) {
        this.naturePeine = naturePeine;
    }

    public String getObservation() {
        return observation;
    }
    public void setObservation(String observation) {
        this.observation = observation;
    }

    @Override
    public String toString() {
        String teste = infoConserned == null ? "null" : infoConserned.getNom();
        return "Condamnation{" +
                "idCondamnation=" + idCondamnation +
                ", dateCondamnation=" + dateCondamnation +
                ", coursOutrubinaux='" + coursOutrubinaux + '\'' +
                ", natureCrime='" + natureCrime + '\'' +
                ", naturePeine='" + naturePeine + '\'' +
                ", observation='" + observation + '\'' +
                ", infoConserned=" +  teste +
                '}';
    }
}
