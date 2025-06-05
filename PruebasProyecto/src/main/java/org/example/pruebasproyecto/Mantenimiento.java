package org.example.pruebasproyecto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;

public class Mantenimiento {

    public static void desconectar(Connection conexion){

        try {
            conexion.close();
            System.out.println("Conexión finalizada.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public static Connection conectar(){

        Connection conexion;
        String host = "jdbc:mariadb://localhost:3306/";
        String usuario = "root";
        String psw = "";
        String bd = "proyecto";

        try {
            conexion = DriverManager.getConnection(host+bd,usuario,psw);
            System.out.println("Conexión realizada con éxito.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return conexion;
    }

    public static ObservableList<Artistas> consultar(Connection conexion){

        String query = "SELECT * FROM artistas";
        ObservableList<Artistas> listaArtistas = FXCollections.observableArrayList();
        Statement stmt;
        ResultSet resultado;
        try {
            stmt = conexion.createStatement();
            resultado = stmt.executeQuery(query);
            while (resultado.next()) {
                int id = resultado.getInt("artistaid");
                String nombre = resultado.getString("nombre");
                String biografia = resultado.getString("biografia");
                String telfeono = resultado.getString("telefono");
                String email = resultado.getString("email");
                if (biografia == null) {
                    listaArtistas.add(new Artistas(id, nombre, email,telfeono));
                }else {
                    listaArtistas.add(new Artistas(id, nombre,biografia, email, telfeono));
                }

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return listaArtistas;

    }

    public static void insertar(Connection conexion, Artistas artista) {

        StringBuilder query = new StringBuilder();
        String querry2 ;

        if (artista.getBiografia() == null){

            query.append("INSERT INTO artistas  (nombre, email, telefono) VALUES ('");
            query.append(artista.getNombre());
            query.append("','");
            query.append(artista.getEmail());
            query.append("','");
            query.append(artista.getTelefono());
            query.append("')");
            querry2 = query.toString();


        }else {
            query.append("INSERT INTO artistas  (nombre, biografia, email, telefono) VALUES ('");
            query.append(artista.getNombre());
            query.append("','");
            query.append(artista.getBiografia());
            query.append("','");
            query.append(artista.getEmail());
            query.append("','");
            query.append(artista.getTelefono());
            query.append("')");
            querry2 = query.toString();

        }


        Statement stmt;

        try {
            stmt = conexion.createStatement();
            stmt.executeUpdate(querry2);
            System.out.println("Pila insertada");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }


    public static void borrar(Connection conexion, Artistas artistas){

        String querry = "Delete from artistas Where artistaid ='" + artistas.getArtistaid() + "'";

        Statement stmt;


        try {
            stmt = conexion.createStatement();
            stmt.executeUpdate(querry);
            System.out.println("Fila Eliminada");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public static void modificar (Connection conexion, Artistas artistas, String nomre_ant){
        String querry;
        System.out.println(artistas);
        if (artistas.getBiografia() == null) {
            querry = "Update artistas Set nombre = '" + artistas.getNombre()
                    + "', telefono = '" + artistas.getEmail() +
                    "', email = '" + artistas.getTelefono() +
                    "' Where nombre = '" + nomre_ant + "'";
        }else{
            querry = "Update artistas Set nombre = '" + artistas.getNombre()
                    + "', biografia = '" + artistas.getBiografia()
                    + "', telefono = '" + artistas.getEmail() +
                    "', email = '" + artistas.getTelefono() +
                    "' Where nombre = '" + nomre_ant + "'";
        }
        Statement stmt;


        try {
            stmt = conexion.createStatement();
            stmt.executeUpdate(querry);
            System.out.println("Fila Modificada");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }



}
