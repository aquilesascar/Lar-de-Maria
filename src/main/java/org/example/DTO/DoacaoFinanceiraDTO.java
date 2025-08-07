package org.example.DTO;

import java.time.LocalDate;

public class DoacaoFinanceiraDTO extends DoacaoDTO {
    private String forma_pagamento;
    private float valor_total;



    public DoacaoFinanceiraDTO(LocalDate data, String tipo_doacao, int id_doacao, Integer id_doador, Integer id_campanha, String forma_pagamento, float valor_total) {
        super(data, tipo_doacao, id_doacao, id_doador, id_campanha);
        this.forma_pagamento = forma_pagamento;
        this.valor_total = valor_total;
    }

    public String getForma_pagamento() {
        return forma_pagamento;
    }

    public void setForma_pagamento(String forma_pagamento) {
        this.forma_pagamento = forma_pagamento;
    }

    public float getValor_total() {
        return valor_total;
    }

    public void setValor_total(int valor_total) {
        this.valor_total = valor_total;
    }
}
