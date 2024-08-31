package com.example.javafxtp;

import com.example.javafxtp.Model.Admin;
import com.example.javafxtp.Repository.CreateRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.application.Platform;

import java.io.IOException;
import java.util.Objects;

public class RegistrationController {
	@FXML
	public TextField prenom;
	@FXML
	public TextField nom;
	public Label errorMessage;

	@FXML
	private TextField emailField;

	@FXML
	private PasswordField passwordField;
	private double x = 0;
	private double y = 0;
	private CreateRepository createRepository = new CreateRepository();

	@FXML
	public void handleCreateAccountButtonAction(ActionEvent event) throws IOException {
		String email = emailField.getText();
		String password = passwordField.getText();
		String name = nom.getText();
		String surname = prenom.getText();
		if (email.isEmpty() || password.isEmpty() || name.isEmpty() || surname.isEmpty()) {
			errorMessage.setText("Please fill in all fields.");
		} else if (!isValidEmail(email)) {
			errorMessage.setText("Invalid email format.");
		} else if (!isValidPassword(password)) {
			errorMessage.setText("Password must be at least 8 characters long.");
		} else {
			Admin newUser = new Admin();
			newUser.setEmail(email);
			newUser.setPassword(password);
			if (createRepository.createAccount(newUser)) {
				errorMessage.setText("Account created successfully.");
				redirectToLoginView(event);
			} else {
				errorMessage.setText("Account creation failed.");
			}
		}
	}

	private boolean isValidEmail(String email) {
		String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
		return email != null && email.matches(emailRegex);
	}

	private boolean isValidPassword(String password) {
		// Example: Password must be at least 8 characters long
		return password != null && password.length() >= 8;
	}
	private void redirectToLoginView(ActionEvent actionEvent) throws IOException {
		Parent root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("LoginPage-view.fxml")));
		Stage stage = new Stage();
		Scene scene = new Scene(root);
		root.setOnMousePressed((event) -> {
			this.x = event.getSceneX();
			this.y = event.getSceneY();
		});
		root.setOnMouseDragged((event) -> {
			stage.setX(event.getScreenX() - this.x);
			stage.setY(event.getScreenY() - this.y);
		});
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.setScene(scene);
		stage.show();
	}

	public void handleCancelButtonAction(ActionEvent event) throws IOException {
		Stage stage = (Stage) emailField.getScene().getWindow();
		stage.close();
	}
}
