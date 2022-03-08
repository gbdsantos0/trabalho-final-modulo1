package com.dbc.model.entidades;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

import com.dbc.model.enums.Dificuldades;
import com.dbc.model.enums.Raridades;
import com.dbc.model.enums.TipoPokemon;
import com.dbc.model.enums.Utils;
import com.dbc.model.interfaces.Impressao;

public class Pokemon extends Entidade implements Impressao {

	private Integer id;
    private String pokemon;
    private Integer level;
    //Usamos Enumerations para Padronizar os atributos que influenciam na captura
    private final Dificuldades dificuldade;
    private final TipoPokemon[] tipo;
    private final Raridades raridade;
    private Integer idMochila;

    public Pokemon(String nome, String pokemon, Integer idade, Double peso, Utils sexo,
                   Dificuldades dificuldade, Integer level, TipoPokemon tipo1, TipoPokemon tipo2,
                   Raridades raridade, Integer idMochila) {
        super(null, idade, peso, sexo);
        this.pokemon = pokemon;
        this.dificuldade = dificuldade;
        this.level = level;
        //checa se o Pokemon tem somente um atributo ou se ele tem dois
        this.tipo = (tipo2 == null) ? new TipoPokemon[]{tipo1} : new TipoPokemon[]{tipo1, tipo2};
        this.raridade = raridade;
		this.idMochila = idMochila;
    }


    //getter e setters
	public Integer getIdMochila() {
		return idMochila;
	}
	public void setIdMochila(Integer idMochila) {
		this.idMochila = idMochila;
	}
    public String getPokemon() {
		return this.pokemon;
	}
	public void setPokemon(String pokemon) {
		this.pokemon = pokemon;
	}
    public int getLevel() {return level;}
	public void setLevel(int level) {this.level = level;}
    public Dificuldades getDificuldade() {return dificuldade;}
    public TipoPokemon[] getTipo() {return tipo;}
	public void setId(Integer id) {this.id = id;}
	public Integer getId() {return id;}
	public Raridades getRaridade() {return raridade;}
	
    public String getStringTipos() {
        List<TipoPokemon> n = Arrays.stream(this.getTipo()).toList();
        TipoPokemon a = n.get(0);
        if(n.size() > 1){
            TipoPokemon b = n.get(1);
            return a.toString() + " " + b.toString();
        }
        return a.toString();
    }
    
    //talvez retirar
    @Override
    public void imprimir() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#0.00");
        return  "nome:"+ this.getPokemon() +
                "\nApelido:"+ super.getNome() +
                "\nidade: " + super.getIdade() +
                "\npeso: " + df.format(super.getPeso() )+
                "\nsexo: " + ((super.getSexo() == Utils.MASCULINO)?"Masculino":"Feminino") +
                "\nTipo de Pokemon: " + this.getStringTipos() +
                " \nraridade: " + this.getRaridade();
    }
}
