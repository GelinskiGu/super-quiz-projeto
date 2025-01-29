package com.gelinski.superquiz.dto.security;

import lombok.Data;

import java.io.Serializable;

@Data
public class SignUpDTO implements Serializable {
    private String fullName;
    private String username;
    private String email;
    private String password;
}
