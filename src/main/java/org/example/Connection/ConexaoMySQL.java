package org.example.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {

    // --- INFORMAÇÕES PARA A CONEXÃO ---
    // Substitua com suas informações
    private static final String URL = "jdbc:mysql://localhost:3306/seu_banco_de_dados";
    private static final String USUARIO = "seu_usuario";
    private static final String SENHA = "sua_senha";

    // --- MÉTODO PARA OBTER A CONEXÃO ---
    public static Connection getConexao() {
        Connection conexao = null;
        try {
            // Carrega o driver JDBC do MySQL.
            // Em versões mais recentes do JDBC, este passo é opcional, mas é uma boa prática.
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Tenta estabelecer a conexão
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);

            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");

        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC do MySQL não encontrado!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar com o banco de dados!");
            e.printStackTrace();
        }
        return conexao;
    }

    public static void main(String[] args) {
        // Teste rápido da conexão
        Connection conn = getConexao();
        if (conn != null) {
            try {
                // É fundamental fechar a conexão após o uso
                conn.close();
                System.out.println("Conexão fechada.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
