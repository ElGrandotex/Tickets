package com.jv.Tickets.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Da getters, setters, etc
@Data
//Hace mas facil la construccion de un objeto de la clase:
//Usuario usuario = Usuario.builder()
//        .nombre("Juan")
//        .correo("juan@example.com")
//        .build();
@Builder
//Crea un constructor con todos los campos
@AllArgsConstructor
//Crea un cosntructor sin campos
@NoArgsConstructor
public class LoginRequest {
    String username;
    String password;
}