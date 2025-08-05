package org.example.DTO;

import java.sql.Date;
import java.time.LocalDate;

public class DoacaoDTO {
    private LocalDate data;
    private String tipo_doacao;
    private int id_doacao;
    private Integer id_doador;
   private Integer id_campanha;

    public DoacaoDTO(LocalDate data, String tipo_doacao, int id_doacao, Integer id_doador, Integer id_campanha) {
        this.data = data;
        this.tipo_doacao = tipo_doacao;
        this.id_doacao = id_doacao;
        this.id_doador = id_doador;
        this.id_campanha = id_campanha;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getTipo_doacao() {
        return tipo_doacao;
    }

    public void setTipo_doacao(String tipo_doacao) {
        this.tipo_doacao = tipo_doacao;
    }

    public int getId_doacao() {
        return id_doacao;
    }

    public void setId_doacao(int id_doacao) {
        this.id_doacao = id_doacao;
    }

    public Integer getId_doador() {
        return id_doador;
    }

    public void setId_doador(int id_doador) {
        this.id_doador = id_doador;
    }

    public Integer getId_campanha() {
        return id_campanha;
    }

    public void setId_campanha(int id_campanha) {
        this.id_campanha = id_campanha;
    }
}
