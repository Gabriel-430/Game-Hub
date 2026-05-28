package gamehub.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import gamehub.App;
import gamehub.dao.CompraDAO;
import gamehub.utils.JavaFXUtils;

public class ComprasController {
    @FXML private TextField txt_usuario, txt_idVid, txt_idPlat, txt_precio, txt_cantidad;
    @FXML private ComboBox<String> cb_metodo;
    private CompraDAO dao = new CompraDAO();

    @FXML public void initialize() { 
        cb_metodo.getItems().addAll("Tarjeta de Credito", "PayPal", "Transferencia"); 
        cb_metodo.getSelectionModel().selectFirst(); 
    }

    @FXML private void actionComprar() {
        try {
            if(txt_usuario.getText().isBlank() || txt_idVid.getText().isBlank()) {
                JavaFXUtils.mostrarAdvertencia("Faltan Datos", "El usuario y el ID del Videojuego son obligatorios."); return;
            }
            String res = dao.registrarCompra(txt_usuario.getText(), Integer.parseInt(txt_idVid.getText()), Integer.parseInt(txt_idPlat.getText()), Double.parseDouble(txt_precio.getText()), Integer.parseInt(txt_cantidad.getText()), cb_metodo.getValue(), "UI-COMPRA-" + System.currentTimeMillis());
            JavaFXUtils.mostrarMensaje("Resultado de la Transaccion", res);
            txt_usuario.clear(); txt_idVid.clear(); txt_precio.clear(); txt_cantidad.clear();
        } catch(Exception e) { 
            JavaFXUtils.mostrarError("Error", "Verifica los datos ingresados: " + e.getMessage()); 
        }
    }
    
    @FXML private void actionRegresar() { 
        try { App.setRoot("principal"); } catch(Exception e) { e.printStackTrace(); } 
    }
}