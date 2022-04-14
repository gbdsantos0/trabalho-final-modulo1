package com.dbc.emailhandler.dto;

import lombok.Data;

@Data
public class ValidatedUser {
    private String username;
    private Boolean valid;
}
