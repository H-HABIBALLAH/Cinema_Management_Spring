package com.sid.cinema.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class ProjectionFilm {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private Date dateProjection;
    private double prix;
    @OneToMany(mappedBy = "projection")
    private Collection<Ticket> tickets;
    @ManyToOne
    private Seance seance;
    @ManyToOne
    private Film film;
    @ManyToOne
    private Salle salle;
}
