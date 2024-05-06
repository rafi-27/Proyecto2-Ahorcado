package proyectodos;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class proyectoDos implements Initializable {
    @FXML
    AnchorPane izquierdo;
    @FXML
    AnchorPane derecho;

    @FXML
    Pane panelIzquierdo;
    @FXML
    Pane panelDerecho;

    @FXML
    HBox todosLosBotonesPrimeraParte;
    @FXML
    HBox todosLosBotonesSegundaParte;
    @FXML
    HBox hboxSuperior;
    @FXML
    HBox hboxBajBox;
    @FXML
    ImageView imag;
    @FXML
    Pane imagenTitulo;

    Label palabras = new Label();
    String[] topSecretc = { "GITHUB", "THIAR", "PYTHON", "JAVA", "SQL", "JUEGOS", "PROGRA" };
    String secreta;
    private int fallos = 0;
    private final int MAX_FALLOS = 6;
    private ArrayList<Character> letrasPulsadas = new ArrayList<>();

    public void elegirPalabraSecreta() {
        int opcion = ThreadLocalRandom.current().nextInt(0, topSecretc.length);
        secreta = topSecretc[opcion];
    }



    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Image imagen = new Image(getClass().getResourceAsStream("img/titulo.png"));
        ImageView titulo = new ImageView(imagen);
        titulo.setFitHeight(55);
        titulo.setFitWidth(223);

        imagenTitulo.getChildren().add(titulo);

        elegirPalabraSecreta();
        ponerBotones();
        ponerTitulo();
        // actualizarImagen();
        imag.setImage(new Image(getClass().getResourceAsStream("img/Ahorcado1.png")));
        mostrarFormatoSecreto();
    }




    private void ponerTitulo() {
        Label texto = new Label("Etiqueta juego del ahorcado");
        texto.setFont(new Font("Ubuntu", 24));
        hboxSuperior.getChildren().add(texto);
    }

    private void ponerBotones() {
        String[] vocabulario = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "Ñ", "O", "P",
                "Q", "R", "S", "T", "U", "V", "W", "Y", "Z" };
        TeclaPulsada gestor = new TeclaPulsada();
        for (int i = 0; i < vocabulario.length; i++) {
            Button botoncito = new Button(vocabulario[i]);
            botoncito.setOnAction(gestor);
            if (i <= 12) {
                todosLosBotonesPrimeraParte.getChildren().add(botoncito);
            } else {
                todosLosBotonesSegundaParte.getChildren().add(botoncito);
            }
        }
    }

    class TeclaPulsada implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent tecla) {
            Button boton = (Button) tecla.getSource();
            boton.setDisable(true);
            comprobarLetra(boton.getText());
        }
    }

    private void comprobarLetra(String c) {
        letrasPulsadas.add(c.charAt(0));
        if (!secreta.contains(c)) {
            fallos++;
            String ubicacion = "img/Ahorcado" + (fallos + 1) + ".png";
            imag.setImage(new Image(getClass().getResourceAsStream(ubicacion)));
        }
        mostrarFormatoSecreto();

        if (fallos == MAX_FALLOS) {
            fallada();
            alertaReiniciar();
        } else if (formatoSecretoCompleto()) {
            acertada();
            alertaReiniciar();
        }
    }

    private void mostrarFormatoSecreto() {
        String formatoSecreto = "";
        for (int i = 0; i < secreta.length(); i++) {
            char letra = secreta.charAt(i);
            if (letrasPulsadas.contains(letra)) {
                formatoSecreto += letra + " ";
            } else {
                formatoSecreto += "_ ";
            }
        }
        palabras.setFont(new Font("Ubuntu", 20));
        palabras.setText(formatoSecreto.trim());
        hboxBajBox.getChildren().clear();
        hboxBajBox.getChildren().add(palabras);
    }

    private boolean formatoSecretoCompleto() {
        for (int i = 0; i < secreta.length(); i++) {
            if (!letrasPulsadas.contains(secreta.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private void reiniciar() {
        elegirPalabraSecreta();
        todosLosBotonesPrimeraParte.getChildren().clear();
        todosLosBotonesSegundaParte.getChildren().clear();
        fallos = 0;
        letrasPulsadas.clear();
        mostrarFormatoSecreto();
        ponerBotones();
        imag.setImage(new Image(getClass().getResourceAsStream("img/Ahorcado1.png")));
    }

    //------------------------------------------------------------------------------------------------------------------------\\

    private void acertada() {
        Alert alertaGanadora = new Alert(AlertType.CONFIRMATION);
        alertaGanadora.setTitle("Has ganado.");
        alertaGanadora.setHeaderText("Muy bien hecho.");
        alertaGanadora.setContentText("Has adivinado la palabra secreta.");
        alertaGanadora.showAndWait();
    }

    private void fallada() {
        Alert alertaPerdedora = new Alert(AlertType.ERROR);
        alertaPerdedora.setTitle("Has perdido.");
        alertaPerdedora.setHeaderText("No has adivinado la palabra.");
        alertaPerdedora.setContentText("La palabra secreta era: " + secreta);
        alertaPerdedora.showAndWait();
    }

    public void alertaReiniciar() {
        ButtonType bottonSi = new ButtonType("Si");
        ButtonType bottonNo = new ButtonType("No");
        Alert seguir = new Alert(AlertType.CONFIRMATION);
        seguir.getButtonTypes().clear();
        seguir.setTitle("¿Otra?");
        seguir.setContentText("¿Deseas repetir juego?");
        seguir.getButtonTypes().addAll(bottonSi, bottonNo);

        Optional<ButtonType> result = seguir.showAndWait();
        if (result.get().getText().equalsIgnoreCase("si")) {
            reiniciar();
        } else {
            System.exit(0);
        }
    }
}