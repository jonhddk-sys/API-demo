package Teste.de.dependencia.demo.dto.responseDto;

import Teste.de.dependencia.demo.entitys.ComprasEntity;

import java.math.BigDecimal;
import java.time.Instant;

public record ComprasResponseDto(Long idCompras,
                                 Long idUsuario,
                                 Long idProduto,
                                 BigDecimal qtdCompra,
                                 BigDecimal valorTotal,
                                 Instant dataCompra) {
    public static ComprasResponseDto fromEntity(ComprasEntity entity) {
        return new ComprasResponseDto(
                entity.getIdcompra(),
                entity.getProduto().getIdproduto(),
                entity.getUsuario().getIdUsuario(),
                entity.getQtdCompra(),
                entity.getValorTotal(),
                entity.getDataCompra()
        );
    }


}
