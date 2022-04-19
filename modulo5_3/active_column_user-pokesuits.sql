create schema vem_ser;

set schema 'vem_ser';

create table USER_POKESUITS (
  id_user numeric not null,
  nome text not null,
  password text not null,
  username text not null,
  email text not null,
  active boolean not null,
  primary key(id_user)
);

create table TREINADOR (
  id_treinador numeric not null,
  id_user numeric not null,
  nome text not null,
  sexo numeric not null,
  PRIMARY KEY (id_treinador),
  CONSTRAINT FK_TREINADOR_id_user
    FOREIGN KEY (id_user)
      REFERENCES USER_POKESUITS(id_user)
);

create table MOCHILA (
  id_mochila numeric not null,
  id_treinador numeric not null,
  quantidadeGreatBalls numeric not null,
  quantidadeHeavyBalls numeric not null,
  quantidadeMasterBalls numeric not null,
  quantidadeNetBalls numeric not null,
  quantidadePokeBalls numeric not null,
  PRIMARY KEY (id_mochila),
  CONSTRAINT FK_MOCHILA_id_treinador
    FOREIGN KEY (id_treinador)
      REFERENCES TREINADOR(id_treinador)
  
);

create table POKEMON (
  id_pokemon numeric not null,
  racaPokemon text not null,
  peso decimal(5,2) not null,
  sexo numeric not null,
  nome text,
  dificuldade numeric not null,
  level numeric not null,
  tipo1 numeric not null,
  tipo2 numeric,
  raridade numeric not null,
  id_mochila numeric not null,
  PRIMARY KEY (id_pokemon),
  CONSTRAINT FK_POKEMON_id_mochila
    FOREIGN KEY (id_mochila)
      REFERENCES MOCHILA(id_mochila)
);

create sequence IF NOT EXISTS seq_pokemon
 increment 1
 start 1;
 
create sequence IF NOT EXISTS seq_treinador
 increment 1
 start 1;
 
create sequence IF NOT exists seq_mochila
 increment 1
 start 1;
 
create sequence IF NOT exists seq_user
 increment 1
 start 1;

create table REGRA (
  id_regra numeric not null,
  nome text not null,
  primary key(id_regra)
);


create table USER_REGRA (
	id_user numeric not null,
	id_regra numeric not null,
	CONSTRAINT FK_USER_REGRA_id_user
    FOREIGN KEY (id_user)
      REFERENCES USER_POKESUITS(id_user),
    CONSTRAINT FK_USER_REGRA_id_regra
    FOREIGN KEY (id_regra)
      REFERENCES REGRA(id_regra)
);

create sequence IF NOT EXISTS seq_regra
 increment 1
 start 1;
 
insert into REGRA
values (nextval('seq_regra'), 'ROLE_ADMIN');

insert into REGRA
values (nextval('seq_regra'), 'ROLE_LEAGUE_CHAMPION');

insert into REGRA
values (nextval('seq_regra'), 'ROLE_USER');

insert into user_pokesuits
values (1, 'admin', '$2a$10$sxzzh2Jws6afyLNxynJcfu9rRUOf6e.ZuDgjQeD0EyED/eUWf6Iq.', 'Administrator', 'admin@adm.com', true);