package org.example.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {

    // URL, usuário e senha para a conexão com o banco de dados.
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/lardemaria_doacao?useTimezone=true&serverTimezone=UTC";
    private static final String USUARIO = "root"; // Seu usuário do MySQL
    private static final String SENHA = "Aquiles";     // Sua senha do MySQL

    /**
     * Estabelece e retorna uma conexão com o banco de dados.
     * Este é o método que suas classes DAO devem chamar.
     *
     * @return um objeto Connection pronto para uso.
     * @throws SQLException se ocorrer um erro ao tentar conectar.
     */
    public static Connection getConnection() throws SQLException {
        // O método agora propaga a exceção (throws SQLException).
        // Isso permite que a classe que chamou o método (o DAO) trate o erro,
        // geralmente mostrando uma mensagem para o usuário.
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }


    /**
     * Método principal usado apenas para testar a conexão diretamente.
     */
    public static void main(String[] args) {
        // A forma correta de testar é usando um bloco try-with-resources,
        // que garante que a conexão será fechada automaticamente.
        try (Connection conexao = getConnection()) {
            System.out.println("Conexão estabelecida com sucesso ao banco de dados LarDeMaria!");
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
