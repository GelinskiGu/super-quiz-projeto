package com.gelinski.superquiz.dto.security;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountCredentialsDTO implements Serializable {
    private String username;
    private String password;
}
