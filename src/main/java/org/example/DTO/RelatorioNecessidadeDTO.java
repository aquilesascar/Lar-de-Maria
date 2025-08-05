package org.example.DTO;

/**
 * DTO para armazenar os dados agregados do relatório de necessidades por criança.
 */
public class RelatorioNecessidadeDTO {

    private String nomeCrianca;
    private String status;
    private int totalPorStatus;

    // Getters e Setters
    public String getNomeCrianca() {
        return nomeCrianca;
    }

    public void setNomeCrianca(String nomeCrianca) {
        this.nomeCrianca = nomeCrianca;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalPorStatus() {
        return totalPorStatus;
    }

    public void setTotalPorStatus(int totalPorStatus) {
        this.totalPorStatus = totalPorStatus;
    }
}
