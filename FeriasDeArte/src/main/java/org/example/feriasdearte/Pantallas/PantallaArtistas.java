package org.example.feriasdearte.Pantallas;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.feriasdearte.Conexiones.Conexiones;
import org.example.feriasdearte.HelloApplication;
import org.example.feriasdearte.Mantenimiento.MantenimientoArtistas;
import org.example.feriasdearte.Objetos.Artistas;

import java.io.IOException;
import java.sql.Connection;

public class PantallaArtistas {

    @FXML
    private TableView<Artistas> tablaArtistas;

    @FXML
    private TableColumn<Artistas, Integer> idTable;

    @FXML
    private TableColumn<Artistas, String> nombreTable;

    @FXML
    private TableColumn<Artistas, String> biografiaTable;

    @FXML
    private TableColumn<Artistas, String> telefonoTable;

    @FXML
    private TableColumn<Artistas, String> emailTable;

    @FXML
    private TextField nombreTextField;

    @FXML
    private TextField biografiaTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField telefonoTextField;

    @FXML
    private Button anyadirButton;

    @FXML
    private Button guardarButton;

    @FXML
    private Button editarBotton;

    @FXML
    private Button eliminarBotton;

    private Connection conexion;
    private int id_anterior;

    @FXML
    public void initialize() {
        conexion = Conexiones.conectar();

        tablaArtistas.setItems(MantenimientoArtistas.consultar(conexion));

        idTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getArtistaid()));
        nombreTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getNombre()));
        biografiaTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getBiografia()));
        telefonoTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getTelefono()));
        emailTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getEmail()));
    }

    @FXML
    protected void onAnyadirButtonClick() {
        String nombre = nombreTextField.getText();
        String biografia = biografiaTextField.getText();
        String telefono = telefonoTextField.getText();
        String email = emailTextField.getText();

        Artistas artista;

        if (biografia == null) {
            artista = new Artistas(nombre, email, telefono);
        }else {
            artista = new Artistas(nombre, biografia, email, telefono);
        }

        MantenimientoArtistas.insertar(conexion, artista);

        limpiarCampos();
        tablaArtistas.setItems(MantenimientoArtistas.consultar(conexion));
    }

    @FXML
    protected void onGuardarButtonClick() {
        anyadirButton.setDisable(false);
        guardarButton.setDisable(true);

        String nombre = nombreTextField.getText();
        String biografia = biografiaTextField.getText();
        String telefono = telefonoTextField.getText();
        String email = emailTextField.getText();

        Artistas artista;

        if (biografia == null) {
            artista = new Artistas(nombre, email, telefono);
        }else {
            artista = new Artistas(nombre, biografia, email, telefono);
        }

        MantenimientoArtistas.modificar(conexion, artista, id_anterior);

        nombreTextField.clear();
        biografiaTextField.clear();
        telefonoTextField.clear();
        emailTextField.clear();
        tablaArtistas.setItems(MantenimientoArtistas.consultar(conexion));
    }

    @FXML
    protected void onEliminarButtonClick() {
        Artistas artista = tablaArtistas.getSelectionModel().getSelectedItem();

        if (artista != null) {
            int id_borrar = artista.getArtistaid();
            MantenimientoArtistas.borrar(conexion, id_borrar);
        } else {
            System.out.println("No hay ningún artista seleccionado.");
        }

        tablaArtistas.setItems(MantenimientoArtistas.consultar(conexion));
    }

    @FXML
    protected void onEditarButtonClick() {

        anyadirButton.setDisable(true);
        guardarButton.setDisable(false);


        Artistas seleccionado = tablaArtistas.getSelectionModel().getSelectedItem();
        id_anterior = seleccionado.getArtistaid();
        if (seleccionado != null) {

            nombreTextField.setText(seleccionado.getNombre());
            biografiaTextField.setText(seleccionado.getBiografia());
            telefonoTextField.setText(seleccionado.getTelefono());
            emailTextField.setText(seleccionado.getEmail());

        } else {
            System.out.println("No hay ningún artista seleccionado.");
        }
    }

    private void limpiarCampos() {
        nombreTextField.clear();
        biografiaTextField.clear();
        telefonoTextField.clear();
        emailTextField.clear();
    }

}
