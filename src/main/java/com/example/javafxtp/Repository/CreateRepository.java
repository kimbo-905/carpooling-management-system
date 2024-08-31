package com.example.javafxtp.Repository;

import com.example.javafxtp.Model.Admin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class CreateRepository {
	private EntityManager entityManager;

	public CreateRepository() {
		entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	}

	public boolean createAccount(Admin utilisateur) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.persist(utilisateur);
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
			return false;
		}
	}
}
