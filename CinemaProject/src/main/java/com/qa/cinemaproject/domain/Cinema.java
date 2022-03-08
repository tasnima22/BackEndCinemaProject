package com.qa.cinemaproject.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cinema {
	
	@Id //PRIMARY KEY
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO INCREMENT
	private Integer id;
	
	@Column(nullable = false)
	private String cinema;
	private String film;
	private Double cost;
	
	
	public Cinema() {
		super();
	}

	public Cinema(Integer id, String cinema, String film, Double cost) {
		super();
		this.id = id;
		this.cinema = cinema;
		this.film = film;
		this.cost = cost;
		
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCinema() {
		return cinema;
	}

	public void setCinema(String cinema) {
		this.cinema = cinema;
	}

	public String getFilm() {
		return film;
	}

	public void setFilm(String film) {
		this.film = film;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "Cinema [id=" + id + ", cinema=" + cinema + ", film=" + film + ", cost=" + cost + "]";
	}
	

	
}
