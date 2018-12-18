package edu.sjsu.entertainmentbox.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.stereotype.Repository;

import edu.sjsu.entertainmentbox.dao.UserDao;
import edu.sjsu.entertainmentbox.model.Movie;
import edu.sjsu.entertainmentbox.model.MoviePlayLog;
import edu.sjsu.entertainmentbox.model.User;
import edu.sjsu.entertainmentbox.model.UserRole;
import edu.sjsu.entertainmentbox.model.VerificationToken;

@Repository
public class UserDaoImpl implements UserDao {
/*
	@Override
	public void saveUser(User user) {
		Configuration con = new Configuration().configure()
				.addAnnotatedClass(User.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		SessionFactory sf = con.buildSessionFactory(reg);
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		if (user != null) {
			try {
				session.saveOrUpdate(user);
				tx.commit();
				session.close();
			} catch (Exception e) {
				tx.rollback();
				session.close();
				e.printStackTrace();
			}
		}
	}*/
	
	
	@Override
	public void saveUser(User user) {
		Configuration con = new Configuration().configure()
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(UserRole.class)
				.addAnnotatedClass(VerificationToken.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		SessionFactory sf = con.buildSessionFactory(reg);
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(user);
		tx.commit();
		session.close();
	}

	@Override
	public User getUser(String emailAddress) {
		List<User> users = new ArrayList<User>();
		Configuration con = new Configuration().configure()
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(UserRole.class)
				.addAnnotatedClass(VerificationToken.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		SessionFactory sf = con.buildSessionFactory(reg);
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		users = session
			.createQuery("from User where username=?")
			.setParameter(0, emailAddress)
			.list();

		User result = null;
		if (users.size() > 0) {
			result = users.get(0);
		}
		tx.commit();
		return result;
	}

	@Override
	public void saveUserAndRole(UserRole userRole, User user) {
		Configuration con = new Configuration().configure()
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(UserRole.class)
				.addAnnotatedClass(VerificationToken.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		SessionFactory sf = con.buildSessionFactory(reg);
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(user);
		session.saveOrUpdate(userRole);
		tx.commit();
		session.close();
	}

	@Override
	public void saveLog(MoviePlayLog log) {
		Configuration con = new Configuration().configure()
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(UserRole.class)
				.addAnnotatedClass(VerificationToken.class)
				.addAnnotatedClass(UserRole.class)
				.addAnnotatedClass(Movie.class)
				.addAnnotatedClass(MoviePlayLog.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		SessionFactory sf = con.buildSessionFactory(reg);
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(log);
		tx.commit();
		session.close();
	}

}
