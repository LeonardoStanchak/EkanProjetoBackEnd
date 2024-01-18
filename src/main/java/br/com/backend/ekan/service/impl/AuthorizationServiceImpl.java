package br.com.backend.ekan.service.impl;

import br.com.backend.ekan.dto.AuthetinticationDto;
import br.com.backend.ekan.dto.LoginResponseDTO;
import br.com.backend.ekan.dto.RegisterDTO;
import br.com.backend.ekan.model.UserModel;
import br.com.backend.ekan.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Date;
@Service
public class AuthorizationServiceImpl implements UserDetailsService {
    @Autowired
    private ApplicationContext context;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenServiceImpl tokenServiceImpl;

    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }

    public ResponseEntity<Object> login(@RequestBody @Valid AuthetinticationDto data){
        authenticationManager = context.getBean(AuthenticationManager.class);

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenServiceImpl.generateToken((UserModel) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }


    public ResponseEntity<Object> register (@RequestBody RegisterDTO registerDto){
        if (this.userRepository.findByEmail(registerDto.email()) != null ) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.password());

        UserModel newUser = new UserModel(registerDto.email(), encryptedPassword, registerDto.role());
        newUser.setCreatedAt(new Date(System.currentTimeMillis()));
        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }




}