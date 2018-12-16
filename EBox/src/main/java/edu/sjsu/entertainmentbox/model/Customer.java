package edu.sjsu.entertainmentbox.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties({"movies"})
@Entity
public class Customer {

    private String emailAddress;
    private Set<CustomerSubscription> subscription = new HashSet<>();
    private Set<Movie> movies = new HashSet<Movie>(0);
    private Set<Rating> ratings = new HashSet<>();
    private Set<Transaction> transactions = new HashSet<>();
    private Set<MoviePlayLog> moviePlayLogs = new HashSet<>();

    public Customer() {
    }



    public Customer(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Id
    @Column(name = "EMAIL_ID", unique = true, nullable = false)
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
    public Set<CustomerSubscription> getSubscription() {
        return subscription;
    }

    public void setSubscription(Set<CustomerSubscription> subscription) {
        this.subscription = subscription;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "Customer_Movie", joinColumns = {
            @JoinColumn(name = "EMAIL_ID", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "MOVIE_ID", nullable = false, updatable = false)})
    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    public Set<MoviePlayLog> getMoviePlayLogs() {
        return moviePlayLogs;
    }

    public void setMoviePlayLogs(Set<MoviePlayLog> moviePlayLogs) {
        this.moviePlayLogs = moviePlayLogs;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "customer")
    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }
}
