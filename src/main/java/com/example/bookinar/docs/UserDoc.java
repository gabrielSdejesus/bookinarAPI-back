package com.example.bookinar.docs;

import com.example.bookinar.dto.user.UserDTO;
import com.example.bookinar.dto.user.UserEditDTO;
import com.example.bookinar.dto.user.UserLoginDTO;
import com.example.bookinar.dto.user.UserRegisterDTO;
import com.example.bookinar.exception.ApiNotFoundException;
import com.example.bookinar.exception.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "User", description = "Endpoints to User")
public interface UserDoc {

    @Operation(summary = "Get User Logged", description = "Get data user logged.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sucess!"),
                    @ApiResponse(responseCode = "400", description = "Bad Request!"),
                    @ApiResponse(responseCode = "500", description = "Error servlet!")
            }
    )
    ResponseEntity<UserDTO> getUserLogged() throws ApiNotFoundException;

    @Operation(summary = "Edit User Logged", description = "Edit data user logged.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sucess!"),
                    @ApiResponse(responseCode = "400", description = "Bad Request!"),
                    @ApiResponse(responseCode = "500", description = "Error servlet!")
            }
    )
    ResponseEntity<UserDTO> editUser(@RequestBody @Valid UserEditDTO dto) throws BusinessException, ApiNotFoundException;

    @Operation(summary = "Register User", description = "Register user.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sucess!"),
                    @ApiResponse(responseCode = "400", description = "Bad Request!"),
                    @ApiResponse(responseCode = "500", description = "Error servlet!")
            }
    )
    ResponseEntity<Boolean> registerUser(@RequestBody @Valid UserRegisterDTO dto) throws BusinessException;

    @Operation(summary = "Auth User", description = "Authentication user.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sucess!"),
                    @ApiResponse(responseCode = "400", description = "Bad Request!"),
                    @ApiResponse(responseCode = "500", description = "Error servlet!")
            }
    )
    ResponseEntity<String> auth(@RequestBody @Valid UserLoginDTO dto) throws BusinessException;
}
