package gamehub.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBD {
    
    protected Connection connection;

    protected void conectar() throws SQLException {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config/database.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                throw new SQLException("No se encontró config/database.properties");
            }
            prop.load(input);
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                prop.getProperty("db.url"), 
                prop.getProperty("db.user"), 
                prop.getProperty("db.password")
            );
        } catch (Exception ex) {
            throw new SQLException("Error al establecer la conexión: " + ex.getMessage());
        }
    }

    protected void desconectar() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar conexión: " + e.getMessage());
        }
    }
}
