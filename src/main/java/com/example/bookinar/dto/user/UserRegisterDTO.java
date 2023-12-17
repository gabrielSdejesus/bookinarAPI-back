package com.example.bookinar.dto.user;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserRegisterDTO {

//    @NotNull(message = "Login is required!")
//    @Length(min = 8, max = 255, message = "Min 8 and Max 255 caracters!")
    private String login;

//    @NotNull(message = "Password is required!")
//    @Length(min = 10, max = 255, message = "Min 10 and Max 255 caracters!")
    private String password;
}
