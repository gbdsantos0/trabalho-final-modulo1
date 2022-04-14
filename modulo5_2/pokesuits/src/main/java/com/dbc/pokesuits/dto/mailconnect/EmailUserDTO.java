package com.dbc.pokesuits.dto.mailconnect;

import lombok.Data;

@Data
public class EmailUserDTO {
    private String name;
    private String username;
    private String email;
    private Operation operation;
}
