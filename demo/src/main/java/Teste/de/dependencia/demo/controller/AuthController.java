package Teste.de.dependencia.demo.controller;

import Teste.de.dependencia.demo.dto.CreateUsuarioDto;
import Teste.de.dependencia.demo.dto.LoginUserDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Teste.de.dependencia.demo.service.AuthService;


@RestController
@RequestMapping("/auth")
@SecurityRequirement(name = "bearer-key")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/registro")
    public ResponseEntity<String> registrarUsuario(@RequestBody CreateUsuarioDto createUsuarioDto) {
        authService.create(createUsuarioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Novo usuario criado com sucesso");

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginUserDto dto){
        String token = authService.login(dto);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}
