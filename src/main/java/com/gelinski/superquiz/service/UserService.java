package com.gelinski.superquiz.service;

import com.gelinski.superquiz.dto.UserDTO;
import com.gelinski.superquiz.exception.ResourceNotFoundException;
import com.gelinski.superquiz.logger.LoggerFacade;
import com.gelinski.superquiz.logger.LoggerFactory;
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
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private LoggerFacade log = LoggerFactory.getLogger(this.getClass());

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername: " + username);
        var user = userRepository.findByUsername(username);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("User " + username + " not found");
        }
    }

    public UserDTO findUserById(Long id) {
        log.info("Finding user " + id);
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            log.error("User " + id + " not exist");
            throw new ResourceNotFoundException("User not found");
        }
        return UserMapper.INSTANCE.entityToDTO(user.get());
    }
}
