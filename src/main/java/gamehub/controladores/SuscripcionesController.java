package gamehub.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import gamehub.App;
import gamehub.dao.SuscripcionDAO;
import gamehub.utils.JavaFXUtils;
import java.sql.Timestamp;

public class SuscripcionesController {
    @FXML private TextField txt_usuario, txt_idPlan, txt_monto;
    @FXML private ComboBox<String> cb_metodo;
    private SuscripcionDAO dao = new SuscripcionDAO();

    @FXML public void initialize() { 
        cb_metodo.getItems().addAll("Tarjeta de Credito", "PayPal"); 
        cb_metodo.getSelectionModel().selectFirst(); 
    }

    @FXML private void actionSuscribir() {
        try {
            if(txt_usuario.getText().isBlank()) {
                JavaFXUtils.mostrarAdvertencia("Faltan Datos", "El ID de usuario es obligatorio."); return;
            }
            String res = dao.registrarSuscripcion(txt_usuario.getText(), Integer.parseInt(txt_idPlan.getText()), new Timestamp(System.currentTimeMillis()), Double.parseDouble(txt_monto.getText()), cb_metodo.getValue(), "SUB-UI-" + System.currentTimeMillis());
            JavaFXUtils.mostrarMensaje("Resultado", res);
        } catch(Exception e) { JavaFXUtils.mostrarError("Error", e.getMessage()); }
    }
    
    @FXML private void actionRegresar() { 
        try { App.setRoot("principal"); } catch(Exception e) { e.printStackTrace(); } 
    }
}