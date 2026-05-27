package gamehub.controladores;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import gamehub.App;
import gamehub.dao.videojuegoDAO;
import gamehub.dto.videojuegoDTO;
import gamehub.utils.JavaFXUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class BuscarVideojuegoController implements Initializable {

    @FXML
    private TextField txt_busqueda;
    @FXML
    private TableView<videojuegoDTO> tbl_resultados;
    @FXML
    private TableColumn<videojuegoDTO, String> col_titulo;
    @FXML
    private TableColumn<videojuegoDTO, String> col_genero;
    @FXML
    private TableColumn<videojuegoDTO, String> col_estado;

    private videojuegoDAO dao = new videojuegoDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        col_titulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        col_genero.setCellValueFactory(new PropertyValueFactory<>("generoNombre"));
        col_estado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        actionBuscar();
    }

    @FXML
    private void actionBuscar() {
        String query = txt_busqueda.getText();
        videojuegoDTO dtoParam = new videojuegoDTO();
        dtoParam.setTitulo(query);
        try {
            tbl_resultados.setItems(FXCollections.observableArrayList(dao.buscar(dtoParam)));
        } catch (SQLException ex) {
            JavaFXUtils.mostrarError("Error de búsqueda", ex.getMessage());
        }
    }

    @FXML
    private void actionRegresar() {
        try {
            App.setRoot("principal");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
