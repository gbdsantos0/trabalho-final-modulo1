package com.dbc.emailhandler.dto;

import lombok.Data;

@Data
public class ValidatedUserDTO {
    private String username;
    private Boolean valid;
}
