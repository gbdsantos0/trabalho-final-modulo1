package com.dbc.pokesuits.dto.mailconnect;

import lombok.Data;

@Data
public class ValidatedUser {
    private String username;
    private Boolean valid;
}
