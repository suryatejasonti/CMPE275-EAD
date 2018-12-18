package edu.sjsu.entertainmentbox.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Movie {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int movieId;
	private String title;
	private Genre genre;
	private int year;
	private String studio;
	private String synopsis;
	private String image;
	private String movie;
	private String actors;
	private String director;
	private String country;
	private MPAARating mpaaRating;
	private MovieAvailability availability;
	private int price;
	
	public Movie(){	
	}
	
	public Movie(String title, Genre genre, int year, String studio, String synopsis, String image, String movie,
			String actors, String director, String country, MPAARating mpaaRating, MovieAvailability availability,
			int price) {
		super();
		this.title = title;
		this.genre = genre;
		this.year = year;
		this.studio = studio;
		this.synopsis = synopsis;
		this.image = image;
		this.movie = movie;
		this.actors = actors;
		this.director = director;
		this.country = country;
		this.mpaaRating = mpaaRating;
		this.availability = availability;
		this.price = price;
	}

	public int getMovieId() {
		return movieId;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getStudio() {
		return studio;
	}
	public void setStudio(String studio) {
		this.studio = studio;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getMovie() {
		return movie;
	}
	public void setMovie(String movie) {
		this.movie = movie;
	}
	public String getActors() {
		return actors;
	}
	public void setActors(String actors) {
		this.actors = actors;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public MPAARating getMpaaRating() {
		return mpaaRating;
	}
	public void setMpaaRating(MPAARating mpaaRating) {
		this.mpaaRating = mpaaRating;
	}
	public MovieAvailability getAvailability() {
		return availability;
	}
	public void setAvailability(MovieAvailability availability) {
		this.availability = availability;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", title=" + title + ", genre=" + genre + ", year=" + year + ", studio="
				+ studio + ", synopsis=" + synopsis + ", image=" + image + ", movie=" + movie + ", actors=" + actors
				+ ", director=" + director + ", country=" + country + ", rating=" + mpaaRating + ", availability="
				+ availability + ", price=" + price + "]";
	}
}
