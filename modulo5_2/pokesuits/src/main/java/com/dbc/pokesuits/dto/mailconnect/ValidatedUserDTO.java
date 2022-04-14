package com.dbc.pokesuits.dto.mailconnect;

import lombok.Data;

@Data
public class ValidatedUserDTO {
    private String username;
    private Boolean valid;
}
