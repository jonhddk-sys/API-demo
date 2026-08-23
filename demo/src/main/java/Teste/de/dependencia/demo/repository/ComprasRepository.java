package Teste.de.dependencia.demo.repository;

import Teste.de.dependencia.demo.entitys.ComprasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComprasRepository extends JpaRepository<ComprasEntity,Long> {
}
