package com.dbc.emailhandler.dto;

import lombok.Data;

@Data
public class EmailUser {
    private String username;
    private String email;
    private Operation operation;
}
