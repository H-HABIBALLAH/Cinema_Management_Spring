package com.sid.cinema;

import com.sid.cinema.service.CinemaInitServiceImpl;
import com.sid.cinema.service.ICinemaInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CinemaApplication implements CommandLineRunner {

    @Autowired
    private ICinemaInitService cinemaInitService;

    public static void main(String[] args) {
        SpringApplication.run(CinemaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        cinemaInitService.initVilles();
        cinemaInitService.initCinemas();
        cinemaInitService.initSalles();
        cinemaInitService.initPlaces();
        cinemaInitService.initCategories();
        cinemaInitService.initFilms();
        cinemaInitService.initTickets();
        cinemaInitService.initSeances();
        cinemaInitService.initProjections();
    }
}
