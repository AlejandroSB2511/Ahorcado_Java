package ahorcado;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Jugador implements Serializable {
    private String nombreUsuario;
    private String contrasena;

    public Jugador(String nombreUsuario, String contrasena) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
    }

	public Object getContrasena() {
		
		return contrasena;
	}

	public Object getUsuario() {
		
		return nombreUsuario;
	}
	

	
}
