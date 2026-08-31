package Teste.de.dependencia.demo.service;

import Teste.de.dependencia.demo.dto.ComprasDto;
import Teste.de.dependencia.demo.entitys.ComprasEntity;
import Teste.de.dependencia.demo.entitys.ProdutosEntity;
import Teste.de.dependencia.demo.entitys.UsuariosEntity;
import Teste.de.dependencia.demo.repository.ComprasRepository;
import Teste.de.dependencia.demo.repository.ProdutosRepository;
import Teste.de.dependencia.demo.repository.UsuariosRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ComprasServiceTest {

    @InjectMocks
    private ComprasService comprasService;

    @Mock
    private ComprasRepository  comprasRepository;

    @Mock
    private UsuariosRepository usuariosRepository;

    @Mock
    private ProdutosRepository produtosRepository;


    @Test
    void buycase1() {
        ComprasDto compras = new ComprasDto(1L,1L,new BigDecimal("20"));

        when(usuariosRepository.existsById(compras.idusuario())).thenReturn(true);
        when(produtosRepository.existsById(compras.idproduto())).thenReturn(true);

        comprasService.buy(compras);

        verify(comprasRepository).save(any(ComprasEntity.class));
    }

    @Test
    void buycase2() {
        ComprasDto compras = new ComprasDto(1L,1L,new BigDecimal("20"));


        when(usuariosRepository.existsById(compras.idusuario())).thenReturn(false);
        when(produtosRepository.existsById(compras.idproduto())).thenReturn(true);

        assertThrows(RuntimeException.class, () -> comprasService.buy(compras));

        verify(comprasRepository, never()).save(any());
    }

    @Test
    void deletecase1() {
        Long idCompra = 1L;

        when(comprasRepository.existsById(idCompra)).thenReturn(true);

        comprasService.delete(idCompra);

        verify(comprasRepository).deleteById(idCompra);
    }

    @Test
    void deletecase2() {
        Long idCompra = 1L;
        when(comprasRepository.existsById(idCompra)).thenReturn(false);
        assertThrows(RuntimeException.class, () -> comprasService.delete(idCompra));

        verify(comprasRepository, never()).deleteById(idCompra);
    }
}
