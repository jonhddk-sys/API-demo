package Teste.de.dependencia.demo.dto;

import Teste.de.dependencia.demo.entitys.ComprasEntity;

import java.math.BigDecimal;

public record ComprasDto(Long idproduto, Long idusuario, BigDecimal qtdCompra) {
    public static ComprasDto fromEntity(ComprasEntity entity) {
        return new ComprasDto(
                entity.getProduto().getIdproduto(),
                entity.getUsuario().getIdUsuario(),
                entity.getQtdCompra()
        );
    }

}
