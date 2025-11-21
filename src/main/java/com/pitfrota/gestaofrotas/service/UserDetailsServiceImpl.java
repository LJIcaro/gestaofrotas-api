package com.pitfrota.gestaofrotas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pitfrota.gestaofrotas.model.Administrador;
import com.pitfrota.gestaofrotas.model.Motorista;
import com.pitfrota.gestaofrotas.repository.AdministradorRepository;
import com.pitfrota.gestaofrotas.repository.MotoristaRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.Optional; 

@Service("userDetailsService") // Nome do bean é importante para o Spring Security encontrar
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private MotoristaRepository motoristaRepository;

    @Autowired
    private AdministradorRepository administradorRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 1. Tenta encontrar como Administrador primeiro
        Optional<Administrador> adminOptional = administradorRepository.findByEmail(email);
        if (adminOptional.isPresent()) {
            Administrador admin = adminOptional.get();
            Set<GrantedAuthority> authorities = new HashSet<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMINISTRADOR"));
            return new User(admin.getEmail(), admin.getSenha(), 
                            admin.getStatus() == com.pitfrota.gestaofrotas.model.StatusUsuario.ATIVO, 
                            true, true, true, authorities);
        }
        
        // 2. Se não for admin, tenta encontrar como Motorista
        Motorista motorista = motoristaRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + email));

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_MOTORISTA"));
        
        return new User(motorista.getEmail(), motorista.getSenha(),
                        motorista.getStatus() == com.pitfrota.gestaofrotas.model.StatusUsuario.ATIVO,
                        true, true, true, grantedAuthorities);
    }
}