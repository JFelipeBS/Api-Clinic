package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.domain.dto.UserDto;
import med.voll.api.domain.entities.UserEntity;
import med.voll.api.infra.security.DataTokenJWT;
import med.voll.api.infra.security.TokenService;

@RestController
@RequestMapping("/login")
public class UserController {

    @Autowired
    AuthenticationManager manager;

    @Autowired
    TokenService service;

    @PostMapping
    public ResponseEntity<DataTokenJWT> login(@RequestBody @Valid UserDto data) {
        var token = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authentication = manager.authenticate(token);
        var tokenJWT = service.generatorToken((UserEntity) authentication.getPrincipal());
        return ResponseEntity.ok(new DataTokenJWT(tokenJWT));

    }

}
