package edu.sjsu.entertainmentbox.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.sjsu.entertainmentbox.dao.UserDao;
import edu.sjsu.entertainmentbox.dao.VerificationTokenRepository;
import edu.sjsu.entertainmentbox.model.User;
import edu.sjsu.entertainmentbox.model.UserRole;
import edu.sjsu.entertainmentbox.model.VerificationToken;
import edu.sjsu.entertainmentbox.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Autowired
    private VerificationTokenRepository tokenRepository;

	@Transactional
	public void saveUserAndRole(String emailAddress, String firstName, String lastName, String password, boolean enabled) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		User user = new User(emailAddress, firstName, lastName, hashedPassword, enabled);

		UserRole userRole = new UserRole();
		if (emailAddress.endsWith("sjsu.edu")) {
			userRole.setRole("ROLE_ADMIN");
			userRole.setUser(user);
		} else {
			userRole.setRole("ROLE_USER");
			userRole.setUser(user);
		}

		userDao.saveUserAndRole(userRole, user);
	}

	@Transactional
	public User getUser(String emailAddress) {
		return userDao.getUser(emailAddress);
	}



	@Transactional
	public void saveUser(String emailAddress, String firstName, String lastName, String password) {
		// TODO Auto-generated method stub
		User user= new User();
		user.setEmailAddress(emailAddress);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPassword(password);
		userDao.saveUser(user);
	}

	@Override
	public void createVerificationToken(User user, String token) {
		VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
	}

	@Override
	public void saveRegisteredUser(User user) {
		userDao.saveUser(user);
	}

	@Override
	public VerificationToken getVerificationToken(String token) {
		return tokenRepository.findByToken(token);
	}
}
