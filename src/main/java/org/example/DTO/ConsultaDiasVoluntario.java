package org.example.DTO;

public class ConsultaDiasVoluntario {
    private String dia;
    private String nome;


    public ConsultaDiasVoluntario(String dia, String nome) {
        this.dia = dia;
        this.nome = nome;
    }

    public String getDia() {
        return dia;
    }

    public String getNome() {
        return nome;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
