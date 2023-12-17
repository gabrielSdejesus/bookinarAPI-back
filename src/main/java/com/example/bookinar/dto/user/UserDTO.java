package com.example.bookinar.dto.user;

import com.example.bookinar.entity.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long idUser;
    private String login;
    private Status status;
}
