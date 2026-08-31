package Teste.de.dependencia.demo.service;

import Teste.de.dependencia.demo.dto.CreateUsuarioDto;
import Teste.de.dependencia.demo.dto.LoginUserDto;
import Teste.de.dependencia.demo.entitys.UsuariosEntity;
import Teste.de.dependencia.demo.repository.UsuariosRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UsuariosRepository usersRepository;

    @Mock
    private JwtService tokenService;

    @InjectMocks
    private AuthService authService;

    @Mock
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    void createcase1() {
        CreateUsuarioDto input = new CreateUsuarioDto(
                "jonh",
                "123",
                "jonh@gmail.com"
        );

        when(usersRepository.existsByEmail("jonh@gmail.com")).thenReturn(false);
        when(passwordEncoder.encode("123")).thenReturn("senhaCriptografada");

        authService.create(input);

        verify(usersRepository).save(any(UsuariosEntity.class));

    }

    @Test
    void createcase2() {
        CreateUsuarioDto input = new CreateUsuarioDto(
                "bot",
                "123",
                "bot@gmail"
        );

        when(usersRepository.existsByEmail("bot@gmail")).thenReturn(true);

        assertThrows(RuntimeException.class, () -> authService.create(input));

        verify(usersRepository, never()).save(any(UsuariosEntity.class));
    }




    @Test
    void logincase1() {
        LoginUserDto dto = new LoginUserDto(
                "bot@gmail.com",
                "123"
        );

        UsuariosEntity usuario = new UsuariosEntity();
        usuario.setEmail("bot@gmail.com");
        usuario.setSenha("senhacriptografada");


        when(usersRepository.findByEmail("bot@gmail.com")).thenReturn(usuario);
        when(passwordEncoder.matches("123", "senhacriptografada")).thenReturn(true);
        when(tokenService.generateToken(dto)).thenReturn("token");

        String resultado =  authService.login(dto);

        assertEquals("token", resultado);

    }

    @Test
    void logincase2() {
        LoginUserDto dto = new LoginUserDto(
                "bot@gmail",
                "123"

        );

        when(usersRepository.findByEmail("bot@gmail")).thenReturn(null);
        assertThrows(RuntimeException.class, () -> authService.login(dto));

        verify(tokenService,never()).generateToken(any());
    }

}

