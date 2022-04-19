package com.dbc.pokesuits.dto.mailconnect;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailUserDTO {
    private String username;
    private String name;
    private String email;
    private Operation operation;
}
