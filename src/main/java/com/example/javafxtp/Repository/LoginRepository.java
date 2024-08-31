package com.example.javafxtp.Repository;

import com.example.javafxtp.Model.Admin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class LoginRepository {
	private EntityManager entityManager;

	public LoginRepository() {
		entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	}

	public Admin findByEmailAndPassword(String email, String password) {
		try {
			TypedQuery<Admin> query = entityManager.createQuery(
					"SELECT a FROM Admin a WHERE a.email = :email AND a.password = :password", Admin.class);
			query.setParameter("email", email);
			query.setParameter("password", password);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}


}
