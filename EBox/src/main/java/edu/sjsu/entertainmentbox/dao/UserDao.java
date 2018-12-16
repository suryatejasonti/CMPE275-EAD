package edu.sjsu.entertainmentbox.dao;

import edu.sjsu.entertainmentbox.model.User;
import edu.sjsu.entertainmentbox.model.UserRole;

public interface UserDao {	
	public User getUser(String emailAddress);
	public void saveUser(User user);
	public void saveUserAndRole(UserRole userRole, User user);
}
