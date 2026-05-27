package gamehub.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBD {

    protected Connection connection;

    protected void conectar() throws SQLException {

        try (InputStream input = ConexionBD.class
                .getResourceAsStream("/config/database.properties")) {

            if (input == null) {
                throw new SQLException("No se encontró database.properties");
            }

            Properties prop = new Properties();
            prop.load(input);

            connection = DriverManager.getConnection(
                    prop.getProperty("db.url"),
                    prop.getProperty("db.user"),
                    prop.getProperty("db.password"));

            System.out.println("Conexión exitosa");

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SQLException("Error al conectar", ex);
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
