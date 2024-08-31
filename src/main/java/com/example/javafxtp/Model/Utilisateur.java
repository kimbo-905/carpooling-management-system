package com.example.javafxtp.Model;

import jakarta.persistence.*;

import javafx.beans.binding.BooleanExpression;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "utilisateurs")
@NoArgsConstructor
public class Utilisateur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "nom")
	private String nom;

	@Column(name = "prenom")
	private String prenom;

	@Column(name = "email")
	private String email;

	@OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Reservation> reservations;

	@OneToMany(mappedBy = "conducteur", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Vehicule> vehicules;

	@OneToMany(mappedBy = "conducteur", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Trajet> trajets;

	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private Role role;

	public Utilisateur(String nom, String prenom, String email, Role role) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.role = role;
	}
	public enum Role{
		CONDUCTEUR,
		PASSAGER
	}
	@Override
	public String toString() {
		return nom + " " + prenom;
	}

}
