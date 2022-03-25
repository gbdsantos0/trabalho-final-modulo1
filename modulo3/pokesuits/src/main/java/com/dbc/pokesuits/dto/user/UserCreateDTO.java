package com.dbc.pokesuits.dto.user;

import java.util.ArrayList;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.dbc.pokesuits.config.validation.ValidPassword;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserCreateDTO {
	
	@NotEmpty
	@ApiModelProperty(value = "Nome do Usuário")
	private String nome;
	@Email
	@ApiModelProperty(value = "Email Do Usuário")
	private String email;
	@ApiModelProperty(value = "Senha Do Usuário")
	@ValidPassword
	private String password;
	@NotEmpty@Min(6)@Max(15)
	@ApiModelProperty(value = "Username do Usuário")
	private String username;
	@ApiModelProperty(value = "IDs dos Treinadores Pokemon")
	private ArrayList<Integer> idTreinadores;
	
}
