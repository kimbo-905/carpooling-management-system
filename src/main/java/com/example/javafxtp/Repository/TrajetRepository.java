package com.example.javafxtp.Repository;

import com.example.javafxtp.Model.Trajet;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class TrajetRepository {

	public void insertTrajet(Trajet trajet){
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(trajet);
		entityTransaction.commit();
		entityManager.close();
	}

	public void deleteTrajet(Long id){
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		Trajet trajet = entityManager.find(Trajet.class, id);
		entityManager.remove(trajet);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	public Trajet findTrajetById(Long id){
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		Trajet trajet = entityManager.find(Trajet.class, id);
		entityManager.getTransaction().commit();
		entityManager.close();
		return trajet;
	}

	public void updateTrajet(Trajet trajet){
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(trajet);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public ObservableList<Trajet> getAllTrajet() {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		List<Trajet> trajets;
		try {
			trajets = entityManager.createQuery("SELECT t FROM Trajet t", Trajet.class).getResultList();
		} finally {
			entityManager.close();
		}
		return FXCollections.observableArrayList(trajets);
	}
	public List<Object[]> getTripsPerMonthByDriver(Long id) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		String query = "SELECT MONTH(t.date), COUNT(t.id) FROM Trajet t WHERE t.conducteur.id = :driverId GROUP BY MONTH(t.date)";
		return entityManager.createQuery(query)
				.setParameter("driverId", id)
				.getResultList();
	}
	public List<Object[]> getEarningsPerMonthByDriver(Long driverId) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		String query = "SELECT MONTH(r.dateReservation), SUM(r.nbPlacesReservees * t.price) " +
				"FROM Reservation r JOIN r.trajet t " +
				"WHERE t.conducteur.id = :driverId " +
				"GROUP BY MONTH(r.dateReservation)";
		return entityManager.createQuery(query)
				.setParameter("driverId", driverId)
				.getResultList();
	}

}
