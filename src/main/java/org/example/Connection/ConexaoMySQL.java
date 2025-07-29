package org.example.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/lardemaria_doacao?useTimezone=true&serverTimezone=UTC";
    private static final String USUARIO = "root"; // Ex: root
    private static final String SENHA = "Aquiles";     // A senha do seu usuário

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            System.err.println("Erro ao estabelecer conexão com o banco: " + e.getMessage());
            throw e;
        }
    }

}
