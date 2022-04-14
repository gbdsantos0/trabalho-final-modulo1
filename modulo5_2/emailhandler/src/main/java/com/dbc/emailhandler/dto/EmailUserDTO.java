package com.dbc.emailhandler.dto;

import lombok.Data;

@Data
public class EmailUserDTO {
    private String username;
    private String name;
    private String email;
    private Operation operation;
}
