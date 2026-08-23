package Teste.de.dependencia.demo.service;

import Teste.de.dependencia.demo.dto.ProdutoDto;
import Teste.de.dependencia.demo.entitys.ProdutosEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Teste.de.dependencia.demo.repository.ProdutosRepository;

import java.util.List;

@Slf4j
@Service
public class ProdutosService {
    @Autowired
    private ProdutosRepository produtosRepository;

    public void create(ProdutoDto dto) {
        boolean existe = produtosRepository.existsByProduto(dto.produto());
        if (existe) {
            throw new RuntimeException("Produto existente");
        } else {
            ProdutosEntity produto = new ProdutosEntity();
            produto.setProduto(dto.produto());
            produto.setValor(dto.valor());
            produtosRepository.save(produto);
        }
    }

    public void change(ProdutoDto dto,Long idproduto) {
        boolean existe = produtosRepository.existsByIdproduto(idproduto);
        if (!existe) {
            throw new RuntimeException("Produto inexistente");
        }
        ProdutosEntity produto = produtosRepository.findByIdproduto(idproduto);
        produto.setProduto(dto.produto());
        produto.setValor(dto.valor());
        produtosRepository.save(produto);
    }

    public void delete(Long idproduto){
        boolean existe = produtosRepository.existsByIdproduto(idproduto);
        if (!existe) {
            throw new RuntimeException("Produto inexistente");
        }
        produtosRepository.deleteById(idproduto);
    }

    public List<ProdutosEntity> show(){
        return  produtosRepository.findAll();
    }

}


