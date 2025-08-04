package org.example.DTO;

public class DiaSemanaVoluntarioDTO {

    private int id_semana_voluntario;
    private int id_doador;
    private String dia_semana;
    private String turno;

    public DiaSemanaVoluntarioDTO(int id_disponibilidade_voluntario, int id_doador, String dia_semana, String turno) {
        this.id_semana_voluntario = id_disponibilidade_voluntario;
        this.id_doador = id_doador;
        this.dia_semana = dia_semana;
        this.turno = turno;
    }

    public int getId_semana_voluntario() {
        return id_semana_voluntario;
    }

    public void setId_semana_voluntario(int id_semana_voluntario) {
        this.id_semana_voluntario = id_semana_voluntario;
    }

    public int getId_doador() {
        return id_doador;
    }

    public void setId_doador(int id_doador) {
        this.id_doador = id_doador;
    }

    public String getDia_semana() {
        return dia_semana;
    }

    public void setDia_semana(String dia_semana) {
        this.dia_semana = dia_semana;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
}
