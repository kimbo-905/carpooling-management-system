package com.example.javafxtp;

import com.example.javafxtp.Model.*;
import com.example.javafxtp.Repository.JPAUtil;
import com.example.javafxtp.Repository.UtilisateurRepository;
import jakarta.persistence.EntityManager;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class HelloController implements Initializable {
	@FXML
	public Label currentUserLabel;

	//	totaux +++++++++++++++++++++
	@FXML
	private Label totalUtilisateursLabel;
	@FXML
	private Label totalVehiculesLabel;
	@FXML
	private Label totalTrajetsLabel;
	@FXML
	private Label totalReservationsLabel;


	//	consulter reservation +++++++++++++++++
	@FXML
	private TableView<Reservation> upcomingTable;
	@FXML
	private TableColumn<Reservation, String> upcomingDateColumn;
	@FXML
	private TableColumn<Reservation, String> upcomingPassagerColumn;
	@FXML
	private TableColumn<Reservation, String> upcomingTrajetColumn;
	@FXML
	private TableView<Reservation> pastTable;
	@FXML
	private TableColumn<Reservation, String> pastDateColumn;
	@FXML
	private TableColumn<Reservation, String> pastPassagerColumn;
	@FXML
	private TableColumn<Reservation, String> pastTrajetColumn;
	@FXML
	private TableView<Reservation> futureTable;
	@FXML
	private TableColumn<Reservation, String> futureDateColumn;
	@FXML
	private TableColumn<Reservation, String> futurePassagerColumn;
	@FXML
	private TableColumn<Reservation, String> futureTrajetColumn;

//	handle consultation
	@FXML
	private Button consultReservation_btn;
	@FXML
	private AnchorPane consultation_form;
//	fin handling

	@FXML
	public Button logoutButton;
	@FXML
	public AnchorPane utilisateur_form;
	@FXML
	public AnchorPane home_page;
	@FXML

	public Button home_btn;
	@FXML

	public Button addUser_btn;

	//	debut trajet
	@FXML
	public Button btn_add_trajet;
	@FXML
	public Button btn_update_trajet;
	@FXML
	public Button btn_delete_trajet;
	@FXML
	public Button btn_clear_trajet;
	@FXML
	public Label errorLabel_trajet;
	public AnchorPane reservation_form;
	@FXML
	private ComboBox<Utilisateur> cConducteurTrajet;

	@FXML
	private DatePicker cDatedepart;

	@FXML
	private TextField cPlacesdisponibles;

	@FXML
	private TextField cPrix;

	@FXML
	private TextField cVillearrivee;

	@FXML
	private TextField cVilledepart;

	@FXML
	private Button searchButton_trajet;

	@FXML
	private TextField searchField_trajet;
	@FXML
	private TableColumn<Trajet, String> t_arrivee;

	@FXML
	private TableColumn<Trajet, String> t_conducteur;

	@FXML
	private TableColumn<Trajet, String> t_date;

	@FXML
	private TableColumn<Trajet, String> t_depart;

	@FXML
	private TableColumn<Trajet, Long> t_id_trajet;

	@FXML
	private TableColumn<Trajet, Integer> t_places;

	@FXML
	private TableColumn<Trajet, Double> t_prix;

	@FXML
	private TableView<Trajet> table_trajet;
//	end trajet
	
//	vehicule
	public Button addTrajet_btn;
	@FXML

	public Button addVehicule_btn;
	@FXML

	public Button addReservation_btn;
	@FXML

	public AnchorPane vehicule_form;
	@FXML

	public TextField cImmatriculation;
	@FXML

	public TextField cMarque;
	@FXML

	public TextField cModele;
	@FXML

	public Button btn_add_vehicule;
	@FXML

	public Button btn_update_vehicule;
	@FXML

	public Button btn_delete_vehicule;
	@FXML
	public Button btn_clear_vehicule;
	@FXML
	public Label errorLabel_vehicule;

//	chart
	@FXML
	private PieChart earnings_pie_chart;
	@FXML
	public BarChart<String, Number> home_chart;
	@FXML
	private CategoryAxis xAxis;
	@FXML
	private NumberAxis yAxis;
	public AnchorPane trajet_form;
	@FXML
	private TableColumn<Vehicule, String> t_conducteurID;

	@FXML
	private TableColumn<Vehicule, Integer> t_id_vehicule;

	@FXML
	private TableColumn<Vehicule, String> t_immatriculation;

	@FXML
	private TableColumn<Vehicule, String> t_marque;

	@FXML
	private TableColumn<Vehicule, String> t_modele;

	@FXML
	private TableView<Vehicule> table_vehicule;

	@FXML
	private ComboBox<Utilisateur> cConducteur;
//end vehicule


//	debut reservation
@FXML
private Button btn_add_reservation;

	@FXML
	private Button btn_clear_reservation;

	@FXML
	private Button btn_delete_reservation;

	@FXML
	private Button btn_update_reservation;

	@FXML
	private DatePicker cDatereservation;

	@FXML
	private ComboBox<Utilisateur> cPassager;

	@FXML
	private ComboBox<Trajet> cTrajet;

	@FXML
	private TextField cPlacesreservees;

	@FXML
	private TextField cPlacesDisponibles;
	@FXML
	private TableColumn<Reservation, Long> t_id_reservation;

	@FXML
	private TableColumn<Reservation, LocalDate> t_dateReservation;

	@FXML
	private TableColumn<Reservation, String> t_passager;

	@FXML
	private TableColumn<Reservation, Integer> t_placesReservees;

	@FXML
	private TableColumn<Reservation, String> t_trajet;

	@FXML
	private TableView<Reservation> table_reservation;
//	end reservation

	public TextField searchField_vehicule;
	public Button searchButton_vehicule;
	ObservableList<String> listeGender =
			FXCollections.observableArrayList(
					"CONDUCTEUR",
					"PASSAGER"
			);

	private ObservableList<Utilisateur> originalList;
	private UtilisateurRepository userRepository;

	@FXML
	private Button btn_add;

	@FXML
	private Button btn_clear;

	@FXML
	private Button btn_delete;

	@FXML
	private Button btn_update;

	@FXML
	private TextField cEmail;

	@FXML
	private ComboBox<String> cGenre;

	@FXML
	private PasswordField cMdp;
	@FXML
	private Label errorLabel;

	@FXML
	private TextField cNom;

	@FXML
	private TextField cPrenom;

	@FXML
	private TextField cid;

	@FXML
	private Button searchButton;

	@FXML
	private TextField searchField;

	@FXML
	private TableColumn<Utilisateur, String> t_email;

	@FXML
	private TableColumn<Utilisateur, Integer> t_id;

	@FXML
	private TableColumn<Utilisateur, String> t_mdp;

	@FXML
	private TableColumn<Utilisateur, String> t_nom;

	@FXML
	private TableColumn<Utilisateur, String> t_prenom;

	@FXML
	private TableColumn<Utilisateur, String> t_role;

	@FXML
	private TableView<Utilisateur> table;

	@FXML
	private Label type2;
	private List<Utilisateur> utilisateurs;

	@FXML
	private Label welcomeText;
	private double x = 0;
	private double y = 0;
	@FXML
	private AnchorPane main_form;
	public HelloController() throws IOException {
	}
	private UtilisateurRepository utilisateurRepository = new UtilisateurRepository();
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		loadConducteursAndChartData();
		updateDashboardCounts();
//		utilisateur
		userRepository = new UtilisateurRepository();
		cGenre.setItems(listeGender);
		printAll();
		showHomePage();
		table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> populateFields(newValue));

//		vehicule
		cImmatriculation.setTextFormatter(createImmatriculationFormatter());
		t_id_vehicule.setCellValueFactory(new PropertyValueFactory<>("id"));
		t_immatriculation.setCellValueFactory(new PropertyValueFactory<>("immatriculation"));
		t_marque.setCellValueFactory(new PropertyValueFactory<>("marque"));
		t_modele.setCellValueFactory(new PropertyValueFactory<>("modele"));

		t_conducteurID.setCellValueFactory(cellData -> {
			Vehicule vehicule = cellData.getValue();
			Utilisateur conducteur = vehicule.getConducteur();
			if (conducteur != null) {
				return new SimpleStringProperty(conducteur.getNom() + " " + conducteur.getPrenom());
			} else {
				return new SimpleStringProperty("Pas de conducteur");
			}
		});
		loadConducteurs();
		refreshTableVehicule();

//		trajet
		t_id_trajet.setCellValueFactory(new PropertyValueFactory<>("id"));
		t_depart.setCellValueFactory(new PropertyValueFactory<>("villeDepart"));
		t_arrivee.setCellValueFactory(new PropertyValueFactory<>("villeArrivee"));
		t_date.setCellValueFactory(new PropertyValueFactory<>("dateDepart"));
		t_places.setCellValueFactory(new PropertyValueFactory<>("placesDisponibles"));
		t_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
		t_conducteur.setCellValueFactory(cellData -> new SimpleStringProperty(
				cellData.getValue().getConducteur().getNom() + " " + cellData.getValue().getConducteur().getPrenom()));
		loadConducteursTrajet();
		refreshTableTrajet();

//		reservation
		t_id_reservation.setCellValueFactory(new PropertyValueFactory<>("id"));
		t_dateReservation.setCellValueFactory(new PropertyValueFactory<>("dateReservation"));
		t_passager.setCellValueFactory(cellData -> new SimpleStringProperty(
				cellData.getValue().getUtilisateur().getNom() + " " + cellData.getValue().getUtilisateur().getPrenom()
		));
		t_placesReservees.setCellValueFactory(new PropertyValueFactory<>("nbPlacesReservees"));
		t_trajet.setCellValueFactory(cellData -> new SimpleStringProperty(
				cellData.getValue().getTrajet().getVilleDepart() + " -> " + cellData.getValue().getTrajet().getVilleArrivee()
		));
//		to properly display nom and prenom
		cPassager.setConverter(new StringConverter<>() {
			@Override
			public String toString(Utilisateur utilisateur) {
				if (utilisateur == null) {
					return null;
				}
				return utilisateur.getNom() + " " + utilisateur.getPrenom();
			}
			@Override
			public Utilisateur fromString(String string) {
				return null; // Not needed
			}
		});
		cTrajet.setConverter(new StringConverter<>() {
			@Override
			public String toString(Trajet trajet) {
				if (trajet == null) {
					return null;
				}
				return trajet.getVilleDepart() + " -> " + trajet.getVilleArrivee();
			}
			@Override
			public Trajet fromString(String string) {
				return null; // Not needed
			}
		});
		loadUtilisateurs();
		loadTrajets();
		refreshTableReservation();

//		for table consultation
		futureDateColumn.setCellValueFactory(new PropertyValueFactory<>("dateReservation"));
		futurePassagerColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUtilisateur().toString()));
		futureTrajetColumn.setCellValueFactory(cellData -> {
			Trajet trajet = cellData.getValue().getTrajet();
			return new SimpleStringProperty(trajet.getVilleDepart() + " - " + trajet.getVilleArrivee());
		});
		upcomingDateColumn.setCellValueFactory(new PropertyValueFactory<>("dateReservation"));
		upcomingPassagerColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUtilisateur().toString()));
		upcomingTrajetColumn.setCellValueFactory(cellData -> {
			Trajet trajet = cellData.getValue().getTrajet();
			return new SimpleStringProperty(trajet.getVilleDepart() + " - " + trajet.getVilleArrivee());
		});
		pastDateColumn.setCellValueFactory(new PropertyValueFactory<>("dateReservation"));
		pastPassagerColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUtilisateur().toString()));
		pastTrajetColumn.setCellValueFactory(cellData -> {
			Trajet trajet = cellData.getValue().getTrajet();
			return new SimpleStringProperty(trajet.getVilleDepart() + " - " + trajet.getVilleArrivee());
		});
		loadReservations();
	}

//	also for consultation
	private List<Reservation> filterFutureReservations(List<Reservation> reservations) {
		return reservations.stream()
				.filter(reservation -> reservation.getDateReservation().isAfter(LocalDateTime.now().plusDays(30)))
				.collect(Collectors.toList());
	}
	private List<Reservation> filterUpcomingReservations(List<Reservation> reservations) {
		return reservations.stream()
				.filter(reservation -> reservation.getDateReservation().isAfter(LocalDateTime.now()) && reservation.getDateReservation().isBefore(LocalDateTime.now().plusDays(30)))
				.collect(Collectors.toList());
	}
	private List<Reservation> filterPastReservations(List<Reservation> reservations) {
		return reservations.stream()
				.filter(reservation -> reservation.getDateReservation().isBefore(LocalDateTime.now()))
				.collect(Collectors.toList());
	}
	private void loadReservations() {
		List<Reservation> allReservations = utilisateurRepository.getAllReservation();

		ObservableList<Reservation> futureReservations = FXCollections.observableArrayList(filterFutureReservations(allReservations));
		ObservableList<Reservation> upcomingReservations = FXCollections.observableArrayList(filterUpcomingReservations(allReservations));
		ObservableList<Reservation> pastReservations = FXCollections.observableArrayList(filterPastReservations(allReservations));

		futureTable.setItems(futureReservations);
		upcomingTable.setItems(upcomingReservations);
		pastTable.setItems(pastReservations);
	}

	void printAll() {
		ObservableList<Utilisateur> list = userRepository.getAll();
		originalList = FXCollections.observableArrayList(list);

		t_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		t_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		t_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		t_email.setCellValueFactory(new PropertyValueFactory<>("email"));
		t_role.setCellValueFactory(new PropertyValueFactory<>("role"));

		table.setItems(list);
	}
	@FXML
	void addUser(ActionEvent event) {
		String nom = cNom.getText();
		String prenom = cPrenom.getText();
		String email = cEmail.getText();
		String genre = cGenre.getValue();
		if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || genre == null) {
			errorLabel.setText("All fields are required.");
			return;
		}
		if (!isValidEmail(email)) {
			errorLabel.setText("addresse non valide!.");
			return;
		}
		errorLabel.setText("");
		Utilisateur newUtilisateur = new Utilisateur(nom, prenom, email, Utilisateur.Role.valueOf(genre));
		userRepository.insertUser(newUtilisateur);
		printAll();
		clear(event);
		showAlert("Ajouté avec succès.");
	}

	private boolean isValidEmail(String email) {
		return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
	}

	@FXML
	void clear(ActionEvent event) {
		cNom.clear();
		cPrenom.clear();
		cEmail.clear();
		cGenre.getSelectionModel().clearSelection();
	}

	@FXML
	void deleteUser(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Look, a Confirmation Dialog");
		alert.setContentText("Are you ok with this?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			Utilisateur selectedUser = table.getSelectionModel().getSelectedItem();
			if (selectedUser != null) {
				userRepository.deleteUser((long) Math.toIntExact(selectedUser.getId()));
				printAll();
				clear(event);
			} else {
				System.out.println("No user selected");
			}
		}
	}
	@FXML
	void updateUser(ActionEvent event) {
		Utilisateur selectedUser = table.getSelectionModel().getSelectedItem();
		if (selectedUser != null) {
			String newNom = cNom.getText();
			String newPrenom = cPrenom.getText();
			String newEmail = cEmail.getText();
			Utilisateur.Role newRole = Utilisateur.Role.valueOf(cGenre.getValue());
			userRepository.updateUser(Math.toIntExact(selectedUser.getId()), newNom, newPrenom, newEmail, newRole);
			printAll();
			clear(event);
		}
	}
	@FXML
	void charge(javafx.scene.input.MouseEvent event) {
		Utilisateur selectedUser = table.getSelectionModel().getSelectedItem();
		if (selectedUser != null) {
			populateFields(selectedUser);
		}
	}
	private void populateFields(Utilisateur user) {
		if (user != null) {
			cid.setText(String.valueOf(user.getId()));
			cNom.setText(user.getNom());
			cPrenom.setText(user.getPrenom());
			cEmail.setText(user.getEmail());
//			cMdp.setText(user.getMotDePasse());
			cGenre.setValue(user.getRole().toString());
		}
	}
	public void onSelected(ActionEvent actionEvent) {
	}

	@FXML
	private void searchUser() {
		String query = searchField.getText().toLowerCase();
		List<Utilisateur> filteredUsers = userRepository.getAll().stream()
				.filter(utilisateur -> utilisateur.getNom().toLowerCase().contains(query)
						|| utilisateur.getPrenom().toLowerCase().contains(query)
						|| utilisateur.getEmail().toLowerCase().contains(query))
				.collect(Collectors.toList());

		ObservableList<Utilisateur> utilisateurList = FXCollections.observableArrayList(filteredUsers);
		table.setItems(utilisateurList);
	}

	@FXML
	private void handleLogoutButtonAction(ActionEvent actionEvent) throws IOException {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Message");
		alert.setHeaderText(null);
		alert.setContentText("Etes-vous sur de vouloir vous deconnecté?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginPage-view.fxml")));
			Stage stage = new Stage();
			Scene scene = new Scene(root);

			root.setOnMousePressed(event -> {
				x = event.getSceneX();
				y = event.getSceneY();
			});
			root.setOnMouseDragged(event -> {
				stage.setX(event.getScreenX() - x);
				stage.setY(event.getScreenY() - y);
			});

			stage.initStyle(StageStyle.TRANSPARENT);
			stage.setScene(scene);
			stage.show();

			((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
		}
	}

	public void close(ActionEvent actionEvent) {
		System.exit(0);
	}

	public void minimize(ActionEvent actionEvent) {
		Stage stage = (Stage)main_form.getScene().getWindow();
		stage.setIconified(true);
	}


	private void showHomePage() {
		home_page.setVisible(true);
		utilisateur_form.setVisible(false);
		vehicule_form.setVisible(false);
		trajet_form.setVisible(false);
	    reservation_form.setVisible(false);
		consultation_form.setVisible(false);
	}

	//	++++++++++ VEHICULE CONTROLLER ++++++++++
	@FXML
	void addVehicule(ActionEvent event) {
		String immatriculation = cImmatriculation.getText();
		String marque = cMarque.getText();
		String modele = cModele.getText();
		Utilisateur conducteur = cConducteur.getSelectionModel().getSelectedItem();
		if (immatriculation.isEmpty() || marque.isEmpty() || modele.isEmpty() || conducteur == null) {
			// Show an alert if any field is empty
			showAlert("Erreur", "Please fill out all required fields.");
			return;
		}
		Vehicule vehicule = new Vehicule();
		vehicule.setImmatriculation(immatriculation);
		vehicule.setMarque(marque);
		vehicule.setModele(modele);
		vehicule.setConducteur(conducteur);
		utilisateurRepository.insertVehicule(vehicule);
		refreshTableVehicule();
		clearFields();
		showAlert("Ajouté avec succès.");
	}

	// Method to show an alert
	private void showAlert(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	private void clearFields() {
		cImmatriculation.clear();
		cMarque.clear();
		cModele.clear();
		cConducteur.getSelectionModel().clearSelection();
	}
	@FXML
	void updateVehicule(ActionEvent event) {
		Vehicule selectedVehicule = table_vehicule.getSelectionModel().getSelectedItem();
		if (selectedVehicule != null) {
			selectedVehicule.setImmatriculation(cImmatriculation.getText());
			selectedVehicule.setMarque(cMarque.getText());
			selectedVehicule.setModele(cModele.getText());
			selectedVehicule.setConducteur(cConducteur.getSelectionModel().getSelectedItem());
			utilisateurRepository.updateVehicule(selectedVehicule);
			refreshTableVehicule();
			clearFields();
		}
	}
	@FXML
	void deleteVehicule(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Look, a Confirmation Dialog");
		alert.setContentText("Are you ok with this?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			Vehicule selectedVehicule = table_vehicule.getSelectionModel().getSelectedItem();
			if (selectedVehicule != null) {
				utilisateurRepository.deleteVehicule((int) Math.toIntExact(selectedVehicule.getId()));
				printAll();
				clear(event);
			} else {
				System.out.println("No vehicule selected");
			}
		}
		refreshTableVehicule();
	}
	private TextFormatter<String> createImmatriculationFormatter() {
		UnaryOperator<TextFormatter.Change> filter = change -> {
			String text = change.getControlNewText();
			// Regex for the pattern AA-999-AA
			String pattern = "^[A-Z]{0,2}-?\\d{0,3}-?[A-Z]{0,2}$";
			if (text.matches(pattern)) {
				return change;
			} else {
				return null;
			}
		};
		return new TextFormatter<>(filter);
	}
	private void loadConducteurs() {
		List<Utilisateur> conducteurs = utilisateurRepository.findAllConducteurs();
		ObservableList<Utilisateur> conducteurList = FXCollections.observableArrayList(conducteurs);

		// Customize the cell factory to display nom and prenom
		cConducteur.setCellFactory(param -> new ListCell<>() {
			@Override
			protected void updateItem(Utilisateur conducteur, boolean empty) {
				super.updateItem(conducteur, empty);
				setText(empty || conducteur == null ? null : conducteur.getNom() + " " + conducteur.getPrenom());
			}
		});
		// Set the items to the ComboBox
		cConducteur.setItems(conducteurList);
		// Set the default button cell to display selected item's nom and prenom
		cConducteur.setButtonCell(new ListCell<>() {
			@Override
			protected void updateItem(Utilisateur conducteur, boolean empty) {
				super.updateItem(conducteur, empty);
				setText(empty || conducteur == null ? null : conducteur.getNom() + " " + conducteur.getPrenom());
			}
		});
	}
	private void refreshTableVehicule() {
		ObservableList<Vehicule> vehicules = FXCollections.observableArrayList(UtilisateurRepository.getAllVehicule());
		table_vehicule.setItems(vehicules);
	}
	@FXML
	void chargeVehicule(MouseEvent event) {
		Vehicule selectedVehicule = table_vehicule.getSelectionModel().getSelectedItem();
		if (selectedVehicule != null) {
			cImmatriculation.setText(selectedVehicule.getImmatriculation());
			cMarque.setText(selectedVehicule.getMarque());
			cModele.setText(selectedVehicule.getModele());
			cConducteur.setValue(selectedVehicule.getConducteur());
		}
	}
	public void clearFields(ActionEvent actionEvent) {
		cImmatriculation.clear();
		cMarque.clear();
		cModele.clear();
		cConducteur.getSelectionModel().clearSelection();
	}
	@FXML
	private void searchVehicule() {
		String query = searchField_vehicule.getText().toLowerCase();
		List<Vehicule> filteredVehicules = UtilisateurRepository.getAllVehicule().stream()
				.filter(vehicule -> vehicule.getMarque().toLowerCase().contains(query)
						|| vehicule.getModele().toLowerCase().contains(query)
						|| vehicule.getImmatriculation().toLowerCase().contains(query))
				.collect(Collectors.toList());

		ObservableList<Vehicule> vehiculeList = FXCollections.observableArrayList(filteredVehicules);
		table_vehicule.setItems(vehiculeList);
	}


	//	++++++++++ TRAJET CONTROLLER ++++++++++
	private void refreshTableTrajet() {
		ObservableList<Trajet> trajets = FXCollections.observableArrayList(utilisateurRepository.getAllTrajet());
		table_trajet.setItems(trajets);
	}
	private void loadConducteursTrajet() {
		List<Utilisateur> conducteurs = utilisateurRepository.findAllConducteurs();
		ObservableList<Utilisateur> conducteurList = FXCollections.observableArrayList(conducteurs);
		cConducteurTrajet.setItems(conducteurList);
		cConducteurTrajet.setCellFactory(param -> new ListCell<>() {
			@Override
			protected void updateItem(Utilisateur item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty || item == null ? "" : item.getNom() + " " + item.getPrenom());
			}
		});
		cConducteurTrajet.setButtonCell(new ListCell<>() {
			@Override
			protected void updateItem(Utilisateur item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty || item == null ? "" : item.getNom() + " " + item.getPrenom());
			}
		});
	}

	@FXML
	void addTrajet(ActionEvent event) {
	// Validate form fields
	if (cVilledepart.getText().isEmpty() || cVillearrivee.getText().isEmpty() || cDatedepart.getValue() == null ||
			cPlacesdisponibles.getText().isEmpty() || cPrix.getText().isEmpty() ||
			cConducteurTrajet.getSelectionModel().getSelectedItem() == null) {
		showAlert("Please fill all fields.");
		return;
	}

	Trajet trajet = new Trajet();
	trajet.setVilleDepart(cVilledepart.getText());
	trajet.setVilleArrivee(cVillearrivee.getText());
	trajet.setDateDepart(cDatedepart.getValue().atStartOfDay());
	trajet.setPlacesDisponibles(Integer.parseInt(cPlacesdisponibles.getText()));
	trajet.setPrix(Double.parseDouble(cPrix.getText()));
	trajet.setConducteur(cConducteurTrajet.getSelectionModel().getSelectedItem());
	utilisateurRepository.insertTrajet(trajet);
	refreshTableTrajet();
	clearFieldsTrajet(event);
	showAlert("Ajouté avec Succès.");
	}

	@FXML
	void clearFieldsTrajet(ActionEvent event) {
		cVilledepart.clear();
		cVillearrivee.clear();
		cDatedepart.setValue(null);
		cPlacesdisponibles.clear();
		cPrix.clear();
		cConducteurTrajet.getSelectionModel().clearSelection();
	}
	@FXML
	void deleteTrajet(ActionEvent event) {
		Trajet selectedTrajet = table_trajet.getSelectionModel().getSelectedItem();
		if (selectedTrajet != null) {
			utilisateurRepository.deleteTrajet(selectedTrajet.getId());
			refreshTableTrajet();
		}
	}
	@FXML
	void updateTrajet(ActionEvent event) {
		Trajet selectedTrajet = table_trajet.getSelectionModel().getSelectedItem();
		if (selectedTrajet != null) {
			selectedTrajet.setVilleDepart(cVilledepart.getText());
			selectedTrajet.setVilleArrivee(cVillearrivee.getText());
			selectedTrajet.setDateDepart(cDatedepart.getValue().atStartOfDay());
			selectedTrajet.setPlacesDisponibles(Integer.parseInt(cPlacesdisponibles.getText()));
			selectedTrajet.setPrix(Double.parseDouble(cPrix.getText()));
			selectedTrajet.setConducteur(cConducteurTrajet.getSelectionModel().getSelectedItem());

			utilisateurRepository.updateTrajet(selectedTrajet);
			refreshTableTrajet();
		}
	}
	@FXML
	void chargeTrajet(MouseEvent event) {
		Trajet selectedTrajet = table_trajet.getSelectionModel().getSelectedItem();
		if (selectedTrajet != null) {
			cVilledepart.setText(selectedTrajet.getVilleDepart());
			cVillearrivee.setText(selectedTrajet.getVilleArrivee());
			cDatedepart.setValue(selectedTrajet.getDateDepart().toLocalDate());
			cPlacesdisponibles.setText(String.valueOf(selectedTrajet.getPlacesDisponibles()));
			cPrix.setText(String.valueOf(selectedTrajet.getPrix()));
			cConducteurTrajet.setValue(selectedTrajet.getConducteur());
		}
	}
	private String formatDate(LocalDateTime date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return date.format(formatter);
	}
	@FXML
	private void searchTrajet() {
		String query = searchField_trajet.getText().toLowerCase();
		List<Trajet> filteredTrajets = utilisateurRepository.getAllTrajet().stream()
				.filter(trajet -> trajet.getVilleDepart().toLowerCase().contains(query)
						|| trajet.getVilleArrivee().toLowerCase().contains(query)
						|| formatDate(trajet.getDateDepart()).toLowerCase().contains(query)
						|| (trajet.getConducteur().getNom() + " " + trajet.getConducteur().getPrenom()).toLowerCase().contains(query))
				.collect(Collectors.toList());

		ObservableList<Trajet> trajetList = FXCollections.observableArrayList(filteredTrajets);
		table_trajet.setItems(trajetList);
	}

//	++++++++++ RESERVATION CONTROLLER ++++++++++
	@FXML
	void addReservation(ActionEvent event) {
		if (cDatereservation.getValue() == null || cPlacesreservees.getText().isEmpty() ||
				cPassager.getValue() == null || cTrajet.getValue() == null) {
			showAlert("Please fill all fields.");
			return;
		}
		Reservation reservation = new Reservation(
				cDatereservation.getValue().atStartOfDay(),
				Integer.parseInt(cPlacesreservees.getText()),
				cPassager.getValue(),
				cTrajet.getValue()
		);
		utilisateurRepository.insertReservation(reservation);
		refreshTableReservation();
		clearFormReservation();
		showAlert("Ajouté avec succès.");
	}

	@FXML
	void chargeReservation(MouseEvent event) {
		Reservation selectedReservation = table_reservation.getSelectionModel().getSelectedItem();
		if (selectedReservation != null) {
			cDatereservation.setValue(selectedReservation.getDateReservation().toLocalDate());
			cPlacesreservees.setText(String.valueOf(selectedReservation.getNbPlacesReservees()));
			cPassager.setValue(selectedReservation.getUtilisateur());
			cTrajet.setValue(selectedReservation.getTrajet());
		}
	}

	@FXML
	void clearFieldsReservation(ActionEvent event) {
		clearFormReservation();
	}

	@FXML
	void deleteReservation(ActionEvent event) {
		Reservation selectedReservation = table_reservation.getSelectionModel().getSelectedItem();
		if (selectedReservation != null) {
			utilisateurRepository.deleteReservation(selectedReservation.getId());
			refreshTableReservation();
			clearFormReservation();
		} else {
			showAlert("Please choisir une reservation à supprimer.");
		}
	}

	@FXML
	void updateReservation(ActionEvent event) {
		Reservation selectedReservation = table_reservation.getSelectionModel().getSelectedItem();
		if (selectedReservation != null) {
			selectedReservation.setDateReservation(cDatereservation.getValue().atStartOfDay());
			selectedReservation.setNbPlacesReservees(Integer.parseInt(cPlacesreservees.getText()));
			selectedReservation.setUtilisateur(cPassager.getValue());
			selectedReservation.setTrajet(cTrajet.getValue());
			utilisateurRepository.updateReservation(selectedReservation);
			refreshTableReservation();
			clearFormReservation();
		} else {
			showAlert("Please choisissez une reservation à modifier.");
		}
	}


	private void loadUtilisateurs() {
		List<Utilisateur> utilisateurs = utilisateurRepository.findAllPassager();
		ObservableList<Utilisateur> utilisateurList = FXCollections.observableArrayList(utilisateurs);
		cPassager.setCellFactory(param -> new ListCell<>() {
			@Override
			protected void updateItem(Utilisateur utilisateur, boolean empty) {
				super.updateItem(utilisateur, empty);

				if (empty || utilisateur == null) {
					setText(null);
				} else {
					setText(utilisateur.getNom() + " " + utilisateur.getPrenom());
				}
			}
		});
		cPassager.setItems(utilisateurList);
	}

	private void loadTrajets() {
		List<Trajet> trajets = utilisateurRepository.getAllTrajet();
		ObservableList<Trajet> trajetList = FXCollections.observableArrayList(trajets);
		cTrajet.setCellFactory(param -> new ListCell<>() {
			@Override
			protected void updateItem(Trajet trajet, boolean empty) {
				super.updateItem(trajet, empty);
				if (empty || trajet == null) {
					setText(null);
				} else {
					setText(trajet.getVilleDepart() + " -> " + trajet.getVilleArrivee());
				}
			}
		});
		cTrajet.setItems(trajetList);
		cTrajet.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				cPlacesDisponibles.setText(String.valueOf(newValue.getPlacesDisponibles())+ " places");
			} else {
				cPlacesDisponibles.clear();
			}
		});
	}

	private void refreshTableReservation() {
		List<Reservation> reservations = utilisateurRepository.getAllReservation();
		ObservableList<Reservation> reservationList = FXCollections.observableArrayList(reservations);
		table_reservation.setItems(reservationList);

		//		consulter reservation table
		ObservableList<Reservation> upcomingReservations = FXCollections.observableArrayList();
		ObservableList<Reservation> pastReservations = FXCollections.observableArrayList();
		ObservableList<Reservation> futureReservations = FXCollections.observableArrayList();

		LocalDate today = LocalDate.now();

		for (Reservation reservation : reservations) {
			if (reservation.getDateReservation().toLocalDate().isEqual(today)) {
				upcomingReservations.add(reservation);
			} else if (reservation.getDateReservation().toLocalDate().isBefore(today)) {
				pastReservations.add(reservation);
			} else {
				futureReservations.add(reservation);
			}
		}
	}
	private void clearFormReservation() {
		cDatereservation.setValue(null);
		cPlacesreservees.clear();
		cPassager.getSelectionModel().clearSelection();
		cTrajet.getSelectionModel().clearSelection();
	}
	private void showAlert(String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setContentText(message);
		alert.showAndWait();
	}

//	to display the current user
	public class SessionManager {
		@Getter
		@Setter
		private static Utilisateur currentUser;

	}

//	 ++++++++++++++++++++ barchart ++++++++++++++++++++
	private void loadConducteursAndChartData() {
		List<Utilisateur> conducteurs = utilisateurRepository.findAllConducteurs();
		if (conducteurs == null || conducteurs.isEmpty()) {
			System.out.println("No conducteurs found.");
			return;
		}
		Map<Utilisateur, Long> tripsCount = new HashMap<>();
		for (Utilisateur conducteur : conducteurs) {
			Long count = getTripsCountForConducteur(conducteur);
			tripsCount.put(conducteur, count);
		}
		XYChart.Series<String, Number> series = new XYChart.Series<>();
		series.setName("Nombre de trajets par conducteur");
		for (Map.Entry<Utilisateur, Long> entry : tripsCount.entrySet()) {
			String driverName = entry.getKey().getNom();
			Long tripCount = entry.getValue();
			series.getData().add(new XYChart.Data<>(driverName, tripCount));
		}
		home_chart.getData().clear();
		home_chart.getData().add(series);
	}
	private Long getTripsCountForConducteur(Utilisateur conducteur) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		Long count;
		try {
			count = entityManager.createQuery(
							"SELECT COUNT(t) FROM Trajet t WHERE t.conducteur = :conducteur", Long.class)
					.setParameter("conducteur", conducteur)
					.getSingleResult();
		} finally {
			entityManager.close();
		}
		return count;
	}
	private List<Utilisateur> getConducteursFromUtilisateurs(List<Utilisateur> utilisateurs) {
		return utilisateurs.stream()
				.filter(user -> "Conducteur".equals(user.getRole()))
				.collect(Collectors.toList());
	}

//	 ++++++++++++++++++++ piechart ++++++++++++++++++++



	//	totaux user and everything ++++++++++++++++++
	public long getTotalUtilisateurs() {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		long count;
		try {
			count = entityManager.createQuery("SELECT COUNT(u) FROM Utilisateur u", Long.class).getSingleResult();
		} finally {
			entityManager.close();
		}
		return count;
	}
	public long getTotalVehicules() {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		long count;
		try {
			count = entityManager.createQuery("SELECT COUNT(v) FROM Vehicule v", Long.class).getSingleResult();
		} finally {
			entityManager.close();
		}
		return count;
	}
	public long getTotalTrajets() {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		long count;
		try {
			count = entityManager.createQuery("SELECT COUNT(t) FROM Trajet t", Long.class).getSingleResult();
		} finally {
			entityManager.close();
		}
		return count;
	}
	public long getTotalReservations() {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		long count;
		try {
			count = entityManager.createQuery("SELECT COUNT(r) FROM Reservation r", Long.class).getSingleResult();
		} finally {
			entityManager.close();
		}
		return count;
	}
	private void updateDashboardCounts() {
		totalUtilisateursLabel.setText(String.valueOf(getTotalUtilisateurs()));
		totalVehiculesLabel.setText(String.valueOf(getTotalVehicules()));
		totalTrajetsLabel.setText(String.valueOf(getTotalTrajets()));
		totalReservationsLabel.setText(String.valueOf(getTotalReservations()));
	}

	public void switchForm(ActionEvent event){
		if (event.getSource() == home_btn) {
			home_page.setVisible(true);
			utilisateur_form.setVisible(false);
			vehicule_form.setVisible(false);
			trajet_form.setVisible(false);
			reservation_form.setVisible(false);
			consultation_form.setVisible(false);
			home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
			addUser_btn.setStyle("-fx-background-color:transparent");
			addVehicule_btn.setStyle("-fx-background-color:transparent");
			addTrajet_btn.setStyle("-fx-background-color:transparent");
			addReservation_btn.setStyle("-fx-background-color:transparent");
			loadConducteursAndChartData();
			updateDashboardCounts();
		} else if (event.getSource() == addUser_btn) {
			home_page.setVisible(false);
			utilisateur_form.setVisible(true);
			vehicule_form.setVisible(false);
			trajet_form.setVisible(false);
			reservation_form.setVisible(false);
			consultation_form.setVisible(false);
			addUser_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
			home_btn.setStyle("-fx-background-color:transparent");
			addVehicule_btn.setStyle("-fx-background-color:transparent");
			addTrajet_btn.setStyle("-fx-background-color:transparent");
			addReservation_btn.setStyle("-fx-background-color:transparent");
			loadReservations();
			loadUtilisateurs();
			loadTrajets();
			refreshTableReservation();
			loadConducteursTrajet();
			refreshTableTrajet();
			loadConducteurs();
			refreshTableVehicule();
		} else if (event.getSource() == addVehicule_btn) {
			home_page.setVisible(false);
			utilisateur_form.setVisible(false);
			vehicule_form.setVisible(true);
			trajet_form.setVisible(false);
			reservation_form.setVisible(false);
			consultation_form.setVisible(false);
			addVehicule_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
			home_btn.setStyle("-fx-background-color:transparent");
			addUser_btn.setStyle("-fx-background-color:transparent");
			addTrajet_btn.setStyle("-fx-background-color:transparent");
			addReservation_btn.setStyle("-fx-background-color:transparent");
			loadReservations();
			loadUtilisateurs();
			loadTrajets();
			refreshTableReservation();
			loadConducteursTrajet();
			refreshTableTrajet();
			loadConducteurs();
			refreshTableVehicule();
		} else if (event.getSource() == addTrajet_btn) {
			home_page.setVisible(false);
			utilisateur_form.setVisible(false);
			vehicule_form.setVisible(false);
			trajet_form.setVisible(true);
			reservation_form.setVisible(false);
			consultation_form.setVisible(false);
			addTrajet_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
			home_btn.setStyle("-fx-background-color:transparent");
			addUser_btn.setStyle("-fx-background-color:transparent");
			addVehicule_btn.setStyle("-fx-background-color:transparent");
			addReservation_btn.setStyle("-fx-background-color:transparent");
			loadReservations();
			loadUtilisateurs();
			loadTrajets();
			refreshTableReservation();
			loadConducteursTrajet();
			refreshTableTrajet();
			loadConducteurs();
			refreshTableVehicule();
		} else if (event.getSource() == addReservation_btn) {
			home_page.setVisible(false);
			utilisateur_form.setVisible(false);
			vehicule_form.setVisible(false);
			trajet_form.setVisible(false);
			reservation_form.setVisible(true);
			consultation_form.setVisible(false);
			addReservation_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
			home_btn.setStyle("-fx-background-color:transparent");
			addUser_btn.setStyle("-fx-background-color:transparent");
			addVehicule_btn.setStyle("-fx-background-color:transparent");
			addTrajet_btn.setStyle("-fx-background-color:transparent");
			loadReservations();
			loadUtilisateurs();
			loadTrajets();
			refreshTableReservation();
			loadConducteursTrajet();
			refreshTableTrajet();
			loadConducteurs();
			refreshTableVehicule();
		} else if (event.getSource() == consultReservation_btn) {
			home_page.setVisible(false);
			utilisateur_form.setVisible(false);
			vehicule_form.setVisible(false);
			trajet_form.setVisible(false);
			reservation_form.setVisible(false);
			consultation_form.setVisible(true);
			consultReservation_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
			home_btn.setStyle("-fx-background-color:transparent");
			addUser_btn.setStyle("-fx-background-color:transparent");
			addVehicule_btn.setStyle("-fx-background-color:transparent");
			addTrajet_btn.setStyle("-fx-background-color:transparent");
			addReservation_btn.setStyle("-fx-background-color:transparent");
			loadReservations();
			loadUtilisateurs();
			loadTrajets();
			refreshTableReservation();
			loadConducteursTrajet();
			refreshTableTrajet();
			loadConducteurs();
			refreshTableVehicule();
		}
	}

}

