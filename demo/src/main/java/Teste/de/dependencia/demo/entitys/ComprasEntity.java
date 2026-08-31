package Teste.de.dependencia.demo.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Compras")
public class ComprasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcompra;

    @Column(nullable = false)

    private BigDecimal qtdCompra;

    @Column(nullable = false)
    private BigDecimal valorTotal;


    @Column(nullable = false,updatable = false)
    private Instant dataCompra;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private UsuariosEntity usuario;

    @ManyToOne
    @JoinColumn(name = "idproduto")
    private ProdutosEntity produto;

    @PrePersist
    public void prePersist() {
        this.dataCompra = Instant.now();
        this.valorTotal = this.produto.getValor().multiply(this.qtdCompra);

    }


}
