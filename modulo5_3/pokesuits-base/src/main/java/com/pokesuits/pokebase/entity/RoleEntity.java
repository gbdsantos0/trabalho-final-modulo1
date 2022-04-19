package com.pokesuits.pokebase.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "roles")
public class RoleEntity implements Serializable, GrantedAuthority {
	
	private static final long serialVersionUID = 7475309520813546835L;

	@Id
	private String id;
	
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}