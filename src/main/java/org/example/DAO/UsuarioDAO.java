package org.example.DAO;

import org.example.Connection.ConexaoMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe para realizar operações no banco de dados relacionadas a usuários.
 */
public class UsuarioDAO {
    /**
     * Verifica se um usuário com o nome de usuário e senha fornecidos existe no banco de dados.
     * @param nomeUsuario O nome de usuário a ser verificado.
     * @param senha A senha a ser verificada.
     * @return true se o usuário existir, false caso contrário.
     */
    public boolean checkLogin(String nomeUsuario, String senha) {
        // Define a query SQL para buscar o usuário
        String sql = "SELECT * FROM usuarios WHERE nome_usuario = ? AND senha = ?";
        boolean check = false;

        // Usa try-with-resources para garantir que a conexão e o PreparedStatement sejam fechados
        try (Connection conn = ConexaoMySQL.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Define os parâmetros da query
            stmt.setString(1, nomeUsuario);
            stmt.setString(2, senha);

            // Executa a query e obtém o resultado
            try (ResultSet rs = stmt.executeQuery()) {
                // Se o ResultSet tiver pelo menos uma linha, o usuário existe
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (SQLException e) {
            // Em caso de erro, imprime o stack trace
            // Em uma aplicação real, um tratamento de erro mais robusto seria necessário (logs, etc.)
            e.printStackTrace();
        }

        return check;
    }
}
