package com.example.javafxtp.Repository;
import com.example.javafxtp.Model.Utilisateur;
import com.example.javafxtp.Model.Vehicule;
import jakarta.persistence.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Builder;

import java.util.List;

@Builder
public class VehiculeRepository {
	public void insertVehicule(Vehicule vehicule){
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(vehicule);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	public void deleteVehicule(int id){
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		Vehicule vehicule = entityManager.find(Vehicule.class, id);
		entityManager.remove(vehicule);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	public Vehicule findVehiculeById(int id){
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		Vehicule vehicule = entityManager.find(Vehicule.class, id);
		entityManager.getTransaction().commit();
		entityManager.close();
		return vehicule;
	}
	public void updateVehicule(Vehicule vehicule) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		Vehicule managedVehicule = entityManager.merge(vehicule);
		managedVehicule.setMarque("Updated marque");
		managedVehicule.setModele("Updated modele");
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public static ObservableList<Vehicule> getAll() {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		List<Vehicule> vehicules;
		try {
			vehicules = entityManager.createQuery("SELECT v FROM Vehicule v", Vehicule.class).getResultList();
		} finally {
			entityManager.close();
		}
		return FXCollections.observableArrayList(vehicules);
	}

	public VehiculeRepository() {
	}
}
