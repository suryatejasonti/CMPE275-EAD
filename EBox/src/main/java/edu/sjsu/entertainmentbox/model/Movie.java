package edu.sjsu.entertainmentbox.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@JsonSerialize
@Entity
@Indexed
public class Movie {


	private Integer movieId;
	private String title;
	private String genre;
	private Integer year;
	private String studio;
	private String synopsis;
	private String image;
	private String movieurl;
	private String MPAARating;
	@JsonIgnore
	private Set<Customer> customers = new HashSet<Customer>(0);
	@JsonIgnore
	private Set<CustomerSubscription> customerSubscriptions = new HashSet<CustomerSubscription>(0);
	private String directorName;
	private String actors;
	private String country;
	@JsonIgnore
	private Set<Rating> ratings = new HashSet<>(0);
	@JsonIgnore
	private Set<MoviePlayLog> moviePlayLogs = new HashSet<>(0);
	private MovieAvailability availability;
	private Integer price;

	public Movie() {
	}


	public Movie(String title, String genre, Integer year, String studio, String synopsis, String image, String movieurl, String MPAARating, String actors, String directorName, String country, MovieAvailability availability, Integer price) {
		this.title = title;
		this.genre = genre;
		this.year = year;
		this.studio = studio;
		this.synopsis = synopsis;
		this.image = image;
		this.movieurl = movieurl;
		this.actors = actors;
		this.directorName = directorName;
		this.country = country;
		this.MPAARating = MPAARating;
		this.availability = availability;
		this.price = price;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MOVIE_ID", nullable = false, unique = true)
	public Integer getMovieId() {
		return movieId;
	}

	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

	@Column(name = "TITLE", unique = true)
	@Field
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "GENRE")
	@Field
	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Column(name = "YEAR")
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Column(name = "STUDIO")
	public String getStudio() {
		return studio;
	}

	public void setStudio(String studio) {
		this.studio = studio;
	}

	@Column(name = "SYNOPSIS", length = 2000)
	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	@Column(name = "IMAGE")
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name="MOVIEURL")
	public String getMovieurl() {
		return movieurl;
	}

	public void setMovieurl(String movieurl) {
		this.movieurl = movieurl;
	}


	@Column(name = "DIRECTORNAME")
	public String getDirectorName() {
		return directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	@Column(name = "COUNTRY")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
	@JsonIgnore
	public Set<Rating> getRatings() {
		return ratings;
	}
	@JsonProperty
	public void setRatings(Set<Rating> ratings) {
		this.ratings = ratings;
	}

	@Column(name = "PRICE")
	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Column(name = "MPAARATING")
	public String getMPAARating() {
		return MPAARating;
	}

	public void setMPAARating(String MPAARating) {
		this.MPAARating = MPAARating;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
	@JsonIgnore
	public Set<CustomerSubscription> getCustomerSubscriptions() {
		return customerSubscriptions;
	}
	@JsonProperty
	public void setCustomerSubscriptions(Set<CustomerSubscription> customerSubscriptions) {
		this.customerSubscriptions = customerSubscriptions;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "movies")
	@JsonIgnore
	public Set<Customer> getCustomers() {
		return customers;
	}
	@JsonProperty
	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

	@Column(name = "ACTORS")
	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}
	@Column(name = "AVAILABILITY")
	public MovieAvailability getAvailability() {
		return availability;
	}

	public void setAvailability(MovieAvailability availability) {
		this.availability = availability;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
	public Set<MoviePlayLog> getMoviePlayLogs() {
		return moviePlayLogs;
	}

	public void setMoviePlayLogs(Set<MoviePlayLog> moviePlayLogs) {
		this.moviePlayLogs = moviePlayLogs;
	}

	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", title=" + title + ", genre=" + genre + ", year=" + year + ", studio="
				+ studio + ", synopsis=" + synopsis + ", image=" + image + ", movie=" + movieurl + ", actors=" + actors
				+ ", director=" + directorName + ", country=" + country  + ", availability="
				+ availability + ", price=" + price + "]";
	}
}
