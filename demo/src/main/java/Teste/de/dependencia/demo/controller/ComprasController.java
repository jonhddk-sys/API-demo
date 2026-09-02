package Teste.de.dependencia.demo.controller;

import Teste.de.dependencia.demo.dto.ComprasDto;
import Teste.de.dependencia.demo.dto.responseDto.ComprasResponseDto;
import Teste.de.dependencia.demo.entitys.ComprasEntity;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Teste.de.dependencia.demo.service.ComprasService;

import java.util.List;

@RestController
@RequestMapping("/compras")
@SecurityRequirement(name = "bearer-key")
public class ComprasController {

    @Autowired
    private ComprasService service;

    @PostMapping
    public ResponseEntity<String> buy(@RequestBody ComprasDto dto) {
        service.buy(dto);
        return ResponseEntity.status(HttpStatus.OK).body("Compra realizada com sucesso");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Compra removida com sucesso");
    }

    @GetMapping
    public ResponseEntity<List<ComprasResponseDto>> show() {
        List<ComprasEntity> compras = service.show();
        List<ComprasResponseDto> dtos = compras.stream()
                .map(ComprasResponseDto::fromEntity)
                .toList();
        return ResponseEntity.ok(dtos);
    }


}
