package com.sid.cinema.dao;

import com.sid.cinema.entities.Categorie;
import com.sid.cinema.entities.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}
