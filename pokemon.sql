CREATE TABLE "Cenario" (
  "id_cenario" NUMBER NOT NULL,
  "terreno" NUMBER NOT NULL,
  "levelMedio" NUMBER(3) NOT NULL,
  PRIMARY KEY ("id_cenario")
);

CREATE TABLE "Pokedex" (
  "id_pokedex" NUMBER NOT NULL,
  PRIMARY KEY ("id_pokedex")
);

CREATE TABLE "PokemonBase" (
  "id_pokemonBase" NUMBER NOT NULL,
  "raca" VARCHAR2(50) NOT NULL,
  "idadeMinima" NUMBER(3) NOT NULL,
  "pesoMinimo" DECIMAL(5,2) NOT NULL,
  "pesoMaximo" DECIMAL(5,2) NOT NULL,
  "porcentagemMacho" DECIMAL(6,2) NOT NULL,
  "levelMinimo" NUMBER(3) NOT NULL,
  "dificuldade" NUMBER NOT NULL,
  "tipo1" NUMBER NOT NULL,
  "tipo2" NUMBER,
  "raridade" NUMBER NOT NULL,
  PRIMARY KEY ("id_pokemonBase")
);

CREATE TABLE "Treinador" (
  "id_treinador" NUMBER NOT NULL,
  "nome" VARCHAR2(50) NOT NULL,
  "idade" NUMBER(3) NOT NULL,
  "peso" DECIMAL(5,2) NOT NULL,
  "sexo" CHAR(1) NOT NULL,
  PRIMARY KEY ("id_treinador")
);

CREATE TABLE "Mochila" (
  "id_mochila" NUMBER NOT NULL,
  "id_treinador" NUMBER NOT NULL,
  PRIMARY KEY ("id_mochila"),
  CONSTRAINT "FK_Mochila.id_treinador"
  	FOREIGN KEY ("id_treinador")
  		REFERENCES "Treinador"("id_treinador")
);

CREATE TABLE "Pokemon" (
  "id_pokemon" NUMBER NOT NULL,
  "nome" VARCHAR2(50) NOT NULL,
  "idade" NUMBER(3) NOT NULL,
  "peso" DECIMAL(5,2) NOT NULL,
  "sexo" CHAR(1) NOT NULL,
  "apelido" VARCHAR2(50),
  "dificuldade" NUMBER NOT NULL,
  "level" NUMBER(3) NOT NULL,
  "tipo1" NUMBER NOT NULL,
  "tipo2" NUMBER,
  "raridade" NUMBER NOT NULL,
  "id_mochila" NUMBER NOT NULL,
  PRIMARY KEY ("id_pokemon"),
  CONSTRAINT "FK_Pokemon.id_mochila"
  	FOREIGN KEY ("id_mochila")
  		REFERENCES "Mochila"("id_mochila")
);

CREATE SEQUENCE SEQ_POKEMON
 START WITH     1
 INCREMENT BY   1
 NOCACHE NOCYCLE;
 
CREATE SEQUENCE SEQ_TREINADOR
 START WITH     1
 INCREMENT BY   1
 NOCACHE NOCYCLE;
 
CREATE SEQUENCE SEQ_MOCHILA
 START WITH     1
 INCREMENT BY   1
 NOCACHE NOCYCLE;
 
CREATE SEQUENCE SEQ_POKEMONBASE
 START WITH     1
 INCREMENT BY   1
 NOCACHE NOCYCLE;
 
CREATE SEQUENCE SEQ_CENARIO
 START WITH     1
 INCREMENT BY   1
 NOCACHE NOCYCLE;
 
CREATE SEQUENCE SEQ_POKEDEX
 START WITH     1
 INCREMENT BY   1
 NOCACHE NOCYCLE;
 
/*DROP TABLE "Cenario";
DROP TABLE "Pokedex";
DROP TABLE "PokemonBase";
DROP TABLE "Pokemon";
DROP TABLE "Mochila";
DROP TABLE "Treinador";*/
  	
/*DROP SEQUENCE SEQ_POKEMON;
DROP SEQUENCE SEQ_TREINADOR;
DROP SEQUENCE SEQ_MOCHILA;
DROP SEQUENCE SEQ_POKEMONBASE;
DROP SEQUENCE SEQ_POKEDEX;
DROP SEQUENCE SEQ_CENARIO;*/


INSERT INTO "PokemonBase" 
VALUES (SEQ_POKEMONBASE.nextval, 'Bulbasauro', 20, 6.7, 11.0, 87.5, 5, 0, 0, 0, 0); --, Dificuldades.FACIL, TipoPokemon.GRASS, TipoPokemon.POISON, Raridades.COMUM

SELECT * FROM "PokemonBase";

INSERT INTO "Treinador"
VALUES (SEQ_TREINADOR.nextval, 'Nelson', 684, 45.0, 'M');

SELECT * FROM "Treinador";

INSERT INTO "Mochila"
VALUES (SEQ_MOCHILA.nextval, 1);

SELECT * FROM "Mochila";

INSERT INTO "Pokemon"
VALUES (SEQ_POKEMON.nextval, 'Bulbasauro', 20, 8.0, 'F', NULL, 0, 5, 0, 0, 0, 1);

SELECT * FROM "Pokemon";

INSERT INTO	"Cenario"
VALUES (SEQ_CENARIO.nextval, 0, 20);

SELECT * FROM "Cenario";

INSERT INTO "Pokedex"
VALUES (SEQ_POKEDEX.nextval);

SELECT * FROM "Pokedex";