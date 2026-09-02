package Teste.de.dependencia.demo.controller;

import Teste.de.dependencia.demo.dto.ProdutoDto;
import Teste.de.dependencia.demo.entitys.ProdutosEntity;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Teste.de.dependencia.demo.service.ProdutosService;

import java.util.List;

@RestController
@RequestMapping("/Produtos")
@SecurityRequirement(name = "bearer-key")
public class ProdutoController {
    @Autowired
    private ProdutosService service;

    @PostMapping("/criar_produto")
    public ResponseEntity<String> create(@RequestBody ProdutoDto dto){
        service.create(dto);
        return ResponseEntity.ok().body("Produto criado com sucesso");
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestBody Long id){
        service.delete(id);
        return ResponseEntity.ok().body("Produto deletado com sucesso");
    }

    @PutMapping("{id}")
    public ResponseEntity<String> update(@RequestBody ProdutoDto dto,@PathVariable long id){
        service.change(dto, id);
        return ResponseEntity.ok().body("Produto modificado com sucesso");
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDto>> findAll() {
        List<ProdutosEntity> all = service.show();
        List<ProdutoDto> dtos = all.stream()
                .map(p -> new ProdutoDto(p.getProduto(), p.getValor()))
                .toList();
        return ResponseEntity.ok(dtos);
    }




}
