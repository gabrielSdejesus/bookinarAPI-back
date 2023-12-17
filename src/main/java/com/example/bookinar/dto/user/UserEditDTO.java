package com.example.bookinar.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserEditDTO {

    @NotNull(message = "Login is required!")
    @Length(min = 8, max = 255, message = "Min 8 and Max 255 caracters!")
    private String login;

    @NotNull(message = "Password is required!")
    @Length(min = 10, max = 255, message = "Min 10 and Max 255 caracters!")
    private String oldPassword;

    @NotNull(message = "Password is required!")
    @Length(min = 10, max = 255, message = "Min 10 and Max 255 caracters!")
    private String newPassword;
}
