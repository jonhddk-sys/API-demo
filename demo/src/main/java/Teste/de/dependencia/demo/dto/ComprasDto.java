package Teste.de.dependencia.demo.dto;

import Teste.de.dependencia.demo.entitys.ComprasEntity;

public record ComprasDto(Long idproduto, Long idusuario) {
    public static ComprasDto fromEntity(ComprasEntity entity) {
        return new ComprasDto(
                entity.getProduto().getIdproduto(),
                entity.getUsuario().getIdUsuario()
        );
    }

}
