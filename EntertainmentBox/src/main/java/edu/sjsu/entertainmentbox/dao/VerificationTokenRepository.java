package edu.sjsu.entertainmentbox.dao;

import edu.sjsu.entertainmentbox.model.User;
import edu.sjsu.entertainmentbox.model.VerificationToken;

public interface VerificationTokenRepository {
	VerificationToken findByToken(String token);
    VerificationToken findByUser(User user);
	void save(VerificationToken myToken);
}
