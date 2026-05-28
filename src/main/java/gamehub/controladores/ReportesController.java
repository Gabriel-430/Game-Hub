package gamehub.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import gamehub.App;
import gamehub.dao.ReporteDAO;
import gamehub.utils.JavaFXUtils;

public class ReportesController {
    @FXML private TextField txt_usuario, txt_idVid, txt_anio, txt_mes;
    @FXML private DatePicker dp_inicio, dp_fin;
    private ReporteDAO dao = new ReporteDAO();

    @FXML private void actionTotalGastado() {
        try { 
            if(txt_usuario.getText().isBlank() || dp_inicio.getValue() == null || dp_fin.getValue() == null) {
                JavaFXUtils.mostrarAdvertencia("Faltan Datos", "Llena usuario y fechas."); return;
            }
            double total = dao.obtenerTotalGastado(txt_usuario.getText(), dp_inicio.getValue().toString(), dp_fin.getValue().toString()); 
            JavaFXUtils.mostrarMensaje("Total Gastado", "El usuario ha gastado: $" + total); 
        } catch(Exception e) { JavaFXUtils.mostrarError("Error", e.getMessage()); }
    }

    @FXML private void actionVentasMes() {
        try { 
            if(txt_idVid.getText().isBlank() || txt_anio.getText().isBlank() || txt_mes.getText().isBlank()) {
                JavaFXUtils.mostrarAdvertencia("Faltan Datos", "Llena los campos del videojuego y fecha."); return;
            }
            int total = dao.obtenerVentasVideojuego(Integer.parseInt(txt_idVid.getText()), Integer.parseInt(txt_anio.getText()), Integer.parseInt(txt_mes.getText())); 
            JavaFXUtils.mostrarMensaje("Ventas Mensuales", "Unidades vendidas: " + total); 
        } catch(Exception e) { JavaFXUtils.mostrarError("Error", e.getMessage()); }
    }
    
    @FXML private void actionRegresar() { 
        try { App.setRoot("principal"); } catch(Exception e) { e.printStackTrace(); } 
    }
}