package guru.springframework.spring6restmvc.model.dto.auth;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    private String username;
    private String password;
}
//cuando nos logeamos
//cuando postman/swagger hace una peticion se estructura con AutenticationRequest..que originará un response
//por eso esto es un DTO (objeto de transferencia de datos)