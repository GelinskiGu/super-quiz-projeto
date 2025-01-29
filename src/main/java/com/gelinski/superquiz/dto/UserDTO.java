package com.gelinski.superquiz.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserDTO implements Serializable {
    private String username;
    private String password;
    private String fullName;
    private String email;
}
