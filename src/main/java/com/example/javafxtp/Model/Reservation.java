package com.example.javafxtp.Model;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "reservations")
@NoArgsConstructor
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "date_reservation")
	private LocalDateTime dateReservation;

	@Column(name = "nb_places_reservees")
	private int nbPlacesReservees;

	@ManyToOne
	@JoinColumn(name = "utilisateur_id")
	private Utilisateur utilisateur;

	@ManyToOne
	@JoinColumn(name = "trajet_id")
	private Trajet trajet;

	public Reservation(LocalDateTime dateReservation, int nbPlacesReservees, Utilisateur utilisateur, Trajet trajet) {
		this.dateReservation = dateReservation;
		this.nbPlacesReservees = nbPlacesReservees;
		this.utilisateur = utilisateur;
		this.trajet = trajet;
	}
}
