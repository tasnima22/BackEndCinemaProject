package com.qa.cinemaproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.qa.cinemaproject.domain.Cinema;
import com.qa.cinemaproject.repo.CinemaRepo;

@Service
public class CinemaService implements ServiceIF<Cinema>{
	
	private CinemaRepo repo;

	@Autowired
	public CinemaService(CinemaRepo repo) {
		super();
		this.repo=repo;
	}
	
	// CREATE
		public Cinema create(Cinema c) {
			Cinema created = this.repo.save(c);  
			return created;
		}

		// READ
		public List<Cinema> getAll() {
			return this.repo.findAll();
		}

		public Cinema getOne(Integer id) {
			Optional<Cinema> found = this.repo.findById(id);
			return found.get(); 
			
		}
		
		
		public List<Cinema> getAllbyCinema(String cinema) {
			List<Cinema> found = this.repo.findByCinemaIgnoreCase(cinema);
			return found;
		}
		
		public List<Cinema> getByFilm(String film) {
			List<Cinema> found = this.repo.findByFilmIgnoreCase(film);
			return found;
		}
		
		public List<Cinema> getCinemaByCost(Double cost) {
			List<Cinema> found = this.repo.findByCost(cost);
			return found;
		}

		// UPDATE
		public Cinema replace(Integer id, Cinema newCinema) {
			Cinema existing = this.repo.findById(id).get();
			existing.setCinema(newCinema.getCinema());
			existing.setFilm(newCinema.getFilm());
			existing.setCost(newCinema.getCost());
			Cinema updated = this.repo.save(existing);
			return updated;
		}

		// DELETE
		public void remove(@PathVariable Integer id) {
			this.repo.deleteById(id); //DELETE FROM Cinema WHERE id= 
		}

}
