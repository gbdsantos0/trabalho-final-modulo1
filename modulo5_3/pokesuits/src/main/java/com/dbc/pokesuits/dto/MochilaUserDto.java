package com.dbc.pokesuits.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MochilaUserDto {

	private int quantidadeGreatBalls;	
	private int quantidadeHeavyBalls;
	private int quantidadeMasterBalls;
	private int quantidadeNetBalls;
	private int quantidadePokeBalls;
	private String email;
	private String nome;

}
