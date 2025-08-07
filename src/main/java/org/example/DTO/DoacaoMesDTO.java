package org.example.DTO;

public class DoacaoMesDTO {
    private int mes;
    private float valor_medio;

    public DoacaoMesDTO(int mes, float valor_medio) {
        this.mes = mes;
        this.valor_medio = valor_medio;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public float getValor_medio() {
        return valor_medio;
    }

    public void setValor_medio(float valor_medio) {
        this.valor_medio = valor_medio;
    }
}
