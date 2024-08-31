package com.example.javafxtp;

import com.example.javafxtp.Repository.JPAUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {

//	test
	private double x = 0;
	private double y = 0;
	@Override
	public void start(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("LoginPage-view.fxml")));
		Scene scene = new Scene(root);
		root.setOnMousePressed((event) -> {
			this.x = event.getSceneX();
			this.y = event.getSceneY();
		});
		root.setOnMouseDragged((event) -> {
			stage.setX(event.getScreenX() - this.x);
			stage.setY(event.getScreenY() - this.y);
			stage.setOpacity(0.8);
		});
		root.setOnMouseReleased((event) -> {
			stage.setOpacity(1.0);
		});
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.setScene(scene);
		stage.show();
		JPAUtil.getEntityManagerFactory().createEntityManager();
	}
	public static void main(String[] args) {
		launch();
	}
}