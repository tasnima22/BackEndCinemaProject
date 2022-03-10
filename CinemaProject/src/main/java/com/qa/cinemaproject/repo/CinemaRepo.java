package com.qa.cinemaproject.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.cinemaproject.domain.Cinema;

@Repository
public interface CinemaRepo extends JpaRepository<Cinema, Integer>{

	List<Cinema> findByCinemaIgnoreCase(String cinema);
	
	List<Cinema> findByFilmIgnoreCase(String film);
	
	List<Cinema> findByCost(Double cost);
}
