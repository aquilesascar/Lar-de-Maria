package org.example.View;

public class ComboAuxiliar {
    int id_doador;
    String nome_doador;

    @Override
    public String toString() {
        return nome_doador;
    }

    public ComboAuxiliar(int id_doador, String nome_doador) {
        this.id_doador = id_doador;
        this.nome_doador = nome_doador;
    }

    public int getId_doador() {
        return id_doador;
    }

    public void setId_doador(int id_doador) {
        this.id_doador = id_doador;
    }

    public String getNome_doador() {
        return nome_doador;
    }

    public void setNome_doador(String nome_doador) {
        this.nome_doador = nome_doador;
    }
}
