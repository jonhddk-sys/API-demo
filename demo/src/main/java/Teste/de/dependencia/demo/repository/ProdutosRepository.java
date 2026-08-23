package Teste.de.dependencia.demo.repository;

import Teste.de.dependencia.demo.entitys.ProdutosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepository  extends JpaRepository<ProdutosEntity,Long> {
    boolean existsByIdproduto(Long idproduto);
    boolean existsByProduto(String produto);
    ProdutosEntity findByProduto(String produto);
    ProdutosEntity findByIdproduto(Long idproduto);
}
