package com.dbc.emailhandler.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class MochilaUserDto {

	private int quantidadeGreatBalls;	
	private int quantidadeHeavyBalls;
	private int quantidadeMasterBalls;
	private int quantidadeNetBalls;
	private int quantidadePokeBalls;
	@ToString.Exclude
	private String email;
	@ToString.Exclude
	private String nome;

	public String toHtml(){
		return "<br>Pokeballs: "+quantidadePokeBalls
		+"</br><br>Greatballs: "+quantidadeGreatBalls
		+"</br><br>Heavyballs: "+quantidadeHeavyBalls
		+"</br><br>Netballs: "+quantidadeNetBalls
		+"</br><br>Masterballs: "+quantidadeMasterBalls
		+"</br>";
	}

}
