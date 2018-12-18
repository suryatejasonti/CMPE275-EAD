package edu.sjsu.entertainmentbox.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.sjsu.entertainmentbox.dao.UserDao;
import edu.sjsu.entertainmentbox.dao.VerificationTokenRepository;
import edu.sjsu.entertainmentbox.model.Movie;
import edu.sjsu.entertainmentbox.model.MoviePlayLog;
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

	@Override
	public void saveLog(String emailAddress, Movie mve) throws ParseException {
		User user = this.getUser(emailAddress);

		TimeZone.setDefault(TimeZone.getTimeZone("PST"));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		String stDate = format.format( calendar.getTime() );
		Date startDate = format.parse(stDate);
		MoviePlayLog log = new MoviePlayLog(user, null, mve, startDate, null);
		userDao.saveLog(log);
	}
}
