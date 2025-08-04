package org.example.DTO;

import java.time.LocalDate;

public class DistribuicaoRecursoDTO {
    private int idDistribuicao;
    private int idAlocacaoRecurso;
    private int idCrianca;
    private int idMembroEquipe;
    private LocalDate dataDistribuicao;

    // Getters e Setters
    public int getIdDistribuicao() {
        return idDistribuicao;
    }

    public void setIdDistribuicao(int idDistribuicao) {
        this.idDistribuicao = idDistribuicao;
    }

    public int getIdAlocacaoRecurso() {
        return idAlocacaoRecurso;
    }

    public void setIdAlocacaoRecurso(int idAlocacaoRecurso) {
        this.idAlocacaoRecurso = idAlocacaoRecurso;
    }

    public int getIdCrianca() {
        return idCrianca;
    }

    public void setIdCrianca(int idCrianca) {
        this.idCrianca = idCrianca;
    }

    public int getIdMembroEquipe() {
        return idMembroEquipe;
    }

    public void setIdMembroEquipe(int idMembroEquipe) {
        this.idMembroEquipe = idMembroEquipe;
    }

    public LocalDate getDataDistribuicao() {
        return dataDistribuicao;
    }

    public void setDataDistribuicao(LocalDate dataDistribuicao) {
        this.dataDistribuicao = dataDistribuicao;
    }
}
