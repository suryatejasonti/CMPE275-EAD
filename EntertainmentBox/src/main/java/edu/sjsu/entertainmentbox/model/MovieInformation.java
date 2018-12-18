package edu.sjsu.entertainmentbox.model;

public class MovieInformation {
	
	   private int movieId;
	   private String movieTitle;
	   private String movieLink;
	   private String disabled;
	   private String note;
	   private String enable;
	   private double stars;
	   private int viewCount;
	   MovieInformation() {
		   
	   }
	   
	public MovieInformation(int movieId, String movieTitle, String movieLink, String disabled, String note, String enable) {
		super();
		this.movieId = movieId;
		this.movieTitle = movieTitle;
		this.movieLink = movieLink;
		this.disabled = disabled;
		this.note = note;
		this.enable = enable;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getMovieTitle() {
		return movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	public String getMovieLink() {
		return movieLink;
	}
	public void setMovieLink(String movieLink) {
		this.movieLink = movieLink;
	}
	public String getDisabled() {
		return disabled;
	}
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}

	public double getStars() {
		return stars;
	}

	public void setStars(double stars) {
		this.stars = stars;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	
}
