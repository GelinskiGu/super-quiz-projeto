package com.gelinski.superquiz.service;

import com.gelinski.superquiz.dto.UserDTO;
import com.gelinski.superquiz.exception.ResourceNotFoundException;
import com.gelinski.superquiz.mapper.UserMapper;
import com.gelinski.superquiz.model.User;
import com.gelinski.superquiz.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername: {}", username);
        var user = userRepository.findByUsername(username);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("User " + username + " not found");
        }
    }

    public UserDTO findUserById(Long id) {
        log.info("Finding user {}", id);
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            log.error("User {} not exist", id);
            throw new ResourceNotFoundException("User not found");
        }
        return UserMapper.INSTANCE.entityToDTO(user.get());
    }
}
