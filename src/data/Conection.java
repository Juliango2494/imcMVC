package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conection {
	private static Conection instancia; // la instancia única
	private Connection conn;
	
	private String nombreBd = "java_conection";
	private String usuario = "root";
	private String password = "";
	private String url = "jdbc:mysql://localhost:3306/" + nombreBd +
		"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

	// Constructor privado para evitar que se instancie desde fuera
	private Conection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, usuario, password);
			System.out.println("Conexión inicializada.");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error al inicializar la conexión: " + e.getMessage());
		}
	}

	// Método público y estático para obtener la instancia única
	public static Conection getInstance() {
		if (instancia == null) {
			instancia = new Conection();
		}
		return instancia;
	}

	// Obtener la conexión
	public Connection getConnection() {
		return conn;
	}

	// Cerrar la conexión
	public void desconectar() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
				conn = null;
				instancia = null; // permite reinicializar si se llama después
				System.out.println("Conexión cerrada.");
			}
		} catch (SQLException e) {
			System.out.println("Error al cerrar la conexión: " + e.getMessage());
		}
	}
}

