package com.sid.cinema.entities;


import javax.persistence.*;

@Entity
public class Ticket {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomClient;
    private double prix;
    @Column(unique = true)
    private int codePayement;
    private boolean resrvee;
    @ManyToOne
    private Place place;
    @ManyToOne
    private ProjectionFilm projection;
}
