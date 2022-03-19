package com.dbc.pokesuits.dto.pokemon;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.dbc.pokesuits.enums.Dificuldades;
import com.dbc.pokesuits.enums.Raridades;
import com.dbc.pokesuits.enums.TipoPokemon;
import com.dbc.pokesuits.enums.Utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PokemonCreateDTO {
	
	@NotEmpty
	@ApiModelProperty(name = "Raça do Pokemon Ex.: Bulbassauro")
	private String racaPokemon;
	@Min(0)@Max(500)
	@ApiModelProperty(name = "Peso do Pokemon")
	private Double peso;
	@NotNull
	@ApiModelProperty(name = "Sexo do Pokemon")
	private Utils sexo;
	@ApiModelProperty(name = "Apelido do Pokemon")
    private String nome;
    @NotNull@Min(0)@Max(500)
    @ApiModelProperty(name = "Nivel do Pokemon")
    private Integer level;
    @NotNull
    @ApiModelProperty(name = "Nivel de Dificuldade de a Captura do Pokemon")
    private Dificuldades dificuldade;
    @NotNull
    @ApiModelProperty(name = "Tipo de Pokemon Ex.: INSETO, ELÉTRICO ... ")
    private TipoPokemon tipo1;
    @ApiModelProperty(name = "Segundo Tipo do Pokemon")
    private TipoPokemon tipo2;
    @ApiModelProperty(name = "Chance De Aparição do Pokemon")
    private Raridades raridade;
    @NotNull
    @ApiModelProperty(name = "Id que Indica a qual mochila o Pokemon Pertence")
    private Integer idMochila;
	
	
}
