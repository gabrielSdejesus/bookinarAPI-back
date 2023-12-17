package com.example.bookinar.controller;

import com.example.bookinar.docs.UserDoc;
import com.example.bookinar.dto.user.UserDTO;
import com.example.bookinar.dto.user.UserEditDTO;
import com.example.bookinar.dto.user.UserLoginDTO;
import com.example.bookinar.dto.user.UserRegisterDTO;
import com.example.bookinar.exception.ApiNotFoundException;
import com.example.bookinar.exception.BusinessException;
import com.example.bookinar.service.auth.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController implements UserDoc {


    private final UserService userService;

    @GetMapping("/actual")
    public ResponseEntity<UserDTO> getUserLogged() throws ApiNotFoundException {
        return new ResponseEntity<>(userService.getUserLogged(), HttpStatus.OK);
    }

    @PostMapping("/edit")
    public ResponseEntity<UserDTO> editUser(
            @RequestBody UserEditDTO dto) throws BusinessException, ApiNotFoundException {
        return new ResponseEntity<>(userService.editUser(dto), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> registerUser(
            @RequestBody UserRegisterDTO dto) throws BusinessException {
        return new ResponseEntity<>(userService.registerUser(dto), HttpStatus.OK);
    }

    @PostMapping("/auth")
    public ResponseEntity<String> auth(
            @RequestBody UserLoginDTO dto) throws BusinessException {
        return new ResponseEntity<>(userService.auth(dto), HttpStatus.OK);
    }
}
