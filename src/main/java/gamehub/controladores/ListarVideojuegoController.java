package gamehub.controladores;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import gamehub.App;
import gamehub.dao.videojuegoDAO;
import gamehub.dto.videojuegoDTO;
import gamehub.utils.JavaFXUtils;

public class ListarVideojuegoController implements Initializable {

    @FXML private TableView<videojuegoDTO> tbl_datos;
    @FXML private TableColumn<videojuegoDTO, String> col_titulo;
    @FXML private TableColumn<videojuegoDTO, String> col_publisher;
    @FXML private TableColumn<videojuegoDTO, String> col_plataforma;
    @FXML private TableColumn<videojuegoDTO, Double> col_precio;

    private videojuegoDAO dao = new videojuegoDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        col_titulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        col_publisher.setCellValueFactory(new PropertyValueFactory<>("publisherNombre"));
        col_plataforma.setCellValueFactory(new PropertyValueFactory<>("plataformaNombre"));
        col_precio.setCellValueFactory(new PropertyValueFactory<>("precioBase"));
        cargarDatos();
    }

    private void cargarDatos() {
        try {
            tbl_datos.setItems(FXCollections.observableArrayList(dao.listarCompleto()));
        } catch (SQLException ex) {
            JavaFXUtils.mostrarError("Error de lectura", ex.getMessage());
        }
    }

    @FXML
    private void actionRefrescar() {
        cargarDatos();
        JavaFXUtils.mostrarMensaje("Tabla Actualizada", "Los registros han sido recargados desde la base de datos.");
    }

    @FXML
    private void actionActualizar() {
        videojuegoDTO seleccionado = tbl_datos.getSelectionModel().getSelectedItem();
        
        if (seleccionado != null) {
            App.setMetadato("videojuego_editar", seleccionado);
            try { 
                App.setRoot("actualizarVideojuego"); 
            } catch (Exception e) { 
                e.printStackTrace(); 
            }
        } else {
            JavaFXUtils.mostrarAdvertencia("Seleccion requerida", "Por favor, selecciona un videojuego de la tabla para actualizar.");
        }
    }

    @FXML
    private void actionEliminar() {
        videojuegoDTO seleccionado = tbl_datos.getSelectionModel().getSelectedItem();
        
        if (seleccionado != null) {
            if (JavaFXUtils.mostrarConfirmacion("Confirmar Eliminacion", "Seguro que deseas eliminar el videojuego: " + seleccionado.getTitulo() + "?")) {
                try {
                    if (dao.eliminar(seleccionado)) {
                        JavaFXUtils.mostrarMensaje("Exito", "El videojuego ha sido eliminado correctamente.");
                        cargarDatos();
                    } else {
                        JavaFXUtils.mostrarError("Error", "No se pudo eliminar el registro en la base de datos.");
                    }
                } catch (SQLException ex) {
                    JavaFXUtils.mostrarError("Error de integridad", "No se puede eliminar el videojuego porque esta asociado a otros registros (ej. Ordenes o Compras).");
                }
            }
        } else {
            JavaFXUtils.mostrarAdvertencia("Seleccion requerida", "Por favor, selecciona un videojuego de la tabla para eliminar.");
        }
    }

    @FXML
    private void actionRegresar() {
        try { App.setRoot("principal"); } catch (Exception e) { e.printStackTrace(); }
    }
}