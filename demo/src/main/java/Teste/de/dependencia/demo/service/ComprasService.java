package Teste.de.dependencia.demo.service;

import Teste.de.dependencia.demo.dto.ComprasDto;
import Teste.de.dependencia.demo.entitys.ComprasEntity;
import Teste.de.dependencia.demo.entitys.ProdutosEntity;
import Teste.de.dependencia.demo.entitys.UsuariosEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Teste.de.dependencia.demo.repository.ComprasRepository;
import Teste.de.dependencia.demo.repository.ProdutosRepository;
import Teste.de.dependencia.demo.repository.UsuariosRepository;

import java.util.List;

@Service
public class ComprasService {
    @Autowired
    private ProdutosRepository produtosRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private ComprasRepository comprasRepository;

    public void buy(ComprasDto dto){
        boolean user = usuariosRepository.existsById(dto.idusuario());
        boolean produto = produtosRepository.existsById(dto.idproduto());

        if(!user || !produto){
            throw new RuntimeException("Usuario ou Produto nao encontrado");
        }

        UsuariosEntity usuario = usuariosRepository.getReferenceById(dto.idusuario());
        ProdutosEntity produtor =  produtosRepository.getReferenceById(dto.idproduto());


        ComprasEntity compras = new ComprasEntity();
        compras.setUsuario(usuario);
        compras.setProduto(produtor);
        compras.setQtdCompra(dto.qtdCompra());
        comprasRepository.save(compras);

    }

    public void delete(Long idcompra){
        boolean exixte =  comprasRepository.existsById(idcompra);
        if(!exixte){
            throw new RuntimeException("Compra não encontrada");
        }
        comprasRepository.deleteById(idcompra);
    }

    public List<ComprasEntity> show(){
        return comprasRepository.findAll();
    }
}
