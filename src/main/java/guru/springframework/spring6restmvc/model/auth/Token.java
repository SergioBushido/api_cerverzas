package guru.springframework.spring6restmvc.model.auth;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.flywaydb.core.internal.parser.TokenType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token {
    @Id
    @GeneratedValue
    private long id;
    private String token;

    @Enumerated(EnumType.STRING)//como persiste el token en la bbdd
    private TokenType tokenType;

    public boolean expired;
    public boolean revoked;

    @ManyToOne(fetch = FetchType.LAZY)//varios token pueden estar sociados a un usuario y que solo se carga bajo demanda
    @JoinColumn(name = "user_id")
    public User user;

}