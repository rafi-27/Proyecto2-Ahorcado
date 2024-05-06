package estudiar;


import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControladorVenDos {
    @FXML Button cambiarPalTres;

    public void cambiamosDos() throws IOException{
        App.setRoot("ventanaTres");
    }
}