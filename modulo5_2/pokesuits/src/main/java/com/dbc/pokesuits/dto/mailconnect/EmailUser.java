package com.dbc.pokesuits.dto.mailconnect;

import lombok.Data;

@Data
public class EmailUser {
    private String username;
    private String email;
    private Operation operation;
}
