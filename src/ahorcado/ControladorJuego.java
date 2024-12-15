package ahorcado;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ControladorJuego {
	@FXML
	TextField tf10;
	@FXML
	TextField tf11;
	@FXML
	AnchorPane pane5;

	@FXML
	Pane pane6;
	@FXML
	TextField input;
	@FXML
	Button btnc;
	@FXML
	TextArea txtarea1;
	@FXML
	Button btnvolver;
	@FXML
	ImageView img;
	private List<TextField> letrasTextField;
	@FXML
	Button btngn;

	String[] palabras = { "pájaro", "playa", "juegos", "semáforo", "instituto", "yoga", "profesor", "leopardo",
			"bicicleta", "salmon", "cuervo", "avispa", "águila", "tren", "barco", "ingeniero", "banco", "fútbol" };
	int intentos = 7;
	Random random = new Random();
	int indice = random.nextInt(palabras.length);
	String palabra = palabras[indice];

	public void initialize() {
		tf10.setText(String.valueOf(intentos));
		txtarea1.setText("Pista: La palabra tiene " + palabra.length() + " letras");
		tf11.setEditable(false);
		// Crea y muestra los TextField correspondientes a las letras de la palabra
		letrasTextField = new ArrayList<>();
		for (int i = 0; i < palabra.length(); i++) {
			TextField textField = new TextField();
			textField.setLayoutX(247.0 + i * 25.0);
			textField.setLayoutY(327.0);
			textField.setPrefHeight(25.0);
			textField.setPrefWidth(25.0);
			textField.setEditable(false);
			letrasTextField.add(textField);

			// Agrega los nuevos TextField al AnchorPane en el archivo FXML
			((AnchorPane) tf11.getParent()).getChildren().add(textField);
		}
	}

	Image imagenIntentos1 = new Image("file:///C:/Users/srale/PROYECTO/ProyectoAhorcado/img/1.png");
	Image imagenIntentos2 = new Image("file:///C:/Users/srale/PROYECTO/ProyectoAhorcado/img/2.png");
	Image imagenIntentos3 = new Image("file:///C:/Users/srale/PROYECTO/ProyectoAhorcado/img/3.png");
	Image imagenIntentos4 = new Image("file:///C:/Users/srale/PROYECTO/ProyectoAhorcado/img/4.png");
	Image imagenIntentos5 = new Image("file:///C:/Users/srale/PROYECTO/ProyectoAhorcado/img/5.png");
	Image imagenIntentos6 = new Image("file:///C:/Users/srale/PROYECTO/ProyectoAhorcado/img/6.png");
	Image imagenIntentos7 = new Image("file:///C:/Users/srale/PROYECTO/ProyectoAhorcado/img/7.png");
	Image imagenIntentos8 = new Image("file:///C:/Users/srale/PROYECTO/ProyectoAhorcado/img/8.png");

	public void setImage() {
		if (intentos == 7) {
			img.setImage(imagenIntentos1);
		} else if (intentos == 6) {
			img.setImage(imagenIntentos2);
		} else if (intentos == 5) {
			img.setImage(imagenIntentos3);
		} else if (intentos == 4) {
			img.setImage(imagenIntentos4);
		} else if (intentos == 3) {
			img.setImage(imagenIntentos5);
		} else if (intentos == 2) {
			img.setImage(imagenIntentos6);
		} else if (intentos == 1) {
			img.setImage(imagenIntentos7);
		} else if (intentos == 0) {
			img.setImage(imagenIntentos8);
		}

	}

	public void volverAJugar() {
		intentos = 7;

		Random random = new Random();
		indice = random.nextInt(palabras.length);
		palabra = palabras[indice];

		for (TextField textField : letrasTextField) {
			((AnchorPane) tf11.getParent()).getChildren().remove(textField);
		}
		letrasTextField.clear();
		((AnchorPane) tf11.getParent()).getChildren().removeAll(letrasTextField);
		for (int i = 0; i < palabra.length(); i++) {
			TextField textField = new TextField();
			textField.setLayoutX(247.0 + i * 25.0);
			textField.setLayoutY(327.0);
			textField.setPrefHeight(25.0);
			textField.setPrefWidth(25.0);
			textField.setEditable(false);
			tf11.setEditable(false);
			letrasTextField.add(textField);
			((AnchorPane) tf11.getParent()).getChildren().add(textField);
		}

		img.setImage(imagenIntentos1);
		txtarea1.setText("Pista: La palabra tiene " + palabra.length() + " letras");
	}

	public void comprobarLetra() {
		String letra = input.getText().toLowerCase();

		boolean acierto = false;

		if (palabra.contains(letra)) {

			acierto = true;
			for (int i = 0; i < palabra.length(); i++) {
				String letraPalabra = palabra.substring(i, i + 1);
				if (letraPalabra.equals(letra)) {
					TextField textField = letrasTextField.get(i);
					textField.setText(letraPalabra);
				}
			}
		}

		input.clear();

		if (acierto) {

			boolean palabraCompleta = true;
			for (TextField textField : letrasTextField) {
				if (textField.getText().isEmpty()) {
					palabraCompleta = false;
					break;
				}
			}

			if (palabraCompleta) {

				Alert alert2 = new Alert(AlertType.INFORMATION);
				alert2.setTitle("¡Has ganado!");
				alert2.setHeaderText(null);
				alert2.setContentText("¡Has acertado la palabra secreta!");
				alert2.showAndWait();

			}
		} else {

			intentos--;
			tf10.setText(String.valueOf(intentos));
			setImage();
			if (intentos == 0) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("¡Has perdido!");
				alert.setHeaderText(null);
				alert.setContentText("¡Has perdido! La palabra era: " + palabra);
				alert.showAndWait();

			}
		}
	}

}
