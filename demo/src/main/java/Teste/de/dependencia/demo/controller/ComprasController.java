package Teste.de.dependencia.demo.controller;

import Teste.de.dependencia.demo.dto.ComprasDto;
import Teste.de.dependencia.demo.entitys.ComprasEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Teste.de.dependencia.demo.service.ComprasService;

import java.util.List;

@RestController
@RequestMapping("/compras")
public class ComprasController {

    @Autowired
    private ComprasService service;

    @PostMapping
    public ResponseEntity<String> buy(@RequestBody ComprasDto dto) {
        service.buy(dto);
        return ResponseEntity.status(HttpStatus.OK).body("Compra realizada com sucesso");
    }
    @DeleteMapping
    public ResponseEntity<String> delete(@RequestBody Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Compra removida com sucesso");
    }

    @GetMapping
    public ResponseEntity<List<ComprasDto>> show() {
        List<ComprasEntity> compras = service.show();

        List<ComprasDto> dtos = compras.stream()
                .map(ComprasDto::fromEntity)
                .toList();

        return ResponseEntity.ok(dtos);
    }


}
