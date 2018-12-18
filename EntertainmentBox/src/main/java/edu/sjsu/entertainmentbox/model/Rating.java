package edu.sjsu.entertainmentbox.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Rating {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int ratingId;
	@ManyToOne
    private Movie movie;
	@ManyToOne
    private User user;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date timestamp;
    private int stars;

    public Rating() {
    }

    public Rating(Movie movie, User user, Date timestamp, int stars) {
		super();
		this.movie = movie;
		this.user = user;
		this.timestamp = timestamp;
		this.stars = stars;
	}

	public int getRatingId() {
		return ratingId;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int rating) {
		this.stars = rating;
	}

	
}
