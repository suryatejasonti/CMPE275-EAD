package edu.sjsu.entertainmentbox.model;

public class MovieInformation {
	
	   private String movieTitle;
	   private String movieLink;
	   private String disabled;
	   MovieInformation() {
		   
	   }
	   
	public MovieInformation(String movieTitle, String movieLink, String disabled) {
		super();
		this.movieTitle = movieTitle;
		this.movieLink = movieLink;
		this.disabled = disabled;
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
	


}
