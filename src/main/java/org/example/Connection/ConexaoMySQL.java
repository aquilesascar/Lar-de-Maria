package org.example.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {
    // Sua URL do banco de dados
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/lardemaria_doacao?useTimezone=true&serverTimezone=UTC";
    private static final String USUARIO = "root"; // Ex: root
    private static final String SENHA = "Aquiles";     // A senha do seu usuário

    public static void main(String[] args) {
        Connection conexao = null;
        try {
            // Tenta estabelecer a conexão
            System.out.println("Tentando conectar ao banco de dados LarDeMaria...");
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conexão estabelecida com sucesso ao banco de dados LarDeMaria!");

            // Aqui você pode adicionar lógica para interagir com o banco de dados
            // (ex: Statements, PreparedStatements, ResultSet)

        } catch (SQLException e) {
            System.err.println("Erro ao conectar ou operar no banco de dados: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Garante que a conexão seja fechada, mesmo que ocorra um erro
            try {
                if (conexao != null) {
                    conexao.close();
                    System.out.println("Conexão com o banco de dados fechada.");
                }
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }

    // Método para obter a conexão com o banco de dados
    public Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            System.err.println("Erro ao estabelecer conexão com o banco: " + e.getMessage());
            throw e; // Lança a exceção para que o chamador possa tratar
        }
    }
}
