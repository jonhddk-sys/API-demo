package Teste.de.dependencia.demo.service;

import Teste.de.dependencia.demo.dto.CreateUsuarioDto;
import Teste.de.dependencia.demo.dto.LoginUserDto;
import Teste.de.dependencia.demo.entitys.UsuariosEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import Teste.de.dependencia.demo.repository.UsuariosRepository;

@Slf4j
@Service
public class AuthService {
    @Autowired
    private UsuariosRepository userrepository;

    @Autowired
    private JwtService jwtService;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void create(CreateUsuarioDto user){
        boolean exixte = userrepository.existsByEmail(user.email());

        if(exixte){
            throw new RuntimeException("Usuario existente");
        }else {
            UsuariosEntity userEntity = new UsuariosEntity();
            userEntity.setEmail(user.email());
            userEntity.setSenha(passwordEncoder.encode(user.senha()));
            userEntity.setNome(user.nome());
            userrepository.save(userEntity);
        }
    }


    public String login(LoginUserDto dto){
        UsuariosEntity usuario = userrepository.findByEmail(dto.email());
        if(usuario == null){
            throw new RuntimeException("Esse usuario nao existe");
        }
        boolean correto = passwordEncoder.matches(dto.senha(), usuario.getSenha());
        if(!correto){
            throw new RuntimeException("Senha Incorreta");
        }

        return jwtService.generateToken(dto);

    }

}
