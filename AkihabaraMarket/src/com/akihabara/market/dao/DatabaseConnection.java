package com.akihabara.market.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    //Variables constantes: url user y password
    private static final String DB_URL = "jdbc:mysql://localhost:3306/akihabara_db"; // Ajusta el nombre de la base de datos y host si es necesario
    private static final String USER = "userAkihabara";
    private static final String PASSWORD = "curso";

    //Propiedad de conexión privada
    private Connection conexion;

    //Constructor de la conexion
    public DatabaseConnection() {
        try {
            // Establece la conexión con la base de datos
            conexion = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Se ha establecido con éxito la conexión a la base de datos.");
        } catch (SQLException e) { //Pillamos las excepciones o errores	
            System.out.println("Error al establecer la conexión a la base de datos. " + e.getMessage());
        }
    }

    // Método para devolver la conexion
    public Connection getConexion() {
        return conexion;
    }
    //Metodo que cierra la conexion
    public void cerrarConexion() {
        if (conexion != null) {
            try {
                if (!conexion.isClosed()) {
                    conexion.close();
                    System.out.println("Se ha cerrado la conexión con la base de datos.");
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión con la base de datos. " + e.getMessage());
            }
        }
    }
}
