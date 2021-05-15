package com.sid.cinema.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Film {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private double duree;
    private String realisateur;
    private String description;
    private String photo;
    private Date dateSortie;
    @ManyToOne
    private Categorie categorie;
    @OneToMany(mappedBy = "film")
    private Collection<ProjectionFilm> projections;
}
