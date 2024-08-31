package com.example.javafxtp.Model;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "vehicules")
@NoArgsConstructor
public class Vehicule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "marque")
	private String marque;

	@Column(name = "modele")
	private String modele;

	@Column(name = "immatriculation")
	private String immatriculation;

	@ManyToOne
	@JoinColumn(name = "conducteur_id")
	private Utilisateur conducteur;

	public Vehicule(String marque, String modele, String immatriculation, Utilisateur conducteur) {
		this.marque = marque;
		this.modele = modele;
		this.immatriculation = immatriculation;
		this.conducteur = conducteur;
	}

}
