package com.example.javafxtp.Model;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@Table(name = "Admin")
@NoArgsConstructor
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true, name = "email")
	private String email;

	@Column(nullable = false,name="password")
	private String password;

	public Admin(String email, String password) {
		this.email = email;
		this.password = password;
	}
}
