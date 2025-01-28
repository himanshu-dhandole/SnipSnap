package himanshu.snipsnap.models;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column(nullable = false, unique = true)
    private String name ;
    @Column(nullable = false, unique = true)
    private String email ;
    @Column(nullable = false)
    private String password ;
    private String role = "ROLE_USER" ;
}
