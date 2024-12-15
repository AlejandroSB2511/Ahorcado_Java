package ahorcado;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ControladorRegistro {
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private TextField tf3;
	@FXML
	AnchorPane pane8;
	@FXML
	private PasswordField tf4;
	@FXML
	private PasswordField tf5;
	@FXML
	private Button btnsalir;

	public void salirPrograma() {
		Platform.exit();
		System.exit(0);
	}

	public void changeScene(ActionEvent event) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("Login.fxml"));
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setTitle("LOGIN");
		window.setScene(new Scene(parent, 268, 296));
		window.show();
	}

	public void guardarUsuario() {
		
	    String nombreUsuario = tf3.getText();
	    String contrasena = tf4.getText();
	    if (nombreUsuario.trim().isEmpty() || contrasena.trim().isEmpty()) {
	    	 Alert alert6 = new Alert(AlertType.INFORMATION);
	        	alert6.setTitle("Registro");
	            alert6.setHeaderText(null);
	            alert6.setContentText("El nombre de usuario o la contraseña están vacíos.");
	            alert6.showAndWait();
	   
	    } else {
	    	try (FileWriter fileWriter = new FileWriter("usuarios.txt", true);
	   	         BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
	   	        String linea = nombreUsuario + "," + contrasena;
	   	        bufferedWriter.write(linea);
	   	        bufferedWriter.newLine();
	   	        Alert alert4 = new Alert(AlertType.INFORMATION);
	           	alert4.setTitle("Registro");
	               alert4.setHeaderText(null);
	               alert4.setContentText("Usuario registrado correctamente");
	               alert4.showAndWait();
	               
	   	    } catch (IOException e) {
	   	    	 Alert alert5 = new Alert(AlertType.INFORMATION);
	   	        	alert5.setTitle("Registro");
	   	            alert5.setHeaderText(null);
	   	            alert5.setContentText("Error al guardar los datos de usuario: " + e.getMessage());
	   	            alert5.showAndWait();
	   	       
	   	    }
	    }
	    	  
	}

}
