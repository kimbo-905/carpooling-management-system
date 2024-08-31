package com.example.javafxtp;

import com.example.javafxtp.Model.Admin;
import com.example.javafxtp.Repository.LoginRepository;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

import javafx.stage.StageStyle;

public class LoginController {

	@FXML
	private TextField emailField;

	@FXML
	private PasswordField passwordField;

	// TextField for showing password
	@FXML
	private TextField passwordTextField;

	@FXML
	private CheckBox showPasswordCheckBox;

	@FXML
	private Label errorMessage;

	private double x = 0;
	private double y = 0;
	private LoginRepository loginRepository = new LoginRepository();

	@FXML
	private void initialize() {
		// Initialize the CheckBox state and listener
		showPasswordCheckBox.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
			if (isNowSelected) {
				// Show the password as plain text
				passwordTextField.setText(passwordField.getText());
				passwordTextField.setVisible(true);
				passwordField.setVisible(false);
			} else {
				// Hide the plain text and show the password field
				passwordField.setText(passwordTextField.getText());
				passwordField.setVisible(true);
				passwordTextField.setVisible(false);
			}
		});
	}

	@FXML
	private void handleLoginButtonAction(ActionEvent event) throws IOException {
		String email = emailField.getText();
		String password = showPasswordCheckBox.isSelected() ? passwordTextField.getText() : passwordField.getText();
		if (email.isEmpty() || password.isEmpty()) {
			errorMessage.setText("Please enter email and password.");
		} else if (!isValidEmail(email)) {
			errorMessage.setText("Invalid email format.");
		} else if (!isValidPassword(password)) {
			errorMessage.setText("Password must be at least 8 characters long.");
		} else if (authenticate(email, password)) {
			redirectToUtilisateurView();
		} else {
			errorMessage.setText("Invalid email or password.");
		}
	}

	private boolean authenticate(String email, String password) {
		Admin admin = loginRepository.findByEmailAndPassword(email, password);
		return admin != null;
	}

	private void redirectToUtilisateurView() throws IOException {
		Parent root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("dashboard.fxml")));
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

	public void handleCreateAccountButtonAction(ActionEvent actionEvent) throws IOException {
		Parent root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("RegistrationPage-view.fxml")));
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

	public void handleCloseButton(ActionEvent event) {
		Stage stage = (Stage) emailField.getScene().getWindow();
		stage.close();
	}
	private boolean isValidEmail(String email) {
		String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
		return email != null && email.matches(emailRegex);
	}
	private boolean isValidPassword(String password) {
		// Example: Password must be at least 8 characters long
		return password != null && password.length() >= 8;
	}


}
