package gamehub.utils;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import gamehub.App;

public class JavaFXUtils {

    public static void mostrarMensaje(String titulo, String mensaje) {
        crearAlerta(AlertType.INFORMATION, titulo, mensaje);
    }

    public static void mostrarAdvertencia(String titulo, String mensaje) {
        crearAlerta(AlertType.WARNING, titulo, mensaje);
    }

    public static void mostrarError(String titulo, String mensaje) {
        crearAlerta(AlertType.ERROR, titulo, mensaje);
    }

    public static boolean mostrarConfirmacion(String titulo, String mensaje) {
        Alert alerta = new Alert(AlertType.CONFIRMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.getDialogPane().getStylesheets().add(App.class.getResource("/css/estilo.css").toExternalForm());
        Optional<ButtonType> resultado = alerta.showAndWait();
        return resultado.isPresent() && resultado.get() == ButtonType.OK;
    }

    private static void crearAlerta(AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.getDialogPane().getStylesheets().add(App.class.getResource("/css/estilo.css").toExternalForm());
        alerta.showAndWait();
    }
}
