package org.example.DAO;

import org.example.Connection.ConexaoMySQL;
import org.example.DTO.DoadorDTO;
import org.example.DTO.PessoaFisicaDTO;
import org.example.DTO.PessoaJuridicaDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DoadorDAO {

    public void cadastrarDoador(DoadorDTO doador) throws SQLException {

        String sql = "insert into doadaor_pessoafisica_pessoajuridica(frequencia_doacao," +
                " preferencia_meio_contato, telefone, email, nome_completo, cpf, rg, razao_social, cnpj, nome_contato," +
                "numero, logradouro, bairro, cidade, estado, complemento, tipo_doador) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
        ConexaoMySQL conexao = new ConexaoMySQL();
        Connection conn = conexao.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        try{

            stmt.setString(1, doador.getFrequencia_doacao());
            stmt.setString(2, doador.getPreferencia_meio_contato());
            stmt.setString(3, doador.getTelefone());
            stmt.setString(4, doador.getEmail());
            if(doador instanceof PessoaFisicaDTO){
                stmt.setString(5, ((PessoaFisicaDTO)doador).getNome());
                stmt.setString(6, ((PessoaFisicaDTO)doador).getCpf());
                stmt.setString(7, ((PessoaFisicaDTO)doador).getRg());
                stmt.setString(8,null);
                stmt.setString(9,null);
                stmt.setString(10, null);
            }else if(doador instanceof PessoaJuridicaDTO) {
                stmt.setString(5, null);
                stmt.setString(6, null);
                stmt.setString(7, null);
                stmt.setString(8, ((PessoaJuridicaDTO) doador).getRazao_social());
                stmt.setString(9, ((PessoaJuridicaDTO) doador).getCnpj());
                stmt.setString(10, ((PessoaJuridicaDTO) doador).getNome_contato());
            }
            stmt.setInt(11,doador.getNumero());
            stmt.setString(12,doador.getLogradouro());
            stmt.setString(13,doador.getBairro());
            stmt.setString(14,doador.getCidade());
            stmt.setString(15,doador.getEstado());
            stmt.setString(16,doador.getComplemento());
            stmt.setString(17,doador.getTipo_doador());
            stmt.executeUpdate();




        }finally {
            if(stmt != null){
                stmt.close();
            }
            if(conn != null){
                conn.close();
            }
        }

    }
}
