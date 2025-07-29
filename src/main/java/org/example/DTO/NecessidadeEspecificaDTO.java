package org.example.DTO;

import java.time.LocalDateTime;

public class NecessidadeEspecificaDTO {

    private int idNecessidade;
    private int idCrianca;
    private int idAlocacaoRecurso;
    private String descricao;
    private String status;
    private LocalDateTime dataRegistro;

    // Getters e Setters para todos os campos

    public int getIdNecessidade() {
        return idNecessidade;
    }

    public void setIdNecessidade(int idNecessidade) {
        this.idNecessidade = idNecessidade;
    }

    public int getIdCrianca() {
        return idCrianca;
    }

    public void setIdCrianca(int idCrianca) {
        this.idCrianca = idCrianca;
    }

    public int getIdAlocacaoRecurso() {
        return idAlocacaoRecurso;
    }

    public void setIdAlocacaoRecurso(int idAlocacaoRecurso) {
        this.idAlocacaoRecurso = idAlocacaoRecurso;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }
}
