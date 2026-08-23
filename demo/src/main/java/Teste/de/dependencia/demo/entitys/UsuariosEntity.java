package Teste.de.dependencia.demo.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Id;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Usuarios")
public class UsuariosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "email", nullable = false, length = 100,unique = true)
    private String email;

    @Column(name = "senha", nullable = false, length = 255,unique = false)
    private String senha;


    @OneToMany(mappedBy = "usuario")
    private List<ComprasEntity> usuarios;

}
