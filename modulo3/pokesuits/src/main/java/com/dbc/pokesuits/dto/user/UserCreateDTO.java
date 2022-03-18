package com.dbc.pokesuits.dto.user;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserCreateDTO {
	
	private String nome;
	private String email;
	private Integer password;
	private String username;
	private ArrayList<Integer> idTreinadores;
	
}
