package gamehub.controladores;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import gamehub.App;
import gamehub.dao.videojuegoDAO;
import gamehub.dto.OpcionDTO;
import gamehub.dto.videojuegoDTO;
import gamehub.utils.JavaFXUtils;

public class RegistrarVideojuegoController implements Initializable {

    @FXML private Label lbl_titulo_pantalla;
    @FXML private TextField txt_titulo;
    @FXML private TextArea txt_descripcion;
    @FXML private DatePicker dp_fecha;
    @FXML private ComboBox<String> cb_clasificacion;
    @FXML private TextField txt_precioBase;
    @FXML private ComboBox<String> cb_estado;
    @FXML private ComboBox<OpcionDTO> cb_publisher;
    @FXML private ComboBox<OpcionDTO> cb_genero;
    @FXML private ComboBox<OpcionDTO> cb_plataforma;
    @FXML private TextField txt_precioPlataforma;

    private videojuegoDAO dao = new videojuegoDAO();
    private videojuegoDTO videojuegoEdicion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarCatalogos();

        videojuegoEdicion = (videojuegoDTO) App.getMetadato("videojuego_editar");
        if (videojuegoEdicion != null) {
            lbl_titulo_pantalla.setText("Actualizar Videojuego");
            cargarDatosEdicion();
        } else {
            lbl_titulo_pantalla.setText("Registrar Nuevo Videojuego");
        }
    }

    private void cargarCatalogos() {
        cb_clasificacion.setItems(FXCollections.observableArrayList("E", "E10+", "T", "M", "18+"));
        cb_estado.setItems(FXCollections.observableArrayList("Disponible", "No Disponible"));

        cb_publisher.setItems(FXCollections.observableArrayList(dao.obtenerCatalogos("publisher", "id_publisher", "nombre_comercial")));
        cb_genero.setItems(FXCollections.observableArrayList(dao.obtenerCatalogos("genero", "id_genero", "nombre")));
        cb_plataforma.setItems(FXCollections.observableArrayList(dao.obtenerCatalogos("plataforma", "id_plataforma", "nombre")));
    }

    private void cargarDatosEdicion() {
        txt_titulo.setText(videojuegoEdicion.getTitulo());
        txt_descripcion.setText(videojuegoEdicion.getDescripcion());
        dp_fecha.setValue(videojuegoEdicion.getFechaLanzamiento().toLocalDate());
        cb_clasificacion.setValue(videojuegoEdicion.getClasificacion());
        txt_precioBase.setText(String.valueOf(videojuegoEdicion.getPrecioBase()));
        cb_estado.setValue(videojuegoEdicion.getEstado());
        txt_precioPlataforma.setText(String.valueOf(videojuegoEdicion.getPrecioPlataforma()));

        seleccionarCombo(cb_publisher, videojuegoEdicion.getIdPublisher());
        seleccionarCombo(cb_genero, videojuegoEdicion.getIdGenero());
        seleccionarCombo(cb_plataforma, videojuegoEdicion.getIdPlataforma());
    }

    private void seleccionarCombo(ComboBox<OpcionDTO> combo, Integer id) {
        if (id == null) return;
        for (OpcionDTO op : combo.getItems()) {
            if (op.getId().equals(id)) {
                combo.setValue(op);
                break;
            }
        }
    }

    @FXML
    private void actionGuardar() {
        try {
            if (txt_titulo.getText().isBlank() || dp_fecha.getValue() == null || cb_publisher.getValue() == null || cb_genero.getValue() == null || cb_plataforma.getValue() == null) {
                JavaFXUtils.mostrarAdvertencia("Datos incompletos", "Debe llenar los campos obligatorios.");
                return;
            }

            videojuegoDTO dto = (videojuegoEdicion == null) ? new videojuegoDTO() : videojuegoEdicion;
            dto.setTitulo(txt_titulo.getText());
            dto.setDescripcion(txt_descripcion.getText());
            dto.setFechaLanzamiento(Date.valueOf(dp_fecha.getValue()));
            dto.setClasificacion(cb_clasificacion.getValue());
            dto.setPrecioBase(Double.parseDouble(txt_precioBase.getText()));
            dto.setEstado(cb_estado.getValue());
            dto.setIdPublisher(cb_publisher.getValue().getId());
            dto.setIdGenero(cb_genero.getValue().getId());
            dto.setIdPlataforma(cb_plataforma.getValue().getId());
            dto.setPrecioPlataforma(Double.parseDouble(txt_precioPlataforma.getText()));

            boolean exito;
            if (videojuegoEdicion == null) {
                exito = dao.agregar(dto);
            } else {
                exito = dao.actualizar(dto);
            }

            if (exito) {
                JavaFXUtils.mostrarMensaje("ÃƒÆ’Ã¢â‚¬Â°xito", "Operación realizada correctamente.");
                actionCancelar();
            } else {
                JavaFXUtils.mostrarError("Error", "No se pudo guardar la información.");
            }
        } catch (NumberFormatException e) {
            JavaFXUtils.mostrarError("Error de formato", "Los precios deben ser numéricos.");
        } catch (SQLException e) {
            JavaFXUtils.mostrarError("Error BD", "Error de base de datos: " + e.getMessage());
        }
    }

    @FXML
    private void actionCancelar() {
        try { App.setRoot("principal"); } catch (Exception e) { e.printStackTrace(); }
    }
}
