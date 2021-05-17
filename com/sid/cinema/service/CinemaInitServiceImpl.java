package com.sid.cinema.service;

import com.sid.cinema.dao.*;
import com.sid.cinema.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;


@Service
public class CinemaInitServiceImpl implements ICinemaInitService {

    @Autowired
    VilleRepository villeRepository;

    @Autowired
    CinemaRepository cinemaRepository;

    @Autowired
    SalleRepository salleRepository;

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    CategorieRepository categorieRepository;

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    SeanceRepository seanceRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ProjectionRepository projectionRepository;


    @Override
    public void initVilles() {
        Stream.of("Casablanca", "Agadir", "Marrakech", "Oujda", "Tanger", "Rabat").
                forEach(villeName -> {
                    Ville ville = new Ville();
                    ville.setName(villeName);
                    villeRepository.save(ville);
                });
    }

    @Override
    public void initCinemas() {
        villeRepository.findAll().forEach(ville -> {
            Stream.of("MegaRama", "IMAX", "FOUNOUN", "CHAHRAZAD", "DAOULIZ").
                    forEach(cinemaName -> {
                        Cinema cinema = new Cinema();
                        cinema.setName(cinemaName);
                        cinema.setVille(ville);
                        cinema.setNombreSalles(3 + (int) (Math.random() * 7));
                        cinemaRepository.save(cinema);
                    });
        });
    }

    @Override
    public void initSalles() {
        cinemaRepository.findAll().forEach(cinema -> {
            for (int i = 0; i < cinema.getNombreSalles(); i++) {
                Salle salle = new Salle();
                salle.setName("Salle N : " + (i + 1));
                salle.setCinema(cinema);
                salle.setNombrePlaces(20 + (int) (Math.random() * 10));
                salleRepository.save(salle);
            }
        });
    }

    @Override
    public void initPlaces() {
        salleRepository.findAll().forEach(salle -> {
            for (int i = 0; i < salle.getNombrePlaces(); i++) {
                Place place = new Place();
                place.setNumero(i + 1);
                place.setSalle(salle);
                placeRepository.save(place);
            }
        });
    }

    @Override
    public void initSeances() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Stream.of("12:00", "15:00", "18:00", "20:00", "21:00").
                forEach(s -> {
                    Seance seance = new Seance();
                    try {
                        seance.setHeureDebut(dateFormat.parse(s));
                        seanceRepository.save(seance);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                });
    }

    @Override
    public void initCategories() {
        Stream.of("Aventure", "Policier", "Histoire", "Action").
                forEach(categorieName -> {
                    Categorie categorie = new Categorie();
                    categorie.setName(categorieName);
                    categorie.setFilms(filmRepository.findAll());
                    categorieRepository.save(categorie);
                });
    }

    @Override
    public void initFilms() {
        List<Categorie> categoriesList = categorieRepository.findAll();
        double duree[] = new double[]{1, 1.5, 2, 2.5, 3, 3.5};
        Stream.of("Interstellar", "Inception", "Don't breath", "Avengers : Infinity War", "Avengers : End Game", "Hangover").
                forEach(filmName -> {
                    Film film = new Film();
                    film.setTitre(filmName);
                    film.setCategorie(categoriesList.get(new Random().nextInt(categoriesList.size())));
                    film.setDuree(duree[new Random().nextInt(duree.length)]);
                    film.setPhoto(filmName.replaceAll(" ", "") + ".jpg");
                    filmRepository.save(film);
                });
    }

    @Override
    public void initProjections() {
        double[] prices = new double[]{30, 50, 60, 70, 90, 100};
        villeRepository.findAll().forEach(ville -> {
            ville.getCinemas().forEach(cinema -> {
                cinema.getSalles().forEach(salle -> {
                    filmRepository.findAll().forEach(film -> {
                        seanceRepository.findAll().forEach(seance -> {
                            ProjectionFilm projection = new ProjectionFilm();
                            projection.setDateProjection(new Date());
                            projection.setFilm(film);
                            projection.setPrix(prices[new Random().nextInt(prices.length)]);
                            projection.setSalle(salle);
                            projection.setSeance(seance);
                            projectionRepository.save(projection);
                        });
                    });
                });
            });
        });
    }

    @Override
    public void initTickets() {
        projectionRepository.findAll().forEach(projectionFilm -> {
            placeRepository.findAll().forEach(place -> {
               Ticket ticket = new Ticket();
               ticket.setPlace(place);
               ticket.setProjection(projectionFilm);
               ticket.setPrix(projectionFilm.getPrix());
               ticket.setResrvee(false);
               ticketRepository.save(ticket);
            });
        });
    }
}
