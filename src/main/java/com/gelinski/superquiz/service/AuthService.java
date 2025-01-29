package com.gelinski.superquiz.service;

import com.gelinski.superquiz.dto.security.AccountCredentialsDTO;
import com.gelinski.superquiz.dto.security.SignUpDTO;
import com.gelinski.superquiz.dto.security.TokenDTO;
import com.gelinski.superquiz.exception.ResourceNotFoundException;
import com.gelinski.superquiz.mapper.UserMapper;
import com.gelinski.superquiz.model.Permission;
import com.gelinski.superquiz.model.User;
import com.gelinski.superquiz.repository.PermissionRepository;
import com.gelinski.superquiz.repository.UserRepository;
import com.gelinski.superquiz.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PermissionRepository permissionRepository;

    public TokenDTO login(AccountCredentialsDTO credentialsDTO) {
        User user = userRepository.findByUsername(credentialsDTO.getUsername());

        if (user == null) {
            throw new ResourceNotFoundException("User " + credentialsDTO.getUsername() + " not found");
        }

        if (!passwordEncoder.matches(credentialsDTO.getPassword().trim(), user.getPassword())) {
            throw new RuntimeException("Incorrect password for user " + credentialsDTO.getUsername());
        }

        return jwtTokenProvider.createAccessToken(user.getIdUser(), user.getFullName(), user.getUsername(), user.getRoles());
    }

    public void register(SignUpDTO signUpDTO) {
        User user = UserMapper.INSTANCE.signUpDTOToEntity(signUpDTO);
        user.setPassword(passwordEncoder.encode(signUpDTO.getPassword()).substring(8));

        Permission permission = permissionRepository.findByDescription("TEACHER");
        user.setPermissions(List.of(permission));

        userRepository.save(user);
    }
}
