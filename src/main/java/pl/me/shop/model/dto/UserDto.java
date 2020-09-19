package pl.me.shop.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//Object wykorzystywany do komunikacji z użytkownikiem
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
//Jesli jakies pole nie zostanie wypelnione, wartosc jest nullem
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private Long id;
    private String name;
    private String password;
    private String login;
    private String mail;
    private Long phoneNumber;
}
