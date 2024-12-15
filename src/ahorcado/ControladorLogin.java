package ahorcado;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class ControladorLogin {
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private TextField tf1;
	@FXML
	private PasswordField tf2;
	@FXML
	private Button btnsalir;
	@FXML
	private Button btnlogn;
	@FXML
	private Button btnreg;
	@FXML
	private TextField tf3;
	@FXML
	private PasswordField tf4;
	@FXML
	AnchorPane pane7;
	public void salirPrograma() {
		Platform.exit();
		System.exit(0);
	}



	public boolean comprobarCredenciales(String usuarioIngresado, String contrasenaIngresada) {
		  if (usuarioIngresado.trim().isEmpty() || contrasenaIngresada.trim().isEmpty()) {
		    	 Alert alert6 = new Alert(AlertType.INFORMATION);
		        	alert6.setTitle("Usuario o contraseña vacios");
		            alert6.setHeaderText(null);
		            alert6.setContentText("El nombre de usuario o la contraseña están vacíos.");
		            alert6.showAndWait();
		   
		    } else {
		    	try {
			        FileReader fileReader = new FileReader("usuarios.txt");
			        BufferedReader bufferedReader = new BufferedReader(fileReader);
			        String line;

			        while ((line = bufferedReader.readLine()) != null) {
			            String[] parts = line.split(",");
			            String usuario = parts[0].trim();
			            String contrasena = parts[1].trim();

			            if (usuario.equals(usuarioIngresado) && contrasena.equals(contrasenaIngresada)) {
			                bufferedReader.close();
			                return true;
			            }
			        }

			        bufferedReader.close();
			    } catch (IOException e) {
			        e.printStackTrace();
			    }

			    
		    }
	    return false;
	}
	
	
	public void iniciarSesion(ActionEvent event) throws IOException {
	    String usuarioIngresado = tf1.getText();
	    String contrasenaIngresada = tf2.getText();

	    if (comprobarCredenciales(usuarioIngresado, contrasenaIngresada)) {
	    	Parent parent = FXMLLoader.load(getClass().getResource("Ahorcado.fxml"));
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setTitle("Ahorcado");
			window.setScene(new Scene(parent, 600, 400));
			window.show();
	    } else {
	    	Alert alert3 = new Alert(AlertType.INFORMATION);
        	alert3.setTitle("Credencias Inválidas");
            alert3.setHeaderText(null);
            alert3.setContentText("Las credenciales que has proporcionado no son válidas, vuelve a intentarlo de nuevo");
            alert3.showAndWait();
	    }
	}
	public void changeScene(ActionEvent event) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("Registro2.fxml"));
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setTitle("REGISTRO");
		window.setScene(new Scene(parent, 268, 296));
		window.show();
	}

}

