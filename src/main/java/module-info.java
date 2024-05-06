module proyectodos {
    requires javafx.controls;
    requires javafx.fxml;

    opens proyectodos to javafx.fxml;
    exports proyectodos;

    opens estudiar to javafx.fxml;
    exports estudiar;
}
