package com.example.bookinar.service.auth;

import com.example.bookinar.dto.user.UserDTO;
import com.example.bookinar.dto.user.UserEditDTO;
import com.example.bookinar.dto.user.UserLoginDTO;
import com.example.bookinar.dto.user.UserRegisterDTO;
import com.example.bookinar.entity.user.RoleEntity;
import com.example.bookinar.entity.user.UserEntity;
import com.example.bookinar.exception.ApiNotFoundException;
import com.example.bookinar.exception.BusinessException;
import com.example.bookinar.repository.jpa.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final TokenService tokenService;
    private final ObjectMapper objectMapper;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserService(UserRepository userRepository, RoleService roleService, TokenService tokenService,
                       ObjectMapper objectMapper, @Lazy AuthenticationManager authenticationManager, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.tokenService = tokenService;
        this.objectMapper = objectMapper;
        this.authenticationManager = authenticationManager;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public String auth(UserLoginDTO dto) throws BusinessException {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        dto.getLogin(),
                        dto.getPassword()
                );

        try {
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            return tokenService.generateToken(authentication);
        } catch (BadCredentialsException ex) {
            throw new BusinessException("Invalid credentials!");
        }
    }

    public Boolean registerUser(UserRegisterDTO dto) throws BusinessException {
        if(this.findByLogin(dto.getLogin()).isPresent()) {
            throw new BusinessException("User already exists!");
        }

        UserEntity userEntity = objectMapper.convertValue(dto, UserEntity.class);

        Set<RoleEntity> roles = new HashSet<>();
        RoleEntity role = roleService.takeRoleForName("ROLE_USER");
        role.getUsers().add(userEntity);
        roles.add(role);
        userEntity.setRoles(roles);

        String encodedPass = bCryptPasswordEncoder.encode(dto.getPassword());
        userEntity.setPassword(encodedPass);
        this.userRepository.save(userEntity);

        return Boolean.TRUE;
    }

    public UserDTO editUser(UserEditDTO dto) throws BusinessException, ApiNotFoundException {
        UserEntity user = this.findById(getLoggedUserId());
        if(!Objects.equals(dto.getLogin(), user.getLogin())) {
            throw new BusinessException("Invalid login!");
        }

        if(!bCryptPasswordEncoder.matches(dto.getOldPassword(), user.getPassword())) {
            throw new BusinessException("Invalid old password!");
        }

        user.setLogin(user.getLogin());
        user.setPassword(
                bCryptPasswordEncoder.encode(dto.getNewPassword())
        );

        userRepository.save(user);
        return objectMapper.convertValue(user, UserDTO.class);
    }

    public UserDTO getUserLogged() throws ApiNotFoundException {
        UserEntity user = this.findById(getLoggedUserId());
        return objectMapper.convertValue(user, UserDTO.class);
    }

    public Optional<UserEntity> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public UserEntity findById(Long numero) throws ApiNotFoundException {
        return userRepository.findById(numero).orElseThrow(() -> new ApiNotFoundException("User not found!"));
    }

    private Long getLoggedUserId() {
        return Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
    }
}
