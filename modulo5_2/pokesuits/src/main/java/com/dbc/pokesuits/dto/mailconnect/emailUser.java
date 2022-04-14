package com.dbc.pokesuits.dto.mailconnect;

import lombok.Data;

@Data
public class emailUser {
    private String username;
    private String email;
    private Operacao operacao;
}
