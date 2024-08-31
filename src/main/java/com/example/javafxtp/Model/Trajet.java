package com.example.javafxtp.Model;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "trajets")
@NoArgsConstructor
public class Trajet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "ville_depart")
	private String villeDepart;

	@Column(name = "ville_arrivee")
	private String villeArrivee;

	@Column(name = "date_depart")
	private LocalDateTime dateDepart;

	@Column(name = "places_disponibles")
	private int placesDisponibles;

	@Column(name = "prix")
	private double prix;

	@ManyToOne
	@JoinColumn(name = "conducteur_id")
	private Utilisateur conducteur;

	public Trajet(String villeDepart, String villeArrivee, LocalDateTime dateDepart, int placesDisponibles, double prix, Utilisateur conducteur) {
		this.villeDepart = villeDepart;
		this.villeArrivee = villeArrivee;
		this.dateDepart = dateDepart;
		this.placesDisponibles = placesDisponibles;
		this.prix = prix;
		this.conducteur = conducteur;
	}
}
