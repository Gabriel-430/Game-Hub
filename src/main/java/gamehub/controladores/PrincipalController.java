package gamehub.controladores;

import gamehub.App;
import gamehub.utils.JavaFXUtils;
import javafx.application.Platform;
import javafx.fxml.FXML;

public class PrincipalController {

    @FXML
    private void actionRegistrar() {
        App.setMetadato("videojuego_editar", null);
        navegar("registrarVideojuego");
    }

    @FXML
    private void actionListar() {
        navegar("listarVideojuego");
    }

    @FXML
    private void actionBuscar() {
        navegar("buscarVideojuego");
    }

    @FXML
    private void actionCompras() {
        navegar("compras");
    }

    @FXML
    private void actionSuscripciones() {
        navegar("suscripciones");
    }

    @FXML
    private void actionReportes() {
        navegar("reportes");
    }

    @FXML
    private void actionSalir() {
        Platform.exit();
    }

    private void navegar(String vista) {
        try {
            App.setRoot(vista);
        } catch (Exception e) {
            JavaFXUtils.mostrarAdvertencia("Módulo en Construcció",
                    "La vista '" + vista + ".fxml' aún no ha sido implementada.");
        }
    }
}