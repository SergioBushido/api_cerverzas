package guru.springframework.spring6restmvc.model.dto.auth;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;

}
//esta es la respuesta a authenticationResponse que nos da el token y el de refresco (que se actualiza solo para
//no tener que logearte de nuevo)