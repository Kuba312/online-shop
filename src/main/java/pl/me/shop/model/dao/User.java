package pl.me.shop.model.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

//Klasa opisująca tabelke w bazie danych
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String password;
    private String login;
    private String mail;
    private Long phoneNumber;
    private String activatedCode;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;


}
