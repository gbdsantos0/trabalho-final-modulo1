package com.dbc.pokesuits.dto;


import com.dbc.pokesuits.enums.Utils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TreinadorCreateDTO {
    @ApiModelProperty(value="id da mochila")
    private int idMochila;
    @NotNull
    @ApiModelProperty(value="nome do treinador")
    private String nome;
    @NotNull
    @ApiModelProperty(value = "sexo do treinador!")
    private Utils sexo;
}
