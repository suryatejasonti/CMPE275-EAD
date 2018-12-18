package edu.sjsu.entertainmentbox.service;

import java.text.ParseException;

import edu.sjsu.entertainmentbox.model.Movie;
import edu.sjsu.entertainmentbox.model.User;
import edu.sjsu.entertainmentbox.model.VerificationToken;

public interface UserService {
//	public void saveUser(User user);
	public User getUser(String emailAddress);
	public void saveUser(String emailAddress, String firstName, String lastName, String password);
	public void saveUserAndRole(String emailAddress, String firstName, String lastName, String password, boolean enabled);
	public void createVerificationToken(User user, String token);
	public void saveRegisteredUser(User user);
	public VerificationToken getVerificationToken(String token);
	public void saveLog(String emailAddress, Movie mve) throws ParseException;
}
