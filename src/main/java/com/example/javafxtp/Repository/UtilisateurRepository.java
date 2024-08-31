package com.example.javafxtp.Repository;
import com.example.javafxtp.Model.*;
import jakarta.persistence.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Builder;

import java.util.List;

@Builder
public class UtilisateurRepository {
	public void insertUser(Utilisateur utilisateur) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

//		save
		entityManager.persist(utilisateur);
		entityManager.getTransaction().commit();
		entityManager.close();
	}


	public void deleteUser(Long userId) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();

			// Find the user
			Utilisateur user = em.find(Utilisateur.class, userId);
			if (user != null) {
				// Delete related reservations
				List<Trajet> trajets = em.createQuery("SELECT t FROM Trajet t WHERE t.conducteur.id = :userId", Trajet.class)
						.setParameter("userId", userId)
						.getResultList();
				for (Trajet trajet : trajets) {
					em.createQuery("DELETE FROM Reservation r WHERE r.trajet.id = :trajetId")
							.setParameter("trajetId", trajet.getId())
							.executeUpdate();
				}

				// Delete related trajets
				em.createQuery("DELETE FROM Trajet t WHERE t.conducteur.id = :userId")
						.setParameter("userId", userId)
						.executeUpdate();

				// Delete related vehicules
				em.createQuery("DELETE FROM Vehicule v WHERE v.conducteur.id = :userId")
						.setParameter("userId", userId)
						.executeUpdate();

				// Finally, delete the user
				em.remove(user);
			}

			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		} finally {
			em.close();
		}
	}

	private EntityManager getEntityManager() {
		return JPAUtil.getEntityManagerFactory().createEntityManager();
	}

	public Utilisateur findUserById(int id) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();

		Utilisateur utilisateur = entityManager.find(Utilisateur.class, id);

		entityManager.getTransaction().commit();
		entityManager.close();

		return utilisateur;
	}

	public void updateUser(int id, String newNom, String newPrenom, String newEmail, Utilisateur.Role newRole) {
		EntityManager entityManager = null;
		try {
			entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
			entityManager.getTransaction().begin();

			Utilisateur utilisateur = entityManager.find(Utilisateur.class, id);
			if (utilisateur != null) {
				if (newNom != null) {
					utilisateur.setNom(newNom);
				}
				if (newPrenom != null) {
					utilisateur.setPrenom(newPrenom);
				}
				if (newEmail != null) {
					utilisateur.setEmail(newEmail);
				}
				if (newRole != null) {
					utilisateur.setRole(newRole);
				}
				entityManager.getTransaction().commit();
			} else {
				System.out.println("Utilisateur with id " + id + " not found.");
			}
		} catch (Exception e) {
			if (entityManager != null && entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
	}

	public static ObservableList<Utilisateur> getAll() {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		List<Utilisateur> utilisateurs;
		try {
			utilisateurs = entityManager.createQuery("SELECT u FROM Utilisateur u", Utilisateur.class).getResultList();
		} finally {
			entityManager.close();
		}
		return FXCollections.observableArrayList(utilisateurs);
	}

	public UtilisateurRepository() {
	}

	public List<Utilisateur> findAllConducteurs() {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		List<Utilisateur> conducteurs;
		try {
			conducteurs = entityManager.createQuery(
							"SELECT u FROM Utilisateur u WHERE u.role = :role", Utilisateur.class)
					.setParameter("role", Utilisateur.Role.CONDUCTEUR)
					.getResultList();
		} finally {
			entityManager.close();
		}
		return conducteurs;
	}

	public List<Utilisateur> findAllPassager() {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		List<Utilisateur> passagers;
		try {
			passagers = entityManager.createQuery(
							"SELECT p FROM Utilisateur p WHERE p.role = :role", Utilisateur.class)
					.setParameter("role", Utilisateur.Role.PASSAGER)
					.getResultList();
		} finally {
			entityManager.close();
		}
		return passagers;
	}


//	+++++++++++++++++ VEHICULE REPOSITORY ++++++++++++++++++++

	public void insertVehicule(Vehicule vehicule) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(vehicule);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public void deleteVehicule(int id) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		Vehicule vehicule = entityManager.find(Vehicule.class, id);
		entityManager.remove(vehicule);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public void updateVehicule(Vehicule vehicule) {
	EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(vehicule);
		entityManager.getTransaction().
	commit();
		entityManager.close();

}
	public static ObservableList<Vehicule> getAllVehicule() {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		List<Vehicule> vehicules;
		try {
			vehicules = entityManager.createQuery("SELECT v FROM Vehicule v", Vehicule.class).getResultList();
		} finally {
			entityManager.close();
		}
		return FXCollections.observableArrayList(vehicules);
	}


	//	+++++++++++++++++ TRAJET REPOSITORY ++++++++++++++++++++

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


	//	+++++++++++++++++ RESERVATION REPOSITORY ++++++++++++++++++++
	public void insertReservation(Reservation reservation) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.persist(reservation);
			Trajet trajet = reservation.getTrajet();
			trajet.setPlacesDisponibles(trajet.getPlacesDisponibles() - reservation.getNbPlacesReservees());
			entityManager.merge(trajet);
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}


	public void deleteReservation(Long id){
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		Reservation reservation = entityManager.find(Reservation.class, id);
		entityManager.remove(reservation);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public Reservation findReservationById(Long id){
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		Reservation reservation = entityManager.find(Reservation.class, id);
		entityManager.getTransaction().commit();
		entityManager.close();
		return reservation;
	}

	public void updateReservation(Reservation reservation){
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(reservation);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public ObservableList<Reservation> getAllReservation() {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		List<Reservation> reservations;
		try {
			reservations = entityManager.createQuery("SELECT r FROM Reservation r", Reservation.class).getResultList();
		} finally {
			entityManager.close();
		}
		return FXCollections.observableArrayList(reservations);
	}

}
