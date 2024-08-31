//package com.example.javafxtp.Repository;
//
//import com.example.javafxtp.Model.Reservation;
//import com.example.javafxtp.Model.Trajet;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityTransaction;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//
//import java.util.List;
//
//public class ReservationRepository {
//
//	public void insertReservation(Reservation reservation) {
//		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
//		EntityTransaction transaction = entityManager.getTransaction();
//		try {
//			transaction.begin();
//			entityManager.persist(reservation);
//			Trajet trajet = reservation.getTrajet();
//			trajet.setPlacesDisponibles(trajet.getPlacesDisponibles() - reservation.getNbPlacesReservees());
//			entityManager.merge(trajet);
//			transaction.commit();
//		} catch (Exception e) {
//			if (transaction.isActive()) {
//				transaction.rollback();
//			}
//			e.printStackTrace();
//		} finally {
//			entityManager.close();
//		}
//	}
//
//
//	public void deleteReservation(Long id){
//		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
//		entityManager.getTransaction().begin();
//		Reservation reservation = entityManager.find(Reservation.class, id);
//		entityManager.remove(reservation);
//		entityManager.getTransaction().commit();
//		entityManager.close();
//	}
//
//	public Reservation findReservationById(Long id){
//		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
//		entityManager.getTransaction().begin();
//		Reservation reservation = entityManager.find(Reservation.class, id);
//		entityManager.getTransaction().commit();
//		entityManager.close();
//		return reservation;
//	}
//
//	public void updateReservation(Reservation reservation){
//		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
//		entityManager.getTransaction().begin();
//		entityManager.merge(reservation);
//		entityManager.getTransaction().commit();
//		entityManager.close();
//	}
//
//	public ObservableList<Reservation> getAllReservation() {
//		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
//		List<Reservation> reservations;
//		try {
//			reservations = entityManager.createQuery("SELECT r FROM Reservation r", Reservation.class).getResultList();
//		} finally {
//			entityManager.close();
//		}
//		return FXCollections.observableArrayList(reservations);
//	}
//}
