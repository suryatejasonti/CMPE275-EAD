package edu.sjsu.entertainmentbox.dao.impl;

import java.util.List;
import java.util.Optional;

import edu.sjsu.entertainmentbox.dao.VerificationTokenJPARepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.sjsu.entertainmentbox.dao.VerificationTokenRepository;
import edu.sjsu.entertainmentbox.model.User;
import edu.sjsu.entertainmentbox.model.UserRole;
import edu.sjsu.entertainmentbox.model.VerificationToken;

@Repository
public class VerificationTokenRepositoryImpl implements VerificationTokenRepository {

	@Autowired
	VerificationTokenJPARepository verificationTokenJPARepository;
	@Override
	public VerificationToken findByToken(String token) {
		/*Configuration con = new Configuration().configure()
				.addAnnotatedClass(VerificationToken.class)
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(UserRole.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		SessionFactory sf = con.buildSessionFactory(reg);
		Session session = sf.openSession();
		List<VerificationToken> tokens = session.createCriteria(VerificationToken.class).list();
		session.close();
		VerificationToken result = null;
		for (VerificationToken vt: tokens) {
			if (token.equals(vt.getToken())) {
				result = vt;
			}
		}
		return result;*/
		Optional<VerificationToken> optionalVerificationToken = verificationTokenJPARepository.findByToken(token);
		VerificationToken verificationToken = new VerificationToken();
		if(optionalVerificationToken.isPresent())
		{
			verificationToken = optionalVerificationToken.get();
		}
		return verificationToken;
	}

	@Override
	public VerificationToken findByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(VerificationToken myToken) {
		/*Configuration con = new Configuration().configure()
				.addAnnotatedClass(VerificationToken.class)
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(UserRole.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		SessionFactory sf = con.buildSessionFactory(reg);
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(myToken);
		tx.commit();
		session.close();*/
		verificationTokenJPARepository.save(myToken);
	}
}
