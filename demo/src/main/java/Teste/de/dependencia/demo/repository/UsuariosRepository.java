package Teste.de.dependencia.demo.repository;

import Teste.de.dependencia.demo.entitys.UsuariosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<UsuariosEntity,Long> {
    boolean existsByEmail(String email);
    UsuariosEntity findByEmail(String email);
}
