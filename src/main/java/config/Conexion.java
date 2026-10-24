package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    // PASO 1: Cadena JDBC configurada con seguridad SSL para SQL Server
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=bdalumno;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "jhsg"; // Cambia por tu usuario de SQL Server
    private static final String PASSWORD = "Admin123*"; // Cambia por tu contraseña de SQL Server

    // PASO 2: Método para devolver la conexión activa
    public static Connection getConexion() {
        Connection cn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error de conexión JDBC: " + e.getMessage());
        }
        return cn;
    }
}
