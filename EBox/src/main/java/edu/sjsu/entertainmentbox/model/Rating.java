package edu.sjsu.entertainmentbox.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@JsonIgnoreProperties({"movie"})
@Entity
public class Rating {

    private Integer ratingId;
    //private Integer customerId;
    @JsonIgnore
    private Movie movie;
    private Double rating;
    private Date ratingTS;
    private String review;
    @JsonIgnore
    private Customer customer;

    public Rating() {
    }

    public Rating(Customer customer, Movie movie, Double rating, Date ratingTS, String review) {
        this.customer = customer;
        this.movie = movie;
        this.rating = rating;
        this.ratingTS = ratingTS;
        this.review = review;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RATING_ID", nullable = false, unique = true)
    public Integer getRatingId() {
        return ratingId;
    }

    public void setRatingId(Integer ratingId) {
        this.ratingId = ratingId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOVIE_ID", nullable = false)
    @JsonIgnore
    public Movie getMovie() {
        return movie;
    }
    @JsonProperty
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Column(name = "RATING")
    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "RATING_TS")
    public Date getRatingTS() {
        return ratingTS;
    }

    public void setRatingTS(Date ratingTS) {
        this.ratingTS = ratingTS;
    }


    @Column(name = "REVIEW", length = 500)
    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMAIL_ID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    public Customer getCustomer() {
        return customer;
    }
    @JsonProperty
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
