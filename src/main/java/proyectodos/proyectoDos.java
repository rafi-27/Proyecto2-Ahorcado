package proyectodos;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class proyectoDos implements Initializable{
    @FXML AnchorPane izquierdo;
    @FXML AnchorPane derecho;

    @FXML Pane panelIzquierdo;
    @FXML Pane panelDerecho;

    @FXML HBox todosLosBotonesPrimeraParte;
    @FXML HBox todosLosBotonesSegundaParte;
    @FXML HBox hboxSuperior;
    @FXML HBox hboxBajBox;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        String[] vocabulario = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "Ñ", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "Y", "Z"};
        for (int i = 0; i < vocabulario.length; i++) {
            if (i<=13) {
                todosLosBotonesPrimeraParte.getChildren().add(new Button(vocabulario[i]));

            }else{
                todosLosBotonesSegundaParte.getChildren().add(new Button(vocabulario[i]));
            }
        }
        Label texto = new Label("Etiqueta juego del ahorcado");
        texto.setFont(new Font("Ubuntu",24));
        hboxSuperior.getChildren().add(texto);

    }


    
    
}
