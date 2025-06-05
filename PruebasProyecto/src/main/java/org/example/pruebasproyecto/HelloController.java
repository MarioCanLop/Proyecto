package org.example.pruebasproyecto;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.time.LocalDate;

public class HelloController {

    @FXML
    private TableColumn<Artistas, Integer> idTable;
    @FXML
    private TableColumn<Artistas, String> nombreTable;
    @FXML
    private TableColumn<Artistas, String > biografiaTable;
    @FXML
    private TableColumn<Artistas, String> telefonoTable;
    @FXML
    private TableColumn<Artistas, String> emailTable;

    @FXML
    private Button editarBotton;
    @FXML
    private Button eliminarBotton;
    @FXML
    private Button anyadirButton;
    @FXML
    private Button guardarButton;

    @FXML
    private TextField nombreTextField;
    @FXML
    private TextField biografiaTextField;
    @FXML
    private TextField telefonoTextField;
    @FXML
    private TextField emailTextField;

    @FXML
    private TableView<Artistas> tablaArtistas;

    Connection conexion;
    String nombre_ant;

    @FXML
    public void initialize(){

        conexion = Mantenimiento.conectar();
        tablaArtistas.setItems(Mantenimiento.consultar(conexion));
        idTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getArtistaid()));
        nombreTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getNombre()));
        biografiaTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getBiografia()));
        telefonoTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getTelefono()));
        emailTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getEmail()));


    }

    @FXML
    public void onEditarButtonClick(ActionEvent actionEvent) {

        anyadirButton.setDisable(true);
        guardarButton.setDisable(false);

        Artistas artista_seleccionado = tablaArtistas.getSelectionModel().getSelectedItem();
        nombre_ant = artista_seleccionado.getNombre();
        if (artista_seleccionado != null){
            if (artista_seleccionado.getBiografia() == null) {
                nombreTextField.setText(String.valueOf(artista_seleccionado.getNombre()));
                biografiaTextField.clear();
                telefonoTextField.setText(String.valueOf(artista_seleccionado.getEmail()));
                emailTextField.setText(String.valueOf(artista_seleccionado.getTelefono()));
            }else {
                nombreTextField.setText(String.valueOf(artista_seleccionado.getNombre()));
                biografiaTextField.setText(String.valueOf(artista_seleccionado.getBiografia()));
                telefonoTextField.setText(String.valueOf(artista_seleccionado.getEmail()));
                emailTextField.setText(String.valueOf(artista_seleccionado.getTelefono()));
            }
        }else {

            System.out.println("No hay ninguna fila seleccionada");

        }

    }
    @FXML
    public void onEliminarButtonClick(ActionEvent actionEvent) {

        Artistas artista_seleccionado = tablaArtistas.getSelectionModel().getSelectedItem();

        if (artista_seleccionado != null){

            Mantenimiento.borrar(conexion, artista_seleccionado);


        }else {
            System.out.println("No hay ninguna fila seleccionada");
        }

        tablaArtistas.setItems(Mantenimiento.consultar(conexion));


    }
    @FXML
    public void onGuardarButtonClick(ActionEvent actionEvent) {

        anyadirButton.setDisable(false);
        guardarButton.setDisable(true);

        String nombre = nombreTextField.getText();
        String biografia = biografiaTextField.getText();
        String telefono = telefonoTextField.getText();
        String email = emailTextField.getText();

        if (biografia.isEmpty()){
            Mantenimiento.modificar(conexion, new Artistas(nombre,email,telefono),nombre_ant);
        }else {
            Mantenimiento.modificar(conexion, new Artistas(nombre, biografia, email, telefono), nombre_ant );
        }



        nombreTextField.clear();
        biografiaTextField.clear();
        telefonoTextField.clear();
        emailTextField.clear();
        tablaArtistas.setItems(Mantenimiento.consultar(conexion));

    }
    @FXML
    public void onAnyadirButtonClick(ActionEvent actionEvent) {

        String nombre = nombreTextField.getText();
        String biografia = biografiaTextField.getText();
        String telefono = telefonoTextField.getText();
        String email = emailTextField.getText();

        if (biografia.isEmpty()){
            Mantenimiento.insertar(conexion, new Artistas(nombre,email, telefono));
        }else {
            Mantenimiento.insertar(conexion, new Artistas(nombre, biografia,email,telefono) );
        }



        nombreTextField.clear();
        biografiaTextField.clear();
        telefonoTextField.clear();
        emailTextField.clear();
        tablaArtistas.setItems(Mantenimiento.consultar(conexion));

    }
}