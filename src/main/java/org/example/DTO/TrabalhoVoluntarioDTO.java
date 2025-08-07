package org.example.DTO;

import java.time.LocalDate;

public class TrabalhoVoluntarioDTO extends DoacaoDTO{
    private String descricao_serviço;
    private LocalDate data_inicio;
    private LocalDate data_final;

    public TrabalhoVoluntarioDTO(LocalDate data, String tipo_doacao, int id_doacao, Integer id_doador, Integer id_campanha, String descricao_serviço, LocalDate data_inicio, LocalDate data_final) {
        super(data, tipo_doacao, id_doacao, id_doador, id_campanha);
        this.descricao_serviço = descricao_serviço;

        this.data_inicio = data_inicio;
        this.data_final = data_final;
    }

    public LocalDate getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(LocalDate data_inicio) {
        this.data_inicio = data_inicio;
    }

    public LocalDate getData_final() {
        return data_final;
    }

    public void setData_final(LocalDate data_final) {
        this.data_final = data_final;
    }

    public String getDescricao_serviço() {
        return descricao_serviço;
    }

    public void setDescricao_serviço(String descricao_serviço) {
        this.descricao_serviço = descricao_serviço;
    }

}
