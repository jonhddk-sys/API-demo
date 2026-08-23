package Teste.de.dependencia.demo.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Produtos")
public class ProdutosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idproduto;

    @Column(name = "produto",length = 255,nullable = false)
    private String produto;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @OneToMany(mappedBy = "produto")
    private List<ComprasEntity> compras;






}
