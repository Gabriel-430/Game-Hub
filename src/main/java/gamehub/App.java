package gamehub;

import java.io.IOException;
import java.util.HashMap;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static HashMap<String, Object> metadatos;
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("GameHub - Plataforma Digital");
        stage.setMinWidth(800);
        stage.setMinHeight(600);
        
        scene = new Scene(loadFXML("vistas/principal"), 900, 700);
        scene.getStylesheets().add(App.class.getResource("/css/estilo.css").toExternalForm());
        
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML("vistas/" + fxml));
    }

    public static void setMetadato(String nombre, Object valor) {
        if (metadatos == null) {
            metadatos = new HashMap<>();
        }
        metadatos.put(nombre, valor);
    }

    public static Object getMetadato(String nombre) {
        if (metadatos == null) {
            return null;
        }
        return metadatos.get(nombre);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
