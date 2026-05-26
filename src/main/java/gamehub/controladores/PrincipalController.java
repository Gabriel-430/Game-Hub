package gamehub.controladores;

import javafx.fxml.FXML;
import gamehub.App;
import javafx.application.Platform;

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
    private void actionSalir() {
        Platform.exit();
    }

    private void navegar(String vista) {
        try {
            App.setRoot(vista);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
