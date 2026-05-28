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

public class ActualizarVideojuegoController implements Initializable {

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
            cargarDatosEdicion();
        } else {
            JavaFXUtils.mostrarError("Error CrÃ­tico", "No se encontrÃ³ ningÃºn videojuego seleccionado para actualizar.");
            actionCancelar();
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
        
        if (videojuegoEdicion.getFechaLanzamiento() != null) {
            dp_fecha.setValue(videojuegoEdicion.getFechaLanzamiento().toLocalDate());
        }
        
        cb_clasificacion.setValue(videojuegoEdicion.getClasificacion());
        txt_precioBase.setText(String.valueOf(videojuegoEdicion.getPrecioBase()));
        cb_estado.setValue(videojuegoEdicion.getEstado());
        txt_precioPlataforma.setText(String.valueOf(videojuegoEdicion.getPrecioPlataforma()));

        seleccionarCombo(cb_publisher, videojuegoEdicion.getIdPublisher());
        seleccionarCombo(cb_genero, videojuegoEdicion.getIdGenero());
        seleccionarCombo(cb_plataforma, videojuegoEdicion.getIdPlataforma());
    }

    private void seleccionarCombo(ComboBox<OpcionDTO> combo, Integer idSeleccionado) {
        if (idSeleccionado == null) return;
        for (OpcionDTO opcion : combo.getItems()) {
            if (opcion.getId().equals(idSeleccionado)) {
                combo.setValue(opcion);
                break;
            }
        }
    }

    @FXML
    private void actionGuardar() {
        try {
            if (txt_titulo.getText().isBlank() || dp_fecha.getValue() == null || cb_publisher.getValue() == null || cb_genero.getValue() == null || cb_plataforma.getValue() == null) {
                JavaFXUtils.mostrarAdvertencia("Datos incompletos", "Debe llenar todos los campos obligatorios antes de actualizar.");
                return;
            }

            videojuegoEdicion.setTitulo(txt_titulo.getText());
            videojuegoEdicion.setDescripcion(txt_descripcion.getText());
            videojuegoEdicion.setFechaLanzamiento(Date.valueOf(dp_fecha.getValue()));
            videojuegoEdicion.setClasificacion(cb_clasificacion.getValue());
            videojuegoEdicion.setPrecioBase(Double.parseDouble(txt_precioBase.getText()));
            videojuegoEdicion.setEstado(cb_estado.getValue());
            videojuegoEdicion.setIdPublisher(cb_publisher.getValue().getId());
            videojuegoEdicion.setIdGenero(cb_genero.getValue().getId());
            videojuegoEdicion.setIdPlataforma(cb_plataforma.getValue().getId());
            videojuegoEdicion.setPrecioPlataforma(Double.parseDouble(txt_precioPlataforma.getText()));

            if (dao.actualizar(videojuegoEdicion)) {
                JavaFXUtils.mostrarMensaje("ActualizaciÃ³n Exitosa", "El videojuego ha sido actualizado correctamente.");
                App.setMetadato("videojuego_editar", null);
                actionCancelar();
            } else {
                JavaFXUtils.mostrarError("Error al Actualizar", "No se pudo actualizar la informaciÃ³n en la base de datos.");
            }
            
        } catch (NumberFormatException e) {
            JavaFXUtils.mostrarError("Error de formato", "AsegÃºrate de ingresar solo nÃºmeros vÃ¡lidos en los campos de precio.");
        } catch (SQLException e) {
            JavaFXUtils.mostrarError("Error de Base de Datos", "OcurriÃ³ un error SQL: " + e.getMessage());
        }
    }

    @FXML
    private void actionCancelar() {
        try { 
            App.setRoot("listarVideojuego"); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        }
    }
}