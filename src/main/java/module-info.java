module com.example.javafxtp {
	requires javafx.controls;
	requires javafx.fxml;
	requires jakarta.persistence;
	requires static lombok;
	requires org.hibernate.orm.core;
	requires fontawesomefx;

	opens com.example.javafxtp.Model;
	opens com.example.javafxtp to javafx.fxml;
	exports com.example.javafxtp;
}