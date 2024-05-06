package estudiar;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Optional;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("ventanaDos"), 633, 276);
        stage.setTitle("Rafik Bachri Marouf");
        // stage.getIcons().add(new
        // Image(getClass().getResourceAsStream("img/Ahorcado7.png")));
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent arg0) {
                arg0.consume();
                Alert alertaCierre = new Alert(Alert.AlertType.CONFIRMATION);
                alertaCierre.setTitle("¿Quieres cerrar?");
                alertaCierre.setHeaderText("¿Quieres salir o no?");
                alertaCierre.setContentText("Si sales dejas de juegar.");

                alertaCierre.getButtonTypes().clear();
                ButtonType si = new ButtonType("Si");
                ButtonType no = new ButtonType("No");
                alertaCierre.getButtonTypes().addAll(si, no);

                Optional<ButtonType> result = alertaCierre.showAndWait();

                if (result.get() == si) {
                    System.exit(0);
                } else {
                    alertaCierre.close();
                }
            }
        });
        // stage.setOnCloseRequest(event -> {
        // event.consume();
        // Alert alertaCierre = new Alert(Alert.AlertType.CONFIRMATION);
        // alertaCierre.setTitle("¿Quieres cerrar?");
        // alertaCierre.setHeaderText("¿Quieres salir o no?");
        // alertaCierre.setContentText("Si sales dejas de juegar.");

        // alertaCierre.getButtonTypes().clear();
        // ButtonType si = new ButtonType("Si");
        // ButtonType no = new ButtonType("No");
        // alertaCierre.getButtonTypes().addAll(si, no);

        // Optional<ButtonType> result = alertaCierre.showAndWait();
        // if (result.get().getText().equalsIgnoreCase("si")) {
        // System.exit(0);
        // } else {
        // alertaCierre.close();

        // }
        // });
        stage.setResizable(false);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}