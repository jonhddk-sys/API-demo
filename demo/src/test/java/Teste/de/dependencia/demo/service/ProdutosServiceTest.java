package Teste.de.dependencia.demo.service;

import Teste.de.dependencia.demo.dto.ProdutoDto;
import Teste.de.dependencia.demo.entitys.ProdutosEntity;
import Teste.de.dependencia.demo.repository.ProdutosRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutosServiceTest {

    @InjectMocks
    private ProdutosService produtosService;

    @Mock
    private ProdutosRepository produtosRepository;



    @Test
    void createcase1() {
        ProdutoDto input = new ProdutoDto("frango",new BigDecimal("20"));

        when(produtosRepository.existsByProduto(input.produto())).thenReturn(false);

        produtosService.create(input);
        verify(produtosRepository).save(any(ProdutosEntity.class));

    }

    @Test
    void createcase2() {
        ProdutoDto input = new ProdutoDto("frango",new BigDecimal("20"));

        when(produtosRepository.existsByProduto(input.produto())).thenReturn(true);

        assertThrows(RuntimeException.class, () -> produtosService.create(input));
        verify(produtosRepository,never()).save(any(ProdutosEntity.class));
    }

    @Test
    void updatecase1() {
        ProdutoDto input = new ProdutoDto("frango",new BigDecimal("20"));
        ProdutosEntity novo = new ProdutosEntity();
        Long id = 1L;

        when(produtosRepository.existsByIdproduto(id)).thenReturn(true);
        when(produtosRepository.findByIdproduto(id)).thenReturn(novo);

        produtosService.change(input,id);
        verify(produtosRepository).save(any(ProdutosEntity.class));
    }

    @Test
    void updatecase2() {
        ProdutoDto input = new ProdutoDto("frango",new BigDecimal("20"));
        Long id = 1L;

        when(produtosRepository.existsByIdproduto(id)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> produtosService.change(input,id));
        verify(produtosRepository,never()).save(any(ProdutosEntity.class));

    }

    @Test
    void deletecase1() {
        Long id = 1L;
        when(produtosRepository.existsByIdproduto(id)).thenReturn(true);
        produtosService.delete(id);
        verify(produtosRepository).deleteById(id);
    }

    @Test
    void deletecase2() {
        Long id = 1L;
        when(produtosRepository.existsByIdproduto(id)).thenReturn(false);
        assertThrows(RuntimeException.class, () -> produtosService.delete(id));
        verify(produtosRepository,never()).deleteById(id);
    }

}