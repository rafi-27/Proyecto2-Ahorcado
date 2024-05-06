package estudiar;

import java.io.IOException;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

public class ControladorVenTres {
    @FXML Button bottonTres;

    public void cambiamosDos() throws IOException{
        App.setRoot("ventanaDos");
    }
}
