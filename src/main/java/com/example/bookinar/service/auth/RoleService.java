package com.example.bookinar.service.auth;

import com.example.bookinar.entity.user.RoleEntity;
import com.example.bookinar.exception.BusinessException;
import com.example.bookinar.repository.jpa.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleEntity takeRoleForName(String name) throws BusinessException {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new BusinessException("Position not found!"));
    }
}
