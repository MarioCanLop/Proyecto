package org.example.feriasdearte;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.feriasdearte.Pantallas.PantallaArtistas;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void buttonArtistas(ActionEvent actionEvent) throws IOException {

    }

    public void onEditarButton(ActionEvent actionEvent) {
    }

    public void onBorrarButton(ActionEvent actionEvent) {
    }

    public void onAnyadirEstudiante(ActionEvent actionEvent) {
    }

    public void onGuardarButton(ActionEvent actionEvent) {
    }

//    public void buttonFerias(ActionEvent actionEvent)throws IOException {
//
//        HelloApplication.setRoot("pantallaFerias");
//
//    }
//
//    public void buttonVentas(ActionEvent actionEvent)throws IOException {
//
//        HelloApplication.setRoot("pantallaVentas");
//
//    }
//
//    public void buttonEntradas(ActionEvent actionEvent)throws IOException {
//
//        HelloApplication.setRoot("pantallaEntradas");
//
//    }
//
//    public void buttonEstantes(ActionEvent actionEvent)throws IOException {
//
//        HelloApplication.setRoot("pantallaEstantes");
//
//    }
//
//    public void buttonObrasDeArte(ActionEvent actionEvent)throws IOException {
//
//        HelloApplication.setRoot("pantallaObrasDeArte");
//
//
//    }
//
//    public void buttonAsistentes(ActionEvent actionEvent)throws IOException {
//
//        HelloApplication.setRoot("pantallaAsistentes");
//
//    }
//
//    public void buttonCatalogo(ActionEvent actionEvent) throws IOException{
//
//        HelloApplication.setRoot("pantallaCatalogo");
//
//    }
}