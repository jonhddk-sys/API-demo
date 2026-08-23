package Teste.de.dependencia.demo.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private UsuariosEntity usuario;

    @ManyToOne
    @JoinColumn(name = "idproduto")
    private ProdutosEntity produto;


}
