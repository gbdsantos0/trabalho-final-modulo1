package com.pokesuits.pokebase.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pokesuits.pokebase.entity.UserEntity;
import com.pokesuits.pokebase.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<UserEntity> optionalUsuario = userService.findByUsername(login);//busca usuario
        
        if(optionalUsuario.isPresent()){//se existe
            return optionalUsuario.get();//retorna o usuario encontrado
        }
        throw new UsernameNotFoundException("User not found!");
    }
}
