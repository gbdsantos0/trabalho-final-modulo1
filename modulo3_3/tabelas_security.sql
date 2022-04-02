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
values (nextval('seq_regra'), 'RULE_ADMIN');

insert into REGRA
values (nextval('seq_regra'), 'RULE_LEAGUE_CHAMPION');

insert into REGRA
values (nextval('seq_regra'), 'RULE_USER');
